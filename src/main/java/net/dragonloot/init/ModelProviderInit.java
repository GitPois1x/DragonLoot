package net.dragonloot.init;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModelProviderInit {

    public static void init() {
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW_ITEM, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW_ITEM, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0;
            } else
                return CrossbowItem.isCharged(itemStack) ? 0.0F : (float) (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / (float) CrossbowItem.getPullTime(itemStack);
        });
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("pulling"), (itemStack, clientWorld1, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("charged"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("firework"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && CrossbowItem.isCharged(itemStack) && CrossbowItem.hasProjectile(itemStack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_TRIDENT_ITEM, new Identifier("throwing"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }
}