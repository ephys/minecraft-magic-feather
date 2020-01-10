package be.ephys.magicfeather;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MagicFeatherMod.MODID)
public class ModItems {
    public static ItemMagicFeather magicFeather;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
      magicFeather = new ItemMagicFeather();

      event.getRegistry().register(magicFeather);
      MinecraftForge.EVENT_BUS.register(ModItems.magicFeather);
    }
}
