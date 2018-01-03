package be.ephys.magicfeather;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MagicFeatherMod.MODID)
public class ModItems {
    public static ItemMagicFeather magicFeather;

    public static void init() {
        magicFeather = new ItemMagicFeather();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(magicFeather);
        MinecraftForge.EVENT_BUS.register(ModItems.magicFeather);

        magicFeather.setCreativeTab(CreativeTabs.TRANSPORTATION);
    }
}
