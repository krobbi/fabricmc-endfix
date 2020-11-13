package io.github.krobbi.fabricmc.endfix.mixin.client;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

// Utils:
import net.minecraft.util.math.Direction;

// Classes:
import net.minecraft.block.entity.EndPortalBlockEntity;

@Mixin(EndPortalBlockEntity.class)
public class MixinEndPortalBlockEntity {

    /**
     * @reason Fix end portals only being visible from above.
     * @author Krobbizoid
     */
    @Overwrite @Environment(EnvType.CLIENT)
    public boolean shouldDrawSide(Direction direction){
        return direction == Direction.UP || direction == Direction.DOWN;
    }
}
