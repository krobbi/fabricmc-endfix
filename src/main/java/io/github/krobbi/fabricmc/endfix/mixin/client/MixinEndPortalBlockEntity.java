package io.github.krobbi.fabricmc.endfix.mixin.client;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.spongepowered.asm.mixin.Overwrite;

// Utils:
import net.minecraft.util.math.Direction;

// Classes:
import net.minecraft.block.entity.EndPortalBlockEntity;

@Mixin(EndPortalBlockEntity.class) @Environment(EnvType.CLIENT)
public class MixinEndPortalBlockEntity {

    /**
     * @reason Make End portals visible from all sides.
     * @author Krobbizoid
     */
    @Overwrite @Environment(EnvType.CLIENT)
    public boolean shouldDrawSide(Direction direction){
        return true;
    }
}
