package net.minecraft.src;

import java.util.Random;

public class MathHelper
{
	private static float[] SIN_TABLE = new float[65536];
	
	public static float abs(float p_76135_0_)
	{
		return p_76135_0_ >= 0.0F ? p_76135_0_ : -p_76135_0_;
	}
	
	public static int abs_int(int p_76130_0_)
	{
		return p_76130_0_ >= 0 ? p_76130_0_ : -p_76130_0_;
	}
	
	public static double abs_max(double p_76132_0_, double p_76132_2_)
	{
		if(p_76132_0_ < 0.0D)
		{
			p_76132_0_ = -p_76132_0_;
		}
		if(p_76132_2_ < 0.0D)
		{
			p_76132_2_ = -p_76132_2_;
		}
		return p_76132_0_ > p_76132_2_ ? p_76132_0_ : p_76132_2_;
	}
	
	public static double average(long[] p_76127_0_)
	{
		long var1 = 0L;
		long[] var3 = p_76127_0_;
		int var4 = p_76127_0_.length;
		for(int var5 = 0; var5 < var4; ++var5)
		{
			long var6 = var3[var5];
			var1 += var6;
		}
		return (double) var1 / (double) p_76127_0_.length;
	}
	
	public static int bucketInt(int par0, int par1)
	{
		return par0 < 0 ? -((-par0 - 1) / par1) - 1 : par0 / par1;
	}
	
	public static int ceiling_double_int(double p_76143_0_)
	{
		int var2 = (int) p_76143_0_;
		return p_76143_0_ > var2 ? var2 + 1 : var2;
	}
	
	public static int ceiling_float_int(float p_76123_0_)
	{
		int var1 = (int) p_76123_0_;
		return p_76123_0_ > var1 ? var1 + 1 : var1;
	}
	
	public static float clamp_float(float p_76131_0_, float p_76131_1_, float p_76131_2_)
	{
		return p_76131_0_ < p_76131_1_ ? p_76131_1_ : p_76131_0_ > p_76131_2_ ? p_76131_2_ : p_76131_0_;
	}
	
	public static int clamp_int(int p_76125_0_, int p_76125_1_, int p_76125_2_)
	{
		return p_76125_0_ < p_76125_1_ ? p_76125_1_ : p_76125_0_ > p_76125_2_ ? p_76125_2_ : p_76125_0_;
	}
	
	public static final float cos(float p_76134_0_)
	{
		return SIN_TABLE[(int) (p_76134_0_ * 10430.378F + 16384.0F) & 65535];
	}
	
	public static int floor_double(double p_76128_0_)
	{
		int var2 = (int) p_76128_0_;
		return p_76128_0_ < var2 ? var2 - 1 : var2;
	}
	
	public static long floor_double_long(double p_76124_0_)
	{
		long var2 = (long) p_76124_0_;
		return p_76124_0_ < var2 ? var2 - 1L : var2;
	}
	
	public static int floor_float(float p_76141_0_)
	{
		int var1 = (int) p_76141_0_;
		return p_76141_0_ < var1 ? var1 - 1 : var1;
	}
	
	public static double func_82713_a(String p_82713_0_, double p_82713_1_, double p_82713_3_)
	{
		double var5 = p_82713_1_;
		try
		{
			var5 = Double.parseDouble(p_82713_0_);
		} catch(Throwable var8)
		{
			;
		}
		if(var5 < p_82713_3_)
		{
			var5 = p_82713_3_;
		}
		return var5;
	}
	
	public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_)
	{
		return p_82716_1_ >= p_82716_3_ ? p_82716_1_ : p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_;
	}
	
	public static int getRandomIntegerInRange(Random p_76136_0_, int p_76136_1_, int p_76136_2_)
	{
		return p_76136_1_ >= p_76136_2_ ? p_76136_1_ : p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + 1) + p_76136_1_;
	}
	
	public static double parseDoubleWithDefault(String p_82712_0_, double p_82712_1_)
	{
		double var3 = p_82712_1_;
		try
		{
			var3 = Double.parseDouble(p_82712_0_);
		} catch(Throwable var6)
		{
			;
		}
		return var3;
	}
	
	public static int parseIntWithDefault(String p_82715_0_, int p_82715_1_)
	{
		int var2 = p_82715_1_;
		try
		{
			var2 = Integer.parseInt(p_82715_0_);
		} catch(Throwable var4)
		{
			;
		}
		return var2;
	}
	
	public static int parseIntWithDefaultAndMax(String p_82714_0_, int p_82714_1_, int p_82714_2_)
	{
		int var3 = p_82714_1_;
		try
		{
			var3 = Integer.parseInt(p_82714_0_);
		} catch(Throwable var5)
		{
			;
		}
		if(var3 < p_82714_2_)
		{
			var3 = p_82714_2_;
		}
		return var3;
	}
	
	public static final float sin(float p_76126_0_)
	{
		return SIN_TABLE[(int) (p_76126_0_ * 10430.378F) & 65535];
	}
	
	public static final float sqrt_double(double p_76133_0_)
	{
		return (float) Math.sqrt(p_76133_0_);
	}
	
	public static final float sqrt_float(float p_76129_0_)
	{
		return (float) Math.sqrt(p_76129_0_);
	}
	
	public static boolean stringNullOrLengthZero(String par0Str)
	{
		return par0Str == null || par0Str.length() == 0;
	}
	
	public static int truncateDoubleToInt(double par0)
	{
		return (int) (par0 + 1024.0D) - 1024;
	}
	
	public static double wrapAngleTo180_double(double p_76138_0_)
	{
		p_76138_0_ %= 360.0D;
		if(p_76138_0_ >= 180.0D)
		{
			p_76138_0_ -= 360.0D;
		}
		if(p_76138_0_ < -180.0D)
		{
			p_76138_0_ += 360.0D;
		}
		return p_76138_0_;
	}
	
	public static float wrapAngleTo180_float(float p_76142_0_)
	{
		p_76142_0_ %= 360.0F;
		if(p_76142_0_ >= 180.0F)
		{
			p_76142_0_ -= 360.0F;
		}
		if(p_76142_0_ < -180.0F)
		{
			p_76142_0_ += 360.0F;
		}
		return p_76142_0_;
	}
	
	static
	{
		for(int var0 = 0; var0 < 65536; ++var0)
		{
			SIN_TABLE[var0] = (float) Math.sin(var0 * Math.PI * 2.0D / 65536.0D);
		}
	}
}
