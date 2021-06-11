package net.dragonloot.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends MobEntity {
  @Shadow
  public int ticksSinceDeath;

  public EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(method = "updatePostDeath", at = @At("TAIL"))
  public void updatePostDeathMixin(CallbackInfo info) {
    if (!this.world.isClient && this.ticksSinceDeath == 180) {
      Box box = new Box(this.getBlockPos());
      List<PlayerEntity> list = world.getEntitiesByClass(PlayerEntity.class, box.expand(128D),
          EntityPredicates.EXCEPT_SPECTATOR);
      int dragonscalebonus = 0;
      for (int i = 0; i < list.size(); ++i) {
        PlayerEntity entity = (PlayerEntity) list.get(i);
        if (entity instanceof PlayerEntity) {
          int dropBonus = ConfigInit.CONFIG.additional_scales_per_player;
          dragonscalebonus = dragonscalebonus + dropBonus;
        }
      }

      for (int i = 0; i < (ConfigInit.CONFIG.scale_minimum_drop_amount); i++) {
        this.dropStack(new ItemStack(ItemInit.DRAGON_SCALE_ITEM));
      }
      for (int u = 0; u < dragonscalebonus; u++) {
        if (this.world.random.nextFloat() <= ConfigInit.CONFIG.additional_scale_drop_chance) {
          this.dropStack(new ItemStack(ItemInit.DRAGON_SCALE_ITEM));
        }

      }
    }
  }

}