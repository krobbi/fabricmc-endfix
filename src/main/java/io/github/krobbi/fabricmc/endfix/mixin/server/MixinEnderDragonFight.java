package io.github.krobbi.fabricmc.endfix.mixin.server;

import io.github.krobbi.fabricmc.endfix.util.EndfixUtil;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Server-side mixin for the Ender dragon fight to generate End gateways at the
 * correct positions.
 * @author Chris Roberts (Krobbizoid)
 */
@Mixin(EnderDragonFight.class)
public class MixinEnderDragonFight {

	@Shadow @Final private List<Integer> gateways;

	@Shadow private void generateEndGateway(BlockPos pos){}

	/**
	 * Generates a new End gateway after an Ender dragon fight by from popping
	 * an index from a shuffled list. If the list is not empty, a new End
	 * gateway will be generated. The mod overwrites this method to generate
	 * End spikes in the correct locations by using the ring position utility.
	 * @author Chris Roberts (Krobbizoid)
	 */
	@Overwrite
	private void generateNewEndGateway(){
		if(!gateways.isEmpty()){
			int index = gateways.remove(gateways.size() - 1);
			BlockPos pos = EndfixUtil.getRingPos(20, 96, 75, index);
			generateEndGateway(pos);
		}
	}
}
