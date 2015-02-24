package com.pbjboss.pvpdisabler.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * Created by PbJBOSS on 2/24/2015.
 */
public
class EventHandler
{
    @SubscribeEvent
    public void worldLoadEvent(WorldEvent.Load event)
    {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0)
            PvPHandler.load(event.world.provider.getDimensionName());
    }

    @SubscribeEvent
    public void playerAttackEvent(LivingAttackEvent event)
    {
        if (event.entity instanceof EntityPlayer && event.source.getEntity() instanceof EntityPlayer)
        {
            if (!(PvPHandler.doesWhitelistContainPlayer((EntityPlayer) event.entity) && PvPHandler.doesWhitelistContainPlayer((EntityPlayer) event.source.getEntity())))
                event.setCanceled(true);
        }
    }
}
