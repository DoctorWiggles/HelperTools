package helpertools;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDirtBomb;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemMilkBottle;
import helpertools.renders.ItemRenderStaff4;
import helpertools.renders.ItemRenderStaff5;
import helpertools.renders.ItemRenderTorchLauncher1;

import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helpertools.tools.ItemDebugTool;
import helpertools.tools.ItemEuclideanTransposer;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.tools.ItemTorchLauncher;
import helpertools.util.ForgeEventHandler;
import mofmod.Mofmodcore;
import net.java.games.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="HelperToolsID", name="HelperTools", version="1.1.4c")
//@NetworkMod(clientSideRequired=true) // not used in 1.7
public class Helpertoolscore {
	
		
	
		//Blocks - Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
		public final static Block Ibedrock = new helpertools.blocks.ImitationBedrock();
		public final static Block StokedPipe = new helpertools.blocks.StokedPipe();
		public final static Block StokedBlock = new helpertools.blocks.StokedBlock();
		public final static Block SelectionBlock = new helpertools.blocks.SelectionBlock();		
		public final static Block TranscriberBlock = new helpertools.blocks.TranscriberBlock();	
		public final static Block MagicalFuelBlock = new helpertools.blocks.MagicalFuelBlock();	
		public final static Block ActiveMagicalFuelBlock = new helpertools.blocks.ActiveMagicalFuelBlock();	
		public final static Block LooseDirtBlock= new helpertools.blocks.LooseDirtBlock(Material.sand);
		
	 	//Items & Tools - Items & Tools - Items & Tools - Items & Tools - Items & Tools 
		public static Item staffofexpansion;  
		public static Item staffoftransformation2;
		public static Item torchlauncher;
		public static Item euclideantransposer;
		
		public static Item dynamitebolt;
		public static Item dirtbomb;
		public static Item bottledmilk;
		public static Item chocolatemilk;
		
		public static Item debugtool;		
		
    			
		//Forge Support
		public static ForgeEventHandler eventHandler = new ForgeEventHandler();
		public static final Logger logger = LogManager.getLogger("HelperToolsID");
		
		
		///////////////////////////////
		/** Configuration calls **/
		//////////////////////////
		//Tool durability
		public static int  DurabilityExpandingRod;
		public static int  DurabilityMetamorphicStaff;
		public static int  DurabilityTorchLauncher;
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
		//ExpStaff
		public static boolean RecipeEmeraldsForExpansionStaff;
		public static boolean RecipeDiamondsForExpansionStaff;
		public static boolean RecipePearlsForExpansionStaff;
		//MetaStaff
		public static boolean RecipeEmeraldsForMetamorphicStaff;
		public static boolean RecipeDiamondsForMetamorphicStaff;
		public static boolean RecipePearlsForMetamorphicStaff;
		//Torch Launcher
		public static boolean RecipeTorchLauncher;
		public static boolean RecipeStringForDynamiteBolt;		
		public static boolean RecipeSlimeForDynamiteBolt;
		//Blocks
		public static boolean RecipeImitationBedrock;
		public static boolean RecipeMagicalFuel;
		public static boolean RecipeChimenyPipes;
		//Items
		public static boolean RecipeDirtBomb;
		
		
		
        // The instance of your mod that Forge uses.
        @Instance(value = "HelperToolsID")
        public static Helpertoolscore instance;
       
        
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="helpertools.HelpertoolsclientProxy", serverSide="helpertools.HelpertoolsCommonProxy")
        public static HelpertoolsCommonProxy proxy;
        
               
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	
        	////////////////////
        	/** Config Core **/
        	//////////////////       	        	
        	
        	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        	
        	config.load();
        	
        	logger.info("Loading Configs");
        	//Tools
        	DurabilityExpandingRod = config.get(Configuration.CATEGORY_GENERAL, "DurabilityExpandingRod", 1024).getInt();
        	DurabilityMetamorphicStaff = config.get(Configuration.CATEGORY_GENERAL, "DurabilityMetamorphicStaff", 1024).getInt();
        	DurabilityTorchLauncher = config.get(Configuration.CATEGORY_GENERAL, "DurabilityTorchLauncher", 1428).getInt();
        	//Items
        	OutputDynamiteBolt = config.get(Configuration.CATEGORY_GENERAL, "OutputDynamiteBolt", 4).getInt();
        	//Blocks
        	OutputImitationBedrock = config.get(Configuration.CATEGORY_GENERAL, "OutputImitationBedrock", 4).getInt();
        	OutputChimneyPipe = config.get(Configuration.CATEGORY_GENERAL, "OutputChimneyPipe", 8).getInt();
        	OutputMagicalFuel = config.get(Configuration.CATEGORY_GENERAL, "OutputMagicalFuel", 1).getInt();
        	OutputDirtBomb = config.get(Configuration.CATEGORY_GENERAL, "OutputDirtBomb", 4).getInt();
        	//Boolean Enables
        	/**3D models **/
        	Render3DStaffModels = config.get(Configuration.CATEGORY_GENERAL, "Render3DStaffModels", true).getBoolean(true);
        	Render3DCrossbowModel = config.get(Configuration.CATEGORY_GENERAL, "Render3DCrossbowModel", true).getBoolean(true);
        	//Expansion Staff Recipes
        	RecipeDiamondsForExpansionStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipeDiamondsForExpansionStaff", true).getBoolean(true);
        	RecipeEmeraldsForExpansionStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipeEmeraldsForExpansionStaff", true).getBoolean(true);
        	RecipePearlsForExpansionStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipePearlsForExpansionStaff", true).getBoolean(true);
        	//Metamorphic Staff Recipes
        	RecipeDiamondsForMetamorphicStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipeDiamondsForMetamorphicStaff", true).getBoolean(true);
        	RecipeEmeraldsForMetamorphicStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipeEmeraldsForMetamorphicStaff", true).getBoolean(true);
        	RecipePearlsForMetamorphicStaff = config.get(Configuration.CATEGORY_GENERAL, "RecipePearlsForMetamorphicStaff", true).getBoolean(true);
        	//Torch Launcher
        	RecipeTorchLauncher = config.get(Configuration.CATEGORY_GENERAL, "RecipeTorchLauncher", true).getBoolean(true);
        	//Dynamite Bolt
        	RecipeStringForDynamiteBolt = config.get(Configuration.CATEGORY_GENERAL, "RecipeStringForDynamiteBolt", true).getBoolean(true);
        	RecipeSlimeForDynamiteBolt = config.get(Configuration.CATEGORY_GENERAL, "RecipeSlimeForDynamiteBolt", true).getBoolean(true);
        	//Blocks
        	RecipeImitationBedrock = config.get(Configuration.CATEGORY_GENERAL, "RecipeImitationBedrock", true).getBoolean(true);
        	RecipeMagicalFuel = config.get(Configuration.CATEGORY_GENERAL, "RecipeMagicalFuel", true).getBoolean(true);
        	RecipeChimenyPipes = config.get(Configuration.CATEGORY_GENERAL, "RecipeChimenyPipes", true).getBoolean(true);
        	//Items
        	RecipeDirtBomb = config.get(Configuration.CATEGORY_GENERAL, "RecipeDirtBomb", false).getBoolean(true);
        	
        	config.save(); 
        	logger.info("Configurations Saved");
        	
        	//////////////////////////////
    		/**Material Configurations**/
        	////////////////////////////
        	
    		ToolMaterial helpMaterial = EnumHelper.addToolMaterial("helpMaterial", 0, 1024, 0.8F, 4F, 15); 
    		
    		ToolMaterial ExpRodMaterial = EnumHelper.addToolMaterial("ExpRodMaterial", 0, DurabilityExpandingRod, 0.8F, 4F, 15);
    		ToolMaterial MetaStaffMaterial = EnumHelper.addToolMaterial("MetaStaffMaterial", 0, DurabilityMetamorphicStaff, 0.8F, 4F, 15);
    		ToolMaterial TorchMaterial = EnumHelper.addToolMaterial("TorchMaterial", 0, DurabilityTorchLauncher, 0.8F, 4F, 15);
    		//name, harvest level, max uses, efficiency, damage, enchantability
        	
        	
            
        	  ////////////////
        	 /**  Casts   **/
        	////////////////
        	//Items & Tools - Items & Tools - Items & Tools - Items & Tools - Items & Tools 
        	
        	staffofexpansion = new ItemStaffofExpansion(ExpRodMaterial);        	
        	staffoftransformation2 = new ItemStaffofTransformation2(MetaStaffMaterial);        	
        	torchlauncher = new ItemTorchLauncher(TorchMaterial);        	
        	euclideantransposer = new ItemEuclideanTransposer(MetaStaffMaterial);        	
        	//debugtool = new ItemDebugTool();	
        	dynamitebolt = new ItemDynamiteBolt();        	
        	dirtbomb = new ItemDirtBomb();
        	bottledmilk = new ItemMilkBottle();
        	chocolatemilk = new ItemChocolateMilk( 3, 0.5f, true).setAlwaysEdible();
        	
        	
        	//////////////
        	/**Forge Handlers**/
        	FMLCommonHandler.instance().bus().register(Helpertoolscore.eventHandler);
        	MinecraftForge.EVENT_BUS.register(Helpertoolscore.eventHandler);
        	
        	
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                
              ////////////////
           	 /**Registries**/
           	////////////////
                
                //Items - Items - Items - Items - Items - Items - Items
                ///////////////////////////////////////////////////////
             	
                GameRegistry.registerItem(Helpertoolscore.staffofexpansion, "staffofexpansion");               
                GameRegistry.registerItem(Helpertoolscore.staffoftransformation2, "staffoftransformation2");                
                GameRegistry.registerItem(Helpertoolscore.torchlauncher, "torchlauncher");                
                GameRegistry.registerItem(Helpertoolscore.euclideantransposer, "euclideantransposer");
             
                //GameRegistry.registerItem(Helpertoolscore.debugtool, "debugtool");
                //LanguageRegistry.addName(Helpertoolscore.debugtool, "Debugging Tool");
                
               
                
                //Consumable Items//
                ////////////////////
                GameRegistry.registerItem(Helpertoolscore.dynamitebolt, "dynamitebolt");
                
                GameRegistry.registerItem(Helpertoolscore.dirtbomb, "dirtbomb");
                
                GameRegistry.registerItem(Helpertoolscore.bottledmilk, "bottledmilk");
                GameRegistry.registerItem(Helpertoolscore.chocolatemilk, "chocolatemilk");
                //GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk( 2, 0.2f, false ).setAlwaysEdible(), "chocolatemilk");
              
                
                //Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
                //////////////////////////////////////////////////////
                GameRegistry.registerBlock(Ibedrock, "Imitation Bedrock");
                GameRegistry.registerBlock(StokedPipe, "StokedPipe");
                GameRegistry.registerBlock(StokedBlock, "StokedBlock");
                //GameRegistry.registerBlock(SelectionBlock, "SelectionBlock");
                GameRegistry.registerBlock(TranscriberBlock, "TranscriberBlock");
                GameRegistry.registerBlock(MagicalFuelBlock, "MagicalFuelBlock");
                GameRegistry.registerBlock(ActiveMagicalFuelBlock, "ActiveMagicalFuelBlock");
                GameRegistry.registerBlock(LooseDirtBlock,  "LooseDirtBlock");
               
                
                //Entities - Entities - Entities - Entities - Entities - Entities
                /////////////////////////////////////////////////////////////////
                EntityRegistry.registerModEntity(EntityTorchProjectile.class, "TorchBolt", 10, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityRedTorchProjectile.class, "RedTorchBolt", 11, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityDynamiteProjectile.class, "DynamiteBolt", 12, this, 350, 30, true);
                                
                EntityRegistry.registerModEntity(EntityBoltProjectile.class, "CrossBolt", 13, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityDirtBombProjectile.class, "DirtBomb", 14, this, 350, 30, true);
                //class, stringname, entity ID, thisobject, loading range, update polls, velocity updates
        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
        	
        	

        	/**Recipes Tool //Recipes Tools	//Recipes Tools	//Recipes Tools	//Recipes Tools **/
        	
        	//None Ore dictionary version
        	
        	//GameRegistry.addRecipe(new ItemStack(staffofexpansion), new Object[]
        			//{"XZX",
        			//" Y ",
        			//" Y ",
        			//'X', Items.iron_ingot, 'Y', Items.stick, 'Z', Items.diamond
        			//});
        	
        	
        	//Expansion Recipes
        	///////////////////
        	if(RecipeDiamondsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'),"gemDiamond",
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	if(RecipeEmeraldsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'),"gemEmerald",
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	if(RecipePearlsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'), Items.ender_pearl,
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	//Transformation Recipes
        	/////////////////////////
        	if(RecipeDiamondsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'),"gemDiamond",
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	if(RecipeEmeraldsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'),"gemEmerald",
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	if(RecipePearlsForExpansionStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
        		"XZX",
    			" Y ",
    			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'), Items.ender_pearl,
    			Character.valueOf('Y'),"stickWood"}));
        	}
        	        	
        	//Torch Launcher Recipes
        	////////////////////////       
        	if(RecipeTorchLauncher == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(torchlauncher),true, new Object[]{
        		"   ",
        		"FBR",
        		"   ", Character.valueOf('B'), Items.bow, Character.valueOf('F'), Items.flint_and_steel,
    			 Character.valueOf('R'), "plankWood"}));
        	
        	
        	//Torch Launcher Alternate Recipes
        	//////////////////////////////////        	
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(torchlauncher),true, new Object[]{
        		"FWS",
        		"W S",
        		"RWS", Character.valueOf('S'), "helpstring", Character.valueOf('F'), Items.flint_and_steel,
        		Character.valueOf('R'), "plankWood",
    			Character.valueOf('W'), "stickWood"}));
        	
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(torchlauncher,1, 0), new Object[]{
        		Items.bow, Items.flint_and_steel, "plankWood"}));
        	}
        	
        	
        	//Dynamite bolt Recipes
        	////////////////////////
        	if(RecipeStringForDynamiteBolt == true){
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.dynamitebolt, OutputDynamiteBolt, 0), new Object[]{
        		Blocks.tnt, Items.arrow, Items.arrow, Items.arrow,Items.arrow, "string"}));
        	}
        	if(RecipeSlimeForDynamiteBolt == true){
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.dynamitebolt, OutputDynamiteBolt, 0), new Object[]{
        		Blocks.tnt, Items.arrow, Items.arrow, Items.arrow, Items.arrow, "slimeball"}));
        	}
        	
        	
        	
        	
        	//Imitation bedrock recipes
        	
        	//GameRegistry.addShapelessRecipe(new ItemStack(Helpertoolscore.Ibedrock, 2 , 0), new Object[]
        	//		{ Items.redstone, Blocks.sandstone, Blocks.cobblestone, Blocks.gravel
        	//		});
        	
        	/*
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.Ibedrock, 2 , 0), new Object[]{
        		"dustRedstone", "cobblestone", "sandstone", "gravel" }));
        	*/
        	if(RecipeImitationBedrock == true){
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.Ibedrock, OutputImitationBedrock , 0), new Object[]{
        		"cobblestone", "cobblestone", "cobblestone", Blocks.coal_block }));
        	}
        	if(RecipeChimenyPipes == true){
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.StokedPipe, OutputChimneyPipe , 0), new Object[]{
        		"ingotIron", Items.coal, "ingotIron"}));
        	}
        	if(RecipeMagicalFuel == true){
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.MagicalFuelBlock, OutputMagicalFuel , 0), new Object[]{
        		"ingotIron", "helpbonemeal", "gemLapis"}));
        	}
        	//Debugging tool
        	/**          	
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.debugtool, 1 , 0), new Object[]{
        		Items.nether_star, Blocks.bedrock, }));
        		**/
        	if(RecipeDirtBomb == true){
            	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(dirtbomb, OutputDirtBomb, 0), true, new Object[]{
            		"ZZZ",
        			"ZXZ",
        			"ZZZ", Character.valueOf('X'), Items.gunpowder, Character.valueOf('Z'), Blocks.dirt}));
            	}
        	
        	ItemStack BucketOut = new ItemStack(Items.milk_bucket.setContainerItem(Items.bucket));
        	//ItemStack BottleOut = new ItemStack(Helpertoolscore.bottledmilk.setContainerItem(Items.glass_bottle));
        	//I don't know but once set it overrides it for every other recipe, setting it to null only override the other.
        	//.oncreated --consume item seems to balance this with only a minor dupe bug for the first in the inventory
        	//Recipes shouldn't require this much effort, what the hell
        	ItemStack Bottledestroy = new ItemStack(Helpertoolscore.bottledmilk.setContainerItem(null));
        	
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.bottledmilk, 1 , 0), new Object[]{
        		 BucketOut, Items.glass_bottle}));
        	
        	//GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.milk_bucket, 1 , 0), new Object[]{
        	//	BottleOut, Items.bucket}));
        	
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.chocolatemilk, 1 , 0), new Object[]{
        		Bottledestroy, new ItemStack(Items.dye, 1, 3)}));
        	
        	//podzol recipe
        	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.dirt, 1 , 2), new Object[]{
        		Blocks.dirt, "treeLeaves"}));
        	
        	
        	
        	
        	/**Extra Dictionaries**/
        	///////////////////////
        	OreDictionary.registerOre("helpstring", Items.string);
        	 OreDictionary.registerOre("helpgravel", Blocks.gravel);
        	 OreDictionary.registerOre("helpbonemeal", new ItemStack(Items.dye, 1, 15));
        	//  
        	 
        }
         
}