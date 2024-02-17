package com.bajookie.echoes_of_the_elders.mixin;

import com.bajookie.echoes_of_the_elders.effects.ModEffects;
import com.bajookie.echoes_of_the_elders.entity.ModDamageSources;
import com.bajookie.echoes_of_the_elders.item.ModItems;
import com.bajookie.echoes_of_the_elders.system.Capability.ModCapabilities;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSources;playerAttack(Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/entity/damage/DamageSource;"))
    private DamageSource playerAttackProxy(DamageSources instance, PlayerEntity attacker) {
        var stack = attacker.getMainHandStack();

        if (stack.isOf(ModItems.GODSLAYER)) {
            return ModDamageSources.divineAttack(attacker);
        }

        return instance.playerAttack(attacker);
    }

    @Inject(method = "getAttackCooldownProgress", at = @At("HEAD"), cancellable = true)
    private void getAttackCooldownProgress(float baseTime, CallbackInfoReturnable<Float> info) {
        if ((((PlayerEntity) (Object) this)).hasStatusEffect(ModEffects.ECHO_HIT)) {
            info.setReturnValue(1.0f);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onServerPlayerCreated(NbtCompound nbt, CallbackInfo ci) {
        var player = (PlayerEntity) (Object) this;

        ModCapabilities.PERSISTENT_COOLDOWN.attach(player);
    }

    /* can work well if we need to do special attack
        @Inject(method = "attack",at = @At(value = "INVOKE",target = "Lnet/minecraft/enchantment/EnchantmentHelper;getSweepingMultiplier(Lnet/minecraft/entity/LivingEntity;)F"))
    public void attack(Entity target ,CallbackInfo info) {
        ItemStack artiStack = (((PlayerEntity)(Object)this)).getStackInHand(Hand.MAIN_HAND);
        if (artiStack != null && artiStack.getItem() instanceof ILeftClickAbility iLeftClickAbility) {
            iLeftClickAbility.performLeftClickAbility(artiStack,(((PlayerEntity)(Object)this)).getWorld(),(((PlayerEntity)(Object)this)));
        }
    }
     */
}
