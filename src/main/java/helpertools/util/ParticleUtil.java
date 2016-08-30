package helpertools.util;

import java.util.Random;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import helpertools.entities.particle.Phantom_Cube;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class ParticleUtil {

	
	
	public static void GhostBlock(World world, double x, double y, double z, Block block, int meta){
		Entity part = new Phantom_Cube(world, x,y,z, block, meta);
			world.spawnEntityInWorld(part);
	}
	
	public static void Render_Ghost(World world, double x, double y, double z, Block block, int meta){
		final RenderBlocks RB = new RenderBlocks();
		
		
		Tessellator t = Tessellator.instance;
        GL11.glPushMatrix();
        //GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_LIGHTING);
        
        t.setBrightness(15 << 20 | 15 << 4);
        //GL11.glTranslatef((float)-x, (float)-y, (float)-z);
        //System.out.println(x + " " + y + " " + z);
        //System.out.println(" " );
        
         
		  
		   //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		   //GL11.glColor4f(0.5F, 0.5F, 0.5F,0.5F);
		   
        
        
        
		RB.setRenderBoundsFromBlock(block); 
		//RB.renderBlockSandFalling(block, world, (int) x, (int)y, (int)z, meta);
		RB.renderBlockSandFalling(block, world, (int) x, (int)y, (int)z, meta);
		
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
	}
	
	protected static Random growrand = new Random();
	//TODO
	
	public static void render_test(RenderWorldLastEvent evt,
			EntityClientPlayerMP p, Set<Coordinate> coordinates) {
		
		for (Coordinate coordinate : coordinates) {
			float f = (growrand.nextFloat()/30F) ;	        
	        float f1 = (growrand .nextFloat()/30F );
	        float f2 = (growrand .nextFloat()/30F );
			
			float x = coordinate.getX()+.5F+f;
			float y = coordinate.getY()+.5F+f1;
			float z = coordinate.getZ()+.5F+f2;
			
			ParticleUtil.Render_Ghost(p.worldObj, x, y, z, Blocks.glass, 0);
		}
	
	}
}
