package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Packet51MapChunk extends Packet
{
	public int xCh;
	public int zCh;
	public int yChMin;
	public int yChMax;
	private byte[] chunkData;
	private byte[] compressedChunkData;
	public boolean includeInitialize;
	private int tempLength;
	private static byte[] temp = new byte[196864];
	
	public Packet51MapChunk()
	{
		isChunkDataPacket = true;
	}
	
	public Packet51MapChunk(Chunk par1Chunk, boolean par2, int par3)
	{
		isChunkDataPacket = true;
		xCh = par1Chunk.xPosition;
		zCh = par1Chunk.zPosition;
		includeInitialize = par2;
		Packet51MapChunkData var4 = getMapChunkData(par1Chunk, par2, par3);
		Deflater var5 = new Deflater(-1);
		yChMax = var4.chunkHasAddSectionFlag;
		yChMin = var4.chunkExistFlag;
		try
		{
			compressedChunkData = var4.compressedData;
			var5.setInput(var4.compressedData, 0, var4.compressedData.length);
			var5.finish();
			chunkData = new byte[var4.compressedData.length];
			tempLength = var5.deflate(chunkData);
		} finally
		{
			var5.end();
		}
	}
	
	public byte[] getCompressedChunkData()
	{
		return compressedChunkData;
	}
	
	@Override public int getPacketSize()
	{
		return 17 + tempLength;
	}
	
	@Override public void processPacket(NetHandler par1NetHandler)
	{
		par1NetHandler.handleMapChunk(this);
	}
	
	@Override public void readPacketData(DataInputStream par1DataInputStream) throws IOException
	{
		xCh = par1DataInputStream.readInt();
		zCh = par1DataInputStream.readInt();
		includeInitialize = par1DataInputStream.readBoolean();
		yChMin = par1DataInputStream.readShort();
		yChMax = par1DataInputStream.readShort();
		tempLength = par1DataInputStream.readInt();
		if(temp.length < tempLength)
		{
			temp = new byte[tempLength];
		}
		par1DataInputStream.readFully(temp, 0, tempLength);
		int var2 = 0;
		int var3;
		for(var3 = 0; var3 < 16; ++var3)
		{
			var2 += yChMin >> var3 & 1;
		}
		var3 = 12288 * var2;
		if(includeInitialize)
		{
			var3 += 256;
		}
		compressedChunkData = new byte[var3];
		Inflater var4 = new Inflater();
		var4.setInput(temp, 0, tempLength);
		try
		{
			var4.inflate(compressedChunkData);
		} catch(DataFormatException var9)
		{
			throw new IOException("Bad compressed data format");
		} finally
		{
			var4.end();
		}
	}
	
	@Override public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
	{
		par1DataOutputStream.writeInt(xCh);
		par1DataOutputStream.writeInt(zCh);
		par1DataOutputStream.writeBoolean(includeInitialize);
		par1DataOutputStream.writeShort((short) (yChMin & 65535));
		par1DataOutputStream.writeShort((short) (yChMax & 65535));
		par1DataOutputStream.writeInt(tempLength);
		par1DataOutputStream.write(chunkData, 0, tempLength);
	}
	
	public static Packet51MapChunkData getMapChunkData(Chunk par0Chunk, boolean par1, int par2)
	{
		int var3 = 0;
		ExtendedBlockStorage[] var4 = par0Chunk.getBlockStorageArray();
		int var5 = 0;
		Packet51MapChunkData var6 = new Packet51MapChunkData();
		byte[] var7 = temp;
		if(par1)
		{
			par0Chunk.sendUpdates = true;
		}
		int var8;
		for(var8 = 0; var8 < var4.length; ++var8)
		{
			if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && (par2 & 1 << var8) != 0)
			{
				var6.chunkExistFlag |= 1 << var8;
				if(var4[var8].getBlockMSBArray() != null)
				{
					var6.chunkHasAddSectionFlag |= 1 << var8;
					++var5;
				}
			}
		}
		for(var8 = 0; var8 < var4.length; ++var8)
		{
			if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && (par2 & 1 << var8) != 0)
			{
				byte[] var9 = var4[var8].getBlockLSBArray();
				System.arraycopy(var9, 0, var7, var3, var9.length);
				var3 += var9.length;
			}
		}
		NibbleArray var10;
		for(var8 = 0; var8 < var4.length; ++var8)
		{
			if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && (par2 & 1 << var8) != 0)
			{
				var10 = var4[var8].getMetadataArray();
				System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
				var3 += var10.data.length;
			}
		}
		for(var8 = 0; var8 < var4.length; ++var8)
		{
			if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && (par2 & 1 << var8) != 0)
			{
				var10 = var4[var8].getBlocklightArray();
				System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
				var3 += var10.data.length;
			}
		}
		if(!par0Chunk.worldObj.provider.hasNoSky)
		{
			for(var8 = 0; var8 < var4.length; ++var8)
			{
				if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && (par2 & 1 << var8) != 0)
				{
					var10 = var4[var8].getSkylightArray();
					System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
					var3 += var10.data.length;
				}
			}
		}
		if(var5 > 0)
		{
			for(var8 = 0; var8 < var4.length; ++var8)
			{
				if(var4[var8] != null && (!par1 || !var4[var8].isEmpty()) && var4[var8].getBlockMSBArray() != null && (par2 & 1 << var8) != 0)
				{
					var10 = var4[var8].getBlockMSBArray();
					System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
					var3 += var10.data.length;
				}
			}
		}
		if(par1)
		{
			byte[] var11 = par0Chunk.getBiomeArray();
			System.arraycopy(var11, 0, var7, var3, var11.length);
			var3 += var11.length;
		}
		var6.compressedData = new byte[var3];
		System.arraycopy(var7, 0, var6.compressedData, 0, var3);
		return var6;
	}
}
