package cz.czsksmp.dialogblocker.command;

import cz.czsksmp.dialogblocker.DialogBlocker;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class DialogBlockerCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("dialogblocker")
                    .executes(DialogBlockerCommand::sendStatus)
                    .then(ClientCommandManager.literal("on").executes(context -> setStatus(context, true)))
                    .then(ClientCommandManager.literal("off").executes(context -> setStatus(context, false)))
                    .then(ClientCommandManager.literal("toggle").executes(DialogBlockerCommand::toggleStatus))
            );
        });
    }

    private static int sendStatus(CommandContext<FabricClientCommandSource> context) {
        boolean state = DialogBlocker.isEnabled();
        context.getSource().sendFeedback(Text.literal("DialogBlocker: " + (state ? "ON" : "OFF")));
        return Command.SINGLE_SUCCESS;
    }

    private static int setStatus(CommandContext<FabricClientCommandSource> context, boolean enable) {
        DialogBlocker.setEnabled(enable);
        context.getSource().sendFeedback(Text.literal("DialogBlocker: " + (enable ? "ON" : "OFF")));
        return Command.SINGLE_SUCCESS;
    }

    private static int toggleStatus(CommandContext<FabricClientCommandSource> context) {
        boolean newState = !DialogBlocker.isEnabled();
        DialogBlocker.setEnabled(newState);
        context.getSource().sendFeedback(Text.literal("DialogBlocker: " + (newState ? "ON" : "OFF")));
        return Command.SINGLE_SUCCESS;
    }
}
