package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.server.MinecraftServer;

public class SaveHandler implements ISaveHandler, IPlayerFileData
{
	private final File worldDirectory;
	private final File playersDirectory;
	private final File mapDataDir;
	private final long initializationTime = System.currentTimeMillis();
	private final String saveDirectoryName;
	
	public SaveHandler(File p_i3912_1_, String p_i3912_2_, boolean p_i3912_3_)
	{
		worldDirectory = new File(p_i3912_1_, p_i3912_2_);
		worldDirectory.mkdirs();
		playersDirectory = new File(worldDirectory, "players");
		mapDataDir = new File(worldDirectory, "data");
		mapDataDir.mkdirs();
		saveDirectoryName = p_i3912_2_;
		if(p_i3912_3_)
		{
			playersDirectory.mkdirs();
		}
		setSessionLock();
	}
	
	@Override public void checkSessionLock() throws MinecraftException
	{
		try
		{
			File var1 = new File(worldDirectory, "session.lock");
			DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
			try
			{
				if(var2.readLong() != initializationTime) throw new MinecraftException("The save is being accessed from another location, aborting");
			} finally
			{
				var2.close();
			}
		} catch(IOException var7)
		{
			throw new MinecraftException("Failed to check session lock, aborting");
		}
	}
	
	@Override public void flush()
	{
	}
	
	@Override public String[] getAvailablePlayerDat()
	{
		String[] var1 = playersDirectory.list();
		for(int var2 = 0; var2 < var1.length; ++var2)
		{
			if(var1[var2].endsWith(".dat"))
			{
				var1[var2] = var1[var2].substring(0, var1[var2].length() - 4);
			}
		}
		return var1;
	}
	
	@Override public IChunkLoader getChunkLoader(WorldProvider p_75763_1_)
	{
		throw new RuntimeException("Old Chunk Storage is no longer supported.");
	}
	
	@Override public File getMapFileFromName(String p_75758_1_)
	{
		return new File(mapDataDir, p_75758_1_ + ".dat");
	}
	
	public NBTTagCompound getPlayerData(String p_75764_1_)
	{
		try
		{
			File var2 = new File(playersDirectory, p_75764_1_ + ".dat");
			if(var2.exists()) return CompressedStreamTools.readCompressed(new FileInputStream(var2));
		} catch(Exception var3)
		{
			MinecraftServer.getServer().getLogAgent().logWarning("Failed to load player data for " + p_75764_1_);
		}
		return null;
	}
	
	@Override public IPlayerFileData getSaveHandler()
	{
		return this;
	}
	
	protected File getWorldDirectory()
	{
		return worldDirectory;
	}
	
	@Override public String getWorldDirectoryName()
	{
		return saveDirectoryName;
	}
	
	@Override public WorldInfo loadWorldInfo()
	{
		File var1 = new File(worldDirectory, "level.dat");
		NBTTagCompound var2;
		NBTTagCompound var3;
		if(var1.exists())
		{
			try
			{
				var2 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
				var3 = var2.getCompoundTag("Data");
				return new WorldInfo(var3);
			} catch(Exception var5)
			{
				var5.printStackTrace();
			}
		}
		var1 = new File(worldDirectory, "level.dat_old");
		if(var1.exists())
		{
			try
			{
				var2 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
				var3 = var2.getCompoundTag("Data");
				return new WorldInfo(var3);
			} catch(Exception var4)
			{
				var4.printStackTrace();
			}
		}
		return null;
	}
	
	@Override public NBTTagCompound readPlayerData(EntityPlayer p_75752_1_)
	{
		NBTTagCompound var2 = getPlayerData(p_75752_1_.username);
		if(var2 != null)
		{
			p_75752_1_.readFromNBT(var2);
		}
		return var2;
	}
	
	@Override public void saveWorldInfo(WorldInfo p_75761_1_)
	{
		NBTTagCompound var2 = p_75761_1_.getNBTTagCompound();
		NBTTagCompound var3 = new NBTTagCompound();
		var3.setTag("Data", var2);
		try
		{
			File var4 = new File(worldDirectory, "level.dat_new");
			File var5 = new File(worldDirectory, "level.dat_old");
			File var6 = new File(worldDirectory, "level.dat");
			CompressedStreamTools.writeCompressed(var3, new FileOutputStream(var4));
			if(var5.exists())
			{
				var5.delete();
			}
			var6.renameTo(var5);
			if(var6.exists())
			{
				var6.delete();
			}
			var4.renameTo(var6);
			if(var4.exists())
			{
				var4.delete();
			}
		} catch(Exception var7)
		{
			var7.printStackTrace();
		}
	}
	
	@Override public void saveWorldInfoWithPlayer(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_)
	{
		NBTTagCompound var3 = p_75755_1_.cloneNBTCompound(p_75755_2_);
		NBTTagCompound var4 = new NBTTagCompound();
		var4.setTag("Data", var3);
		try
		{
			File var5 = new File(worldDirectory, "level.dat_new");
			File var6 = new File(worldDirectory, "level.dat_old");
			File var7 = new File(worldDirectory, "level.dat");
			CompressedStreamTools.writeCompressed(var4, new FileOutputStream(var5));
			if(var6.exists())
			{
				var6.delete();
			}
			var7.renameTo(var6);
			if(var7.exists())
			{
				var7.delete();
			}
			var5.renameTo(var7);
			if(var5.exists())
			{
				var5.delete();
			}
		} catch(Exception var8)
		{
			var8.printStackTrace();
		}
	}
	
	private void setSessionLock()
	{
		try
		{
			File var1 = new File(worldDirectory, "session.lock");
			DataOutputStream var2 = new DataOutputStream(new FileOutputStream(var1));
			try
			{
				var2.writeLong(initializationTime);
			} finally
			{
				var2.close();
			}
		} catch(IOException var7)
		{
			var7.printStackTrace();
			throw new RuntimeException("Failed to check session lock, aborting");
		}
	}
	
	@Override public void writePlayerData(EntityPlayer p_75753_1_)
	{
		try
		{
			NBTTagCompound var2 = new NBTTagCompound();
			p_75753_1_.writeToNBT(var2);
			File var3 = new File(playersDirectory, p_75753_1_.username + ".dat.tmp");
			File var4 = new File(playersDirectory, p_75753_1_.username + ".dat");
			CompressedStreamTools.writeCompressed(var2, new FileOutputStream(var3));
			if(var4.exists())
			{
				var4.delete();
			}
			var3.renameTo(var4);
		} catch(Exception var5)
		{
			MinecraftServer.getServer().getLogAgent().logWarning("Failed to save player data for " + p_75753_1_.username);
		}
	}
}
