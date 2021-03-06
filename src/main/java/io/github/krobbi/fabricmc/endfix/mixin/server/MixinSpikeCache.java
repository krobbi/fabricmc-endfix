package io.github.krobbi.fabricmc.endfix.mixin.server;

import com.google.common.collect.Lists;
import io.github.krobbi.fabricmc.endfix.util.EndfixUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Server-side mixin for a cache used by the End spike feature to generate End
 * spikes at the correct positions. Depends on an access widener to access a
 * nested class.
 * @author Chris Roberts (Krobbizoid)
 */
@Mixin(EndSpikeFeature.SpikeCache.class)
public class MixinSpikeCache {

	/**
	 * Loads a list of End spike information from a given seed. The seed is
	 * generated by the End spike feature based on the world seed. The mod
	 * overwrites this method to generate End spikes in the correct locations by
	 * using the ring position utility.
	 * @author Chris Roberts (Krobbizoid)
	 * @param spikeSeed The seed used by the End spike feature to generate the
	 * End spike layout from.
	 * @return The loaded list of End spike information.
	 */
	@Overwrite
	public List<EndSpikeFeature.Spike> load(Long spikeSeed){
		/*
		 * The set of 10 End spikes generated on the main End island is
		 * identical between worlds, except for their positioning. This is
		 * because the properties of each End spike (radius, height, whether it
		 * is guarded) are based on a list of integers from 0 to 9 inclusive,
		 * which is shuffled based on a seed derived from the world seed. These
		 * integers can be thought of as an ID for each unique shape of End
		 * spike, although they are not stored anywhere, and are only used to
		 * generate the layout of End spikes.
		 */
		List<Integer> spikeIds = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		Collections.shuffle(spikeIds, new Random(spikeSeed));

		List<EndSpikeFeature.Spike> spikes = Lists.newArrayList();

		for(int index = 0; index < 10; ++index){
			int id = spikeIds.get(index);
			int radius = 2 + id / 3;
			int height = 76 + id * 3;
			boolean guarded = id == 1 || id == 2;
			BlockPos pos = EndfixUtil.getRingPos(10, 42, height, index);
			spikes.add(new EndSpikeFeature.Spike(pos.getX(), pos.getZ(), radius, height, guarded));
		}

		return spikes;
	}
}
