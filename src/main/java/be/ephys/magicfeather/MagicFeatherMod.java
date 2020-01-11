package be.ephys.magicfeather;

import be.ephys.cookiecore.config.ConfigSynchronizer;
import net.minecraftforge.fml.common.Mod;

@Mod(MagicFeatherMod.MODID)
public class MagicFeatherMod {
    public static final String MODID = "magicfeather";

    public MagicFeatherMod() {
        ConfigSynchronizer.synchronizeConfig();
    }
}
