package net.minecraft.src;

public class EntityWolf extends EntityTameable
{
	private float field_70926_e;
	private float field_70924_f;
	private boolean isShaking;
	private boolean field_70928_h;
	private float timeWolfIsShaking;
	private float prevTimeWolfIsShaking;
	
	public EntityWolf(World p_i3526_1_)
	{
		super(p_i3526_1_);
		texture = "/mob/wolf.png";
		setSize(0.6F, 0.8F);
		moveSpeed = 0.3F;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(6, new EntityAIMate(this, moveSpeed));
		tasks.addTask(7, new EntityAIWander(this, moveSpeed));
		tasks.addTask(8, new EntityAIBeg(this, 8.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 16.0F, 200, false));
	}
	
	@Override public boolean attackEntityAsMob(Entity p_70652_1_)
	{
		int var2 = isTamed() ? 4 : 2;
		return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
	}
	
	@Override public boolean attackEntityFrom(DamageSource p_70097_1_, int p_70097_2_)
	{
		if(isEntityInvulnerable()) return false;
		else
		{
			Entity var3 = p_70097_1_.getEntity();
			aiSit.setSitting(false);
			if(var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow))
			{
				p_70097_2_ = (p_70097_2_ + 1) / 2;
			}
			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		}
	}
	
	@Override protected boolean canDespawn()
	{
		return isAngry() && !isTamed();
	}
	
	@Override public boolean canMateWith(EntityAnimal p_70878_1_)
	{
		if(p_70878_1_ == this) return false;
		else if(!isTamed()) return false;
		else if(!(p_70878_1_ instanceof EntityWolf)) return false;
		else
		{
			EntityWolf var2 = (EntityWolf) p_70878_1_;
			return !var2.isTamed() ? false : var2.isSitting() ? false : isInLove() && var2.isInLove();
		}
	}
	
	@Override public EntityAgeable createChild(EntityAgeable p_90011_1_)
	{
		return spawnBabyAnimal(p_90011_1_);
	}
	
	@Override protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, new Integer(getHealth()));
		dataWatcher.addObject(19, new Byte((byte) 0));
		dataWatcher.addObject(20, new Byte((byte) BlockCloth.getBlockFromDye(1)));
	}
	
	public void func_70918_i(boolean p_70918_1_)
	{
		byte var2 = dataWatcher.getWatchableObjectByte(19);
		if(p_70918_1_)
		{
			dataWatcher.updateObject(19, Byte.valueOf((byte) 1));
		} else
		{
			dataWatcher.updateObject(19, Byte.valueOf((byte) 0));
		}
	}
	
	public boolean func_70922_bv()
	{
		return dataWatcher.getWatchableObjectByte(19) == 1;
	}
	
	public int getCollarColor()
	{
		return dataWatcher.getWatchableObjectByte(20) & 15;
	}
	
	@Override protected String getDeathSound()
	{
		return "mob.wolf.death";
	}
	
	@Override protected int getDropItemId()
	{
		return -1;
	}
	
	@Override public float getEyeHeight()
	{
		return height * 0.8F;
	}
	
	@Override protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}
	
	public float getInterestedAngle(float par1)
	{
		return (field_70924_f + (field_70926_e - field_70924_f) * par1) * 0.15F * (float) Math.PI;
	}
	
	@Override protected String getLivingSound()
	{
		return isAngry() ? "mob.wolf.growl" : rand.nextInt(3) == 0 ? isTamed() && dataWatcher.getWatchableObjectInt(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting" : "mob.wolf.bark";
	}
	
	@Override public int getMaxHealth()
	{
		return isTamed() ? 20 : 8;
	}
	
	@Override public int getMaxSpawnedInChunk()
	{
		return 8;
	}
	
	public float getShadingWhileShaking(float par1)
	{
		return 0.75F + (prevTimeWolfIsShaking + (timeWolfIsShaking - prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
	}
	
	public float getShakeAngle(float par1, float par2)
	{
		float var3 = (prevTimeWolfIsShaking + (timeWolfIsShaking - prevTimeWolfIsShaking) * par1 + par2) / 1.8F;
		if(var3 < 0.0F)
		{
			var3 = 0.0F;
		} else if(var3 > 1.0F)
		{
			var3 = 1.0F;
		}
		return MathHelper.sin(var3 * (float) Math.PI) * MathHelper.sin(var3 * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
	}
	
	@Override protected float getSoundVolume()
	{
		return 0.4F;
	}
	
	public float getTailRotation()
	{
		return isAngry() ? 1.5393804F : isTamed() ? (0.55F - (20 - dataWatcher.getWatchableObjectInt(18)) * 0.02F) * (float) Math.PI : (float) Math.PI / 5F;
	}
	
	@Override public String getTexture()
	{
		return isTamed() ? "/mob/wolf_tame.png" : isAngry() ? "/mob/wolf_angry.png" : super.getTexture();
	}
	
	@Override public int getVerticalFaceSpeed()
	{
		return isSitting() ? 20 : super.getVerticalFaceSpeed();
	}
	
	public boolean getWolfShaking()
	{
		return isShaking;
	}
	
	@Override public void handleHealthUpdate(byte par1)
	{
		if(par1 == 8)
		{
			field_70928_h = true;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
		} else
		{
			super.handleHealthUpdate(par1);
		}
	}
	
	@Override public boolean interact(EntityPlayer p_70085_1_)
	{
		ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
		if(isTamed())
		{
			if(var2 != null)
			{
				if(Item.itemsList[var2.itemID] instanceof ItemFood)
				{
					ItemFood var3 = (ItemFood) Item.itemsList[var2.itemID];
					if(var3.isWolfsFavoriteMeat() && dataWatcher.getWatchableObjectInt(18) < 20)
					{
						if(!p_70085_1_.capabilities.isCreativeMode)
						{
							--var2.stackSize;
						}
						heal(var3.getHealAmount());
						if(var2.stackSize <= 0)
						{
							p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
						}
						return true;
					}
				} else if(var2.itemID == Item.dyePowder.itemID)
				{
					int var4 = BlockCloth.getBlockFromDye(var2.getItemDamage());
					if(var4 != getCollarColor())
					{
						setCollarColor(var4);
						if(!p_70085_1_.capabilities.isCreativeMode && --var2.stackSize <= 0)
						{
							p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
						}
						return true;
					}
				}
			}
			if(p_70085_1_.username.equalsIgnoreCase(getOwnerName()) && !worldObj.isRemote && !isBreedingItem(var2))
			{
				aiSit.setSitting(!isSitting());
				isJumping = false;
				setPathToEntity((PathEntity) null);
			}
		} else if(var2 != null && var2.itemID == Item.bone.itemID && !isAngry())
		{
			if(!p_70085_1_.capabilities.isCreativeMode)
			{
				--var2.stackSize;
			}
			if(var2.stackSize <= 0)
			{
				p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
			}
			if(!worldObj.isRemote)
			{
				if(rand.nextInt(3) == 0)
				{
					setTamed(true);
					setPathToEntity((PathEntity) null);
					setAttackTarget((EntityLiving) null);
					aiSit.setSitting(true);
					setEntityHealth(20);
					setOwner(p_70085_1_.username);
					playTameEffect(true);
					worldObj.setEntityState(this, (byte) 7);
				} else
				{
					playTameEffect(false);
					worldObj.setEntityState(this, (byte) 6);
				}
			}
			return true;
		}
		return super.interact(p_70085_1_);
	}
	
	@Override public boolean isAIEnabled()
	{
		return true;
	}
	
	public boolean isAngry()
	{
		return (dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}
	
	@Override public boolean isBreedingItem(ItemStack p_70877_1_)
	{
		return p_70877_1_ == null ? false : !(Item.itemsList[p_70877_1_.itemID] instanceof ItemFood) ? false : ((ItemFood) Item.itemsList[p_70877_1_.itemID]).isWolfsFavoriteMeat();
	}
	
	@Override public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if(!worldObj.isRemote && isShaking && !field_70928_h && !hasPath() && onGround)
		{
			field_70928_h = true;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
			worldObj.setEntityState(this, (byte) 8);
		}
	}
	
	@Override public void onUpdate()
	{
		super.onUpdate();
		field_70924_f = field_70926_e;
		if(func_70922_bv())
		{
			field_70926_e += (1.0F - field_70926_e) * 0.4F;
		} else
		{
			field_70926_e += (0.0F - field_70926_e) * 0.4F;
		}
		if(func_70922_bv())
		{
			numTicksToChaseTarget = 10;
		}
		if(isWet())
		{
			isShaking = true;
			field_70928_h = false;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
		} else if((isShaking || field_70928_h) && field_70928_h)
		{
			if(timeWolfIsShaking == 0.0F)
			{
				playSound("mob.wolf.shake", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
			}
			prevTimeWolfIsShaking = timeWolfIsShaking;
			timeWolfIsShaking += 0.05F;
			if(prevTimeWolfIsShaking >= 2.0F)
			{
				isShaking = false;
				field_70928_h = false;
				prevTimeWolfIsShaking = 0.0F;
				timeWolfIsShaking = 0.0F;
			}
			if(timeWolfIsShaking > 0.4F)
			{
				float var1 = (float) boundingBox.minY;
				int var2 = (int) (MathHelper.sin((timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);
				for(int var3 = 0; var3 < var2; ++var3)
				{
					float var4 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					float var5 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					worldObj.spawnParticle("splash", posX + var4, var1 + 0.8F, posZ + var5, motionX, motionY, motionZ);
				}
			}
		}
	}
	
	@Override protected void playStepSound(int p_70036_1_, int p_70036_2_, int p_70036_3_, int p_70036_4_)
	{
		playSound("mob.wolf.step", 0.15F, 1.0F);
	}
	
	@Override public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		super.readEntityFromNBT(p_70037_1_);
		setAngry(p_70037_1_.getBoolean("Angry"));
		if(p_70037_1_.hasKey("CollarColor"))
		{
			setCollarColor(p_70037_1_.getByte("CollarColor"));
		}
	}
	
	public void setAngry(boolean p_70916_1_)
	{
		byte var2 = dataWatcher.getWatchableObjectByte(16);
		if(p_70916_1_)
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 | 2)));
		} else
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 & -3)));
		}
	}
	
	@Override public void setAttackTarget(EntityLiving p_70624_1_)
	{
		super.setAttackTarget(p_70624_1_);
		if(p_70624_1_ instanceof EntityPlayer)
		{
			setAngry(true);
		}
	}
	
	public void setCollarColor(int p_82185_1_)
	{
		dataWatcher.updateObject(20, Byte.valueOf((byte) (p_82185_1_ & 15)));
	}
	
	public EntityWolf spawnBabyAnimal(EntityAgeable p_70879_1_)
	{
		EntityWolf var2 = new EntityWolf(worldObj);
		String var3 = getOwnerName();
		if(var3 != null && var3.trim().length() > 0)
		{
			var2.setOwner(var3);
			var2.setTamed(true);
		}
		return var2;
	}
	
	@Override protected void updateAITick()
	{
		dataWatcher.updateObject(18, Integer.valueOf(getHealth()));
	}
	
	@Override public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		super.writeEntityToNBT(p_70014_1_);
		p_70014_1_.setBoolean("Angry", isAngry());
		p_70014_1_.setByte("CollarColor", (byte) getCollarColor());
	}
}
