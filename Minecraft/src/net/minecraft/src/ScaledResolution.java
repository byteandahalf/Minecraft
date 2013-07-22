package net.minecraft.src;

public class ScaledResolution
{
	private int scaledWidth;
	private int scaledHeight;
	private double scaledWidthD;
	private double scaledHeightD;
	private int scaleFactor;
	
	public ScaledResolution(GameSettings par1GameSettings, int par2, int par3)
	{
		scaledWidth = par2;
		scaledHeight = par3;
		scaleFactor = 1;
		int var4 = par1GameSettings.guiScale;
		if(var4 == 0)
		{
			var4 = 1000;
		}
		while(scaleFactor < var4 && scaledWidth / (scaleFactor + 1) >= 320 && scaledHeight / (scaleFactor + 1) >= 240)
		{
			++scaleFactor;
		}
		scaledWidthD = (double) scaledWidth / (double) scaleFactor;
		scaledHeightD = (double) scaledHeight / (double) scaleFactor;
		scaledWidth = MathHelper.ceiling_double_int(scaledWidthD);
		scaledHeight = MathHelper.ceiling_double_int(scaledHeightD);
	}
	
	public int getScaledHeight()
	{
		return scaledHeight;
	}
	
	public double getScaledHeight_double()
	{
		return scaledHeightD;
	}
	
	public int getScaledWidth()
	{
		return scaledWidth;
	}
	
	public double getScaledWidth_double()
	{
		return scaledWidthD;
	}
	
	public int getScaleFactor()
	{
		return scaleFactor;
	}
}
