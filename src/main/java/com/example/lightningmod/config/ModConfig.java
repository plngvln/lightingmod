package com.example.lightningmod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

@Config(name = "lightningmod")
public class ModConfig implements ConfigData {
    public boolean modEnabled = true;
    public float lightningChance = 0.1f; // Chance of lightning strike per tick per player
    public int lightningRadius = 10; // Radius around the player for lightning strikes

    public static void register() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static Screen getConfigScreen(Screen parent) {
        return ModConfigScreen.createConfigScreen(parent);
    }
}
