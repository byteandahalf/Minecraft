package net.minecraft.src;

public class ItemBed extends Item
{
	public ItemBed(int p_i3620_1_)
	{
		super(p_i3620_1_);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if(p_77648_3_.isRemote) return true;
		else if(p_77648_7_ != 1) return false;
		else
		{
			++p_77648_5_;
			BlockBed var11 = (BlockBed) Block.bed;
			int var12 = MathHelper.floor_double(p_77648_2_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			byte var13 = 0;
			byte var14 = 0;
			if(var12 == 0)
			{
				var14 = 1;
			}
			if(var12 == 1)
			{
				var13 = -1;
			}
			if(var12 == 2)
			{
				var14 = -1;
			}
			if(var12 == 3)
			{
				var13 = 1;
			}
			if(p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_ + var13, p_77648_5_, p_77648_6_ + var14, p_77648_7_, p_77648_1_))
			{
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_3_.isAirBlock(p_77648_4_ + var13, p_77648_5_, p_77648_6_ + var14) && p_77648_3_.doesBlockHaveSolidTopSurface(p_77648_4_, p_77648_5_ - 1, p_77648_6_) && p_77648_3_.doesBlockHaveSolidTopSurface(p_77648_4_ + var13, p_77648_5_ - 1, p_77648_6_ + var14))
				{
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, var11.blockID, var12, 3);
					if(p_77648_3_.getBlockId(p_77648_4_, p_77648_5_, p_77648_6_) == var11.blockID)
					{
						p_77648_3_.setBlock(p_77648_4_ + var13, p_77648_5_, p_77648_6_ + var14, var11.blockID, var12 + 8, 3);
					}
					--p_77648_1_.stackSize;
					return true;
				} else return false;
			} else return false;
		}
	}
}
