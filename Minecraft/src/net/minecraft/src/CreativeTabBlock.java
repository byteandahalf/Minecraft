package net.minecraft.src;

final class CreativeTabBlock extends CreativeTabs
{
	CreativeTabBlock(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	
	@Override public int getTabIconItemIndex()
	{
		return Block.brick.blockID;
	}
}
