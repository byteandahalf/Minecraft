package net.minecraft.src;

public class ItemSaddle extends Item
{
	public ItemSaddle(int par1)
	{
		super(par1);
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabTransport);
	}
	
	@Override public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		itemInteractionForEntity(par1ItemStack, par2EntityLiving);
		return true;
	}
	
	@Override public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
	{
		if(par2EntityLiving instanceof EntityPig)
		{
			EntityPig var3 = (EntityPig) par2EntityLiving;
			if(!var3.getSaddled() && !var3.isChild())
			{
				var3.setSaddled(true);
				--par1ItemStack.stackSize;
			}
			return true;
		} else return false;
	}
}
