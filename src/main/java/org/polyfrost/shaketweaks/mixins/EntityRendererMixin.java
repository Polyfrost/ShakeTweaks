package org.polyfrost.shaketweaks.mixins;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import org.polyfrost.shaketweaks.ShakeTweaks;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Shadow
    private Minecraft mc;

    @Dynamic("OptiFine adds its own version of renderHand")
    @Redirect(
        method = {"renderHand(FI)V", "renderHand(FIZZZ)V", "func_78476_b"},
        at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;viewBobbing:Z", ordinal = 0, remap = true),
        remap = false
    )
    private boolean shaketweaks$mapBobbing(GameSettings instance) {
        return instance.viewBobbing && !shaketweaks$hasMap();
    }

    @Redirect(method = "setupCameraTransform", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;viewBobbing:Z", ordinal = 0))
    private boolean shaketweaks$viewBobbing(GameSettings instance) {
        return instance.viewBobbing && !ShakeTweaks.config.removeViewBobbing;
    }

    @Unique
    private boolean shaketweaks$hasMap() {
        if (!ShakeTweaks.config.mapBobbing || mc.thePlayer == null) return false;
        //#if MC==10809
        ItemStack heldItem = mc.thePlayer.getHeldItem();
        return heldItem != null && heldItem.getItem() instanceof ItemMap;
        //#else
        //$$ ItemStack mainHandItem = mc.player.getHeldItemMainhand();
        //$$ ItemStack offHandItem = mc.player.getHeldItemOffhand();
        //$$ return (mainHandItem != null && mainHandItem.getItem() instanceof ItemMap) || (offHandItem != null && offHandItem.getItem() instanceof ItemMap);
        //#endif
    }
}
