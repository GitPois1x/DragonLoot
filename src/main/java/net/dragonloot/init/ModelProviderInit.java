package net.dragonloot.init;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModelProviderInit {

        public static void init() {
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW_ITEM, new Identifier("pull"), (
                                itemStack, clientWorld,
                                livingEntity) -> livingEntity != null && livingEntity.getActiveItem() != itemStack
                                                ? 0.0F
                                                : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft())
                                                                / 20.0F);
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW_ITEM, new Identifier("pulling"),
                                (itemStack, clientWorld,
                                                livingEntity) -> livingEntity != null && livingEntity.isUsingItem()
                                                                && livingEntity.getActiveItem() == itemStack ? 1.0F
                                                                                : 0.0F);

                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("pull"),
                                (itemStack, clientWorld, livingEntity) -> {
                                        if (livingEntity == null) {
                                                return 0;
                                        }
                                        return CrossbowItem.isCharged(itemStack) ? 0.0F
                                                        : (float) (itemStack.getMaxUseTime()
                                                                        - livingEntity.getItemUseTimeLeft())
                                                                        / (float) CrossbowItem.getPullTime(itemStack);
                                });
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("pulling"),
                                (itemStack, clientWorld1,
                                                livingEntity) -> livingEntity != null && livingEntity.isUsingItem()
                                                                && livingEntity.getActiveItem() == itemStack
                                                                && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("charged"),
                                (itemStack, clientWorld, livingEntity) -> livingEntity != null
                                                && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW_ITEM, new Identifier("firework"),
                                (itemStack, clientWorld, livingEntity) -> livingEntity != null
                                                && CrossbowItem.isCharged(itemStack)
                                                && CrossbowItem.hasProjectile(itemStack, Items.FIREWORK_ROCKET) ? 1.0F
                                                                : 0.0F);
                FabricModelPredicateProviderRegistry.register(ItemInit.DRAGON_TRIDENT_ITEM, new Identifier("throwing"),
                                (itemStack, clientWorld,
                                                livingEntity) -> livingEntity != null && livingEntity.isUsingItem()
                                                                && livingEntity.getActiveItem() == itemStack ? 1.0F
                                                                                : 0.0F);
        }
}