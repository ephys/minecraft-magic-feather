package be.ephys.magicfeather;

import be.ephys.cookiecore.config.ConfigSynchronizer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

@Mod(MagicFeatherMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MagicFeatherMod.MODID)
public class MagicFeatherMod {
    public static final String MODID = "magicfeather";

    public MagicFeatherMod() {
        ConfigSynchronizer.synchronizeConfig();
    }

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        // add support for netherite plus beacon
        if (ModList.get().isLoaded("netherite_plus")) {
            NetheritePlusIntegration.init();
        }
    }

    @SubscribeEvent
    public static void processInterComms(InterModProcessEvent event) {
        event.getIMCStream(method -> method.equals("add-beacon-handler")).forEach(msg -> {
            Object data = msg.getMessageSupplier().get();

            if (data instanceof BeaconTypeHandler) {
                BeaconRangeCalculator.registerBeaconType((BeaconTypeHandler) data);
            }
        });
    }
}
