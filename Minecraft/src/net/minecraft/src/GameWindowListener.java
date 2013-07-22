package net.minecraft.src;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class GameWindowListener extends WindowAdapter
{
	@Override public void windowClosing(WindowEvent par1WindowEvent)
	{
		System.err.println("Someone is closing me!");
		System.exit(1);
	}
}
