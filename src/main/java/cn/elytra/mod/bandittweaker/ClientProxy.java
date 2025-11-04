package cn.elytra.mod.bandittweaker;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
    private static KeyBinding openGuiKey;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        openGuiKey = new KeyBinding("key.bandittweaker.opengui", Keyboard.KEY_K, "key.categories.misc");
        ClientRegistry.registerKeyBinding(openGuiKey);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent ev) {
        if (openGuiKey.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiBT());
        }
    }
}
