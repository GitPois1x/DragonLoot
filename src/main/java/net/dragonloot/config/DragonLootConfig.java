package net.dragonloot.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "dragonloot")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class DragonLootConfig implements ConfigData {

  public int scale_minimum_drop_amount = 3;
  public int additional_scales_per_player = 2;
  @Comment("0.8F = 80%")
  public float additional_scale_drop_chance = 0.8F;
  @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
  public int dragon_armor_protection_helmet = 7;
  @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
  public int dragon_armor_protection_chest = 10;
  @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
  public int dragon_armor_protection_leggings = 9;
  @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
  public int dragon_armor_protection_boots = 7;

}