package net.minecraft.src;

public class ModelBat extends ModelBase
{
	private ModelRenderer batHead;
	private ModelRenderer batBody;
	private ModelRenderer batRightWing;
	private ModelRenderer batLeftWing;
	private ModelRenderer batOuterRightWing;
	private ModelRenderer batOuterLeftWing;
	
	public ModelBat()
	{
		textureWidth = 64;
		textureHeight = 64;
		batHead = new ModelRenderer(this, 0, 0);
		batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
		ModelRenderer var1 = new ModelRenderer(this, 24, 0);
		var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
		batHead.addChild(var1);
		ModelRenderer var2 = new ModelRenderer(this, 24, 0);
		var2.mirror = true;
		var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
		batHead.addChild(var2);
		batBody = new ModelRenderer(this, 0, 16);
		batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
		batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
		batRightWing = new ModelRenderer(this, 42, 0);
		batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
		batOuterRightWing = new ModelRenderer(this, 24, 16);
		batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
		batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
		batLeftWing = new ModelRenderer(this, 42, 0);
		batLeftWing.mirror = true;
		batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
		batOuterLeftWing = new ModelRenderer(this, 24, 16);
		batOuterLeftWing.mirror = true;
		batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
		batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
		batBody.addChild(batRightWing);
		batBody.addChild(batLeftWing);
		batRightWing.addChild(batOuterRightWing);
		batLeftWing.addChild(batOuterLeftWing);
	}
	
	public int getBatSize()
	{
		return 36;
	}
	
	@Override public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		EntityBat var8 = (EntityBat) par1Entity;
		float var9;
		if(var8.getIsBatHanging())
		{
			var9 = 180F / (float) Math.PI;
			batHead.rotateAngleX = par6 / (180F / (float) Math.PI);
			batHead.rotateAngleY = (float) Math.PI - par5 / (180F / (float) Math.PI);
			batHead.rotateAngleZ = (float) Math.PI;
			batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
			batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
			batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
			batBody.rotateAngleX = (float) Math.PI;
			batRightWing.rotateAngleX = -0.15707964F;
			batRightWing.rotateAngleY = -((float) Math.PI * 2F / 5F);
			batOuterRightWing.rotateAngleY = -1.7278761F;
			batLeftWing.rotateAngleX = batRightWing.rotateAngleX;
			batLeftWing.rotateAngleY = -batRightWing.rotateAngleY;
			batOuterLeftWing.rotateAngleY = -batOuterRightWing.rotateAngleY;
		} else
		{
			var9 = 180F / (float) Math.PI;
			batHead.rotateAngleX = par6 / (180F / (float) Math.PI);
			batHead.rotateAngleY = par5 / (180F / (float) Math.PI);
			batHead.rotateAngleZ = 0.0F;
			batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
			batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			batBody.rotateAngleX = (float) Math.PI / 4F + MathHelper.cos(par4 * 0.1F) * 0.15F;
			batBody.rotateAngleY = 0.0F;
			batRightWing.rotateAngleY = MathHelper.cos(par4 * 1.3F) * (float) Math.PI * 0.25F;
			batLeftWing.rotateAngleY = -batRightWing.rotateAngleY;
			batOuterRightWing.rotateAngleY = batRightWing.rotateAngleY * 0.5F;
			batOuterLeftWing.rotateAngleY = -batRightWing.rotateAngleY * 0.5F;
		}
		batHead.render(par7);
		batBody.render(par7);
	}
}
