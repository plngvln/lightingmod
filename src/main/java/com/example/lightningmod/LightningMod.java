package com.example.lightningmod;

import com.example.lightningmod.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class LightningMod implements ModInitializer {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger("lightningmod");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Lightning Mod...");
        ModConfig.init();
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
        LOGGER.info("Lightning Mod initialized.");
    }

    private void onServerTick(MinecraftServer server) {
        if (!ModConfig.get().modEnabled) {
            return; // If the mod is disabled, do nothing
        }

        for (ServerWorld world : server.getWorlds()) {
            if (world.getRegistryKey() == World.OVERWORLD) {
                if (world.isThundering()) {
                    for (ServerPlayerEntity player : world.getPlayers()) {
                        if (RANDOM.nextFloat() < ModConfig.get().lightningChance) {
                            spawnLightning(world, player.getBlockPos());
                        }
                    }
                }
            }
        }
    }

    private void spawnLightning(ServerWorld world, BlockPos playerPos) {
        int radius = ModConfig.get().lightningRadius;
        double x = playerPos.getX() + RANDOM.nextInt(radius * 2) - radius;
        double z = playerPos.getZ() + RANDOM.nextInt(radius * 2) - radius;
        BlockPos pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, new BlockPos((int) x, 0, (int) z));

        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        if (lightning != null) {
            lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(lightning);
        }
    }
}
