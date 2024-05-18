package com.example.lightningmod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "lightningmod")
public class ModConfig implements ConfigData {
    private static final Logger LOGGER = LoggerFactory.getLogger("lightningmod");
    public boolean modEnabled = true;
    public float lightningChance = 0.1f; // Chance of lightning strike per tick per player
    public int lightningRadius = 256; // Radius around the player for lightning strikes

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
