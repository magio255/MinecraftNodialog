package cz.czsksmp.dialogblocker;

import cz.czsksmp.dialogblocker.command.DialogBlockerCommand;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DialogBlocker implements ClientModInitializer {
    public static final String MOD_ID = "dialogblocker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static boolean enabled = true;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Dialog Blocker for Minecraft 1.20.1");
        DialogBlockerCommand.register();
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean value) {
        enabled = value;
    }

    public static boolean isDialogScreen(Screen screen) {
        if (screen == null) {
            return false;
        }
        String className = screen.getClass().getName().toLowerCase();
        return className.contains("dialog") || className.contains("showdialog");
    }
}
