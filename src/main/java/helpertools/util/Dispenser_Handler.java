package helpertools.util;

import helpertools.Common_Registry;
import helpertools.entities.BombProjectile_Entity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Dispenser_Handler {
	
    public static void registerVanillaDispenserBehaviors()
    {
        BlockDispenser.dispenseBehaviorRegistry.putObject(Common_Registry.bomb, new DispenserBehaviour());

    }
    

final static class DispenserBehaviour extends BehaviorDefaultDispenseItem
{
    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    public ItemStack dispenseStack(IBlockSource BlockSource, ItemStack stack)
    {
    	double sx =BlockSource.getX();
        double sy =BlockSource.getY();
        double sz =BlockSource.getZ();
        
        EnumFacing enumfacing = BlockDispenser.func_149937_b(BlockSource.getBlockMetadata());
        double d0 = BlockSource.getX() + (double)enumfacing.getFrontOffsetX();
        //double d1 = (double)((float)BlockSource.getYInt() + 0.2F);
        double d1 = BlockSource.getY() +(double)enumfacing.getFrontOffsetY();
        double d2 = BlockSource.getZ() + (double)enumfacing.getFrontOffsetZ();
        
        int type = stack.getItemDamage();
        World world = BlockSource.getWorld(); 
       
        double xh = d0 - sx;
        double yh = d1 - sy;
        double zh = d2 - sz;
        
        int amp = 0;
        amp = scan_local_charms(world, (int)sx, (int)sy, (int)sz-1);
        
        //BombProjectile_Entity entity = new BombProjectile_Entity(world, d0, d1, d2, type, xh, yh, zh, 2, 2);
        BombProjectile_Entity entity = new BombProjectile_Entity(world, d0, d1, d2, type, amp*5, xh, yh, zh, 2, 2);
        world.spawnEntityInWorld(entity);
        
        stack.splitStack(1);
        return stack;
    }
}


	//scans neighboring blocks for charms and returns whichever is the most powerful
	public static int scan_local_charms(World world, int x, int y, int z){
		int amplify = 0;
		int check = 0;
		Block charm = Common_Registry.Charm_block;
		
		if(world.getBlock(x, y,z+1) == charm){
			check = world.getBlockMetadata(x, y, z+1);			
			if( check > amplify){ amplify = check;}
		}
		if(world.getBlock(x, y,z-1) == charm){
			check = world.getBlockMetadata(x, y, z-1);			
			if( check > amplify){ amplify = check;}
		}
		if(world.getBlock(x, y+1,z) == charm){
			check = world.getBlockMetadata(x, y+1, z);
			if( check > amplify){ amplify = check;}
		}
		if(world.getBlock(x, y-1,z) == charm){
			check = world.getBlockMetadata(x, y-1, z);
			if( check > amplify){ amplify = check;}
		}
		if(world.getBlock(x+1, y,z) == charm){
			check = world.getBlockMetadata(x+1, y, z);
			if( check > amplify){ amplify = check;}
		}
		if(world.getBlock(x-1, y,z) == charm){
			check = world.getBlockMetadata(x-1, y, z);
			if( check > amplify){ amplify = check;}
		}
		return amplify;
	}

}