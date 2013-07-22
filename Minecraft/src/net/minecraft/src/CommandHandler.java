package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommandHandler implements ICommandManager
{
	private final Map commandMap = new HashMap();
	private final Set commandSet = new HashSet();
	
	@Override public int executeCommand(ICommandSender par1ICommandSender, String par2Str)
	{
		par2Str = par2Str.trim();
		if(par2Str.startsWith("/"))
		{
			par2Str = par2Str.substring(1);
		}
		String[] var3 = par2Str.split(" ");
		String var4 = var3[0];
		var3 = dropFirstString(var3);
		ICommand var5 = (ICommand) commandMap.get(var4);
		int var6 = getUsernameIndex(var5, var3);
		int var7 = 0;
		try
		{
			if(var5 == null) throw new CommandNotFoundException();
			if(var5.canCommandSenderUseCommand(par1ICommandSender))
			{
				if(var6 > -1)
				{
					EntityPlayerMP[] var8 = PlayerSelector.matchPlayers(par1ICommandSender, var3[var6]);
					String var9 = var3[var6];
					EntityPlayerMP[] var10 = var8;
					int var11 = var8.length;
					for(int var12 = 0; var12 < var11; ++var12)
					{
						EntityPlayerMP var13 = var10[var12];
						var3[var6] = var13.getEntityName();
						try
						{
							var5.processCommand(par1ICommandSender, var3);
							++var7;
						} catch(CommandException var15)
						{
							par1ICommandSender.sendChatToPlayer(EnumChatFormatting.RED + par1ICommandSender.translateString(var15.getMessage(), var15.getErrorOjbects()));
						}
					}
					var3[var6] = var9;
				} else
				{
					var5.processCommand(par1ICommandSender, var3);
					++var7;
				}
			} else
			{
				par1ICommandSender.sendChatToPlayer("" + EnumChatFormatting.RED + "You do not have permission to use this command.");
			}
		} catch(WrongUsageException var16)
		{
			par1ICommandSender.sendChatToPlayer(EnumChatFormatting.RED + par1ICommandSender.translateString("commands.generic.usage", new Object[] { par1ICommandSender.translateString(var16.getMessage(), var16.getErrorOjbects()) }));
		} catch(CommandException var17)
		{
			par1ICommandSender.sendChatToPlayer(EnumChatFormatting.RED + par1ICommandSender.translateString(var17.getMessage(), var17.getErrorOjbects()));
		} catch(Throwable var18)
		{
			par1ICommandSender.sendChatToPlayer(EnumChatFormatting.RED + par1ICommandSender.translateString("commands.generic.exception", new Object[0]));
			var18.printStackTrace();
		}
		return var7;
	}
	
	@Override public Map getCommands()
	{
		return commandMap;
	}
	
	@Override public List getPossibleCommands(ICommandSender par1ICommandSender)
	{
		ArrayList var2 = new ArrayList();
		Iterator var3 = commandSet.iterator();
		while(var3.hasNext())
		{
			ICommand var4 = (ICommand) var3.next();
			if(var4.canCommandSenderUseCommand(par1ICommandSender))
			{
				var2.add(var4);
			}
		}
		return var2;
	}
	
	@Override public List getPossibleCommands(ICommandSender par1ICommandSender, String par2Str)
	{
		String[] var3 = par2Str.split(" ", -1);
		String var4 = var3[0];
		if(var3.length == 1)
		{
			ArrayList var8 = new ArrayList();
			Iterator var6 = commandMap.entrySet().iterator();
			while(var6.hasNext())
			{
				Entry var7 = (Entry) var6.next();
				if(CommandBase.doesStringStartWith(var4, (String) var7.getKey()) && ((ICommand) var7.getValue()).canCommandSenderUseCommand(par1ICommandSender))
				{
					var8.add(var7.getKey());
				}
			}
			return var8;
		} else
		{
			if(var3.length > 1)
			{
				ICommand var5 = (ICommand) commandMap.get(var4);
				if(var5 != null) return var5.addTabCompletionOptions(par1ICommandSender, dropFirstString(var3));
			}
			return null;
		}
	}
	
	private int getUsernameIndex(ICommand par1ICommand, String[] par2ArrayOfStr)
	{
		if(par1ICommand == null) return -1;
		else
		{
			for(int var3 = 0; var3 < par2ArrayOfStr.length; ++var3)
			{
				if(par1ICommand.isUsernameIndex(par2ArrayOfStr, var3) && PlayerSelector.matchesMultiplePlayers(par2ArrayOfStr[var3])) return var3;
			}
			return -1;
		}
	}
	
	public ICommand registerCommand(ICommand par1ICommand)
	{
		List var2 = par1ICommand.getCommandAliases();
		commandMap.put(par1ICommand.getCommandName(), par1ICommand);
		commandSet.add(par1ICommand);
		if(var2 != null)
		{
			Iterator var3 = var2.iterator();
			while(var3.hasNext())
			{
				String var4 = (String) var3.next();
				ICommand var5 = (ICommand) commandMap.get(var4);
				if(var5 == null || !var5.getCommandName().equals(var4))
				{
					commandMap.put(var4, par1ICommand);
				}
			}
		}
		return par1ICommand;
	}
	
	private static String[] dropFirstString(String[] par0ArrayOfStr)
	{
		String[] var1 = new String[par0ArrayOfStr.length - 1];
		for(int var2 = 1; var2 < par0ArrayOfStr.length; ++var2)
		{
			var1[var2 - 1] = par0ArrayOfStr[var2];
		}
		return var1;
	}
}
