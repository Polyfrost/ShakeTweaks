package dev.microcontrollers.shaketweaks.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.microcontrollers.shaketweaks.config.ShakeTweaksConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapWithCondition(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobView(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"), remap = false)
    private boolean disableScreenBobbing(GameRenderer instance, PoseStack poseStack, float partialTicks) {
        return !ShakeTweaksConfig.CONFIG.instance().disableScreenBobbing;
    }

    @WrapWithCondition(method = "renderItemInHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobView(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
    private boolean disableHandBobbing(GameRenderer instance, PoseStack poseStack, float partialTicks) {
        if (ShakeTweaksConfig.CONFIG.instance().disableHandBobbing) return false;
        if (ShakeTweaksConfig.CONFIG.instance().disableMapBobbing) {
            LocalPlayer entity = Minecraft.getInstance().player;
            if (entity != null) {
                ItemStack mainHand = entity.getMainHandItem();
                ItemStack offHand = entity.getOffhandItem();
                if (mainHand.getItem() instanceof MapItem) return false;
                return !(offHand.getItem() instanceof MapItem);
            }
        }
        return true;
    }

    @WrapWithCondition(method = "renderItemInHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
    private boolean disableHandDamageTilt(GameRenderer instance, PoseStack poseStack, float partialTicks) {
        return !ShakeTweaksConfig.CONFIG.instance().disableHandDamage;
    }

    @WrapWithCondition(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
    private boolean disableScreenDamageTilt(GameRenderer instance, PoseStack poseStack, float partialTicks) {
        return !ShakeTweaksConfig.CONFIG.instance().disableScreenDamage;
    }

}
