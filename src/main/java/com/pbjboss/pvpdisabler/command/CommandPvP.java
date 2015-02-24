package com.pbjboss.pvpdisabler.command;

import com.pbjboss.pvpdisabler.handler.PvPHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.List;

/**
 * Created by PbJBOSS on 2/24/2015.
 */
public
class CommandPvP implements ICommand
{
    @Override
    public
    String getCommandName()
    {
        return "pvp";
    }

    @Override
    public
    String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "/pvp <enable|disable>";
    }

    @Override
    public
    List getCommandAliases()
    {
        return null;
    }

    @Override
    public
    void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_1_ instanceof EntityPlayer && !((EntityPlayer) p_71515_1_).worldObj.isRemote)
        {
            EntityPlayer player = (EntityPlayer) p_71515_1_;

            if (p_71515_2_.length < 1)
            {
                player.addChatComponentMessage(new ChatComponentText(getCommandUsage(p_71515_1_)));
                return;
            }

            if (p_71515_2_[0].equalsIgnoreCase("enable"))
            {
                player.addChatComponentMessage(new ChatComponentText(PvPHandler.addPlayerToWhitelist(player)));
            }
            else if (p_71515_2_[0].equalsIgnoreCase("disable"))
            {
                player.addChatComponentMessage(new ChatComponentText(PvPHandler.removePlayerFromWhitelist(player)));
            }
            else
            {
                player.addChatComponentMessage(new ChatComponentText(getCommandUsage(p_71515_1_)));
            }
        }
    }

    @Override
    public
    boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
    {
        return true;
    }

    @Override
    public
    List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return null;
    }

    @Override
    public
    boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
    {
        return false;
    }

    @Override
    public
    int compareTo(Object o)
    {
        return 0;
    }
}
