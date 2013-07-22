package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet208SetDisplayObjective extends Packet
{
	public int scoreboardPosition;
	public String scoreName;
	
	public Packet208SetDisplayObjective()
	{
	}
	
	public Packet208SetDisplayObjective(int par1, ScoreObjective par2ScoreObjective)
	{
		scoreboardPosition = par1;
		if(par2ScoreObjective == null)
		{
			scoreName = "";
		} else
		{
			scoreName = par2ScoreObjective.getName();
		}
	}
	
	@Override public int getPacketSize()
	{
		return 3 + scoreName.length();
	}
	
	@Override public void processPacket(NetHandler par1NetHandler)
	{
		par1NetHandler.handleSetDisplayObjective(this);
	}
	
	@Override public void readPacketData(DataInputStream par1DataInputStream) throws IOException
	{
		scoreboardPosition = par1DataInputStream.readByte();
		scoreName = readString(par1DataInputStream, 16);
	}
	
	@Override public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
	{
		par1DataOutputStream.writeByte(scoreboardPosition);
		writeString(scoreName, par1DataOutputStream);
	}
}
