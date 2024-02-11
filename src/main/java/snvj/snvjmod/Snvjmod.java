package snvj.snvjmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.*;

public class Snvjmod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("snvjmod");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> {
            dispatcher.register(literal("snvj").executes(context -> {
                ItemStack stack = context.getSource().getPlayer().getMainHandStack();

                if (stack.getItem().toString() == "map") {
                    context.getSource().sendFeedback(
                            () -> Text.literal("Selected slot: " + (stack.getItem().toString() == "map")),
                            false);
                    return 1;
                }
                return 1;
            }));
        });

        LOGGER.info("Hello Snvj world!");
    }
}