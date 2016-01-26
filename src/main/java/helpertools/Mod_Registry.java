package helpertools;

import java.util.Set;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import helpertools.blocks.Jelly_Fluid_Block;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.entities.BombProjectile_Entity;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityGravelBombProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntitySandBombProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.entities.Entity_Extraction_Balloon;
import helpertools.item_blocks.Charm_block_item;
import helpertools.item_blocks.Floater_block_item;
import helpertools.item_blocks.Item_Block_Balloon;
import helpertools.item_blocks.Item_Block_Magical_Lamp;
import helpertools.item_blocks.Item_block_Chimney;
import helpertools.item_blocks.TranscriberBlock_Item;
import helpertools.items.Armor_Mystic;
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDirtBomb;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemMilkBottle;
import helpertools.items.ItemSandBomb;
import helpertools.items.Item_Block_Bomb;
import helpertools.items.Item_Extraction_Balloon;
import helpertools.items.Item_creative_builder_charm;
import helpertools.items.Item_debug;
import helpertools.items.Item_jelly_Bucket;
import helpertools.tools.ItemEuclideanTransposer;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.tools.ItemTorchLauncher;
import helpertools.tools.Item_Bubblegun;
import helpertools.tools.Item_Bubblegun_2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class Mod_Registry extends Mod_Configuration{
	
	/** Tools **/
	public final static Set properharvest = Sets.newHashSet(new Block[] {Blocks.leaves, Blocks.leaves2, Blocks.web, Blocks.wool, 
			Blocks.trapped_chest, Blocks.chest, Blocks.bookshelf});
	
	public static Item staffofexpansion;  
	public static Item staffoftransformation2;
	public static Item euclideantransposer;
	public static Item torchlauncher;	
	
	//public static Item bubblegun;
	//public static Item bubblegun_2;
	
	/** Items **/	
	public static Item dynamitebolt;
	//public static Item dirtbomb;
	public static Item sandbomb;
	public static Item gravelbomb;
	public static Item bottledmilk;
	public static Item chocolatemilk;	
	//public static Item powercrystal;	
	//public static Item debugtool;
	//public static Item rfdebugtool;
	public static Item item_debug;
	
	/** Armor **/
	public static Item mystic_mask;
	
	
	public static Item bomb;
	public static Item cbc;
	public static Item extraction_balloon;
	
	/** Fluids **/
	//public static Fluid jelly_fluid = new Fluid("jelly");
	//public static Block jelly_block;		
	//public static Item jelly_bucket = new Item_jelly_Bucket(jelly_block);
	
	/** Blocks **/
	public final static Block Ibedrock = new helpertools.blocks.ImitationBedrock();
	public final static Block StokedPipe = new helpertools.blocks.StokedPipe();		
	public final static Block TranscriberBlock = new helpertools.blocks.TranscriberBlock();	
	public final static Block MagicalFuelBlock = new helpertools.blocks.MagicalFuelBlock();	
	public final static Block ActiveMagicalFuelBlock = new helpertools.blocks.ActiveMagicalFuelBlock();	
	public final static Block LooseDirtBlock = new helpertools.blocks.LooseDirtBlock(Material.sand);	
	//public final static Block IllusionBlock = new helpertools.blocks.IllusionBlock(Material.glass, false, "helpertools:IllusionFace");	
	//public final static Block SugarBlock = new helpertools.blocks.SugarBlock(Material.sand);
	//public final static Block TransitionGlass = new helpertools.blocks.TransitionGlass(Material.glass, false, "helpertools:TransitionGlassOn");
	//public final static Block ObeliskBlock = new helpertools.blocks.ObeliskBlock();
	public final static Block Charm_block = new helpertools.blocks.Charm_block("Charm_block");
	public final static Block Balloon = new helpertools.blocks.Balloon_Block("Balloon_block");
	public final static Block Floater = new helpertools.blocks.Floater_Block("Floater_block");
	
	
	
	//=====================================================================================//
	
	/** Register things **/
	public static void create_Items() {
		Main.logger.info("Registering items");
		
		
		/** Tools **/
		staffofexpansion = new ItemStaffofExpansion(ExpRodMaterial);        	
    	staffoftransformation2 = new ItemStaffofTransformation2(MetaStaffMaterial); 
    	euclideantransposer = new ItemEuclideanTransposer(EUStaffMaterial); 
    	torchlauncher = new ItemTorchLauncher(TorchMaterial);  
		GameRegistry.registerItem(staffofexpansion, "staffofexpansion");               
        GameRegistry.registerItem(staffoftransformation2, "staffoftransformation2");                
        GameRegistry.registerItem(torchlauncher, "torchlauncher");                
        GameRegistry.registerItem(euclideantransposer, "euclideantransposer");   
        
        
        //GameRegistry.registerItem(Helpertoolscore.debugtool, "debugtool");
        //GameRegistry.registerItem(Helpertoolscore.rfdebugtool, "rfdebugtool");
        //LanguageRegistry.addName(Helpertoolscore.debugtool, "Debugging Tool");	
        
        //bubblegun = new Item_Bubblegun(jelly_block);
        //GameRegistry.registerItem(bubblegun, "bubblegun");
        
        //bubblegun_2 = new Item_Bubblegun_2(965, 16000);
        //GameRegistry.registerItem(bubblegun_2, "bubblegun_2");

        /** Items **/
        dynamitebolt = new ItemDynamiteBolt();
    	bottledmilk = new ItemMilkBottle();
    	chocolatemilk = new ItemChocolateMilk( 3, 0.5f, true).setAlwaysEdible();
        GameRegistry.registerItem(dynamitebolt, "dynamitebolt");  
        GameRegistry.registerItem(bottledmilk, "bottledmilk");
        GameRegistry.registerItem(chocolatemilk, "chocolatemilk");
        
        GameRegistry.registerItem(item_debug = new Item_debug("item_debug"), "item_debug");
        
        bomb = new Item_Block_Bomb("bomb");
        GameRegistry.registerItem(bomb, "bomb");
        
        cbc = new Item_creative_builder_charm("cbc");
        GameRegistry.registerItem(cbc, "cbc");
        
                
        GameRegistry.registerItem(extraction_balloon = new Item_Extraction_Balloon("extraction_balloon"), "extraction_balloon");
        
        /** Armor **/
        GameRegistry.registerItem(mystic_mask = 
        		new Armor_Mystic("mystic_mask", Mystic_Material, "mystic", 0), "mystic_mask");
        
        //GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk( 2, 0.2f, false ).setAlwaysEdible(), "chocolatemilk");
        //GameRegistry.registerItem(Helpertoolscore.powercrystal, "powercrystal");
        		
     	/** Fluids **/   
        /**
        FluidRegistry.registerFluid(jelly_fluid); 
        jelly_block = new Jelly_Fluid_Block(jelly_fluid, Material.water).setBlockName("jelly");
        GameRegistry.registerBlock(jelly_block, "helpertools" + "_" + jelly_block.getUnlocalizedName().substring(5));
        jelly_fluid.setUnlocalizedName(jelly_block.getUnlocalizedName());                  
        //bucket
        jelly_bucket.setUnlocalizedName("jelly_bucket").setContainerItem(Items.bucket);
        GameRegistry.registerItem(jelly_bucket, "jelly_bucket");
        FluidContainerRegistry.registerFluidContainer(jelly_fluid, new ItemStack(jelly_bucket), new ItemStack(Items.bucket));
		**/
        
        /** Blocks **/
        GameRegistry.registerBlock(Ibedrock, "Imitation Bedrock");
        GameRegistry.registerBlock(StokedPipe, Item_block_Chimney.class, "StokedPipe");
        //GameRegistry.registerBlock(StokedBlock, "StokedBlock");
        GameRegistry.registerBlock(TranscriberBlock, TranscriberBlock_Item.class, "TranscriberBlock");
        GameRegistry.registerBlock(MagicalFuelBlock, Item_Block_Magical_Lamp.class, "MagicalFuelBlock");
        GameRegistry.registerBlock(ActiveMagicalFuelBlock,  "ActiveMagicalFuelBlock");
        GameRegistry.registerBlock(LooseDirtBlock,  "LooseDirtBlock");
        //GameRegistry.registerBlock(IllusionBlock, "IllusionBlock");   
        //GameRegistry.registerBlock(SugarBlock, "SugarBlock");
        //GameRegistry.registerBlock(TransitionGlass, "TransitionGlass");
        //GameRegistry.registerBlock(ObeliskBlock, "ObeliskBlock");
        GameRegistry.registerBlock(Charm_block, Charm_block_item.class, "Charm_block");
        GameRegistry.registerBlock(Balloon, Item_Block_Balloon.class , "Balloon_block");
        GameRegistry.registerBlock(Floater, Floater_block_item.class, "Floater_block");
		
	}
	
	//===========================================================================================//
	
	/** Register Entities **/
	public static void create_Entities() {
		Main.logger.info("Registering Entities");
		
		/** Entities **/
        EntityRegistry.registerModEntity(EntityTorchProjectile.class, "TorchBolt", 10, instance, 350, 10, true);
        EntityRegistry.registerModEntity(EntityRedTorchProjectile.class, "RedTorchBolt", 11, instance, 350, 10, true);
        EntityRegistry.registerModEntity(EntityDynamiteProjectile.class, "DynamiteBolt", 12, instance, 350, 30, true);
                        
        EntityRegistry.registerModEntity(EntityBoltProjectile.class, "CrossBolt", 13, instance, 350, 10, true);
        EntityRegistry.registerModEntity(EntityDirtBombProjectile.class, "DirtBomb", 14, instance, 350, 30, true);
        EntityRegistry.registerModEntity(EntitySandBombProjectile.class, "SandBomb", 15, instance, 350, 30, true);
        EntityRegistry.registerModEntity(EntityGravelBombProjectile.class, "GravelBomb", 16, instance, 350, 30, true);
        
        EntityRegistry.registerModEntity(BombProjectile_Entity.class, "Bomb", 17, instance, 350, 30, true);
        //class, stringname, entity ID, thisobject, loading range, update polls, velocity updates
        
        EntityRegistry.registerModEntity(Entity_Extraction_Balloon.class, "Extraction_Balloon", 18, instance, 350, 30, true);
        EntityList.addMapping(Entity_Extraction_Balloon.class, "Extraction_Balloon", 3539, 0xB3B4C4, 0x715A75);
        
        //EntityRegistry.registerGlobalEntityID(Entity_Extraction_Balloon.class, "Extraction_Balloon", 35, 0x14D21E, 0x58595C);
       
		/** Tile Ents **/        
	    GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
	    //GameRegistry.registerTileEntity(TileEntityIllusion.class, TileEntityIllusion.publicName);
	    //GameRegistry.registerTileEntity(TileEntityObelisk.class, TileEntityObelisk.publicName);
	}
		
		
	

	

}
