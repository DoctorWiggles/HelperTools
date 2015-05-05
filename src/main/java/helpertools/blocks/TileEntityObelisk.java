package helpertools.blocks;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;

public class TileEntityObelisk extends TileEntity{
 
    public static final String publicName = "tileEntityObelisk";
    private String name = "tileEntityObelisk";
 
    public String getName() {
 
        return name;
    }
    
    //global integers work fine but reset on exit
    //need real compounds whenever
    int tick = 0;
    public int offX = 0;
    public int offY = 1;
    public int offZ = 0;
    //int actk7 = 0;
	
    protected static Random growrand = new Random();
    
}