package be.ephys.magicfeather;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Config;

import java.util.List;

public final class BeaconRangeCalculator {

    public enum BeaconVerticalRangeType {
        Java(0, 256),
        FullHeight(0, 0);

        private final int downRangeExtension;
        private final int upRangeExtension;

        BeaconVerticalRangeType(int downRangeExtension, int upRangeExtension) {

            this.downRangeExtension = downRangeExtension;
            this.upRangeExtension = upRangeExtension;
        }
    }

    @Config(category = "range_computation", description = "How the beacon range is calculated vertically. Java = Vanilla Java Behavior. Bedrock = Vanilla Bedrock behavior. FullHeight = expand vertical range to maximum")
    public static BeaconVerticalRangeType verticalRangeType = BeaconVerticalRangeType.FullHeight;

    @Config(category = "range_computation", description = "What is the beacon base range?")
    public static int baseRange = 10;

    @Config(category = "range_computation", description = "How many blocks are added to the range per level?")
    public static int rangeStep = 10;

    public static boolean isInBeaconRange(Entity entity) {
        World world = entity.getEntityWorld();
        Vec3d entityPos = entity.getPositionVec();

        List<TileEntity> tileEntities = world.loadedTileEntityList;
        for (TileEntity t : tileEntities) {
            if (!(t instanceof BeaconTileEntity)) {
                continue;
            }

            BeaconTileEntity beacon = (BeaconTileEntity) t;

            int level = beacon.getLevels();
            int radius = (level * rangeStep + baseRange);

            BlockPos pos = beacon.getPos();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            if (entityPos.x < (x - radius) || entityPos.x > (x + radius)) {
                continue;
            }

            if (entityPos.z < (z - radius) || entityPos.z > (z + radius)) {
                continue;
            }

            if (verticalRangeType != BeaconVerticalRangeType.FullHeight) {
                if (entityPos.y < (y - radius - verticalRangeType.downRangeExtension)
                    || entityPos.y > (y + radius + verticalRangeType.upRangeExtension)) {
                    continue;
                }
            }

            return true;
        }

        return false;
    }
}
