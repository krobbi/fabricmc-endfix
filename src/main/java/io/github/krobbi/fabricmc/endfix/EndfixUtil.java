package io.github.krobbi.fabricmc.endfix;

// Utils:
import net.minecraft.util.math.BlockPos;

public class EndfixUtil {

    private static final double TAU = 6.283185307179586D;

    public static BlockPos getRingPos(int count, int index, int radius, int height){
        double angle = TAU / (double) count * (double) index;
        int posX = (int) Math.round((double) radius * Math.cos(angle));
        int posZ = (int) Math.round((double) radius * Math.sin(angle));
        return new BlockPos(posX, height, posZ);
    }
}
