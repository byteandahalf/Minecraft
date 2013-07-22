package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageWell extends ComponentVillage
{
	private final boolean field_74924_a = true;
	private int averageGroundLevel = -1;
	
	public ComponentVillageWell(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, int par4, int par5)
	{
		super(par1ComponentVillageStartPiece, par2);
		coordBaseMode = par3Random.nextInt(4);
		switch(coordBaseMode)
		{
			case 0:
			case 2:
				boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
				break;
			default:
				boundingBox = new StructureBoundingBox(par4, 64, par5, par4 + 6 - 1, 78, par5 + 6 - 1);
		}
	}
	
	@Override public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
	{
		if(averageGroundLevel < 0)
		{
			averageGroundLevel = getAverageGroundLevel(par1World, par3StructureBoundingBox);
			if(averageGroundLevel < 0) return true;
			boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + 3, 0);
		}
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 4, 12, 4, Block.cobblestone.blockID, Block.waterMoving.blockID, false);
		placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 13, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 14, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 13, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 14, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 13, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 14, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 13, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 14, 4, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 15, 1, 4, 15, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		for(int var4 = 0; var4 <= 5; ++var4)
		{
			for(int var5 = 0; var5 <= 5; ++var5)
			{
				if(var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5)
				{
					placeBlockAtCurrentPosition(par1World, Block.gravel.blockID, 0, var5, 11, var4, par3StructureBoundingBox);
					clearCurrentPositionBlocksUpwards(par1World, var5, 12, var4, par3StructureBoundingBox);
				}
			}
		}
		return true;
	}
	
	@Override public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
	{
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, boundingBox.minX - 1, boundingBox.maxY - 4, boundingBox.minZ + 1, 1, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, boundingBox.maxX + 1, boundingBox.maxY - 4, boundingBox.minZ + 1, 3, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, boundingBox.minX + 1, boundingBox.maxY - 4, boundingBox.minZ - 1, 2, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, boundingBox.minX + 1, boundingBox.maxY - 4, boundingBox.maxZ + 1, 0, getComponentType());
	}
}
