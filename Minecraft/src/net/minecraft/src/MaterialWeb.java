package net.minecraft.src;

final class MaterialWeb extends Material
{
	MaterialWeb(MapColor par1MapColor)
	{
		super(par1MapColor);
	}
	
	@Override public boolean blocksMovement()
	{
		return false;
	}
}
