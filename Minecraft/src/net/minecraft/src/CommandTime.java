package net.minecraft.src;

import java.util.List;

import net.minecraft.server.MinecraftServer;

public class CommandTime extends CommandBase
{
	@Override public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
	{
		return p_71516_2_.length == 1 ? getListOfStringsMatchingLastWord(p_71516_2_, new String[] { "set", "add" }) : p_71516_2_.length == 2 && p_71516_2_[0].equals("set") ? getListOfStringsMatchingLastWord(p_71516_2_, new String[] { "day", "night" }) : null;
	}
	
	protected void addTime(ICommandSender p_71553_1_, int p_71553_2_)
	{
		for(WorldServer var4 : MinecraftServer.getServer().worldServers)
		{
			var4.setWorldTime(var4.getWorldTime() + p_71553_2_);
		}
	}
	
	@Override public String getCommandName()
	{
		return "time";
	}
	
	@Override public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return p_71518_1_.translateString("commands.time.usage", new Object[0]);
	}
	
	@Override public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_)
	{
		if(p_71515_2_.length > 1)
		{
			int var3;
			if(p_71515_2_[0].equals("set"))
			{
				if(p_71515_2_[1].equals("day"))
				{
					var3 = 0;
				} else if(p_71515_2_[1].equals("night"))
				{
					var3 = 12500;
				} else
				{
					var3 = parseIntWithMin(p_71515_1_, p_71515_2_[1], 0);
				}
				setTime(p_71515_1_, var3);
				notifyAdmins(p_71515_1_, "commands.time.set", new Object[] { Integer.valueOf(var3) });
				return;
			}
			if(p_71515_2_[0].equals("add"))
			{
				var3 = parseIntWithMin(p_71515_1_, p_71515_2_[1], 0);
				addTime(p_71515_1_, var3);
				notifyAdmins(p_71515_1_, "commands.time.added", new Object[] { Integer.valueOf(var3) });
				return;
			}
		}
		throw new WrongUsageException("commands.time.usage", new Object[0]);
	}
	
	protected void setTime(ICommandSender p_71552_1_, int p_71552_2_)
	{
		for(WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			worldServer.setWorldTime(p_71552_2_);
		}
	}
}
