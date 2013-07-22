package net.minecraft.src;

final class EntitySelectorInventory implements IEntitySelector
{
	@Override public boolean isEntityApplicable(Entity par1Entity)
	{
		return par1Entity instanceof IInventory && par1Entity.isEntityAlive();
	}
}
