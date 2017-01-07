package helpertools.Client;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Blocks.FloaterBlock.FloaterBlock_Item;
import helpertools.Com.Entity.Phantom_Cube;
import helpertools.Com.Tools.ItemEuclideanTransposer;
import helpertools.Com.Tools.ItemStaffofExpansion;
import helpertools.Com.Tools.ItemStaffofTransformation;
import helpertools.Com.Tools.ToolBase_Patterns;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.Texty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Highlight_Handler {
	
	private ItemStack heldstack;
	@SubscribeEvent
	public void Floater_Highlight(RenderWorldLastEvent evt) {
		if(!Config.Use_Wire_Frame_Guides){return;}
		try {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		EnumHand hand = null;
		if(player.getHeldItemMainhand() != null){
			if(player.getHeldItemMainhand().getItem() instanceof FloaterBlock_Item){
				hand = EnumHand.MAIN_HAND;
			}
		}
		if(player.getHeldItemOffhand() != null){
			if(player.getHeldItemOffhand().getItem() instanceof FloaterBlock_Item){
				hand = EnumHand.OFF_HAND;
			}
		}	
		if(hand == null){return;}
		
				ItemStack heldstack = player.getHeldItem(hand);
				Item held = heldstack.getItem();

				World world = player.worldObj;

				if (!player.isSneaking()) {
					RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
					BlockPos pos = mouseOver.getBlockPos();
					IBlockState state = world.getBlockState(pos); 
					if(Texty.isValid(state, world, pos)){
						
						fluctuate();
						Render_Outlines(evt, state, pos, 240, 180, 240, 3F*this.scale);
					}
				
			

				}
		} catch(Exception e){}
	}
	
	@SubscribeEvent
	public void TransformationTool_Highlight(RenderWorldLastEvent evt) {
		if(!Config.Use_Wire_Frame_Guides){return;}
		try{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		EnumHand hand = null;
		if(player.getHeldItemMainhand() != null){
			if(player.getHeldItemMainhand().getItem() instanceof ItemStaffofTransformation){
				hand = EnumHand.MAIN_HAND;
			}
		}
		if(player.getHeldItemOffhand() != null){
			if(player.getHeldItemOffhand().getItem() instanceof ItemStaffofTransformation){
				hand = EnumHand.OFF_HAND;
			}
		}	
		if(hand == null){return;}
		
				ItemStack heldstack = player.getHeldItem(hand);
				Item held = heldstack.getItem();
				World world = player.worldObj;

				if (!player.isSneaking()) {
					RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
					EnumFacing theface = mouseOver.sideHit;
					BlockPos pos = mouseOver.getBlockPos();
					IBlockState state = world.getBlockState(pos); 
					
					ItemStaffofTransformation staff = (ItemStaffofTransformation)held;
					fluctuate();
					
					Set<BlockPos> positions = staff.Mode_Function(heldstack, player, pos, theface, true);
					if(positions != null && !positions.isEmpty()){
						for (BlockPos location : positions) {
							Render_Outlines(evt, state, location, 180, 240, 180, 3.5F*this.scale);
							
							
						}
					}
			

		}
		} catch(Exception e){}
		
	}
	
	@SubscribeEvent
	public void ExpandingTool_Highlight(RenderWorldLastEvent evt) {
		try{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		EnumHand hand = null;
		if(player.getHeldItemMainhand() != null){
			if(player.getHeldItemMainhand().getItem() instanceof ItemStaffofExpansion){
				hand = EnumHand.MAIN_HAND;
			}
		}
		if(player.getHeldItemOffhand() != null){
			if(player.getHeldItemOffhand().getItem() instanceof ItemStaffofExpansion){
				hand = EnumHand.OFF_HAND;
			}
		}	
		if(hand == null){return;}
		
				ItemStack heldstack = player.getHeldItem(hand);
				Item held = heldstack.getItem();
				World world = player.worldObj;

				if (!player.isSneaking()) {
					RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
					EnumFacing theface = mouseOver.sideHit;
					BlockPos pos = mouseOver.getBlockPos();
					IBlockState state = world.getBlockState(pos); 
					
					ItemStaffofExpansion staff = (ItemStaffofExpansion)held;
					fluctuate();
					IBlockState blocky = BlockStateHelper.returnState(staff.getTBlock(heldstack));
					
					
					Set<BlockPos> positions = staff.Mode_Function(heldstack, player, pos, theface, true);
					if(positions != null && !positions.isEmpty()){
						for (BlockPos location : positions) {
							if(Config.Use_Wire_Frame_Guides){
							Render_Outlines(evt, state, location, 180, 240, 180, 3.5F*this.scale);
							}
							if(Config.Use_Fake_Block_Guides){
							Phantom_Cube cube = new Phantom_Cube(world,location.getX(),location.getY(),location.getZ(),blocky);							
							world.spawnEntityInWorld(cube);
							}
							
						}
					}
			

		}
		} catch(Exception e){}
		
	}
	
	@SubscribeEvent
	public void Euclidean_Highlight(RenderWorldLastEvent evt) {
		try{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		EnumHand hand = null;
		if(player.getHeldItemMainhand() != null){
			if(player.getHeldItemMainhand().getItem() instanceof ItemEuclideanTransposer){
				hand = EnumHand.MAIN_HAND;
			}
		}
		if(player.getHeldItemOffhand() != null){
			if(player.getHeldItemOffhand().getItem() instanceof ItemEuclideanTransposer){
				hand = EnumHand.OFF_HAND;
			}
		}	
		if(hand == null){return;}
		
				ItemStack heldstack = player.getHeldItem(hand);
				Item held = heldstack.getItem();
				World world = player.worldObj;

				if (!player.isSneaking()) {
					RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
					EnumFacing theface = mouseOver.sideHit;
					BlockPos pos = mouseOver.getBlockPos();
					
					if(BlockStateHelper.getBlockfromState(player.worldObj, pos) == Blocks.AIR){return;}
							
					IBlockState state = world.getBlockState(pos); 
					
					ItemEuclideanTransposer staff = (ItemEuclideanTransposer)held;
					fluctuate();
					pos = staff.apply_Offset(heldstack, player, world, pos, theface, false);
					
					
					int NBT = 0;
					switch(staff.getCorner(heldstack)){
					case 0:	
						for(int X = 0; X < 5; ++X){ for(int Y = 0; Y < 5; ++Y){ for(int Z = 0; Z < 5; ++Z){	
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;	
						
					case 1:	
						for(int Z = 0; Z > -5; --Z){ for(int Y = 0; Y < 5; ++Y){ for(int X = 0; X < 5; ++X){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;	
						
					case 2: 
						for(int X = 0; X > -5; --X){ for(int Y = 0; Y < 5; ++Y){ for(int Z = 0; Z > -5; --Z){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;		
						
					case 3: 
						for(int Z = 0; Z < 5; ++Z){ for(int Y = 0; Y < 5; ++Y){ for(int X = 0; X > -5; --X){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;			
						
					case 4:	
						for(int X = 0; X < 5; ++X){ for(int Y = 0; Y > -5; --Y){ for(int Z = 0; Z < 5; ++Z){	
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;		
						
					case 5:	
						for(int Z = 0; Z > -5; --Z){ for(int Y = 0; Y > -5; --Y){ for(int X = 0; X < 5; ++X){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;		
						
					case 6: 
						for(int X = 0; X > -5; --X){ for(int Y = 0; Y > -5; --Y){ for(int Z = 0; Z > -5; --Z){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;			
						
					case 7: 
						for(int Z = 0; Z < 5; ++Z){ for(int Y = 0; Y > -5; --Y){ for(int X = 0; X > -5; --X){
							
							Eu_Cubes(world, player, pos, heldstack, X, Y, Z, NBT, staff, evt, state); NBT++;}}}	break;		
						
					default:
					}
					
			

		}
		} catch(Exception e){}
		
	}
	
	
	public void Eu_Cubes(World world, EntityPlayer player, BlockPos pos,ItemStack heldstack, double X, double Y, double Z, int NBT, ItemEuclideanTransposer staff, RenderWorldLastEvent evt, IBlockState state){
		
		BlockPos pos2 = pos.add(X, Y, Z);
		IBlockState states = BlockStateHelper.returnState(staff.getTBlock(heldstack, NBT));
		IBlockState state_to_replace = world.getBlockState(pos2);
		
		
		if(states.getBlock() == Blocks.AIR){return;}
		if(!(Texty.isValid(state_to_replace, world, pos2))){return;}
		if(!staff.inventory_Check(heldstack, player, NBT, true)){return;}
				
		if(Config.Use_Fake_Block_Guides){
			//false_block(evt, world, pos2, player, states);
			
			Phantom_Cube cube = new Phantom_Cube(world,pos2.getX(),pos2.getY(),pos2.getZ(), states);							
			world.spawnEntityInWorld(cube);
		
		}
		if(Config.Use_Wire_Frame_Guides){
			Render_Outlines(evt, states, pos2, 180, 240, 180, 3.5F*this.scale);
			}
		
	}
	
	static RenderManager render = Minecraft.getMinecraft().getRenderManager();	
	//hnm.... doesn't actually seem to increase performance despite not dumping hundreds of entities every moment
	public void false_block(RenderWorldLastEvent event, World world, BlockPos pos, EntityPlayer player, IBlockState state){

		render.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.pushMatrix();
		//GlStateManager.disableLighting();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();


		vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
		double partialTicks = event.getPartialTicks();
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
		
		GlStateManager.translate(
				-d0, 
				-d1, 
				-d2);
		
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		blockrendererdispatcher.getBlockModelRenderer().
		renderModel(world, blockrendererdispatcher.getModelForState(state), state, pos,
				vertexbuffer,false);
		tessellator.draw();

		//GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}


	
	
	/*
	protected static Random growrand = new Random();
	float f = (this.growrand.nextFloat()/30F) ;	        
    float f1 = (this.growrand .nextFloat()/30F );
    float f2 = (this.growrand .nextFloat()/30F );
	*/
	
	//Highlighter
	public void Render_Outlines (RenderWorldLastEvent event, IBlockState state, BlockPos pos, int r, int g, int b, float f){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        //GlStateManager.depthMask(true);
        GL11.glLineWidth(f);
            double partialTicks = event.getPartialTicks();
            double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
            double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
            double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

            AxisAlignedBB slightlyLargeBB = state.getSelectedBoundingBox(player.worldObj, pos)
                    .expand(0.005, 0.005, 0.005)
                    .offset(-d0, -d1, -d2);
            RenderGlobal.func_189697_a(slightlyLargeBB, (float) r/255F, (float) g/255F, (float) b/255F, (float) 100/255F);
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        //GlStateManager.depthMask(false);
        GlStateManager.popMatrix();
	}
	
	Float scale = 1F;
	Boolean toggle = false;
	//Global scaling
	public void fluctuate(){
		Float prev_scale = this.scale;
				
		if (prev_scale >= 2.5F){
			this.toggle = true;
		}
		if (prev_scale <= 1F){
			this.toggle = false;
		}
		if(this.toggle){
			this.scale = prev_scale - 0.05F ;
			return;
		}
		if(!this.toggle){
			this.scale = prev_scale + 0.05F ;
			return;
		}
		
	}
	
}
