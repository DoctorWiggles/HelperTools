package helpertools;

import helpertools.blocks.ActiveMagicalFuelBlock;
import helpertools.blocks.Balloon_Block;
import helpertools.blocks.Charm_block;
import helpertools.blocks.Floater_Block;
import helpertools.blocks.ImitationBedrock;
import helpertools.blocks.LooseDirt_Block;
import helpertools.blocks.MagicalFuel_Block;
import helpertools.blocks.Stoked_Pipe;
import helpertools.blocks.Transcriber_Block;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.entities.Bolt_Projectile;
import helpertools.entities.Bomb_Projectile;
import helpertools.entities.Dynamite_Projectile;
import helpertools.entities.RedTorch_Projectile;
import helpertools.entities.Torch_Projectile;
import helpertools.item_blocks.Charm_block_item;
import helpertools.item_blocks.Floater_block_item;
import helpertools.item_blocks.Item_Block_Balloon;
import helpertools.item_blocks.Item_Block_Magical_Lamp;
import helpertools.item_blocks.Item_block_Chimney;
import helpertools.item_blocks.TranscriberBlock_Item;
import helpertools.items.Armor_Mystic;
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemMilkBottle;
import helpertools.items.Item_Bomb;
import helpertools.items.Item_creative_builder_charm;
import helpertools.tools.Staff_EuclideanTransposer;
import helpertools.tools.Staff_Expansion;
import helpertools.tools.Staff_Transformation;
import helpertools.tools.Tool_TorchLauncher;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRegistry extends ConfigurationFactory{
	
	/** Tools **/
	public final static Set properharvest = Sets.newHashSet(new Block[] {Blocks.leaves, Blocks.leaves2, Blocks.web, Blocks.wool, 
			Blocks.trapped_chest, Blocks.chest, Blocks.bookshelf});	
	public static Item staffofexpansion;  
	public static Item staffoftransformation;
	public static Item euclideantransposer;
	public static Item torchlauncher;	
		
	/** Items **/	
	public static Item dynamitebolt;
	public static Item sandbomb;
	public static Item gravelbomb;
	public static Item bottledmilk;
	public static Item chocolatemilk;	
	public static Item bomb;
	public static Item cbc;
	
	/** Armor **/
	public static Item mystic_mask;
	
	/** Blocks **/
	public static Block Ibedrock;
	public static Block StokedPipe;		
	public static Block TranscriberBlock;	
	public static Block MagicalFuelBlock;	
	public static Block ActiveMagicalFuelBlock;	
	public static Block LooseDirtBlock;	
	public static Block Charm_block;
	public static Block Balloon;
	public static Block Floater;
		
	
//=====================================================================================//
	
	/** Register things **/
	public static void create_Items() {
		Main.logger.info("Registering items");
		
		
		/** Tools **/        
        reg(staffofexpansion = new Staff_Expansion(ExpRodMaterial,"staffofexpansion"));
        reg(staffoftransformation = new Staff_Transformation(MetaStaffMaterial,"staffoftransformation"));
        reg(torchlauncher = new Tool_TorchLauncher(TorchMaterial,"torchlauncher"));
        reg(euclideantransposer = new Staff_EuclideanTransposer(EUStaffMaterial,"euclideantransposer"));
        
        /** Items **/ 
        reg(bottledmilk = new ItemMilkBottle("bottledmilk"));
        reg(chocolatemilk = new ItemChocolateMilk( 3, 0.5f, true, "chocolatemilk").setAlwaysEdible());
        reg(dynamitebolt = new ItemDynamiteBolt("dynamitebolt"));
        
        reg(bomb = new Item_Bomb("bomb"));        
        reg(cbc = new Item_creative_builder_charm("cbc"));
        
        /** Armor **/
        reg(mystic_mask = new Armor_Mystic("mystic_mask", Mystic_Material, "mystic", 0));
        
        /** Blocks **/
        reg(Ibedrock = new ImitationBedrock("ImitationBedrock"));
        reg(StokedPipe = new Stoked_Pipe("StokedPipe"), Item_block_Chimney.class);
        reg(TranscriberBlock = new Transcriber_Block("TranscriberBlock"), TranscriberBlock_Item.class);
        reg(MagicalFuelBlock = new MagicalFuel_Block("MagicalFuelBlock"), Item_Block_Magical_Lamp.class);
        reg(ActiveMagicalFuelBlock = new ActiveMagicalFuelBlock("ActiveMagicalFuelBlock"));
        reg(LooseDirtBlock = new LooseDirt_Block(Material.sand,"LooseDirtBlock"));
        reg(Charm_block = new Charm_block("Charm_block"), Charm_block_item.class);
        reg(Floater = new Floater_Block("Floater_block"), Floater_block_item.class);
        reg(Balloon = new Balloon_Block("Balloon_block"), Item_Block_Balloon.class);
		
	}
	
	public static void reg(Item item){
		
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static void reg(Block block){
		
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	public static void reg(Block block, Class<? extends ItemBlock> itemclass){
		
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName());
	}
	

	//===========================================================================================//
	
	/** Register Entities **/
	public static void create_Entities() {
		Main.logger.info("Registering Entities");
		
		/** Entities **/
		reg(Torch_Projectile.class, "TorchBolt", 10,  350);
		reg(RedTorch_Projectile.class, "RedTorchBolt", 11,  350);
		reg(Dynamite_Projectile.class, "DynamiteBolt", 12,  350);                        
		reg(Bolt_Projectile.class, "CrossBolt", 13,  350);
		reg(Bomb_Projectile.class, "Bomb", 17, 350);        
       
		/** Tile Ents **/        
	    GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
	}
				
	public static void reg(Class<? extends Entity> entityClass, String name, int id,  int trackingRange){
		
		 EntityRegistry.registerModEntity(entityClass, name, id, instance, 350, 3, true);
	}


}
