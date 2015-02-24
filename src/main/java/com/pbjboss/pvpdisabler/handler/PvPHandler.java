package com.pbjboss.pvpdisabler.handler;

import com.pbjboss.pvpdisabler.PvPDisabler;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by PbJBOSS on 2/24/2015.
 */
public
class PvPHandler
{
    private static
    ArrayList<UUID> pvpWhiteList;

    private static final File configLocation = new File(PvPDisabler.configLocation, "/pvpdisabler/");
    private static File fileLocation;

    public static void load(String worldName)
    {
        if (!configLocation.exists())
        {
            configLocation.mkdir();
        }

        fileLocation = new File(configLocation, worldName + ".pvp");

        if (!fileLocation.exists())
        {
            try
            {
                fileLocation.createNewFile();
            } catch (IOException e)
            {
                FMLLog.log(Level.ERROR, "Error creating world pvp file!");
                e.printStackTrace();
            }
        }

        loadPlayerList();

        if (pvpWhiteList == null)
            pvpWhiteList = new ArrayList<UUID>();
    }

    public static void loadPlayerList()
    {
        try
        {
            FileInputStream fis = new FileInputStream(fileLocation);
            ObjectInputStream is = new ObjectInputStream(fis);
            pvpWhiteList = (ArrayList) is.readObject();

            is.close();
            fis.close();

        } catch (FileNotFoundException e)
        {
            FMLLog.log(Level.ERROR, "Error loading player list!");
            e.printStackTrace();
        } catch (IOException e)
        {
            FMLLog.log(Level.ERROR, "Error loading player list!");
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
        }
    }

    public static void savePlayerList()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            os.writeObject(pvpWhiteList);

            os.close();
            fos.close();

        } catch (FileNotFoundException e)
        {
            FMLLog.log(Level.ERROR, "Error saving player list!");
            e.printStackTrace();
        } catch (IOException e)
        {
            FMLLog.log(Level.ERROR, "Error saving player list!");
            e.printStackTrace();
        }
    }

    public static boolean doesWhitelistContainPlayer(EntityPlayer player)
    {
        if (pvpWhiteList.contains(player.getPersistentID()))
            return true;

        return false;
    }

    public static String addPlayerToWhitelist(EntityPlayer player)
    {
        if (!doesWhitelistContainPlayer(player))
        {
            pvpWhiteList.add(player.getPersistentID());
            savePlayerList();
            return "PvP Enabled!";
        }
        return "PvP Already Enabled";
    }

    public static String removePlayerFromWhitelist(EntityPlayer player)
    {
        if (doesWhitelistContainPlayer(player))
        {
            pvpWhiteList.remove(player.getPersistentID());
            savePlayerList();
            return "PvP Disabled!";
        }
        return "PvP Already Disabled!";
    }
}
