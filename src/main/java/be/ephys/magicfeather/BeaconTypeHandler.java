package be.ephys.magicfeather;

import net.minecraft.tileentity.TileEntity;

public interface BeaconTypeHandler {
  Class<? extends TileEntity> getTargetClass();
  int getFlightRangeAroundBeacon(TileEntity beacon);
}
