package be.ephys.magicfeather;

import com.oroarmor.netherite_plus.block.entity.NetheriteBeaconBlockEntity;
import net.minecraft.tileentity.TileEntity;

public final class NetheritePlusIntegration {
  public static void init() {
    BeaconRangeCalculator.registerBeaconType(new NetheriteBeaconHandler());
  }

  static class NetheriteBeaconHandler implements BeaconTypeHandler {

    @Override
    public Class<? extends TileEntity> getTargetClass() {
      return NetheriteBeaconBlockEntity.class;
    }

    @Override
    public int getFlightRangeAroundBeacon(TileEntity te) {
      NetheriteBeaconBlockEntity beacon = (NetheriteBeaconBlockEntity) te;

      int rangeStep = BeaconRangeCalculator.rangeStep.get();
      int baseRange = BeaconRangeCalculator.baseRange.get();

      int level = beacon.getBeaconLevel();
      int radius = (level * rangeStep + baseRange);

      return radius;
    }
  }
}
