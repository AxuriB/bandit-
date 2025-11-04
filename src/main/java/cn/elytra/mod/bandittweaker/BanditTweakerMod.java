package cn.elytra.mod.bandittweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BanditTweakerMod.MODID, name = BanditTweakerMod.NAME, version = BanditTweakerMod.VERSION, acceptedMinecraftVersions = "1.12.2")
public class BanditTweakerMod {
    public static final String MODID = "bandittweaker";
    public static final String NAME = "Bandit Tweaker";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "cn.elytra.mod.bandittweaker.ClientProxy", serverSide = "cn.elytra.mod.bandittweaker.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        // register the server command
        event.registerServerCommand(new CommandBT());
    }
}
