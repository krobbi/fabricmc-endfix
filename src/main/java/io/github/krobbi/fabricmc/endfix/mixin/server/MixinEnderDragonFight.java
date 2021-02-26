package io.github.krobbi.fabricmc.endfix.mixin.server;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Overwrite;

// Utils:
import java.util.List;
import net.minecraft.util.math.BlockPos;
import io.github.krobbi.fabricmc.endfix.EndfixUtil;

// Classes:
import net.minecraft.entity.boss.dragon.EnderDragonFight;

@Mixin(EnderDragonFight.class)
public class MixinEnderDragonFight {

    @Shadow @Final
    private List<Integer> gateways;

    @Shadow
    private void generateEndGateway(BlockPos blockPos){}

    /**
     * @reason Make End gateway generation symmetrical.
     * @author Krobbizoid
     */
    @Overwrite
    private void generateNewEndGateway(){
        final int GATEWAY_COUNT = 20;
        final int GATEWAY_RING_RADIUS = 96;
        final int GATEWAY_RING_HEIGHT = 75;

        if(!this.gateways.isEmpty()){
            int index = this.gateways.remove(this.gateways.size() - 1);
            generateEndGateway(EndfixUtil.getRingPos(GATEWAY_COUNT, index, GATEWAY_RING_RADIUS, GATEWAY_RING_HEIGHT));
        }
    }
}
