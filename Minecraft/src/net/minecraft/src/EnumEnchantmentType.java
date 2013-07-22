package net.minecraft.src;

public enum EnumEnchantmentType
{
	all, armor, armor_feet, armor_legs, armor_torso, armor_head, weapon, digger, bow;
	public boolean canEnchantItem(Item p_77557_1_)
	{
		if(this == all) return true;
		else if(p_77557_1_ instanceof ItemArmor)
		{
			if(this == armor) return true;
			else
			{
				ItemArmor var2 = (ItemArmor) p_77557_1_;
				return var2.armorType == 0 ? this == armor_head : var2.armorType == 2 ? this == armor_legs : var2.armorType == 1 ? this == armor_torso : var2.armorType == 3 ? this == armor_feet : false;
			}
		} else return p_77557_1_ instanceof ItemSword ? this == weapon : p_77557_1_ instanceof ItemTool ? this == digger : p_77557_1_ instanceof ItemBow ? this == bow : false;
	}
}
