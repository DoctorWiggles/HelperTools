package helpertools.handlers;

import helpertools.Mod_Registry;
import helpertools.item_blocks.Floater_block_item;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.util.Coordinate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;

/**Featuring not enough wands,
 Openblocks, and Enderio code */
/** Event moshpit for rendering visual guides**/
public class Ghostblock_Handler {

	
	@SubscribeEvent
	public void Floater_Highlight(RenderWorldLastEvent evt) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityClientPlayerMP player = mc.thePlayer;
		if (player.getHeldItem() != null) {

			ItemStack heldstack = player.getHeldItem();
			Item held = heldstack.getItem();

			if (held != null && held instanceof Floater_block_item) {
				Set<Coordinate> coordinates = new HashSet<Coordinate>();
				World world = player.worldObj;

				if (!player.isSneaking()) {
					MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;

					int x = mouseOver.blockX;
					int y = mouseOver.blockY;
					int z = mouseOver.blockZ;
					Block block = world.getBlock(x, y, z);
					Material matt;
					matt = block.getMaterial();

					if (block == Blocks.air || matt == Material.fire
							|| matt == Material.water || matt == Material.lava
							|| matt == Material.plants || matt == Material.vine
							|| block == Blocks.snow_layer) {

						coordinates.add(new Coordinate(x, y, z));

						//renderOutlines(evt, player, coordinates, 240, 180, 240);
					} else {
						int hit;
						hit = mouseOver.sideHit;
						ForgeDirection direction = ForgeDirection
								.getOrientation(hit);

						coordinates.add(new Coordinate(x, y, z).add(direction));
						//renderOutlines(evt, player, coordinates, 240, 180, 240);
					}

				}
				if (player.isSneaking()) {
				}
			}
		}

	}
	
	@SubscribeEvent
	public void Tool_Highlight(RenderWorldLastEvent evt) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityClientPlayerMP player = mc.thePlayer;
		if (player.getHeldItem() != null
				&& !player.isSneaking()) {

			ItemStack heldstack = player.getHeldItem();
			Item held = heldstack.getItem();

			if (held != null && held instanceof ItemStaffofExpansion) {
				
				ItemStaffofExpansion Tool = (ItemStaffofExpansion)held;
				//Set<Coordinate> coordinates = new HashSet<Coordinate>();
				World world = player.worldObj;

				MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;

				int x = mouseOver.blockX;
				int y = mouseOver.blockY;
				int z = mouseOver.blockZ;
				
				int hit;
				hit = mouseOver.sideHit;
				
				if(world.getBlock(x,y,z) != Blocks.air){
					int mode = Tool.getMode(heldstack);
						
							Set<Coordinate> coordinates;
							if (mode ==2){coordinates = Tool.pillar_selection(heldstack,(EntityPlayer)player, world, x, y, z, hit, true);
							renderOutlines(evt, player, coordinates, 80, 180, 240);}
							
							if (mode ==4){coordinates = Tool.wall_selection(heldstack,(EntityPlayer)player, world, x, y, z, hit, true);
							renderOutlines(evt, player, coordinates, 80, 180, 240);}
							
							if (mode ==6){coordinates = Tool.matching_selection(heldstack,(EntityPlayer)player, world, x, y, z, hit, true);
							renderOutlines(evt, player, coordinates, 80, 180, 240);}
							
				}
			}
		}

	}
	
	@SubscribeEvent 
	public void Block_Render(RenderWorldLastEvent evt){
		int a = 0;
		if (a == 0){return;}
		Minecraft mc = Minecraft.getMinecraft();
		  MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;

			int x = mouseOver.blockX;
			int y = mouseOver.blockY;
			int z = mouseOver.blockZ;
			
			int mx = mouseOver.blockX;
			int my = mouseOver.blockY;
			int mz = mouseOver.blockZ;
			
			int hit;
			hit = mouseOver.sideHit;
			ForgeDirection direction = ForgeDirection.getOrientation(hit);
			
		  World world = mc.thePlayer.worldObj;
		  EntityPlayer player = mc.thePlayer;
		  EntityClientPlayerMP p= mc.thePlayer;
		 	ChunkCoordinates coord;
  			coord = player.getPlayerCoordinates();
  			
      		int px = (int) coord.posX;
      		int py = (int) player.posY-1;
      		int pz = (int) coord.posZ;
      		
      		double dx = p.lastTickPosX + (p.posX - p.lastTickPosX)
    				* evt.partialTicks;
    		double dy = p.lastTickPosY + (p.posY - p.lastTickPosY)
    				* evt.partialTicks;
    		dy = dy -1;
    		double dz = p.lastTickPosZ + (p.posZ - p.lastTickPosZ)
    				* evt.partialTicks;
      		
      		int tx = mx - px;
      		int ty = my - py;
      		int tz = mz - pz;
		 
      		
      		Coordinate translate;
      		translate= new Coordinate(tx, ty, tz).add(direction);
      		
      		Block block = world.getBlock(x, y, z);
			Material matt;
			matt = block.getMaterial();
			
			ItemStack stack;
			stack = player.getHeldItem();
			Item held = null;
			if(stack != null)
			held = stack.getItem();			

			if (block == Blocks.air || matt == Material.fire
					|| matt == Material.water || matt == Material.lava
					|| matt == Material.plants || matt == Material.vine
					|| block == Blocks.snow_layer) {
      			
      			translate= new Coordinate(tx, ty, tz);
      		}
      		else translate= new Coordinate(tx, ty, tz).add(direction);
			
      		tx = translate.getX();
      		ty =translate.getY();
      		tz =translate.getZ();
      		double fx = px - dx;
      		double fy = py - dy;
      		double fz = pz - dz;
		  //Block block3 = Common_Registry.Floater;
		 // Block block4 = Common_Registry.Balloon;
		  
		  Block block2 = world.getBlock(77, 6, -11);
		  int meta = world.getBlockMetadata(77, 6, -11);
		  //Block block2 = Common_Registry.LooseDirtBlock;
		  		  
		   GL11.glPushMatrix();   
		   //GL11.glEnable(GL11.GL_BLEND);
		   //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		Tessellator t = Tessellator.instance;
		
		RenderBlocks renderBlocks = new RenderBlocks();
		renderBlocks.blockAccess = world;
		RenderItem itemRender = new RenderItem();
		
		//ItemStack stack2 = new ItemStack(Blocks.dirt, 1, 0);
		//ItemStack stack2 = new ItemStack(Blocks.log, 1, 5);
		//ItemStack stack2 = new ItemStack(Blocks.anvil, 1, 0);
		ItemStack stack2 = new ItemStack(block2, 1, meta);
		
		EntityItem entity = new EntityItem(world, 0, 0, 0, stack2);
		
		//GL11.glTranslated( 0F, 10f, 0F);	
		//GL11.glTranslated( 0.5F, -0.5f, 0.5F);
		//GL11.glTranslated( tx, ty, tz);
		//GL11.glTranslated( fx, fy, fz);
		//mc.renderEngine.bindTexture(stack2.getItem() instanceof ItemBlock ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);

		
		renderBlocks.setRenderBounds(0D, 0D, 0D, 1D, 1D, 1D);
		t.startDrawingQuads();
		t.setBrightness(15 << 20 | 15 << 4);
		
		try{
			if(block != Blocks.air){
			//renderBlocks.renderBlockByRenderType(block2, 0, -11, 0);	
			//itemRender.doRender(entity, 0, 0, 0, 0, 0.5F);
				renderBlockLog(block2, 0, -11, 0, renderBlocks, meta);
			//if(stack2.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(stack2.getItem()).getRenderType())) {
			//renderBlocks.renderBlockAsItem(Block.getBlockFromItem(stack2.getItem()), stack2.getItemDamage(), 1F);
			//}
			}			
			
		GL11.glTranslated( 0F, 10f, 0F);		
		GL11.glTranslated( tx, ty, tz);
		GL11.glTranslated( fx, fy, fz);
		}
	      catch(NullPointerException exception){
	    	  System.out.println(exception);
	    	  exception.printStackTrace();
	      }
		t.draw();
		
		
		
		//GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		coordinates.add(new Coordinate(x, y, z).add(direction));
		//renderOutlines(evt, mc.thePlayer, coordinates, 10, 10, 10);
	}
	public boolean renderBlockLog(Block p_147742_1_, int p_147742_2_, int p_147742_3_, int p_147742_4_, RenderBlocks render, int meta)
    {
        int l = meta;
        int i1 = l & 12;

        if (i1 == 4)
        {
        	render.uvRotateEast = 1;
        	render.uvRotateWest = 1;
        	render.uvRotateTop = 1;
        	render.uvRotateBottom = 1;
        }
        else if (i1 == 8)
        {
        	render.uvRotateSouth = 1;
        	render.uvRotateNorth = 1;
        }

        boolean flag = renderStandardBlock(p_147742_1_, p_147742_2_, p_147742_3_, p_147742_4_, render);
        render.uvRotateSouth = 0;
        render.uvRotateEast = 0;
        render.uvRotateWest = 0;
        render.uvRotateNorth = 0;
        render.uvRotateTop = 0;
        render.uvRotateBottom = 0;
        return flag;
    }
	
	public boolean renderStandardBlock(Block p_147784_1_, int p_147784_2_, int p_147784_3_, int p_147784_4_, RenderBlocks render)
    {
        int l = p_147784_1_.colorMultiplier(render.blockAccess, p_147784_2_, p_147784_3_, p_147784_4_);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        return Minecraft.isAmbientOcclusionEnabled() && p_147784_1_.getLightValue() == 0 ? 
        		(render.partialRenderBounds ? render.renderStandardBlockWithAmbientOcclusionPartial(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2) :
        	render.renderStandardBlockWithAmbientOcclusion(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2)) : 
        		render.renderStandardBlockWithColorMultiplier(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2);
    }
	  
		      
	protected static void renderOutlines(RenderWorldLastEvent evt,
			EntityClientPlayerMP p, Set<Coordinate> coordinates, int r, int g,
			int b) {
		double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX)
				* evt.partialTicks;
		double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY)
				* evt.partialTicks;
		double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ)
				* evt.partialTicks;

		GL11.glPushAttrib(GL11.GL_CURRENT_BIT | GL11.GL_DEPTH_BUFFER_BIT
				| GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT
				| GL11.GL_TEXTURE_BIT);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glPushMatrix();
		GL11.glTranslated(-doubleX, -doubleY, -doubleZ);

		//renderOutlines(coordinates, r, g, b, 4);
		//renderOutlines(coordinates, r, g, b, 2);
		renderOutlines(coordinates, r, g, b, 3);

		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}

	private static void renderOutlines(Set<Coordinate> coordinates, int r,
			int g, int b, int thickness) {
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawing(GL11.GL_LINES);
		tessellator.setColorOpaque(r, g, b);
		tessellator.setBrightness(240);

		GL11.glColor3ub((byte) r, (byte) g, (byte) b);
		GL11.glLineWidth(thickness);

		for (Coordinate coordinate : coordinates) {
			float x = coordinate.getX();
			float y = coordinate.getY();
			float z = coordinate.getZ();

			renderBlockOutline(tessellator, x, y, z, 0.0f); // .02f
		}
		tessellator.draw();
	}

	private static void renderBlockOutline(Tessellator tessellator, float mx,
			float my, float mz, float o) {
		tessellator.addVertex(mx - o, my - o, mz - o);
		tessellator.addVertex(mx + 1 + o, my - o, mz - o);
		tessellator.addVertex(mx - o, my - o, mz - o);
		tessellator.addVertex(mx - o, my + 1 + o, mz - o);
		tessellator.addVertex(mx - o, my - o, mz - o);
		tessellator.addVertex(mx - o, my - o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz + 1 + o);
		tessellator.addVertex(mx - o, my + 1 + o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my - o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz - o);

		tessellator.addVertex(mx - o, my + 1 + o, mz - o);
		tessellator.addVertex(mx - o, my + 1 + o, mz + 1 + o);
		tessellator.addVertex(mx - o, my + 1 + o, mz - o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz - o);

		tessellator.addVertex(mx + 1 + o, my - o, mz - o);
		tessellator.addVertex(mx + 1 + o, my - o, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my - o, mz - o);
		tessellator.addVertex(mx + 1 + o, my + 1 + o, mz - o);

		tessellator.addVertex(mx, my, mz + 1 + o);
		tessellator.addVertex(mx + 1 + o, my, mz + 1 + o);
		tessellator.addVertex(mx, my, mz + 1 + o);
		tessellator.addVertex(mx, my + 1 + o, mz + 1 + o);
	}

}
