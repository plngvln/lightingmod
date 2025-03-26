package com.example.lightningmod.mixins;

import com.example.lightningmod.config.ModConfig;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerWorld.class)
public abstract class WorldMixin {
    @ModifyConstant(
            method = "tickChunk(Lnet/minecraft/world/chunk/WorldChunk;I)V",
            constant = @Constant(intValue = 100000)
    )
    private int modifyLightningChance(int original) {
        return (int) ModConfig.get().lightningChance;
    }
}