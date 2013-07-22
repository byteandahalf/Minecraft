package net.minecraft.src;

public class ModelPig extends ModelQuadruped
{
	public ModelPig()
	{
		this(0.0F);
	}
	
	public ModelPig(float p_i3132_1_)
	{
		super(6, p_i3132_1_);
		head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, p_i3132_1_);
		field_78145_g = 4.0F;
	}
}
