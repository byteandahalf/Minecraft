package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase
{
	private List tagList = new ArrayList();
	private byte tagType;
	
	public NBTTagList()
	{
		super("");
	}
	
	public NBTTagList(String par1Str)
	{
		super(par1Str);
	}
	
	public void appendTag(NBTBase par1NBTBase)
	{
		tagType = par1NBTBase.getId();
		tagList.add(par1NBTBase);
	}
	
	@Override public NBTBase copy()
	{
		NBTTagList var1 = new NBTTagList(getName());
		var1.tagType = tagType;
		Iterator var2 = tagList.iterator();
		while(var2.hasNext())
		{
			NBTBase var3 = (NBTBase) var2.next();
			NBTBase var4 = var3.copy();
			var1.tagList.add(var4);
		}
		return var1;
	}
	
	@Override public boolean equals(Object par1Obj)
	{
		if(super.equals(par1Obj))
		{
			NBTTagList var2 = (NBTTagList) par1Obj;
			if(tagType == var2.tagType) return tagList.equals(var2.tagList);
		}
		return false;
	}
	
	@Override public byte getId()
	{
		return (byte) 9;
	}
	
	@Override public int hashCode()
	{
		return super.hashCode() ^ tagList.hashCode();
	}
	
	@Override void load(DataInput par1DataInput, int par2) throws IOException
	{
		if(par2 > 512) throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
		else
		{
			tagType = par1DataInput.readByte();
			int var3 = par1DataInput.readInt();
			tagList = new ArrayList();
			for(int var4 = 0; var4 < var3; ++var4)
			{
				NBTBase var5 = NBTBase.newTag(tagType, (String) null);
				var5.load(par1DataInput, par2 + 1);
				tagList.add(var5);
			}
		}
	}
	
	public NBTBase removeTag(int par1)
	{
		return (NBTBase) tagList.remove(par1);
	}
	
	public NBTBase tagAt(int par1)
	{
		return (NBTBase) tagList.get(par1);
	}
	
	public int tagCount()
	{
		return tagList.size();
	}
	
	@Override public String toString()
	{
		return "" + tagList.size() + " entries of type " + NBTBase.getTagName(tagType);
	}
	
	@Override void write(DataOutput par1DataOutput) throws IOException
	{
		if(!tagList.isEmpty())
		{
			tagType = ((NBTBase) tagList.get(0)).getId();
		} else
		{
			tagType = 1;
		}
		par1DataOutput.writeByte(tagType);
		par1DataOutput.writeInt(tagList.size());
		for(int var2 = 0; var2 < tagList.size(); ++var2)
		{
			((NBTBase) tagList.get(var2)).write(par1DataOutput);
		}
	}
}
