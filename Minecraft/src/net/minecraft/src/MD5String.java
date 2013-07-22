package net.minecraft.src;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5String
{
	private String salt;
	
	public MD5String(String par1Str)
	{
		salt = par1Str;
	}
	
	public String getMD5String(String par1Str)
	{
		try
		{
			String var2 = salt + par1Str;
			MessageDigest var3 = MessageDigest.getInstance("MD5");
			var3.update(var2.getBytes(), 0, var2.length());
			return new BigInteger(1, var3.digest()).toString(16);
		} catch(NoSuchAlgorithmException var4)
		{
			throw new RuntimeException(var4);
		}
	}
}
