package net.flakey.solarupgrade.block.entity;

import net.flakey.solarupgrade.networking.ModMessages;
import net.flakey.solarupgrade.networking.packet.EnergySyncS2CPacket;
import net.flakey.solarupgrade.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class SolarPanelBlockEntity extends BlockEntity{
    int maxTransfer = 256;
    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(4000, maxTransfer) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));

        }
    };


    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SOLAR_PANEL.get(), pos, state);
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }



    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("solar_panel.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    public boolean canTransferTo(LevelAccessor world, BlockPos pos, Direction facing) {
        BlockEntity te = world.getBlockEntity(pos.below());
        return (te != null && te.getCapability(ForgeCapabilities.ENERGY,facing.getOpposite()).isPresent());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ENERGY_STORAGE.setEnergy(nbt.getInt("solar_panel.energy"));
    }

    private void sendEnergy() {
        AtomicInteger capacity = new AtomicInteger(ENERGY_STORAGE.getEnergyStored());

        for(int i = 0; (i < Direction.values().length) && (capacity.get() > 0); i++) {
            Direction facing = Direction.values()[i];
            if (facing.equals(Direction.UP))
                continue;

            BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(facing));
            if (blockEntity == null)
                continue;
            blockEntity.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite())
                    .ifPresent(handler -> {
                        if(handler.canReceive()) {
                            int received = handler.receiveEnergy(Math.min(capacity.get(), maxTransfer), false);
                            capacity.addAndGet(-received);
                            ENERGY_STORAGE.consumePower(received);
                            setChanged();
                        }
                    });
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SolarPanelBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(level.isDay() && level.canSeeSky(pos.above())){
            pEntity.ENERGY_STORAGE.receiveEnergy(1, false);
        }

        pEntity.sendEnergy();
    }
}
