package net.minecraft.src;

import java.io.IOException;

public class TaskOnlineConnect extends TaskLongRunning
{
	private NetClientHandler field_96586_a;
	private final McoServer field_96585_c;
	private final GuiScreen field_96584_d;
	
	public TaskOnlineConnect(GuiScreen par1GuiScreen, McoServer par2McoServer)
	{
		field_96584_d = par1GuiScreen;
		field_96585_c = par2McoServer;
	}
	
	@Override public void func_96573_a()
	{
		if(field_96586_a != null)
		{
			field_96586_a.processReadPackets();
		}
	}
	
	private void func_96581_e()
	{
		try
		{
			Thread.sleep(5000L);
		} catch(InterruptedException var2)
		{
			System.err.println(var2);
		}
	}
	
	private void func_96582_a(String par1Str, int par2)
	{
		new ThreadOnlineConnect(this, par1Str, par2).start();
	}
	
	@Override public void run()
	{
		func_96576_b(StringTranslate.getInstance().translateKey("mco.connect.connecting"));
		McoClient var1 = new McoClient(func_96578_b().session);
		boolean var2 = false;
		boolean var3 = false;
		McoServerAddress var4 = null;
		for(int var5 = 0; var5 < 10 && !func_96577_c(); ++var5)
		{
			try
			{
				var4 = var1.func_96374_a(field_96585_c.field_96408_a);
				var2 = true;
			} catch(ExceptionRetryCall var7)
			{
				;
			} catch(ExceptionMcoService var8)
			{
				var3 = true;
				func_96575_a(var8.getLocalizedMessage());
				break;
			} catch(IOException var9)
			{
				;
			} catch(Exception var10)
			{
				var3 = true;
				func_96575_a(var10.getLocalizedMessage());
			}
			if(var2)
			{
				break;
			}
			func_96581_e();
		}
		if(!func_96577_c() && !var3)
		{
			if(var2)
			{
				ServerAddress var11 = ServerAddress.func_78860_a(var4.field_96417_a);
				func_96582_a(var11.getIP(), var11.getPort());
			} else
			{
				func_96578_b().displayGuiScreen(field_96584_d);
			}
		}
	}
	
	static NetClientHandler func_96580_a(TaskOnlineConnect par0TaskOnlineConnect)
	{
		return par0TaskOnlineConnect.field_96586_a;
	}
	
	static NetClientHandler func_96583_a(TaskOnlineConnect par0TaskOnlineConnect, NetClientHandler par1NetClientHandler)
	{
		return par0TaskOnlineConnect.field_96586_a = par1NetClientHandler;
	}
	
	static GuiScreen func_98172_a(TaskOnlineConnect par0TaskOnlineConnect)
	{
		return par0TaskOnlineConnect.field_96584_d;
	}
}
