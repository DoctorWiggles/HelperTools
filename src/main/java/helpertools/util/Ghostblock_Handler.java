package helpertools.util;

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
	
	
	
	

	/**
	  @SubscribeEvent public void renderWorldLastEvent(RenderWorldLastEvent evt) {
	  
	  //MovingObjectPosition mouseOver =  Minecraft.getMinecraft().objectMouseOver;
	  Minecraft mc = Minecraft.getMinecraft();
	  MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;

		int x = mouseOver.blockX;
		int y = mouseOver.blockY;
		int z = mouseOver.blockZ;
		
	  World world = mc.thePlayer.worldObj;
	 
	 //Block block = world.getBlock((int)mx, (int)my, (int)mz);
	   
	   Block block = Blocks.stone;
	   
	   IBlockAccess acces = world;
	   //RenderBlocks renderblocks = new mc.RenderBlocks(); 
	   //RenderBlocks renderblocks = new RenderBlocks(acces);
	   RenderBlocks renderblocks = new RenderBlocks(world);
	   
	   
	   //ResourceLocation rec = block.getResourceLocation;
	   //TextureMap.locationBlocksTexture
	   //Minecraft.getMinecraft().renderEngine.func_110577_a(); 
	   ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" +"textures/client/gui/HudTab_4.png");
	   Minecraft.getMinecraft().renderEngine.bindTexture(backgroundimage);
	   Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
	   
	   //renderblocks.renderBlockByRenderType(block, x, y, z);
	   renderblocks.renderStandardBlock(block, x, y, z) ;
	  
	   //RenderBlocks.renderBlockByRenderType(Blocks.stone, (int)mx, (int)my,(int)mz);
	   }
	  **/
	
		//IBlockAccess acces = world;
	   //RenderBlocks renderblocks = new mc.RenderBlocks(); 
	   //RenderBlocks renderblocks = new RenderBlocks(acces);
		//protected static Minecraft mc = Minecraft.getMinecraft();
	/**
		protected static RenderBlocks RB = new RenderBlocks();
	  @SubscribeEvent public void renderWorldLastEvent(RenderWorldLastEvent evt) {
		  
		  //MovingObjectPosition mouseOver =  Minecraft.getMinecraft().objectMouseOver;
		  Minecraft mc = Minecraft.getMinecraft();
		  MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;

			int x = mouseOver.blockX;
			int y = mouseOver.blockY;
			int z = mouseOver.blockZ;
			
		  World world = mc.thePlayer.worldObj;
		  //RB = new RenderBlocks(world);
		  Set<Coordinate> coordinates = new HashSet<Coordinate>();
		  coordinates.add(new Coordinate(x, y, z));
			EntityClientPlayerMP player = mc.thePlayer;
			renderOutlines(evt, player, coordinates, 0, 0, 0);
		 //Block block = world.getBlock((int)mx, (int)my, (int)mz);
		   
		   Block block = Blocks.sponge;
		   
		   Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setBrightness(15 << 20 | 15 << 4);

		    
		      if(block != null) {
		          RB.renderAllFaces = true;
		          RB.setRenderAllFaces(true);
		          RB.setRenderBounds(0, 0, 0, 1, 1, 1);
		          try {
		            RB.renderBlockByRenderType(block,x, y, z);
		          } catch (Exception e) {
		            //Ignore, things might blow up in rendering due to the modified block access
		            //but this is about as good as we can do
		          }
		        
		     
		    Tessellator.instance.draw();
		    Tessellator.instance.setTranslation(0, 0, 0);
		   }
	  }
	**/
		      
		      
		      
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
