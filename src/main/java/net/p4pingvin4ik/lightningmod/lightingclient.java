package net.p4pingvin4ik.lightningmod;

import net.p4pingvin4ik.lightningmod.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;

public class lightingclient implements ClientModInitializer  {
    @Override
    public void onInitializeClient() {
        ModConfig.register();
    }
}
