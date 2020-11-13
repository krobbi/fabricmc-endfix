package io.github.krobbi.fabricmc.endfix;

import net.minecraft.util.math.BlockPos;

public class EndfixUtil {
    private static final double PI = 3.141592653589793D;

    public static BlockPos getOriginRingPos(int count, int id, int radius, int height){
        double offset = PI / (double) count;
        double angle  = 2.0D * (-PI + offset * (double) id);

        int posX = (int) Math.round((double) radius * Math.cos(angle));
        int posZ = (int) Math.round((double) radius * Math.sin(angle));

        return new BlockPos(posX, height, posZ);
    }
}
