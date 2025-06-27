package com.example.lightningmod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

@Config(name = "lightningmod")
public class ModConfig implements ConfigData {

    // ИЗМЕНЕНИЕ 1: Используем int, так как Minecraft использует его для шанса.
    // ИЗМЕНЕНИЕ 2: Ставим ванильное значение по умолчанию. Так пользователю понятнее.
    public int lightningChance = 10000;

    public static void register() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
    @ConfigEntry.Gui.Tooltip
    public double skeletonHorseChanceMultiplier = 1.0;

    public static Screen getConfigScreen(Screen parent) {
        return ModConfigScreen.create(parent);
    }
}
