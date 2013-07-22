package net.minecraft.src;

import java.util.List;
import java.util.regex.Matcher;

import net.minecraft.server.MinecraftServer;

public class CommandServerPardonIp extends CommandBase
{
	@Override public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return par2ArrayOfStr.length == 1 ? getListOfStringsFromIterableMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getBannedList().keySet()) : null;
	}
	
	@Override public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
	{
		return MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isListActive() && super.canCommandSenderUseCommand(par1ICommandSender);
	}
	
	@Override public String getCommandName()
	{
		return "pardon-ip";
	}
	
	@Override public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "commands.unbanip.usage";
	}
	
	@Override public int getRequiredPermissionLevel()
	{
		return 3;
	}
	
	@Override public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		if(par2ArrayOfStr.length == 1 && par2ArrayOfStr[0].length() > 1)
		{
			Matcher var3 = CommandServerBanIp.IPv4Pattern.matcher(par2ArrayOfStr[0]);
			if(var3.matches())
			{
				MinecraftServer.getServer().getConfigurationManager().getBannedIPs().remove(par2ArrayOfStr[0]);
				notifyAdmins(par1ICommandSender, "commands.unbanip.success", new Object[] { par2ArrayOfStr[0] });
			} else throw new SyntaxErrorException("commands.unbanip.invalid", new Object[0]);
		} else throw new WrongUsageException("commands.unbanip.usage", new Object[0]);
	}
}
