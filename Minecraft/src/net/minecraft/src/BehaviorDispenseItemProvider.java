package net.minecraft.src;

final class BehaviorDispenseItemProvider implements IBehaviorDispenseItem
{
	@Override public ItemStack dispense(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
	{
		return par2ItemStack;
	}
}
