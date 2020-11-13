package io.github.krobbi.fabricmc.endfix.mixin.server;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Overwrite;

// Utils:
import java.util.List;
import io.github.krobbi.fabricmc.endfix.EndfixUtil;

// Classes:
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.util.math.BlockPos;

@Mixin(EnderDragonFight.class)
public class MixinEnderDragonFight {

    @Shadow @Final
    private List<Integer> gateways;

    @Shadow
    private void generateEndGateway(BlockPos blockPos){}

    private static final int GATEWAY_COUNT  = 20;
    private static final int GATEWAY_RADIUS = 96;
    private static final int GATEWAY_HEIGHT = 75;

    /**
     * @reason Fix the end gateway generation.
     * @author Krobbizoid
     */
    @Overwrite
    private void generateNewEndGateway(){
        if(!this.gateways.isEmpty()){
            int gatewayID = (int) this.gateways.remove(this.gateways.size() - 1);
            generateEndGateway(EndfixUtil.getOriginRingPos(GATEWAY_COUNT, gatewayID, GATEWAY_RADIUS, GATEWAY_HEIGHT));
        }
    }
}
