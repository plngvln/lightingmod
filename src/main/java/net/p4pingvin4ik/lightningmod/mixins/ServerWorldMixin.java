package net.p4pingvin4ik.lightningmod.mixins;

import net.p4pingvin4ik.lightningmod.config.ModConfig;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @ModifyConstant(
            // ИЗМЕНЕНИЕ: Указываем правильный метод и его дескриптор
            method = "tickThunder(Lnet/minecraft/world/chunk/WorldChunk;)V",
            constant = @Constant(intValue = 100000)
    )
    private int modifyLightningChance(int original) {
        // Ваша логика остается абсолютно правильной
        return ModConfig.get().lightningChance;
    }
    @ModifyConstant(
            method = "tickThunder(Lnet/minecraft/world/chunk/WorldChunk;)V",
            // Мы целимся в константу типа double со значением 0.01
            constant = @Constant(doubleValue = 0.01)
    )
    private double modifySkeletonHorseChance(double original) {
        // 'original' здесь равен 0.01
        // Мы умножаем его на наш множитель из конфига
        return original * ModConfig.get().skeletonHorseChanceMultiplier;
    }
}