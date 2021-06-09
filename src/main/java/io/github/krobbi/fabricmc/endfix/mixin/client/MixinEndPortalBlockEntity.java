package io.github.krobbi.fabricmc.endfix.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Client-side mixin for End portal block entities to allow all sides of End
 * portals to be visible.
 * @author Chris Roberts (Krobbizoid)
 */
@Mixin(EndPortalBlockEntity.class) @Environment(EnvType.CLIENT)
public class MixinEndPortalBlockEntity {

	/**
	 * Gets whether a side of an End portal block entity may be drawn. The mod
	 * overwrites this method to allow all sides of End portals to be visible.
	 * @author Chris Roberts (Krobbizoid)
	 * @param direction The side of the End portal block entity to test
	 * (unused).
	 * @return Whether the side of the End portal block entity should be drawn
	 * (always `true`).
	 */
	@Overwrite @Environment(EnvType.CLIENT)
	public boolean shouldDrawSide(Direction direction){ return true; }
}
