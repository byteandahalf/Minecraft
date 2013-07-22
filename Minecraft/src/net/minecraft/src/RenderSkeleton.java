package net.minecraft.src;


public class RenderSkeleton extends RenderBiped
{
	public RenderSkeleton()
	{
		super(new ModelSkeleton(), 0.5F);
	}
	
	@Override protected void func_82422_c()
	{
		GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
	}
	
	protected void func_82438_a(EntitySkeleton par1EntitySkeleton, float par2)
	{
		if(par1EntitySkeleton.getSkeletonType() == 1)
		{
			GL11.glScalef(1.2F, 1.2F, 1.2F);
		}
	}
	
	@Override protected void preRenderCallback(EntityLiving par1EntityLivingBase, float par2)
	{
		func_82438_a((EntitySkeleton) par1EntityLivingBase, par2);
	}
}
