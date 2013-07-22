package net.minecraft.src;

import java.util.ArrayList;

public class RecipesArmorDyes implements IRecipe
{
	@Override public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		ItemStack var2 = null;
		int[] var3 = new int[3];
		int var4 = 0;
		int var5 = 0;
		ItemArmor var6 = null;
		int var7;
		int var9;
		float var10;
		float var11;
		int var17;
		for(var7 = 0; var7 < p_77572_1_.getSizeInventory(); ++var7)
		{
			ItemStack var8 = p_77572_1_.getStackInSlot(var7);
			if(var8 != null)
			{
				if(var8.getItem() instanceof ItemArmor)
				{
					var6 = (ItemArmor) var8.getItem();
					if(var6.getArmorMaterial() != EnumArmorMaterial.CLOTH || var2 != null) return null;
					var2 = var8.copy();
					var2.stackSize = 1;
					if(var6.hasColor(var8))
					{
						var9 = var6.getColor(var2);
						var10 = (var9 >> 16 & 255) / 255.0F;
						var11 = (var9 >> 8 & 255) / 255.0F;
						float var12 = (var9 & 255) / 255.0F;
						var4 = (int) (var4 + Math.max(var10, Math.max(var11, var12)) * 255.0F);
						var3[0] = (int) (var3[0] + var10 * 255.0F);
						var3[1] = (int) (var3[1] + var11 * 255.0F);
						var3[2] = (int) (var3[2] + var12 * 255.0F);
						++var5;
					}
				} else
				{
					if(var8.itemID != Item.dyePowder.itemID) return null;
					float[] var14 = EntitySheep.fleeceColorTable[BlockCloth.getBlockFromDye(var8.getItemDamage())];
					int var16 = (int) (var14[0] * 255.0F);
					int var15 = (int) (var14[1] * 255.0F);
					var17 = (int) (var14[2] * 255.0F);
					var4 += Math.max(var16, Math.max(var15, var17));
					var3[0] += var16;
					var3[1] += var15;
					var3[2] += var17;
					++var5;
				}
			}
		}
		if(var6 == null) return null;
		else
		{
			var7 = var3[0] / var5;
			int var13 = var3[1] / var5;
			var9 = var3[2] / var5;
			var10 = (float) var4 / (float) var5;
			var11 = Math.max(var7, Math.max(var13, var9));
			var7 = (int) (var7 * var10 / var11);
			var13 = (int) (var13 * var10 / var11);
			var9 = (int) (var9 * var10 / var11);
			var17 = (var7 << 8) + var13;
			var17 = (var17 << 8) + var9;
			var6.func_82813_b(var2, var17);
			return var2;
		}
	}
	
	@Override public ItemStack getRecipeOutput()
	{
		return null;
	}
	
	@Override public int getRecipeSize()
	{
		return 10;
	}
	
	@Override public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_)
	{
		ItemStack var3 = null;
		ArrayList var4 = new ArrayList();
		for(int var5 = 0; var5 < p_77569_1_.getSizeInventory(); ++var5)
		{
			ItemStack var6 = p_77569_1_.getStackInSlot(var5);
			if(var6 != null)
			{
				if(var6.getItem() instanceof ItemArmor)
				{
					ItemArmor var7 = (ItemArmor) var6.getItem();
					if(var7.getArmorMaterial() != EnumArmorMaterial.CLOTH || var3 != null) return false;
					var3 = var6;
				} else
				{
					if(var6.itemID != Item.dyePowder.itemID) return false;
					var4.add(var6);
				}
			}
		}
		return var3 != null && !var4.isEmpty();
	}
}
