package net.minecraft.src;


public class RenderOcelot extends RenderLiving
{
	public RenderOcelot(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}
	
	@Override public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderLivingOcelot((EntityOcelot) par1Entity, par2, par4, par6, par8, par9);
	}
	
	@Override public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderLivingOcelot((EntityOcelot) par1EntityLiving, par2, par4, par6, par8, par9);
	}
	
	@Override protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
	{
		preRenderOcelot((EntityOcelot) par1EntityLiving, par2);
	}
	
	protected void preRenderOcelot(EntityOcelot par1EntityOcelot, float par2)
	{
		super.preRenderCallback(par1EntityOcelot, par2);
		if(par1EntityOcelot.isTamed())
		{
			GL11.glScalef(0.8F, 0.8F, 0.8F);
		}
	}
	
	public void renderLivingOcelot(EntityOcelot par1EntityOcelot, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityOcelot, par2, par4, par6, par8, par9);
	}
}
