package net.minecraft.src;

public class EntityAIDefendVillage extends EntityAITarget
{
	EntityIronGolem irongolem;
	EntityLiving villageAgressorTarget;
	
	public EntityAIDefendVillage(EntityIronGolem p_i3497_1_)
	{
		super(p_i3497_1_, 16.0F, false, true);
		irongolem = p_i3497_1_;
		setMutexBits(1);
	}
	
	@Override public boolean shouldExecute()
	{
		Village var1 = irongolem.getVillage();
		if(var1 == null) return false;
		else
		{
			villageAgressorTarget = var1.findNearestVillageAggressor(irongolem);
			if(!isSuitableTarget(villageAgressorTarget, false))
			{
				if(taskOwner.getRNG().nextInt(20) == 0)
				{
					villageAgressorTarget = var1.func_82685_c(irongolem);
					return isSuitableTarget(villageAgressorTarget, false);
				} else return false;
			} else return true;
		}
	}
	
	@Override public void startExecuting()
	{
		irongolem.setAttackTarget(villageAgressorTarget);
		super.startExecuting();
	}
}
