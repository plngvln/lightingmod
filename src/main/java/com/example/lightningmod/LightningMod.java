package com.example.lightningmod;

import com.example.lightningmod.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LightningMod implements ModInitializer {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger("lightningmod");

    private final List<PendingExtinguishTask> pendingTasks = new ArrayList<>();
    private static final int LIGHTNING_ROD_RADIUS = 64;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Lightning Mod...");
        ModConfig.register();
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
        LOGGER.info("Lightning Mod initialized.");
    }

    private void onServerTick(MinecraftServer server) {
        if (!ModConfig.get().modEnabled) {
            return; // Если мод отключен, ничего не делаем
        }
        for (ServerWorld world : server.getWorlds()) {
            if (world.getRegistryKey() == World.OVERWORLD && world.isThundering()) {
                List<ServerPlayerEntity> players = world.getPlayers();
                if (!players.isEmpty()) {
                    // Выбираем случайного игрока
                    ServerPlayerEntity randomPlayer = players.get(RANDOM.nextInt(players.size()));

                    if (RANDOM.nextFloat() < ModConfig.get().lightningChance) {
                        BlockPos lightningPos = getRandomLightningPosition(world, randomPlayer.getBlockPos());
                        BlockPos targetPos = lightningPos;
                        boolean struckLightningRod = false;

                        if (ModConfig.get().lightningRodEnabled) {
                            targetPos = findLightningRod(world, lightningPos);
                            struckLightningRod = targetPos != null;
                            if (!struckLightningRod) {
                                targetPos = lightningPos;
                            }
                        }

                        spawnLightning(world, targetPos, struckLightningRod);
                    }
                }
            }
        }

        // Обрабатываем отложенные задачи
        List<PendingExtinguishTask> tasksToProcess = new ArrayList<>(pendingTasks);
        pendingTasks.clear();

        for (PendingExtinguishTask task : tasksToProcess) {
            if (task.delay == 0) {
                extinguishFireAroundLightningRod(task.world, task.rodPos);
            } else {
                task.delay--;
                pendingTasks.add(task);
            }
        }
    }

    private BlockPos findLightningRod(ServerWorld world, BlockPos strikePos) {
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int x = -LIGHTNING_ROD_RADIUS; x <= LIGHTNING_ROD_RADIUS; x++) {
            for (int y = -LIGHTNING_ROD_RADIUS; y <= LIGHTNING_ROD_RADIUS; y++) {
                for (int z = -LIGHTNING_ROD_RADIUS; z <= LIGHTNING_ROD_RADIUS; z++) {
                    pos.set(strikePos.getX() + x, strikePos.getY() + y, strikePos.getZ() + z);
                    if (world.getBlockState(pos).getBlock() == Blocks.LIGHTNING_ROD) {
                        return pos.toImmutable();
                    }
                }
            }
        }
        return null;
    }

    // Исправленный метод для правильной высоты удара молнии
    private BlockPos getRandomLightningPosition(ServerWorld world, BlockPos playerPos) {
        int radius = ModConfig.get().lightningRadius;
        double x = playerPos.getX() + RANDOM.nextInt(radius * 2) - radius;
        double z = playerPos.getZ() + RANDOM.nextInt(radius * 2) - radius;

        // Найдем верхнюю блокирующую позицию для этой точки (например, поверхность)
        BlockPos topPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, new BlockPos((int) x, 0, (int) z));

        // Возвращаем позицию с правильной высотой Y
        return new BlockPos(topPos.getX(), topPos.getY(), topPos.getZ());
    }

    private void spawnLightning(ServerWorld world, BlockPos pos, boolean struckLightningRod) {
        BlockPos topPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos);

        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world, null, topPos, SpawnReason.TRIGGERED, false, false);
        if (lightning != null) {
            lightning.refreshPositionAfterTeleport(topPos.getX(), topPos.getY(), topPos.getZ());
            world.spawnEntity(lightning);

            if (struckLightningRod) {
                pendingTasks.add(new PendingExtinguishTask(world, pos, 1)); // Schedule extinguish after one tick
            }
        }
    }


    private void extinguishFireAroundLightningRod(ServerWorld world, BlockPos rodPos) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    pos.set(rodPos.getX() + x, rodPos.getY() + y, rodPos.getZ() + z);
                    if (world.getBlockState(pos).getBlock() == Blocks.FIRE) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
    }

    private static class PendingExtinguishTask {
        ServerWorld world;
        BlockPos rodPos;
        int delay;

        public PendingExtinguishTask(ServerWorld world, BlockPos rodPos, int delay) {
            this.world = world;
            this.rodPos = rodPos;
            this.delay = delay;
        }
    }
}
