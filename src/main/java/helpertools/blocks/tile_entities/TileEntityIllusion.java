package helpertools.blocks.tile_entities;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIllusion extends TileEntity{
 
    public static final String publicName = "tileEntityIllusion";
    private String name = "tileEntityIllusion";
 
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
    
    
    
    //Entity manages to reset even with nbt compounds
    //I'm definitly goofing something up big
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
 	   
    	super.readFromNBT(nbt);
    	this.offX = nbt.getInteger("offX");
   	  this.offY = nbt.getInteger("offY");
   	 this.offZ = nbt.getInteger("offZ");
 	   
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
 	   
    	super.writeToNBT(nbt);
    	nbt.setInteger("offX", this.offX);
   	  nbt.setInteger("offY", this.offY);
   	 nbt.setInteger("offZ", this.offZ);
 	   
    }
	
    protected static Random growrand = new Random();
    
    @Override
    public void updateEntity() {

	      int i4 = this.xCoord;
	      int j4 = this.yCoord;
	      int k4 = this.zCoord;
	      
		        
    }
}