package com.bajookie.echoes_of_the_elders.item.custom;

import com.bajookie.echoes_of_the_elders.entity.custom.ChainLightningProjectileEntity;
import com.bajookie.echoes_of_the_elders.item.IHasCooldown;
import com.bajookie.echoes_of_the_elders.system.StackedItem.StackedItemStat;
import com.bajookie.echoes_of_the_elders.system.Text.TextArgs;
import com.bajookie.echoes_of_the_elders.system.Text.TextUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrbOfLightning extends Item implements IArtifact, IStackPredicate, IHasCooldown {
    protected final StackedItemStat.Int cooldown = new StackedItemStat.Int(20 * 20, 20 * 5);

    public OrbOfLightning() {
        super(new FabricItemSettings().maxCount(16).rarity(Rarity.RARE));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var itemStack = user.getStackInHand(hand);

        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            user.getItemCooldownManager().set(this, this.getCooldown(itemStack));

            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                ChainLightningProjectileEntity chainLightningProjectileEntity = new ChainLightningProjectileEntity(world, user);
                chainLightningProjectileEntity.setItem(itemStack);
                chainLightningProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
                world.spawnEntity(chainLightningProjectileEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));

            return TypedActionResult.success(itemStack, world.isClient());
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        var args = new TextArgs().put("numLightning", Text.literal("5"));

        tooltip.add(TextUtil.translatable("tooltip.echoes_of_the_elders.chain_lightning_item.effect", args));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getCooldown(ItemStack itemStack) {
        return cooldown.get(itemStack);
    }
}