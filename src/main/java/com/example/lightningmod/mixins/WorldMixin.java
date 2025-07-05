package com.example.lightningmod.mixins;

import com.example.lightningmod.config.ModConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {

    // Используем @Inject, чтобы вмешаться в начало метода
    @Inject(method = "hasRain", at = @At("HEAD"), cancellable = true)
    private void onHasRain(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Проверяем, включена ли наша опция в конфиге
        if (ModConfig.get().lightningInAllBiomes) {
            // Если да, то принудительно устанавливаем возвращаемое значение на 'true'
            // и отменяем выполнение остальной части оригинального метода.
            cir.setReturnValue(true);
        }
        // Если опция выключена, этот код ничего не делает, и метод hasRain работает как обычно.
    }
}