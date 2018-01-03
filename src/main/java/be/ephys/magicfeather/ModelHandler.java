package be.ephys.magicfeather;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MagicFeatherMod.MODID)
public final class ModelHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent evt) {
        ModItems.magicFeather.registerModel();
    }
}
