package net.minecraft.src;

import java.util.List;
import java.util.Random;

abstract class ComponentVillage extends StructureComponent
{
	private int villagersSpawned;
	protected ComponentVillageStartPiece startPiece;
	
	protected ComponentVillage(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2)
	{
		super(par2);
		startPiece = par1ComponentVillageStartPiece;
	}
	
	@Override protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
	{
		int var8 = getBiomeSpecificBlock(par2, par3);
		int var9 = getBiomeSpecificBlockMetadata(par2, par3);
		super.fillCurrentPositionBlocksDownwards(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
	}
	
	@Override protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, boolean par11)
	{
		int var12 = getBiomeSpecificBlock(par9, 0);
		int var13 = getBiomeSpecificBlockMetadata(par9, 0);
		int var14 = getBiomeSpecificBlock(par10, 0);
		int var15 = getBiomeSpecificBlockMetadata(par10, 0);
		super.fillWithMetadataBlocks(par1World, par2StructureBoundingBox, par3, par4, par5, par6, par7, par8, var12, var13, var14, var15, par11);
	}
	
	protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox)
	{
		int var3 = 0;
		int var4 = 0;
		for(int var5 = boundingBox.minZ; var5 <= boundingBox.maxZ; ++var5)
		{
			for(int var6 = boundingBox.minX; var6 <= boundingBox.maxX; ++var6)
			{
				if(par2StructureBoundingBox.isVecInside(var6, 64, var5))
				{
					var3 += Math.max(par1World.getTopSolidOrLiquidBlock(var6, var5), par1World.provider.getAverageGroundLevel());
					++var4;
				}
			}
		}
		if(var4 == 0) return -1;
		else return var3 / var4;
	}
	
	protected int getBiomeSpecificBlock(int par1, int par2)
	{
		if(startPiece.inDesert)
		{
			if(par1 == Block.wood.blockID) return Block.sandStone.blockID;
			if(par1 == Block.cobblestone.blockID) return Block.sandStone.blockID;
			if(par1 == Block.planks.blockID) return Block.sandStone.blockID;
			if(par1 == Block.stairsWoodOak.blockID) return Block.stairsSandStone.blockID;
			if(par1 == Block.stairsCobblestone.blockID) return Block.stairsSandStone.blockID;
			if(par1 == Block.gravel.blockID) return Block.sandStone.blockID;
		}
		return par1;
	}
	
	protected int getBiomeSpecificBlockMetadata(int par1, int par2)
	{
		if(startPiece.inDesert)
		{
			if(par1 == Block.wood.blockID) return 0;
			if(par1 == Block.cobblestone.blockID) return 0;
			if(par1 == Block.planks.blockID) return 2;
		}
		return par2;
	}
	
	protected StructureComponent getNextComponentNN(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
	{
		switch(coordBaseMode)
		{
			case 0:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType());
			case 1:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType());
			case 2:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType());
			case 3:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType());
			default:
				return null;
		}
	}
	
	protected StructureComponent getNextComponentPP(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
	{
		switch(coordBaseMode)
		{
			case 0:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType());
			case 1:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType());
			case 2:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType());
			case 3:
				return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType());
			default:
				return null;
		}
	}
	
	protected int getVillagerType(int par1)
	{
		return 0;
	}
	
	@Override protected void placeBlockAtCurrentPosition(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
	{
		int var8 = getBiomeSpecificBlock(par2, par3);
		int var9 = getBiomeSpecificBlockMetadata(par2, par3);
		super.placeBlockAtCurrentPosition(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
	}
	
	protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
	{
		if(villagersSpawned < par6)
		{
			for(int var7 = villagersSpawned; var7 < par6; ++var7)
			{
				int var8 = getXWithOffset(par3 + var7, par5);
				int var9 = getYWithOffset(par4);
				int var10 = getZWithOffset(par3 + var7, par5);
				if(!par2StructureBoundingBox.isVecInside(var8, var9, var10))
				{
					break;
				}
				++villagersSpawned;
				EntityVillager var11 = new EntityVillager(par1World, getVillagerType(var7));
				var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(var11);
			}
		}
	}
	
	protected static boolean canVillageGoDeeper(StructureBoundingBox par0StructureBoundingBox)
	{
		return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
	}
}
