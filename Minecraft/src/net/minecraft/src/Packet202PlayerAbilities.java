package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet202PlayerAbilities extends Packet
{
	private boolean disableDamage = false;
	private boolean isFlying = false;
	private boolean allowFlying = false;
	private boolean isCreativeMode = false;
	private float flySpeed;
	private float walkSpeed;
	
	public Packet202PlayerAbilities()
	{
	}
	
	public Packet202PlayerAbilities(PlayerCapabilities par1PlayerCapabilities)
	{
		setDisableDamage(par1PlayerCapabilities.disableDamage);
		setFlying(par1PlayerCapabilities.isFlying);
		setAllowFlying(par1PlayerCapabilities.allowFlying);
		setCreativeMode(par1PlayerCapabilities.isCreativeMode);
		setFlySpeed(par1PlayerCapabilities.getFlySpeed());
		setWalkSpeed(par1PlayerCapabilities.getWalkSpeed());
	}
	
	@Override public boolean containsSameEntityIDAs(Packet par1Packet)
	{
		return true;
	}
	
	public boolean getAllowFlying()
	{
		return allowFlying;
	}
	
	public boolean getDisableDamage()
	{
		return disableDamage;
	}
	
	public boolean getFlying()
	{
		return isFlying;
	}
	
	public float getFlySpeed()
	{
		return flySpeed;
	}
	
	@Override public int getPacketSize()
	{
		return 2;
	}
	
	public float getWalkSpeed()
	{
		return walkSpeed;
	}
	
	public boolean isCreativeMode()
	{
		return isCreativeMode;
	}
	
	@Override public boolean isRealPacket()
	{
		return true;
	}
	
	@Override public void processPacket(NetHandler par1NetHandler)
	{
		par1NetHandler.handlePlayerAbilities(this);
	}
	
	@Override public void readPacketData(DataInputStream par1DataInputStream) throws IOException
	{
		byte var2 = par1DataInputStream.readByte();
		setDisableDamage((var2 & 1) > 0);
		setFlying((var2 & 2) > 0);
		setAllowFlying((var2 & 4) > 0);
		setCreativeMode((var2 & 8) > 0);
		setFlySpeed(par1DataInputStream.readByte() / 255.0F);
		setWalkSpeed(par1DataInputStream.readByte() / 255.0F);
	}
	
	public void setAllowFlying(boolean par1)
	{
		allowFlying = par1;
	}
	
	public void setCreativeMode(boolean par1)
	{
		isCreativeMode = par1;
	}
	
	public void setDisableDamage(boolean par1)
	{
		disableDamage = par1;
	}
	
	public void setFlying(boolean par1)
	{
		isFlying = par1;
	}
	
	public void setFlySpeed(float par1)
	{
		flySpeed = par1;
	}
	
	public void setWalkSpeed(float par1)
	{
		walkSpeed = par1;
	}
	
	@Override public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
	{
		byte var2 = 0;
		if(getDisableDamage())
		{
			var2 = (byte) (var2 | 1);
		}
		if(getFlying())
		{
			var2 = (byte) (var2 | 2);
		}
		if(getAllowFlying())
		{
			var2 = (byte) (var2 | 4);
		}
		if(isCreativeMode())
		{
			var2 = (byte) (var2 | 8);
		}
		par1DataOutputStream.writeByte(var2);
		par1DataOutputStream.writeByte((int) (flySpeed * 255.0F));
		par1DataOutputStream.writeByte((int) (walkSpeed * 255.0F));
	}
}
