package cz.czsksmp.dialogblocker.mixin;

import cz.czsksmp.dialogblocker.DialogBlocker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        if (screen != null && DialogBlocker.isEnabled() && DialogBlocker.isDialogScreen(screen)) {
            DialogBlocker.LOGGER.info("DialogBlocker intercepted and blocked Dialog screen: {}", screen.getClass().getName());
            ci.cancel();
        }
    }
}
