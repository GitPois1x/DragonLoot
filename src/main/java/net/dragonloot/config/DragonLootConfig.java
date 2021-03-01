package net.dragonloot.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "dragonloot")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class DragonLootConfig implements ConfigData {

  // @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
  public int scale_minimum_drop_amount = 3;
  public int additional_scales_per_player = 2;
  @Comment("0.8F = 80%")
  public float additional_scale_drop_chance = 0.8F;

}