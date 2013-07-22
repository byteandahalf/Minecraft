package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CraftingManager
{
	private static final CraftingManager instance = new CraftingManager();
	private List recipes = new ArrayList();
	
	private CraftingManager()
	{
		new RecipesTools().addRecipes(this);
		new RecipesWeapons().addRecipes(this);
		new RecipesIngots().addRecipes(this);
		new RecipesFood().addRecipes(this);
		new RecipesCrafting().addRecipes(this);
		new RecipesArmor().addRecipes(this);
		new RecipesDyes().addRecipes(this);
		recipes.add(new RecipesArmorDyes());
		recipes.add(new RecipesMapCloning());
		recipes.add(new RecipesMapExtending());
		recipes.add(new RecipeFireworks());
		addRecipe(new ItemStack(Item.paper, 3), new Object[] { "###", '#', Item.reed });
		addShapelessRecipe(new ItemStack(Item.book, 1), new Object[] { Item.paper, Item.paper, Item.paper, Item.leather });
		addShapelessRecipe(new ItemStack(Item.writableBook, 1), new Object[] { Item.book, new ItemStack(Item.dyePowder, 1, 0), Item.feather });
		addRecipe(new ItemStack(Block.fence, 2), new Object[] { "###", "###", '#', Item.stick });
		addRecipe(new ItemStack(Block.cobblestoneWall, 6, 0), new Object[] { "###", "###", '#', Block.cobblestone });
		addRecipe(new ItemStack(Block.cobblestoneWall, 6, 1), new Object[] { "###", "###", '#', Block.cobblestoneMossy });
		addRecipe(new ItemStack(Block.netherFence, 6), new Object[] { "###", "###", '#', Block.netherBrick });
		addRecipe(new ItemStack(Block.fenceGate, 1), new Object[] { "#W#", "#W#", '#', Item.stick, 'W', Block.planks });
		addRecipe(new ItemStack(Block.jukebox, 1), new Object[] { "###", "#X#", "###", '#', Block.planks, 'X', Item.diamond });
		addRecipe(new ItemStack(Block.music, 1), new Object[] { "###", "#X#", "###", '#', Block.planks, 'X', Item.redstone });
		addRecipe(new ItemStack(Block.bookShelf, 1), new Object[] { "###", "XXX", "###", '#', Block.planks, 'X', Item.book });
		addRecipe(new ItemStack(Block.blockSnow, 1), new Object[] { "##", "##", '#', Item.snowball });
		addRecipe(new ItemStack(Block.snow, 6), new Object[] { "###", '#', Block.blockSnow });
		addRecipe(new ItemStack(Block.blockClay, 1), new Object[] { "##", "##", '#', Item.clay });
		addRecipe(new ItemStack(Block.brick, 1), new Object[] { "##", "##", '#', Item.brick });
		addRecipe(new ItemStack(Block.glowStone, 1), new Object[] { "##", "##", '#', Item.glowstone });
		addRecipe(new ItemStack(Block.blockNetherQuartz, 1), new Object[] { "##", "##", '#', Item.netherQuartz });
		addRecipe(new ItemStack(Block.cloth, 1), new Object[] { "##", "##", '#', Item.silk });
		addRecipe(new ItemStack(Block.tnt, 1), new Object[] { "X#X", "#X#", "X#X", 'X', Item.gunpowder, '#', Block.sand });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[] { "###", '#', Block.cobblestone });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[] { "###", '#', Block.stone });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[] { "###", '#', Block.sandStone });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 4), new Object[] { "###", '#', Block.brick });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[] { "###", '#', Block.stoneBrick });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 6), new Object[] { "###", '#', Block.netherBrick });
		addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 7), new Object[] { "###", '#', Block.blockNetherQuartz });
		addRecipe(new ItemStack(Block.woodSingleSlab, 6, 0), new Object[] { "###", '#', new ItemStack(Block.planks, 1, 0) });
		addRecipe(new ItemStack(Block.woodSingleSlab, 6, 2), new Object[] { "###", '#', new ItemStack(Block.planks, 1, 2) });
		addRecipe(new ItemStack(Block.woodSingleSlab, 6, 1), new Object[] { "###", '#', new ItemStack(Block.planks, 1, 1) });
		addRecipe(new ItemStack(Block.woodSingleSlab, 6, 3), new Object[] { "###", '#', new ItemStack(Block.planks, 1, 3) });
		addRecipe(new ItemStack(Block.ladder, 3), new Object[] { "# #", "###", "# #", '#', Item.stick });
		addRecipe(new ItemStack(Item.doorWood, 1), new Object[] { "##", "##", "##", '#', Block.planks });
		addRecipe(new ItemStack(Block.trapdoor, 2), new Object[] { "###", "###", '#', Block.planks });
		addRecipe(new ItemStack(Item.doorIron, 1), new Object[] { "##", "##", "##", '#', Item.ingotIron });
		addRecipe(new ItemStack(Item.sign, 3), new Object[] { "###", "###", " X ", '#', Block.planks, 'X', Item.stick });
		addRecipe(new ItemStack(Item.cake, 1), new Object[] { "AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', Item.egg });
		addRecipe(new ItemStack(Item.sugar, 1), new Object[] { "#", '#', Item.reed });
		addRecipe(new ItemStack(Block.planks, 4, 0), new Object[] { "#", '#', new ItemStack(Block.wood, 1, 0) });
		addRecipe(new ItemStack(Block.planks, 4, 1), new Object[] { "#", '#', new ItemStack(Block.wood, 1, 1) });
		addRecipe(new ItemStack(Block.planks, 4, 2), new Object[] { "#", '#', new ItemStack(Block.wood, 1, 2) });
		addRecipe(new ItemStack(Block.planks, 4, 3), new Object[] { "#", '#', new ItemStack(Block.wood, 1, 3) });
		addRecipe(new ItemStack(Item.stick, 4), new Object[] { "#", "#", '#', Block.planks });
		addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "X", "#", 'X', Item.coal, '#', Item.stick });
		addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "X", "#", 'X', new ItemStack(Item.coal, 1, 1), '#', Item.stick });
		addRecipe(new ItemStack(Item.bowlEmpty, 4), new Object[] { "# #", " # ", '#', Block.planks });
		addRecipe(new ItemStack(Item.glassBottle, 3), new Object[] { "# #", " # ", '#', Block.glass });
		addRecipe(new ItemStack(Block.rail, 16), new Object[] { "X X", "X#X", "X X", 'X', Item.ingotIron, '#', Item.stick });
		addRecipe(new ItemStack(Block.railPowered, 6), new Object[] { "X X", "X#X", "XRX", 'X', Item.ingotGold, 'R', Item.redstone, '#', Item.stick });
		addRecipe(new ItemStack(Block.railActivator, 6), new Object[] { "XSX", "X#X", "XSX", 'X', Item.ingotIron, '#', Block.torchRedstoneActive, 'S', Item.stick });
		addRecipe(new ItemStack(Block.railDetector, 6), new Object[] { "X X", "X#X", "XRX", 'X', Item.ingotIron, 'R', Item.redstone, '#', Block.pressurePlateStone });
		addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[] { "# #", "###", '#', Item.ingotIron });
		addRecipe(new ItemStack(Item.cauldron, 1), new Object[] { "# #", "# #", "###", '#', Item.ingotIron });
		addRecipe(new ItemStack(Item.brewingStand, 1), new Object[] { " B ", "###", '#', Block.cobblestone, 'B', Item.blazeRod });
		addRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[] { "A", "B", 'A', Block.pumpkin, 'B', Block.torchWood });
		addRecipe(new ItemStack(Item.minecartCrate, 1), new Object[] { "A", "B", 'A', Block.chest, 'B', Item.minecartEmpty });
		addRecipe(new ItemStack(Item.minecartPowered, 1), new Object[] { "A", "B", 'A', Block.furnaceIdle, 'B', Item.minecartEmpty });
		addRecipe(new ItemStack(Item.minecartTnt, 1), new Object[] { "A", "B", 'A', Block.tnt, 'B', Item.minecartEmpty });
		addRecipe(new ItemStack(Item.minecartHopper, 1), new Object[] { "A", "B", 'A', Block.hopperBlock, 'B', Item.minecartEmpty });
		addRecipe(new ItemStack(Item.boat, 1), new Object[] { "# #", "###", '#', Block.planks });
		addRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[] { "# #", " # ", '#', Item.ingotIron });
		addRecipe(new ItemStack(Item.flowerPot, 1), new Object[] { "# #", " # ", '#', Item.brick });
		addRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[] { "A ", " B", 'A', Item.ingotIron, 'B', Item.flint });
		addRecipe(new ItemStack(Item.bread, 1), new Object[] { "###", '#', Item.wheat });
		addRecipe(new ItemStack(Block.stairsWoodOak, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 0) });
		addRecipe(new ItemStack(Block.stairsWoodBirch, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 2) });
		addRecipe(new ItemStack(Block.stairsWoodSpruce, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 1) });
		addRecipe(new ItemStack(Block.stairsWoodJungle, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 3) });
		addRecipe(new ItemStack(Item.fishingRod, 1), new Object[] { "  #", " #X", "# X", '#', Item.stick, 'X', Item.silk });
		addRecipe(new ItemStack(Item.carrotOnAStick, 1), new Object[] { "# ", " X", '#', Item.fishingRod, 'X', Item.carrot }).func_92100_c();
		addRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[] { "#  ", "## ", "###", '#', Block.cobblestone });
		addRecipe(new ItemStack(Block.stairsBrick, 4), new Object[] { "#  ", "## ", "###", '#', Block.brick });
		addRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[] { "#  ", "## ", "###", '#', Block.stoneBrick });
		addRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[] { "#  ", "## ", "###", '#', Block.netherBrick });
		addRecipe(new ItemStack(Block.stairsSandStone, 4), new Object[] { "#  ", "## ", "###", '#', Block.sandStone });
		addRecipe(new ItemStack(Block.stairsNetherQuartz, 4), new Object[] { "#  ", "## ", "###", '#', Block.blockNetherQuartz });
		addRecipe(new ItemStack(Item.painting, 1), new Object[] { "###", "#X#", "###", '#', Item.stick, 'X', Block.cloth });
		addRecipe(new ItemStack(Item.itemFrame, 1), new Object[] { "###", "#X#", "###", '#', Item.stick, 'X', Item.leather });
		addRecipe(new ItemStack(Item.appleGold, 1, 0), new Object[] { "###", "#X#", "###", '#', Item.goldNugget, 'X', Item.appleRed });
		addRecipe(new ItemStack(Item.appleGold, 1, 1), new Object[] { "###", "#X#", "###", '#', Block.blockGold, 'X', Item.appleRed });
		addRecipe(new ItemStack(Item.goldenCarrot, 1, 0), new Object[] { "###", "#X#", "###", '#', Item.goldNugget, 'X', Item.carrot });
		addRecipe(new ItemStack(Block.lever, 1), new Object[] { "X", "#", '#', Block.cobblestone, 'X', Item.stick });
		addRecipe(new ItemStack(Block.tripWireSource, 2), new Object[] { "I", "S", "#", '#', Block.planks, 'S', Item.stick, 'I', Item.ingotIron });
		addRecipe(new ItemStack(Block.torchRedstoneActive, 1), new Object[] { "X", "#", '#', Item.stick, 'X', Item.redstone });
		addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[] { "#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.redstone, 'I', Block.stone });
		addRecipe(new ItemStack(Item.comparator, 1), new Object[] { " # ", "#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.netherQuartz, 'I', Block.stone });
		addRecipe(new ItemStack(Item.pocketSundial, 1), new Object[] { " # ", "#X#", " # ", '#', Item.ingotGold, 'X', Item.redstone });
		addRecipe(new ItemStack(Item.compass, 1), new Object[] { " # ", "#X#", " # ", '#', Item.ingotIron, 'X', Item.redstone });
		addRecipe(new ItemStack(Item.emptyMap, 1), new Object[] { "###", "#X#", "###", '#', Item.paper, 'X', Item.compass });
		addRecipe(new ItemStack(Block.stoneButton, 1), new Object[] { "#", '#', Block.stone });
		addRecipe(new ItemStack(Block.woodenButton, 1), new Object[] { "#", '#', Block.planks });
		addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[] { "##", '#', Block.stone });
		addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[] { "##", '#', Block.planks });
		addRecipe(new ItemStack(Block.pressurePlateIron, 1), new Object[] { "##", '#', Item.ingotIron });
		addRecipe(new ItemStack(Block.pressurePlateGold, 1), new Object[] { "##", '#', Item.ingotGold });
		addRecipe(new ItemStack(Block.dispenser, 1), new Object[] { "###", "#X#", "#R#", '#', Block.cobblestone, 'X', Item.bow, 'R', Item.redstone });
		addRecipe(new ItemStack(Block.dropper, 1), new Object[] { "###", "# #", "#R#", '#', Block.cobblestone, 'R', Item.redstone });
		addRecipe(new ItemStack(Block.pistonBase, 1), new Object[] { "TTT", "#X#", "#R#", '#', Block.cobblestone, 'X', Item.ingotIron, 'R', Item.redstone, 'T', Block.planks });
		addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[] { "S", "P", 'S', Item.slimeBall, 'P', Block.pistonBase });
		addRecipe(new ItemStack(Item.bed, 1), new Object[] { "###", "XXX", '#', Block.cloth, 'X', Block.planks });
		addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[] { " B ", "D#D", "###", '#', Block.obsidian, 'B', Item.book, 'D', Item.diamond });
		addRecipe(new ItemStack(Block.anvil, 1), new Object[] { "III", " i ", "iii", 'I', Block.blockIron, 'i', Item.ingotIron });
		addShapelessRecipe(new ItemStack(Item.eyeOfEnder, 1), new Object[] { Item.enderPearl, Item.blazePowder });
		addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[] { Item.gunpowder, Item.blazePowder, Item.coal });
		addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[] { Item.gunpowder, Item.blazePowder, new ItemStack(Item.coal, 1, 1) });
		addRecipe(new ItemStack(Block.daylightSensor), new Object[] { "GGG", "QQQ", "WWW", 'G', Block.glass, 'Q', Item.netherQuartz, 'W', Block.woodSingleSlab });
		addRecipe(new ItemStack(Block.hopperBlock), new Object[] { "I I", "ICI", " I ", 'I', Item.ingotIron, 'C', Block.chest });
		Collections.sort(recipes, new RecipeSorter(this));
		System.out.println(recipes.size() + " recipes");
	}
	
	ShapedRecipes addRecipe(ItemStack p_92103_1_, Object ... p_92103_2_)
	{
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if(p_92103_2_[var4] instanceof String[])
		{
			String[] var7 = (String[]) p_92103_2_[var4++];
			for(String var9 : var7)
			{
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else
		{
			while(p_92103_2_[var4] instanceof String)
			{
				String var11 = (String) p_92103_2_[var4++];
				++var6;
				var5 = var11.length();
				var3 = var3 + var11;
			}
		}
		HashMap var12;
		for(var12 = new HashMap(); var4 < p_92103_2_.length; var4 += 2)
		{
			Character var13 = (Character) p_92103_2_[var4];
			ItemStack var14 = null;
			if(p_92103_2_[var4 + 1] instanceof Item)
			{
				var14 = new ItemStack((Item) p_92103_2_[var4 + 1]);
			} else if(p_92103_2_[var4 + 1] instanceof Block)
			{
				var14 = new ItemStack((Block) p_92103_2_[var4 + 1], 1, 32767);
			} else if(p_92103_2_[var4 + 1] instanceof ItemStack)
			{
				var14 = (ItemStack) p_92103_2_[var4 + 1];
			}
			var12.put(var13, var14);
		}
		ItemStack[] var15 = new ItemStack[var5 * var6];
		for(int var16 = 0; var16 < var5 * var6; ++var16)
		{
			char var10 = var3.charAt(var16);
			if(var12.containsKey(Character.valueOf(var10)))
			{
				var15[var16] = ((ItemStack) var12.get(Character.valueOf(var10))).copy();
			} else
			{
				var15[var16] = null;
			}
		}
		ShapedRecipes var17 = new ShapedRecipes(var5, var6, var15, p_92103_1_);
		recipes.add(var17);
		return var17;
	}
	
	void addShapelessRecipe(ItemStack p_77596_1_, Object ... p_77596_2_)
	{
		ArrayList var3 = new ArrayList();
		Object[] var4 = p_77596_2_;
		int var5 = p_77596_2_.length;
		for(int var6 = 0; var6 < var5; ++var6)
		{
			Object var7 = var4[var6];
			if(var7 instanceof ItemStack)
			{
				var3.add(((ItemStack) var7).copy());
			} else if(var7 instanceof Item)
			{
				var3.add(new ItemStack((Item) var7));
			} else
			{
				if(!(var7 instanceof Block)) throw new RuntimeException("Invalid shapeless recipy!");
				var3.add(new ItemStack((Block) var7));
			}
		}
		recipes.add(new ShapelessRecipes(p_77596_1_, var3));
	}
	
	public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World p_82787_2_)
	{
		int var3 = 0;
		ItemStack var4 = null;
		ItemStack var5 = null;
		int var6;
		for(var6 = 0; var6 < p_82787_1_.getSizeInventory(); ++var6)
		{
			ItemStack var7 = p_82787_1_.getStackInSlot(var6);
			if(var7 != null)
			{
				if(var3 == 0)
				{
					var4 = var7;
				}
				if(var3 == 1)
				{
					var5 = var7;
				}
				++var3;
			}
		}
		if(var3 == 2 && var4.itemID == var5.itemID && var4.stackSize == 1 && var5.stackSize == 1 && Item.itemsList[var4.itemID].isDamageable())
		{
			Item var11 = Item.itemsList[var4.itemID];
			int var13 = var11.getMaxDamage() - var4.getItemDamageForDisplay();
			int var8 = var11.getMaxDamage() - var5.getItemDamageForDisplay();
			int var9 = var13 + var8 + var11.getMaxDamage() * 5 / 100;
			int var10 = var11.getMaxDamage() - var9;
			if(var10 < 0)
			{
				var10 = 0;
			}
			return new ItemStack(var4.itemID, 1, var10);
		} else
		{
			for(var6 = 0; var6 < recipes.size(); ++var6)
			{
				IRecipe var12 = (IRecipe) recipes.get(var6);
				if(var12.matches(p_82787_1_, p_82787_2_)) return var12.getCraftingResult(p_82787_1_);
			}
			return null;
		}
	}
	
	public List getRecipeList()
	{
		return recipes;
	}
	
	public static final CraftingManager getInstance()
	{
		return instance;
	}
}
