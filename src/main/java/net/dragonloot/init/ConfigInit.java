package net.dragonloot.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.dragonloot.config.DragonLootConfig;

public class ConfigInit {
  public static DragonLootConfig CONFIG = new DragonLootConfig();

  public static void init() {
    AutoConfig.register(DragonLootConfig.class, JanksonConfigSerializer::new);
    CONFIG = AutoConfig.getConfigHolder(DragonLootConfig.class).getConfig();
  }

}