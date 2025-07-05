package net.p4pingvin4ik.lightningmod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

@Config(name = "lightningmod")
public class ModConfig implements ConfigData {

    public int lightningChance = 10000;

    public static void register() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
    public double skeletonHorseChanceMultiplier = 1.0;
    public boolean lightningInAllBiomes = false;
    public static Screen getConfigScreen(Screen parent) {
        return ModConfigScreen.create(parent);
    }
}
