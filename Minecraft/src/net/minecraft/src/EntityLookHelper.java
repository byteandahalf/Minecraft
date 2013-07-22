package net.minecraft.src;

public class EntityLookHelper
{
	private EntityLiving entity;
	private float deltaLookYaw;
	private float deltaLookPitch;
	private boolean isLooking = false;
	private double posX;
	private double posY;
	private double posZ;
	
	public EntityLookHelper(EntityLiving p_i3455_1_)
	{
		entity = p_i3455_1_;
	}
	
	public void onUpdateLook()
	{
		entity.rotationPitch = 0.0F;
		if(isLooking)
		{
			isLooking = false;
			double var1 = posX - entity.posX;
			double var3 = posY - (entity.posY + entity.getEyeHeight());
			double var5 = posZ - entity.posZ;
			double var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
			float var9 = (float) (Math.atan2(var5, var1) * 180.0D / Math.PI) - 90.0F;
			float var10 = (float) -(Math.atan2(var3, var7) * 180.0D / Math.PI);
			entity.rotationPitch = updateRotation(entity.rotationPitch, var10, deltaLookPitch);
			entity.rotationYawHead = updateRotation(entity.rotationYawHead, var9, deltaLookYaw);
		} else
		{
			entity.rotationYawHead = updateRotation(entity.rotationYawHead, entity.renderYawOffset, 10.0F);
		}
		float var11 = MathHelper.wrapAngleTo180_float(entity.rotationYawHead - entity.renderYawOffset);
		if(!entity.getNavigator().noPath())
		{
			if(var11 < -75.0F)
			{
				entity.rotationYawHead = entity.renderYawOffset - 75.0F;
			}
			if(var11 > 75.0F)
			{
				entity.rotationYawHead = entity.renderYawOffset + 75.0F;
			}
		}
	}
	
	public void setLookPosition(double p_75650_1_, double p_75650_3_, double p_75650_5_, float p_75650_7_, float p_75650_8_)
	{
		posX = p_75650_1_;
		posY = p_75650_3_;
		posZ = p_75650_5_;
		deltaLookYaw = p_75650_7_;
		deltaLookPitch = p_75650_8_;
		isLooking = true;
	}
	
	public void setLookPositionWithEntity(Entity p_75651_1_, float p_75651_2_, float p_75651_3_)
	{
		posX = p_75651_1_.posX;
		if(p_75651_1_ instanceof EntityLiving)
		{
			posY = p_75651_1_.posY + p_75651_1_.getEyeHeight();
		} else
		{
			posY = (p_75651_1_.boundingBox.minY + p_75651_1_.boundingBox.maxY) / 2.0D;
		}
		posZ = p_75651_1_.posZ;
		deltaLookYaw = p_75651_2_;
		deltaLookPitch = p_75651_3_;
		isLooking = true;
	}
	
	private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_)
	{
		float var4 = MathHelper.wrapAngleTo180_float(p_75652_2_ - p_75652_1_);
		if(var4 > p_75652_3_)
		{
			var4 = p_75652_3_;
		}
		if(var4 < -p_75652_3_)
		{
			var4 = -p_75652_3_;
		}
		return p_75652_1_ + var4;
	}
}
