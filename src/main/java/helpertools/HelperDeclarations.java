package helpertools;

import helpertools.util.ForgeEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class HelperDeclarations {	
	
	//Blocks - Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
	public final static Block Ibedrock = new helpertools.blocks.ImitationBedrock();
	public final static Block StokedPipe = new helpertools.blocks.StokedPipe();		
	public final static Block TranscriberBlock = new helpertools.blocks.TranscriberBlock();	
	public final static Block MagicalFuelBlock = new helpertools.blocks.MagicalFuelBlock();	
	public final static Block ActiveMagicalFuelBlock = new helpertools.blocks.ActiveMagicalFuelBlock();	
	public final static Block LooseDirtBlock = new helpertools.blocks.LooseDirtBlock(Material.sand);
	//
	//public final static Block IllusionBlock = new helpertools.blocks.IllusionBlock(Material.glass, false, "helpertools:IllusionFace");	
	//public final static Block SugarBlock = new helpertools.blocks.SugarBlock(Material.sand);
	//public final static Block TransitionGlass = new helpertools.blocks.TransitionGlass(Material.glass, false, "helpertools:TransitionGlassOn");
	//public final static Block ObeliskBlock = new helpertools.blocks.ObeliskBlock();
	
 	//Items & Tools - Items & Tools - Items & Tools - Items & Tools - Items & Tools 
	public static Item staffofexpansion;  
	public static Item staffoftransformation2;
	public static Item euclideantransposer;
	public static Item torchlauncher;
	//		
	public static Item dynamitebolt;
	public static Item dirtbomb;
	public static Item bottledmilk;
	public static Item chocolatemilk;
	//
	//public static Item powercrystal;
	//
	//public static Item debugtool;
	//public static Item rfdebugtool;
	
	
	
	////////////////////////////
	/** Configuration       **/
	//////////////////////////
	//Tool durability
	public static int  DurabilityExpandingRod;
	public static int  DurabilityMetamorphicStaff;
	public static int  DurabilityTorchLauncher;
	public static int  DurabilityEuclideanStaff;
	//Items
	public static int OutputDynamiteBolt;
	public static int OutputDirtBomb;
	//Blocks
	public static int OutputImitationBedrock;
	public static int OutputChimneyPipe;
	public static int OutputMagicalFuel;		
	//Booleans enable
	
	/** 3D Models **/
	public static boolean Render3DStaffModels;
	public static boolean Render3DCrossbowModel;
	public static boolean RenderToolHuds;
	public static boolean ToolModeMesseges;
	public static boolean ToolPowerMesseges;
	//ExpStaff
	public static boolean RecipeEmeraldsForExpansionStaff;
	public static boolean RecipeDiamondsForExpansionStaff;
	public static boolean RecipePearlsForExpansionStaff;
	//MetaStaff
	public static boolean RecipeEmeraldsForMetamorphicStaff;
	public static boolean RecipeDiamondsForMetamorphicStaff;
	public static boolean RecipePearlsForMetamorphicStaff;
	//EuclideanStaff
	public static boolean RecipeEmeraldsForEuclideanStaff;
	public static boolean RecipeDiamondsForEuclideanStaff;
	public static boolean RecipePearlsForEuclideanStaff;
	//Torch Launcher
	public static boolean RecipeTorchLauncher;
	public static boolean RecipeStringForDynamiteBolt;		
	public static boolean RecipeSlimeForDynamiteBolt;
	//Blocks
	public static boolean RecipeImitationBedrock;
	public static boolean RecipeMagicalFuel;
	public static boolean RecipeChimenyPipes;
	public static boolean RecipeEuclideanBlock;
	//Vanilla Blocks
	public static boolean RecipePodzol;
	public static boolean RecipeDoubleSlab;
	public static boolean RecipeFullSlab;
	public static boolean RecipeFullSandSlab;
	
	//Items
	public static boolean RecipeDirtBomb;
	public static boolean RecipeBottledmilk;
	public static boolean RecipeChocolatemilk;
	//Handler enables
	public static boolean HandlerBottledmilk;

}
