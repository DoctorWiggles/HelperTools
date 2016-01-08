package helpertools.util;

import helpertools.Common_Registry;
import helpertools.item_blocks.Floater_block_item;
import helpertools.tools.ItemStaffofExpansion;

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
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;

//Not enough wands code
public class Ghostblock_Handler {

	/*
	  public void renderWorldLastEvent(RenderWorldLastEvent evt) {
	  //evt.context RenderGlobal renderglobal = evt.context; RenderBlocks
	  renderblocks = renderglobal;
	  RenderBlocks.renderBlockByRenderType(Blocks.stone, (int)finX, (int)finY,
	  (int)finZ);
	  
	  }
	 */
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

						renderOutlines(evt, player, coordinates, 240, 180, 240);
					} else {
						int hit;
						hit = mouseOver.sideHit;
						ForgeDirection direction = ForgeDirection
								.getOrientation(hit);

						coordinates.add(new Coordinate(x, y, z).add(direction));
						renderOutlines(evt, player, coordinates, 240, 180, 240);
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
			ForgeDirection direction = ForgeDirection
					.getOrientation(hit);
			
		  World world = mc.thePlayer.worldObj;
		  EntityPlayer player = mc.thePlayer;
		  EntityClientPlayerMP p= mc.thePlayer;
		 	ChunkCoordinates coord;
  			coord = player.getPlayerCoordinates();
		  
		  	//int px = (int) coord.posX;
      		//int py = (int) player.posY-1;
      		//int pz = (int) coord.posZ;
      		
      		int px = (int) coord.posX;
      		int py = (int) player.posY-1;
      		int pz = (int) coord.posZ;
      		
      		//double dx =  player.posX;
      		//double dy = player.posY-1;
      		//double dz =  player.posZ;
      		
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
		 
      		Coordinate translate = new Coordinate(tx, ty, tz).add(direction);
      		tx = translate.getX();
      		ty =translate.getY();
      		tz =translate.getZ();
      		
      		//double fx = dx - px;
      		//double fy = dy - py;
      		//double fz = dz - pz;
      		
      		double fx = px - dx;
      		double fy = py - dy;
      		double fz = pz - dz;
		 //Block block = Blocks.stone;
		   //Block block = Common_Registry.Floater;
		   //Block block = Blocks.birch_stairs;
		   //Block block = Blocks.glass;
		  Block block = world.getBlock(20, 20, 20);
		  Block block2 = Blocks.torch;
		  
		   //bindDefaultTerrainTexture();
		   
		   GL11.glPushMatrix();   
		   GL11.glEnable(GL11.GL_BLEND);
		   GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		   GL11.glDisable(GL11.GL_LIGHTING);
		  
			
			//GL11.glTranslated(x + 0.5F, y, z + 0.5F);
			
		Tessellator t = Tessellator.instance;
		RenderBlocks renderBlocks = new RenderBlocks();
		renderBlocks.blockAccess = world;
		//RenderBlocks renderBlocks = new RenderBlocks(world);
		renderBlocks.setRenderBounds(0.05D, 0.05D, 0.05D, 0.95D, 0.95D, 0.95D);
		t.startDrawingQuads();
		t.setBrightness(200);
		//t.setBrightness(0);
		// Tessellator.instance.setBrightness(15 << 20 | 15 << 4);
		try{			
		//renderBlocks.renderBlockByRenderType(block, x, y, z);
			
		renderBlocks.renderBlockByRenderType(block, 0, -11, 0);
		renderBlocks.renderBlockByRenderType(block2, 0, -10, 0);
		renderBlocks.renderBlockByRenderType(block, 0, -9, 0);
		//GL11.glTranslated( -0.5F, 9.38f, -0.5F);
		//GL11.glTranslated( 0F, 9.38f, 0F);
		GL11.glTranslated( 0F, 10f, 0F);
		
		GL11.glTranslated( tx, ty, tz);
		GL11.glTranslated( fx, fy, fz);
		//renderBlocks.renderBlockByRenderType(block, 0.5, -2, 0.5);
		
		//renderBlocks.renderBlockByRenderType(block, x1, y1, z1);
		
		//renderBlocks.renderStandardBlockWithColorMultiplier(block, x, y, z, 1f, 1f, 1f);
		}
	      catch(NullPointerException exception){
	    	  System.out.println(exception);
	      }
		t.draw();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		coordinates.add(new Coordinate(x, y, z).add(direction));
		//renderOutlines(evt, mc.thePlayer, coordinates, 10, 10, 10);
	}
	
	
	public static void bindTextureToClient(ResourceLocation texture) {
		if (texture != null) {
			final Minecraft mc = Minecraft.getMinecraft();
			if (mc != null) {
				mc.renderEngine.bindTexture(texture);
			} else {
				System.out.println("Binding texture to null client.");
			}
		} else {
			System.out.println("Invalid texture location '%s'" + texture);
		}
	}
	public static void bindDefaultTerrainTexture() {
		bindTextureToClient(TextureMap.locationBlocksTexture);
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
		renderOutlines(coordinates, r, g, b, 2);

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
