package net.minecraft.src;

public class BlockSourceImpl implements IBlockSource
{
	private final World worldObj;
	private final int xPos;
	private final int yPos;
	private final int zPos;
	
	public BlockSourceImpl(World p_i5025_1_, int p_i5025_2_, int p_i5025_3_, int p_i5025_4_)
	{
		worldObj = p_i5025_1_;
		xPos = p_i5025_2_;
		yPos = p_i5025_3_;
		zPos = p_i5025_4_;
	}
	
	@Override public int getBlockMetadata()
	{
		return worldObj.getBlockMetadata(xPos, yPos, zPos);
	}
	
	@Override public TileEntity getBlockTileEntity()
	{
		return worldObj.getBlockTileEntity(xPos, yPos, zPos);
	}
	
	@Override public World getWorld()
	{
		return worldObj;
	}
	
	@Override public double getX()
	{
		return xPos + 0.5D;
	}
	
	@Override public int getXInt()
	{
		return xPos;
	}
	
	@Override public double getY()
	{
		return yPos + 0.5D;
	}
	
	@Override public int getYInt()
	{
		return yPos;
	}
	
	@Override public double getZ()
	{
		return zPos + 0.5D;
	}
	
	@Override public int getZInt()
	{
		return zPos;
	}
}