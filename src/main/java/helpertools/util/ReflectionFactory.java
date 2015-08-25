package helpertools.util;

import java.lang.reflect.Field;

import net.minecraft.block.BlockSand;

public class ReflectionFactory {
	
	
	public static void SandReflection()
	{
		 for (Field f : BlockSand.class.getDeclaredFields()) {
		        f.setAccessible(true);
		        try {
		        	
		        }
		        catch (Exception e) {
		            System.err.println("Severe error, please report this to the mod author:");
		            System.err.println(e);
		        }
		 }
	}
	static Class sandy = BlockSand.class;
	
	public static void SandReflection2()
	{
		 for (Field f : sandy.getSuperclass().getDeclaredFields()) {
		        f.setAccessible(true);
		        try {
		        	
		        }
		        catch (Exception e) {
		            System.err.println("Severe error, please report this to the mod author:");
		            System.err.println(e);
		        }
		 }
	}

}
