package net.dragonloot.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.dragonloot.config.DragonLootConfig;

public class ConfigInit {
    public static DragonLootConfig CONFIG = new DragonLootConfig();

    public static void init() {
        AutoConfig.register(DragonLootConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DragonLootConfig.class).getConfig();
    }

}