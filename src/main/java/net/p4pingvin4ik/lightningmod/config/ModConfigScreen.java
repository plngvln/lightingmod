package net.p4pingvin4ik.lightningmod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {

    // Этот метод теперь можно вызывать через AutoConfig, но оставим его для наглядности
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.lightningmod.config"));

        // Указываем, что при сохранении нужно вызвать сохранение конфига AutoConfig
        builder.setSavingRunnable(() -> {
            AutoConfig.getConfigHolder(ModConfig.class).save();
        });

        ConfigCategory main = builder.getOrCreateCategory(Text.translatable("category.lightningmod.main"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // Получаем наш объект конфига
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        // Заменяем поле ввода на слайдер
        main.addEntry(entryBuilder.startIntSlider(
                        Text.translatable("option.lightningmod.frequency"), // Название опции
                        config.lightningChance,                             // Текущее значение
                        1,                                                // Минимальное значение
                        100000                                             // Максимальное значение
                )
                .setDefaultValue(100000) // Значение по умолчанию
                .setTooltip(Text.translatable("tooltip.lightningmod.frequency")) // Подсказка
                .setSaveConsumer(newValue -> config.lightningChance = newValue) // Что делать при сохранении
                .build()); // Собираем опцию


        main.addEntry(entryBuilder.startLongSlider(
                        Text.translatable("option.lightningmod.skeleton_horse_chance"),
                        (long) (config.skeletonHorseChanceMultiplier * 100L),
                        0L,  //  Минимальное значение 0
                        100L //  Максимальное значение 100
                )
                .setDefaultValue(100L) // Значение по умолчанию тоже 100 (соответствует 1.0)
                .setTooltip(Text.translatable("tooltip.lightningmod.skeleton_horse_chance"))
                // При сохранении преобразуем значение слайдера (0-100) обратно в double (0.0-1.0)
                .setSaveConsumer(newValue -> config.skeletonHorseChanceMultiplier = newValue / 100.0)
                .setTextGetter(value -> Text.of(value + " %"))
                .build());
        main.addEntry(entryBuilder.startBooleanToggle(
                        Text.translatable("option.lightningmod.all_biomes"), // Название опции
                        config.lightningInAllBiomes // Текущее значение
                )
                .setDefaultValue(false) // Значение по умолчанию
                .setTooltip(Text.translatable("tooltip.lightningmod.all_biomes")) // Подсказка
                .setSaveConsumer(newValue -> config.lightningInAllBiomes = newValue) // Сохранение
                .build());

        return builder.build();
    }
}