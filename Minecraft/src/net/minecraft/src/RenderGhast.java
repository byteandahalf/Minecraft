package net.minecraft.src;


public class RenderGhast extends RenderLiving
{
	private static final ResourceLocation field_110869_a = new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation field_110868_f = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");
	
	public RenderGhast()
	{
		super(new ModelGhast(), 0.5F);
	}
	
	@Override protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return func_110867_a((EntityGhast) par1Entity);
	}
	
	protected ResourceLocation func_110867_a(EntityGhast par1EntityGhast)
	{
		return par1EntityGhast.func_110182_bF() ? field_110868_f : field_110869_a;
	}
	
	@Override protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		preRenderGhast((EntityGhast) par1EntityLivingBase, par2);
	}
	
	protected void preRenderGhast(EntityGhast par1EntityGhast, float par2)
	{
		float var4 = (par1EntityGhast.prevAttackCounter + (par1EntityGhast.attackCounter - par1EntityGhast.prevAttackCounter) * par2) / 20.0F;
		if(var4 < 0.0F)
		{
			var4 = 0.0F;
		}
		var4 = 1.0F / (var4 * var4 * var4 * var4 * var4 * 2.0F + 1.0F);
		float var5 = (8.0F + var4) / 2.0F;
		float var6 = (8.0F + 1.0F / var4) / 2.0F;
		GL11.glScalef(var6, var5, var6);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
