package net.minecraft.src;

public enum EnumEntitySize
{
	SIZE_1, SIZE_2, SIZE_3, SIZE_4, SIZE_5, SIZE_6;
	public int multiplyBy32AndRound(double p_75630_1_)
	{
		double var3 = p_75630_1_ - (MathHelper.floor_double(p_75630_1_) + 0.5D);
		switch(EnumEntitySizeHelper.field_96565_a[ordinal()])
		{
			case 1:
				if(var3 < 0.0D)
				{
					if(var3 < -0.3125D) return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
				} else if(var3 < 0.3125D) return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
				return MathHelper.floor_double(p_75630_1_ * 32.0D);
			case 2:
				if(var3 < 0.0D)
				{
					if(var3 < -0.3125D) return MathHelper.floor_double(p_75630_1_ * 32.0D);
				} else if(var3 < 0.3125D) return MathHelper.floor_double(p_75630_1_ * 32.0D);
				return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
			case 3:
				if(var3 > 0.0D) return MathHelper.floor_double(p_75630_1_ * 32.0D);
				return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
			case 4:
				if(var3 < 0.0D)
				{
					if(var3 < -0.1875D) return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
				} else if(var3 < 0.1875D) return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
				return MathHelper.floor_double(p_75630_1_ * 32.0D);
			case 5:
				if(var3 < 0.0D)
				{
					if(var3 < -0.1875D) return MathHelper.floor_double(p_75630_1_ * 32.0D);
				} else if(var3 < 0.1875D) return MathHelper.floor_double(p_75630_1_ * 32.0D);
				return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
			case 6:
			default:
				if(var3 > 0.0D) return MathHelper.ceiling_double_int(p_75630_1_ * 32.0D);
				else return MathHelper.floor_double(p_75630_1_ * 32.0D);
		}
	}
}