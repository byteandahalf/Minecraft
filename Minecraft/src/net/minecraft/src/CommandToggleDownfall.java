package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class CommandToggleDownfall extends CommandBase
{
	@Override public String getCommandName()
	{
		return "toggledownfall";
	}
	
	@Override public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "commands.downfall.usage";
	}
	
	@Override public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		toggleDownfall();
		notifyAdmins(par1ICommandSender, "commands.downfall.success", new Object[0]);
	}
	
	protected void toggleDownfall()
	{
		MinecraftServer.getServer().worldServers[0].toggleRain();
		MinecraftServer.getServer().worldServers[0].getWorldInfo().setThundering(true);
	}
}
