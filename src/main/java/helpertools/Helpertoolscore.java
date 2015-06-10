package helpertools;
import helpertools.blocks.TileEntityObelisk;
import helpertools.blocks.TileEntityTranscriber;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.gui.GuiBuffBar;
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDirtBomb;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemMilkBottle;
import helpertools.items.ItemPowerCrystal;
import helpertools.renders.ItemRenderStaff4;
import helpertools.renders.ItemRenderStaff5;
import helpertools.renders.ItemRenderTorchLauncher1;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helpertools.tools.ItemDebugTool;
import helpertools.tools.ItemEuclideanTransposer;
import helpertools.tools.ItemRfDebugTool;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.tools.ItemTorchLauncher;
import helpertools.util.ForgeEventHandler;
import helpertools.util.GuiHandler;
import helpertools.util.KeyBindings;
import helpertools.util.KeyInputHandler;
import net.java.games.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="HelperToolsID", name="HelperTools", version="1.1.5I")
public class Helpertoolscore {
	
		
	
		//Blocks - Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
		public final static Block Ibedrock = new helpertools.blocks.ImitationBedrock();
		public final static Block StokedPipe = new helpertools.blocks.StokedPipe();
		//public final static Block StokedBlock = new helpertools.blocks.StokedBlock();
		public final static Block SelectionBlock = new helpertools.blocks.SelectionBlock();		
		public final static Block TranscriberBlock = new helpertools.blocks.TranscriberBlock();	
		public final static Block MagicalFuelBlock = new helpertools.blocks.MagicalFuelBlock();	
		public final static Block ActiveMagicalFuelBlock = new helpertools.blocks.ActiveMagicalFuelBlock();	
		public final static Block LooseDirtBlock= new helpertools.blocks.LooseDirtBlock(Material.sand);
		
		public final static Block SugarBlock= new helpertools.blocks.SugarBlock(Material.sand);
		public final static Block TransitionGlass = new helpertools.blocks.TransitionGlass(Material.glass, false, "helpertools:TransitionGlassOn");
		public final static Block ObeliskBlock = new helpertools.blocks.ObeliskBlock();
		
	 	//Items & Tools - Items & Tools - Items & Tools - Items & Tools - Items & Tools 
		public static Item staffofexpansion;  
		public static Item staffoftransformation2;
		public static Item euclideantransposer;
		public static Item torchlauncher;
		
		
		public static Item dynamitebolt;
		public static Item dirtbomb;
		public static Item bottledmilk;
		public static Item chocolatemilk;
		//
		public static Item powercrystal;
		
		public static Item debugtool;
		public static Item rfdebugtool;
		
    			
		//Forge Support
		public static ForgeEventHandler eventHandler = new ForgeEventHandler();
		//public static ForgeEventHandler eventHandler2 = new ForgeEventHandler();
		public static final Logger logger = LogManager.getLogger("HelperToolsID");
		
		
		///////////////////////////////
		/** Configuration calls **/
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
		public static boolean RecipePodzol;
		//Items
		public static boolean RecipeDirtBomb;
		public static boolean RecipeBottledmilk;
		public static boolean RecipeChocolatemilk;
		//Handler enables
		public static boolean HandlerBottledmilk;
		
		/** @ NotGyro **/
		public static Configuration config;
		
		
		
        // The instance of your mod that Forge uses.
        @Instance(value = "HelperToolsID")
        public static Helpertoolscore instance;
       
        
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="helpertools.HelpertoolsclientProxy", serverSide="helpertools.HelpertoolsCommonProxy")
        public static HelpertoolsCommonProxy proxy;
        
               
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	
        	/////////////////////////////////////
        	/** Configuration assembly moved **/
        	///////////////////////////////////        	
        	ConfigurationFactory.ProcessConfiguration(event);
            
        	
        	///////////////////
        	/** Keybinding **/
        	/////////////////
        	FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        	KeyBindings.init();
        	
        	
        	//////////////////////////////
    		/**Material Configurations**/
        	////////////////////////////
        	
    		ToolMaterial helpMaterial = EnumHelper.addToolMaterial("helpMaterial", 0, 1024, 0.8F, 4F, 15); 
    		
    		ToolMaterial ExpRodMaterial = EnumHelper.addToolMaterial("ExpRodMaterial", 0, DurabilityExpandingRod, 0.8F, 4F, 15);
    		ToolMaterial MetaStaffMaterial = EnumHelper.addToolMaterial("MetaStaffMaterial", 0, DurabilityMetamorphicStaff, 0.8F, 4F, 15);
    		ToolMaterial EUStaffMaterial = EnumHelper.addToolMaterial("EUStaffMaterial", 0, DurabilityEuclideanStaff, 0.8F, 4F, 15);
    		ToolMaterial TorchMaterial = EnumHelper.addToolMaterial("TorchMaterial", 0, DurabilityTorchLauncher, 0.8F, 4F, 15);
    		//name, harvest level, max uses, efficiency, damage, enchantability
        	
    		
    		///////////////////////
    		/** Repair Materials **/
    		///////////////////////
    		powercrystal = new ItemPowerCrystal();
    		ExpRodMaterial.setRepairItem(new ItemStack(powercrystal));
    		MetaStaffMaterial.setRepairItem(new ItemStack(powercrystal));
    		EUStaffMaterial.setRepairItem(new ItemStack(powercrystal));
    		TorchMaterial.setRepairItem(new ItemStack(Items.stick));
    		
            
        	  ////////////////
        	 /**  Casts   **/
        	////////////////
        	//Items & Tools - Items & Tools - Items & Tools - Items & Tools - Items & Tools 
        	
        	staffofexpansion = new ItemStaffofExpansion(ExpRodMaterial);        	
        	staffoftransformation2 = new ItemStaffofTransformation2(MetaStaffMaterial); 
        	euclideantransposer = new ItemEuclideanTransposer(EUStaffMaterial); 
        	torchlauncher = new ItemTorchLauncher(TorchMaterial);        	
        	
        	debugtool = new ItemDebugTool();	
        	rfdebugtool = new ItemRfDebugTool();
        	dynamitebolt = new ItemDynamiteBolt();        	
        	dirtbomb = new ItemDirtBomb();
        	bottledmilk = new ItemMilkBottle();
        	chocolatemilk = new ItemChocolateMilk( 3, 0.5f, true).setAlwaysEdible();
        	
        	//powercrystal = new ItemPowerCrystal();
        	
        	
        	//////////////
        	/**Forge Handlers**/
        	
        	if(HandlerBottledmilk == true){
        	FMLCommonHandler.instance().bus().register(Helpertoolscore.eventHandler);
        	MinecraftForge.EVENT_BUS.register(Helpertoolscore.eventHandler);
        	}
        	GameRegistry.registerFuelHandler(new HelperFuel());
        	
        	MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Minecraft.getMinecraft()));
        	//MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Helpertoolscore.eventHandler));
        	
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
             
                GameRegistry.registerItem(Helpertoolscore.debugtool, "debugtool");
                GameRegistry.registerItem(Helpertoolscore.rfdebugtool, "rfdebugtool");
                //LanguageRegistry.addName(Helpertoolscore.debugtool, "Debugging Tool");
                
               
                
                //Consumable Items//
                ////////////////////
                GameRegistry.registerItem(Helpertoolscore.dynamitebolt, "dynamitebolt");
                
                GameRegistry.registerItem(Helpertoolscore.dirtbomb, "dirtbomb");
                
                GameRegistry.registerItem(Helpertoolscore.bottledmilk, "bottledmilk");
                GameRegistry.registerItem(Helpertoolscore.chocolatemilk, "chocolatemilk");
                //GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk( 2, 0.2f, false ).setAlwaysEdible(), "chocolatemilk");
                GameRegistry.registerItem(Helpertoolscore.powercrystal, "powercrystal");
                
                //Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
                //////////////////////////////////////////////////////
                GameRegistry.registerBlock(Ibedrock, "Imitation Bedrock");
                GameRegistry.registerBlock(StokedPipe, "StokedPipe");
                //GameRegistry.registerBlock(StokedBlock, "StokedBlock");
                //GameRegistry.registerBlock(SelectionBlock, "SelectionBlock");
                GameRegistry.registerBlock(TranscriberBlock, "TranscriberBlock");
                GameRegistry.registerBlock(MagicalFuelBlock, "MagicalFuelBlock");
                GameRegistry.registerBlock(ActiveMagicalFuelBlock, "ActiveMagicalFuelBlock");
                GameRegistry.registerBlock(LooseDirtBlock,  "LooseDirtBlock");
                //
                GameRegistry.registerBlock(SugarBlock, "SugarBlock");
                GameRegistry.registerBlock(TransitionGlass, "TransitionGlass");
                GameRegistry.registerBlock(ObeliskBlock, "ObeliskBlock");
               
                
                //Entities - Entities - Entities - Entities - Entities - Entities
                /////////////////////////////////////////////////////////////////
                EntityRegistry.registerModEntity(EntityTorchProjectile.class, "TorchBolt", 10, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityRedTorchProjectile.class, "RedTorchBolt", 11, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityDynamiteProjectile.class, "DynamiteBolt", 12, this, 350, 30, true);
                                
                EntityRegistry.registerModEntity(EntityBoltProjectile.class, "CrossBolt", 13, this, 350, 10, true);
                EntityRegistry.registerModEntity(EntityDirtBombProjectile.class, "DirtBomb", 14, this, 350, 30, true);
                //class, stringname, entity ID, thisobject, loading range, update polls, velocity updates
                
               
       			//Tile Entities - Tile Entities - Tile Entities - Tile Entities
                //////////////////////////////////////////////////////////////////////////////////////////////
                
        	    GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
        	    GameRegistry.registerTileEntity(TileEntityObelisk.class, TileEntityObelisk.publicName);
        	    
        	   //     Helpertoolscore.logger.info("TILE ENTITY");
        	    
        	    
        	    //DungeonHooks.addDungeonLoot(new ItemStack(youritem), 10, 2, 5);
        	    //ChestGenHooks.addDungeonLoot(ChestGenHooks(String category, WeightedRandomChestContent[] items, int min, int max));
        	    //(ChestGenHooks("villageBlacksmith"), (new ItemStack(Helpertoolscore.staffoftransformation2)) , 100, 1, 1);
               addLoot();
               NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
              
        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
        	
        	////////////////////////////////////////
        	/** Recipes Moved to their own path **/
        	///////////////////////////////////////
        	RecipeFactory.RegisterRecipes();
        }
        
        //Chest Loot
        /** @ Tcon**/
        public void addLoot()
        
        {	//Min stack, Max stack, Weight/rarity
        	//ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.staffoftransformation2), 1, 1, 10));
        	ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.chocolatemilk), 1, 8, 5));
        	//ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.dynamitebolt), 1, 3, 50));
        	logger.info("Chest Things Loaded");
        }
        
       
         
}