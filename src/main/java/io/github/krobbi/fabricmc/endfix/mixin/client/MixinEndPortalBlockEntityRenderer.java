package io.github.krobbi.fabricmc.endfix.mixin.client;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Overwrite;

// Utils:
import java.util.Random;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Direction;

// Classes:
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.client.render.VertexConsumer;

@Mixin(EndPortalBlockEntityRenderer.class) @Environment(EnvType.CLIENT)
public class MixinEndPortalBlockEntityRenderer<T extends EndPortalBlockEntity> {

    @Shadow @Final
    private static Random RANDOM;

    private static final float BOTTOM_Y = 0.0625F;

    /**
     * @reason Render the bottom of End portals 1 pixel up and render the sides of End portals at the correct height.
     * @author Krobbizoid
     */
    @Overwrite @Environment(EnvType.CLIENT) // Builds an end portal mesh:
    private void method_23084(T endPortalBlockEntity, float f, float g, Matrix4f matrix4f, VertexConsumer vertexConsumer){
        float red = (RANDOM.nextFloat() * 0.5F + 0.1F) * g;
        float green = (RANDOM.nextFloat() * 0.5F + 0.4F) * g;
        float blue = (RANDOM.nextFloat() * 0.5F + 0.5F) * g;
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, BOTTOM_Y, f,        1.0F, 1.0F, 1.0F, 1.0F, red, green, blue, Direction.SOUTH);
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, f,        BOTTOM_Y, 0.0F, 0.0F, 0.0F, 0.0F, red, green, blue, Direction.NORTH);
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 1.0F, 1.0F, f,        BOTTOM_Y, 0.0F, 1.0F, 1.0F, 0.0F, red, green, blue, Direction.EAST);
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 0.0F, 0.0F, BOTTOM_Y, f,        0.0F, 1.0F, 1.0F, 0.0F, red, green, blue, Direction.WEST);
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, BOTTOM_Y, BOTTOM_Y, 0.0F, 0.0F, 1.0F, 1.0F, red, green, blue, Direction.DOWN);
        this.method_23085(endPortalBlockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, f,        f,        1.0F, 1.0F, 0.0F, 0.0F, red, green, blue, Direction.UP);
    }

    /**
     * @reason Optimize by removing unnecessary shouldDrawSide check.
     * @author Krobbizoid
     */
    @Overwrite @Environment(EnvType.CLIENT) // Builds an End portal face:
    private void method_23085(T endPortalBlockEntity, Matrix4f matrix4f, VertexConsumer vertexConsumer, float f, float g, float h, float i, float j, float k, float l, float m, float n, float o, float p, Direction direction){
        vertexConsumer.vertex(matrix4f, f, h, j).color(n, o, p, 1.0F).next();
        vertexConsumer.vertex(matrix4f, g, h, k).color(n, o, p, 1.0F).next();
        vertexConsumer.vertex(matrix4f, g, i, l).color(n, o, p, 1.0F).next();
        vertexConsumer.vertex(matrix4f, f, i, m).color(n, o, p, 1.0F).next();
    }
}
