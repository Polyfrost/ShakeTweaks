package org.polyfrost.shaketweaks;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.shaketweaks.config.ShakeTweaksConfig;

//#if FORGE-LIKE
@Mod(modid = ShakeTweaks.ID, name = ShakeTweaks.NAME, version = ShakeTweaks.VERSION)
//#endif
public class ShakeTweaks
    //#if FABRIC
    //$$ implements ModInitializer
    //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static ShakeTweaksConfig config;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
        //#if FORGE
        FMLInitializationEvent event
        //#endif
    ) {
        config = new ShakeTweaksConfig();
    }
}
