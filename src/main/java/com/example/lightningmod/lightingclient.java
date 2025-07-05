package com.example.lightningmod;

import com.example.lightningmod.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;

public class lightingclient implements ClientModInitializer  {
    @Override
    public void onInitializeClient() {
        ModConfig.register();
    }
}
