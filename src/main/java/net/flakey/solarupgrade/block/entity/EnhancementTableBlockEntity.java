package net.flakey.solarupgrade.block.entity;

import net.flakey.solarupgrade.enchantment.ModEnchantments;
import net.flakey.solarupgrade.item.ModItems;
import net.flakey.solarupgrade.networking.ModMessages;
import net.flakey.solarupgrade.networking.packet.EnergySyncS2CPacket;
import net.flakey.solarupgrade.screen.EnhancementTableMenu;
import net.flakey.solarupgrade.util.CustomEnchantedBook;
import net.flakey.solarupgrade.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class EnhancementTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(1000000, 1280) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));

        }
    };
    private static final int ENERGY_REQ = 128;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 312;

    public EnhancementTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENHANCEMENT_TABLE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EnhancementTableBlockEntity.this.progress;
                    case 1 -> EnhancementTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EnhancementTableBlockEntity.this.progress = value;
                    case 1 -> EnhancementTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Enhancement Table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new EnhancementTableMenu(id, inventory, this, this.data);
    }

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("enhancement_table.progress", this.progress);
        nbt.putInt("enhancement_table.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("enhancement_table.progress");
        ENERGY_STORAGE.setEnergy(nbt.getInt("enhancement_table.energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }




    public static void tick(Level level, BlockPos pos, BlockState state, EnhancementTableBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity) && hasEnoughEnergy(pEntity)) {
            pEntity.progress += 4;
            extractEnergy(pEntity);
            setChanged(level, pos, state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
         } else {
            if(pEntity.progress > 0) {
                if (hasCoreInFirstSlot(pEntity) && (hasBookInSecondSlot(pEntity))) {
                    pEntity.progress--;
                }
                else {
                    pEntity.resetProgress();
                }
            }
            setChanged(level, pos, state);
        }

    }

    private static void extractEnergy(EnhancementTableBlockEntity pEntity) {
        pEntity.ENERGY_STORAGE.extractEnergy(ENERGY_REQ, false);
    }

    private static boolean hasEnoughEnergy(EnhancementTableBlockEntity pEntity) {
        return pEntity.ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQ;
    }

    private static boolean hasCoreInFirstSlot(EnhancementTableBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(0).getItem() == ModItems.CHARGED_ENHANCEMENT_CORE.get();

    }
    private static boolean hasBookInSecondSlot(EnhancementTableBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(1).getItem() == Items.BOOK;
    }


    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(EnhancementTableBlockEntity pEntity) {


        List<Enchantment> enchantmentList = Arrays.asList(
                //ModEnchantments.PARRY.get(),
                //ModEnchantments.HOLLOWED_OUT.get(),
                //ModEnchantments.LIFE_LEECH.get(),
                ModEnchantments.BLEED.get(),
                ModEnchantments.LIGHTNING_STRIKE.get(),
                ModEnchantments.RAGE.get(),
                ModEnchantments.SELF_HEALING.get(),
                ModEnchantments.SWIFT_STEP.get()

        );

        if (hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(0, 1, false);
            pEntity.itemHandler.extractItem(1, 1, false);
            ItemStack randomEnchantedBook = CustomEnchantedBook.createRandomEnchantedBook(enchantmentList, 1);
            pEntity.itemHandler.setStackInSlot(2, randomEnchantedBook);
            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(EnhancementTableBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasCoreInFirstSlot = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.CHARGED_ENHANCEMENT_CORE.get();

        boolean hasBookInSecondSlot = entity.itemHandler.getStackInSlot(1).getItem() == Items.BOOK;

        return hasCoreInFirstSlot && hasBookInSecondSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(Items.ENCHANTED_BOOK, 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
