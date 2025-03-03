package com.example.lightningmod.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {
    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.lightningmod.config"));

        builder.setSavingRunnable(() -> {
            // Serialize the config into the config file
            AutoConfig.getConfigHolder(ModConfig.class).save();
        });

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.lightningmod.general"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ModConfig config = ModConfig.get();

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.lightningmod.enabled"), config.modEnabled)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("tooltip.lightningmod.enabled"))
                .setSaveConsumer(newValue -> config.modEnabled = newValue)
                .build());

        general.addEntry(entryBuilder.startFloatField(Text.translatable("option.lightningmod.lightningChance"), config.lightningChance)
                .setDefaultValue(0.1f)
                .setTooltip(Text.translatable("tooltip.lightningmod.lightningChance"))
                .setSaveConsumer(newValue -> config.lightningChance = newValue)
                .build());

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.lightningmod.lightningRadius"), config.lightningRadius, 1, 256)
                .setDefaultValue(256)
                .setTooltip(Text.translatable("tooltip.lightningmod.lightningRadius"))
                .setSaveConsumer(newValue -> config.lightningRadius = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.lightningmod.lightningRodEnabled"), config.lightningRodEnabled)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("tooltip.lightningmod.lightningRodEnabled"))
                .setSaveConsumer(newValue -> config.lightningRodEnabled = newValue)
                .build());

        return builder.build();
    }
}
