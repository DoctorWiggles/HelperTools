package helpertools.Common;

import helpertools.Main;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationFactory extends ItemRegistry{

	public static int DurabilityExpandingRod;
	public static ToolMaterial ExpRodMaterial;
	
	//Creates initial configuration settings
	public static void InitialConfig(FMLPreInitializationEvent event){
		Main.logger.info("Generating Configuration");
		
		DurabilityExpandingRod = 1024;
		
		
	}
	
	//Process objects like materials after configuration settings
	public static void PostConfig(FMLPreInitializationEvent event){
		
		ExpRodMaterial = EnumHelper.addToolMaterial("ExpRodMaterial", 0, DurabilityExpandingRod, 0.8F, 4F, 15);
		
		
	}
	

}
