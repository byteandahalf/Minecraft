package net.minecraft.src;


public class EntityLargeExplodeFX extends EntityFX
{
	private static final ResourceLocation field_110127_a = new ResourceLocation("textures/entity/explosion.png");
	private int field_70581_a;
	private int field_70584_aq;
	private TextureManager theRenderEngine;
	private float field_70582_as;
	
	public EntityLargeExplodeFX(TextureManager par1TextureManager, World par2World, double par3, double par5, double par7, double par9, double par11, double par13)
	{
		super(par2World, par3, par5, par7, 0.0D, 0.0D, 0.0D);
		theRenderEngine = par1TextureManager;
		field_70584_aq = 6 + rand.nextInt(4);
		particleRed = particleGreen = particleBlue = rand.nextFloat() * 0.6F + 0.4F;
		field_70582_as = 1.0F - (float) par9 * 0.5F;
	}
	
	@Override public int getBrightnessForRender(float par1)
	{
		return 61680;
	}
	
	@Override public int getFXLayer()
	{
		return 3;
	}
	
	@Override public void onUpdate()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		++field_70581_a;
		if(field_70581_a == field_70584_aq)
		{
			setDead();
		}
	}
	
	@Override public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		int var8 = (int) ((field_70581_a + par2) * 15.0F / field_70584_aq);
		if(var8 <= 15)
		{
			theRenderEngine.func_110577_a(field_110127_a);
			float var9 = var8 % 4 / 4.0F;
			float var10 = var9 + 0.24975F;
			float var11 = var8 / 4 / 4.0F;
			float var12 = var11 + 0.24975F;
			float var13 = 2.0F * field_70582_as;
			float var14 = (float) (prevPosX + (posX - prevPosX) * par2 - interpPosX);
			float var15 = (float) (prevPosY + (posY - prevPosY) * par2 - interpPosY);
			float var16 = (float) (prevPosZ + (posZ - prevPosZ) * par2 - interpPosZ);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			RenderHelper.disableStandardItemLighting();
			par1Tessellator.startDrawingQuads();
			par1Tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1.0F);
			par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
			par1Tessellator.setBrightness(240);
			par1Tessellator.addVertexWithUV(var14 - par3 * var13 - par6 * var13, var15 - par4 * var13, var16 - par5 * var13 - par7 * var13, var10, var12);
			par1Tessellator.addVertexWithUV(var14 - par3 * var13 + par6 * var13, var15 + par4 * var13, var16 - par5 * var13 + par7 * var13, var10, var11);
			par1Tessellator.addVertexWithUV(var14 + par3 * var13 + par6 * var13, var15 + par4 * var13, var16 + par5 * var13 + par7 * var13, var9, var11);
			par1Tessellator.addVertexWithUV(var14 + par3 * var13 - par6 * var13, var15 - par4 * var13, var16 + par5 * var13 - par7 * var13, var9, var12);
			par1Tessellator.draw();
			GL11.glPolygonOffset(0.0F, 0.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
	}
}
