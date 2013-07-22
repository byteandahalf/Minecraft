package net.minecraft.src;

public class MaterialPortal extends Material
{
	public MaterialPortal(MapColor par1MapColor)
	{
		super(par1MapColor);
	}
	
	@Override public boolean blocksMovement()
	{
		return false;
	}
	
	@Override public boolean getCanBlockGrass()
	{
		return false;
	}
	
	@Override public boolean isSolid()
	{
		return false;
	}
}
