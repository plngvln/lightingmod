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

        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(ModConfig.class).save());

        ConfigCategory main = builder.getOrCreateCategory(Text.translatable("category.lightningmod.main"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ModConfig config = ModConfig.get();

        main.addEntry(entryBuilder.startIntField(Text.translatable("option.lightningmod.frequency"), (int) config.lightningChance)
                .setDefaultValue(100000)
                .setTooltip(Text.translatable("tooltip.lightningmod.frequency"))
                .setMin(100) // Минимальное значение
                .setMax(1000000) // Максимальное значение
                .setSaveConsumer(newValue -> config.lightningChance = newValue)
                .build());

        return builder.build();
    }
}