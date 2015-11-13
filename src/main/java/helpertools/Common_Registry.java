package helpertools;

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
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDirtBomb;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemGravelBomb;
import helpertools.items.ItemMilkBottle;
import helpertools.items.ItemSandBomb;
import helpertools.items.Item_Block_Bomb;
import helpertools.items.Item_creative_builder_charm;
import helpertools.items.Item_jelly_Bucket;
import helpertools.tools.ItemEuclideanTransposer;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.tools.ItemTorchLauncher;
import helpertools.tools.Item_Bubblegun;
import helpertools.tools.Item_Bubblegun_2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class Common_Registry extends ConfigurationFactory{
	
	/** Tools **/
	public static Item staffofexpansion;  
	public static Item staffoftransformation2;
	public static Item euclideantransposer;
	public static Item torchlauncher;	
	
	public static Item bubblegun;
	public static Item bubblegun_2;
	
	/** Items **/	
	public static Item dynamitebolt;
	public static Item dirtbomb;
	public static Item sandbomb;
	public static Item gravelbomb;
	public static Item bottledmilk;
	public static Item chocolatemilk;	
	//public static Item powercrystal;	
	//public static Item debugtool;
	//public static Item rfdebugtool;
	
	public static Item bomb;
	public static Item cbc;
	
	/** Fluids **/
	public static Fluid jelly_fluid = new Fluid("jelly");
	public static Block jelly_block;		
	public static Item jelly_bucket = new Item_jelly_Bucket(jelly_block);
	
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
	
	
	
	
	//=====================================================================================//
	
	/** Register things **/
	public static void create_Items() {
		Helpertoolscore.logger.info("Registering items");
		
		
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
        bubblegun = new Item_Bubblegun(jelly_block);
        GameRegistry.registerItem(bubblegun, "bubblegun");
        
        bubblegun_2 = new Item_Bubblegun_2(965, 16000);
        GameRegistry.registerItem(bubblegun_2, "bubblegun_2");

        /** Items **/
        dynamitebolt = new ItemDynamiteBolt();        	
    	dirtbomb = new ItemDirtBomb();
    	sandbomb = new ItemSandBomb();
    	gravelbomb = new ItemGravelBomb();
    	bottledmilk = new ItemMilkBottle();
    	chocolatemilk = new ItemChocolateMilk( 3, 0.5f, true).setAlwaysEdible();
        GameRegistry.registerItem(dynamitebolt, "dynamitebolt");        
        GameRegistry.registerItem(dirtbomb, "dirtbomb");   
        GameRegistry.registerItem(sandbomb, "sandbomb"); 
        GameRegistry.registerItem(gravelbomb, "gravelbomb"); 
        GameRegistry.registerItem(bottledmilk, "bottledmilk");
        GameRegistry.registerItem(chocolatemilk, "chocolatemilk");
        
        bomb = new Item_Block_Bomb("bomb");
        GameRegistry.registerItem(bomb, "bomb");
        
        cbc = new Item_creative_builder_charm("cbc");
        GameRegistry.registerItem(cbc, "cbc");
        
        //GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk( 2, 0.2f, false ).setAlwaysEdible(), "chocolatemilk");
        //GameRegistry.registerItem(Helpertoolscore.powercrystal, "powercrystal");
        		
     	/** Fluids **/        
        FluidRegistry.registerFluid(jelly_fluid); 
        jelly_block = new Jelly_Fluid_Block(jelly_fluid, Material.water).setBlockName("jelly");
        GameRegistry.registerBlock(jelly_block, "helpertools" + "_" + jelly_block.getUnlocalizedName().substring(5));
        jelly_fluid.setUnlocalizedName(jelly_block.getUnlocalizedName());                  
        //bucket
        jelly_bucket.setUnlocalizedName("jelly_bucket").setContainerItem(Items.bucket);
        GameRegistry.registerItem(jelly_bucket, "jelly_bucket");
        FluidContainerRegistry.registerFluidContainer(jelly_fluid, new ItemStack(jelly_bucket), new ItemStack(Items.bucket));
		
        /** Blocks **/
        GameRegistry.registerBlock(Ibedrock, "Imitation Bedrock");
        GameRegistry.registerBlock(StokedPipe, "StokedPipe");
        //GameRegistry.registerBlock(StokedBlock, "StokedBlock");
        GameRegistry.registerBlock(TranscriberBlock, "TranscriberBlock");
        GameRegistry.registerBlock(MagicalFuelBlock, "MagicalFuelBlock");
        GameRegistry.registerBlock(ActiveMagicalFuelBlock, "ActiveMagicalFuelBlock");
        GameRegistry.registerBlock(LooseDirtBlock,  "LooseDirtBlock");
        //GameRegistry.registerBlock(IllusionBlock, "IllusionBlock");   
        //GameRegistry.registerBlock(SugarBlock, "SugarBlock");
        //GameRegistry.registerBlock(TransitionGlass, "TransitionGlass");
        //GameRegistry.registerBlock(ObeliskBlock, "ObeliskBlock");
		
		
	}
	
	//===========================================================================================//
	
	/** Register Entities **/
	public static void create_Entities() {
		Helpertoolscore.logger.info("Registering Entities");
		
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
        
       
		/** Tile Ents **/        
	    GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
	    //GameRegistry.registerTileEntity(TileEntityIllusion.class, TileEntityIllusion.publicName);
	    //GameRegistry.registerTileEntity(TileEntityObelisk.class, TileEntityObelisk.publicName);
	}
		
		
	

	

}
