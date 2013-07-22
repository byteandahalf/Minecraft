package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class ThreadClientSleep extends Thread
{
	final Minecraft mc;
	
	public ThreadClientSleep(Minecraft par1Minecraft, String par2Str)
	{
		super(par2Str);
		mc = par1Minecraft;
	}
	
	@Override public void run()
	{
		while(mc.running)
		{
			try
			{
				Thread.sleep(2147483647L);
			} catch(InterruptedException var2)
			{
				;
			}
		}
	}
}
