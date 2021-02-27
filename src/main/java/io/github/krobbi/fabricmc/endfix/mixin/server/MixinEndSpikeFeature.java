package io.github.krobbi.fabricmc.endfix.mixin.server;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;

// Utils:
import java.util.List;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import com.google.common.collect.Lists;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import io.github.krobbi.fabricmc.endfix.EndfixUtil;

// Classes:
import net.minecraft.world.gen.feature.EndSpikeFeature;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheLoader;

@Mixin(EndSpikeFeature.class)
public class MixinEndSpikeFeature {

    @Shadow @Final
    private static final LoadingCache<Long, List<EndSpikeFeature.Spike>> CACHE;

    static {
        CACHE = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build(new SpikeCache());
    }

    /**
     * @reason Make End spike generation symmetrical.
     * @author Krobbizoid
     */
    static class SpikeCache extends CacheLoader<Long, List<EndSpikeFeature.Spike>> {

        private SpikeCache(){}

        private static final int SPIKE_COUNT = 10;
        private static final int SPIKE_RADIUS_BASE = 2;
        private static final int SPIKE_RADIUS_STEP = 3;
        private static final int SPIKE_HEIGHT_BASE = 76;
        private static final int SPIKE_HEIGHT_STEP = 3;
        private static final int SPIKE_RING_RADIUS = 42;

        public List<EndSpikeFeature.Spike> load(Long spikeSeed){
            List<EndSpikeFeature.Spike> spikes = Lists.newArrayList();
            List<Integer> spikeIDs = IntStream.range(0, SPIKE_COUNT).boxed().collect(Collectors.toList());
            Collections.shuffle(spikeIDs, new Random(spikeSeed));

            for(int index = 0; index < SPIKE_COUNT; index++){
                int id = (int) spikeIDs.get(index);
                int radius = SPIKE_RADIUS_BASE + id / SPIKE_RADIUS_STEP;
                int height = SPIKE_HEIGHT_BASE + id * SPIKE_HEIGHT_STEP;
                boolean guarded = id == 1 || id == 2;
                BlockPos pos = EndfixUtil.getRingPos(SPIKE_COUNT, index, SPIKE_RING_RADIUS, height);
                spikes.add(new EndSpikeFeature.Spike(pos.getX(), pos.getZ(), radius, height, guarded));
            }

            return spikes;
        }
    }
}
