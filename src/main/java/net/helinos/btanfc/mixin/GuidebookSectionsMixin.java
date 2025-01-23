package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.helinos.btanfc.gui.guidebook.NFCGuidebookSections;
import net.minecraft.client.gui.guidebook.GuidebookSections;

@Mixin(value = GuidebookSections.class, remap = false)
public abstract class GuidebookSectionsMixin {
    @Inject(method = "init()V", at = @At("TAIL"))
    private static void init(CallbackInfo callbackInfo) {
        NFCGuidebookSections.init();
    }
}
