package be.ephys.magicfeather;

import be.ephys.cookiecore.config.ConfigSynchronizer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MagicFeatherMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MagicFeatherMod.MOD_ID)
public class MagicFeatherMod {
  public static final String MOD_ID = "magicfeather";

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicFeatherMod.MOD_ID);

  public static final RegistryObject<ItemMagicFeather> MAGIC_FEATHER_ITEM = ITEMS.register("magicfeather", ItemMagicFeather::new);

  public MagicFeatherMod(FMLJavaModLoadingContext context) {
    ConfigSynchronizer.synchronizeConfig();

    ITEMS.register(context.getModEventBus());
  }

  @SubscribeEvent
  public static void onBuildContents(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
      event.accept(MAGIC_FEATHER_ITEM.get());
    }
  }

  @SubscribeEvent
  public static void processInterComms(InterModProcessEvent event) {
    event.getIMCStream(method -> method.equals("add-beacon-handler")).forEach(msg -> {
      Object data = msg.messageSupplier().get();

      if (data instanceof BeaconTypeHandler) {
        BeaconRangeCalculator.registerBeaconType((BeaconTypeHandler) data);
      }
    });
  }
}
