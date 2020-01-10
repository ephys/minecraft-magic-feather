package be.ephys.magicfeather;

import be.ephys.cookiecore.config.ConfigSynchronizer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(MagicFeatherMod.MODID)
public class MagicFeatherMod {
    public static final String MODID = "magicfeather";

    @Mod.Instance
    public static MagicFeatherMod instance;

    @SidedProxy(clientSide = "be.ephys.magicfeather.ClientProxy", serverSide = "be.ephys.magicfeather.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigSynchronizer.synchronizeConfig(event);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
