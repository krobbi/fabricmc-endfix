package io.github.krobbi.fabricmc.endfix.util;

import net.minecraft.util.math.BlockPos;

/**
 * Class storing utility functions for the Endfix Minecraft Fabric mod.
 * @author Chris Roberts (Krobbizoid)
 */
public class EndfixUtil {

	private static final double TAU = 6.283185307179586D; // 2 pi.

	/**
	 * Gets the block position for a structure in a ring of evenly spaced
	 * structures centered around the origin of a dimension.
	 * @author Chris Roberts (Krobbizoid)
	 * @param count The number of structures in the ring.
	 * @param radius The radius of the ring in blocks.
	 * @param y The Y coordinate to give the block position.
	 * @param index The index number of the structure in the ring.
	 * @return The block position in the ring.
	 */
	public static BlockPos getRingPos(int count, int radius, int y, int index){
		double angle = (TAU / (double) count) * (double) index;
		int x = (int) Math.round((double) radius * Math.cos(angle));
		int z = (int) Math.round((double) radius * Math.sin(angle));
		return new BlockPos(x, y, z);
	}
}
