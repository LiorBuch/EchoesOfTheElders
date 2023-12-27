package com.bajookie.echoes_of_the_elders.item.custom;

import com.bajookie.echoes_of_the_elders.client.animation.AnimationUtil;
import com.bajookie.echoes_of_the_elders.item.IHasUpscaledModel;
import com.bajookie.echoes_of_the_elders.item.ModItems;
import com.bajookie.echoes_of_the_elders.system.StackedItem.StackableItemSettings;
import com.bajookie.echoes_of_the_elders.system.StackedItem.StackedAttributeModifiers;
import com.bajookie.echoes_of_the_elders.system.StackedItem.StackedItemStat;
import com.bajookie.echoes_of_the_elders.util.Color;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GodslayerItem extends SwordItem implements IArtifact, IHasUpscaledModel, FabricItem {
    private static final int MAX_COUNT = 16;
    private final StackedItemStat.Float stackedAttackDamage = new StackedItemStat.Float(10f, 64f);

    private final StackedAttributeModifiers stackedAttributeModifiers = new StackedAttributeModifiers(index -> {
        var progress = index / (float) (MAX_COUNT - 1);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) stackedAttackDamage.get(progress), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2, EntityAttributeModifier.Operation.ADDITION));

        return builder.build();
    });

    public GodslayerItem() {
        super(ModItems.ARTIFACT_BASE_MATERIAL, 0, 0, new StackableItemSettings().rarity(Rarity.EPIC).maxCount(MAX_COUNT));
    }

    @Override
    public String getUpscaledModel() {
        return "godslayer_32";
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return stackedAttributeModifiers.get(stack.getCount() - 1);
        }
        return super.getAttributeModifiers(stack, slot);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        var progress = AnimationUtil.HUE_SHIFT_ANIMATION.getProgress(0);

        var c = Color.fromHSL(360 * progress, 0.5f, 0.7f).getRGB();

        tooltip.add(Text.translatable("tooltip.echoes_of_the_elders.godslayer.effect", Text.translatable("tooltip.echoes_of_the_elders.godslayer.invincible").styled(s -> s.withColor(c))));
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}