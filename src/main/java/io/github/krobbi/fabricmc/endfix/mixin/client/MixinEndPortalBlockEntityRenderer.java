package io.github.krobbi.fabricmc.endfix.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Client-side mixin for the End portal block entity renderer to render the
 * sides of End portals at the correct height.
 * @author Chris Roberts (Krobbizoid)
 * @param <T> The type of End portal block entity to render.
 */
@Mixin(EndPortalBlockEntityRenderer.class) @Environment(EnvType.CLIENT)
public class MixinEndPortalBlockEntityRenderer<T extends EndPortalBlockEntity> {

	@Shadow protected float method_35793(){ return 0.375F; }
	@Shadow protected float getTopYOffset(){ return 0.75F; }
	@Shadow private void renderSide(
			T entity, Matrix4f model, VertexConsumer verticies,
			float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4,
			Direction direction
	){}

	/**
	 * Renders all faces of an End portal block entity. The mod overwrites this
	 * method to draw the side faces of End portal block entities at the correct
	 * height. This method is used for rendering both End portals and End
	 * gateways.
	 *
	 * By default, the side faces of End portal block entities are rendered with
	 * constant Y positions between 0.0F and 1.0F. This is sufficient for End
	 * gateways, which are the only End portal block entity with visible side
	 * faces. With End portals, this causes the side faces to extend above and
	 * below the top and bottom faces.
	 *
	 * Since the mod makes the side faces of End portals visible, the side faces
	 * of End portal block entities are rendered between the heights of the top
	 * and bottom faces, correctly forming a cuboid for any given top and bottom
	 * face height.
	 * @author Chris Roberts (Krobbizoid)
	 * @param entity The End portal block entity to render.
	 * @param matrix4f The model matrix to render the End portal block entity
	 * with.
	 * @param vertexConsumer The vertex consumer to render the End portal block
	 * entity with
	 */
	@Overwrite @Environment(EnvType.CLIENT)
	private void renderSides(T entity, Matrix4f matrix4f, VertexConsumer vertexConsumer){
		float bottomYOffset = method_35793(); // getBottomYOffset();
		float topYOffset = getTopYOffset();
		renderSide(
				entity, matrix4f, vertexConsumer, 0.0F, 1.0F, bottomYOffset, topYOffset,
				1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH
		);
		renderSide(
				entity, matrix4f, vertexConsumer, 0.0F, 1.0F, topYOffset, bottomYOffset,
				0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH
		);
		renderSide(
				entity, matrix4f, vertexConsumer, 1.0F, 1.0F, topYOffset, bottomYOffset,
				0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST
		);
		renderSide(
				entity, matrix4f, vertexConsumer, 0.0F, 0.0F, bottomYOffset, topYOffset,
				0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST
		);
		renderSide(
				entity, matrix4f, vertexConsumer, 0.0F, 1.0F, bottomYOffset, bottomYOffset,
				0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN
		);
		renderSide(
				entity, matrix4f, vertexConsumer, 0.0F, 1.0F, topYOffset, topYOffset,
				1.0F, 1.0F, 0.0F, 0.0F, Direction.UP
		);
	}
}
