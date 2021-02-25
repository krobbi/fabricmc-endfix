package io.github.krobbi.fabricmc.endfix.mixin.server;

// Mixin annotations:
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

// Utils:
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.util.math.BlockPos;
import com.google.common.collect.Lists;
import io.github.krobbi.fabricmc.endfix.EndfixUtil;

// Classes:
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.StructureWorldAccess;

@Mixin(EndSpikeFeature.class)
class MixinEndSpikeFeature {

    private static final int SPIKE_COUNT = 10;
    private static final int SPIKE_HEIGHT_BASE = 76;
    private static final int SPIKE_HEIGHT_STEP = 3;
    private static final int SPIKE_RADIUS_BASE = 2;
    private static final int SPIKE_RADIUS_STEP = 3;
    private static final int SPIKE_RING_RADIUS = 42;

    private static final List<EndSpikeFeature.Spike> SPIKES = Lists.newArrayList();
    private static long cachedSeed = 65536L; // Inaccessible spike seed, forces cache to occur.

    private static void cacheSpikes(Long seed){
        SPIKES.clear();

        List<Integer> spikeIDs = (List<Integer>) IntStream.range(0, SPIKE_COUNT).boxed().collect(Collectors.toList());
        Collections.shuffle(spikeIDs, new Random(seed));

        for(int i = 0; i < SPIKE_COUNT; i++){
            int id = (int) spikeIDs.get(i);
            int radius = SPIKE_RADIUS_BASE + id / SPIKE_RADIUS_STEP;
            int height = SPIKE_HEIGHT_BASE + id * SPIKE_HEIGHT_STEP;
            boolean guarded = id == 1 || id == 2;
            BlockPos pos = EndfixUtil.getOriginRingPos(SPIKE_COUNT, i, SPIKE_RING_RADIUS, height);
            SPIKES.add(new EndSpikeFeature.Spike(pos.getX(), pos.getZ(), radius, pos.getY(), guarded));
        }

        cachedSeed = seed;
    }

    /**
     * @reason SpikeCache in EndSpikeFeature is private and needs to be rewritten to be modified.
     * @author Krobbizoid.
     */
    @Overwrite
    public static List<EndSpikeFeature.Spike> getSpikes(StructureWorldAccess world){
        long spikeSeed = new Random(world.getSeed()).nextLong() & 65535L;

        if(spikeSeed != cachedSeed){
            cacheSpikes(spikeSeed);
        }

        return SPIKES;
    }
}
