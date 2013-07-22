package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class GuiSlider extends GuiButton
{
	public float sliderValue = 1.0F;
	public boolean dragging = false;
	private EnumOptions idFloat = null;
	
	public GuiSlider(int p_i3039_1_, int p_i3039_2_, int p_i3039_3_, EnumOptions p_i3039_4_, String p_i3039_5_, float p_i3039_6_)
	{
		super(p_i3039_1_, p_i3039_2_, p_i3039_3_, 150, 20, p_i3039_5_);
		idFloat = p_i3039_4_;
		sliderValue = p_i3039_6_;
	}
	
	@Override protected int getHoverState(boolean par1)
	{
		return 0;
	}
	
	@Override protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
	{
		if(drawButton)
		{
			if(dragging)
			{
				sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);
				if(sliderValue < 0.0F)
				{
					sliderValue = 0.0F;
				}
				if(sliderValue > 1.0F)
				{
					sliderValue = 1.0F;
				}
				par1Minecraft.gameSettings.setOptionFloatValue(idFloat, sliderValue);
				displayString = par1Minecraft.gameSettings.getKeyBinding(idFloat);
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(xPosition + (int) (sliderValue * (width - 8)), yPosition, 0, 66, 4, 20);
			drawTexturedModalRect(xPosition + (int) (sliderValue * (width - 8)) + 4, yPosition, 196, 66, 4, 20);
		}
	}
	
	@Override public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		if(super.mousePressed(par1Minecraft, par2, par3))
		{
			sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);
			if(sliderValue < 0.0F)
			{
				sliderValue = 0.0F;
			}
			if(sliderValue > 1.0F)
			{
				sliderValue = 1.0F;
			}
			par1Minecraft.gameSettings.setOptionFloatValue(idFloat, sliderValue);
			displayString = par1Minecraft.gameSettings.getKeyBinding(idFloat);
			dragging = true;
			return true;
		} else return false;
	}
	
	@Override public void mouseReleased(int par1, int par2)
	{
		dragging = false;
	}
}
