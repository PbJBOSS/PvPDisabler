package com.pbjboss.pvpdisabler;

import com.pbjboss.pvpdisabler.command.CommandPvP;
import com.pbjboss.pvpdisabler.handler.EventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

/**
 * Created by PbJBOSS on 2/24/2015.
 */
@Mod(modid = "pvpdisabler", name = "PvP Disabler", version = "1.7.10-1.0", acceptableRemoteVersions = "*")
public
class PvPDisabler
{

    public static File configLocation;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandPvP());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        configLocation = event.getModConfigurationDirectory();
    }
}
