package net.minecraft.src;

public class ItemBucketMilk extends Item
{
	public ItemBucketMilk(int p_i3669_1_)
	{
		super(p_i3669_1_);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.drink;
	}
	
	@Override public int getMaxItemUseDuration(ItemStack p_77626_1_)
	{
		return 32;
	}
	
	@Override public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	{
		if(!p_77654_3_.capabilities.isCreativeMode)
		{
			--p_77654_1_.stackSize;
		}
		if(!p_77654_2_.isRemote)
		{
			p_77654_3_.clearActivePotions();
		}
		return p_77654_1_.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : p_77654_1_;
	}
	
	@Override public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	{
		p_77659_3_.setItemInUse(p_77659_1_, getMaxItemUseDuration(p_77659_1_));
		return p_77659_1_;
	}
}