package helpertools.blocks.tile_entities;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTranscriber extends TileEntity{
 
    public static final String publicName = "tileEntityTranscriber";
    private String name = "tileEntityTranscriber";
 
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
	      
	   
	      
 /*
        if(!worldObj.isRemote) {
 
            tick++;
            if(tick == 100) {
 
                this.worldObj.setWorldTime(1000);
                tick = 0;
            }
        }
        */
        if(worldObj.isRemote){
        	int i = growrand.nextInt(2);
        	//if(i ==1){
        	 short short1 =1;
				for (int lp = 0; lp < short1; ++lp)
		       {
		           double d6 = (double)lp / ((double)short1 - 1.0D);
		           float f = (this.growrand.nextFloat()/5F) ;
		           //float f1 = (this.growrand .nextFloat()/1 )-.5F;
		           float f1 = (this.growrand .nextFloat()/5F );
		           float f2 = (this.growrand .nextFloat()/5F );
		           /**
		           float f = (this.growrand.nextFloat()*1.2F)-.55F ;
		           //float f1 = (this.growrand .nextFloat()/1 )-.5F;
		           float f1 = (this.growrand .nextFloat()*1.2F )-.55F;
		           float f2 = (this.growrand .nextFloat()*1.2F -.55F);
		           **/
		           
		           float p1 = (this.growrand .nextFloat()/5 ) ;
		           float p = (this.growrand .nextFloat()*3 )+2F  ;
		           float p4 = (this.growrand .nextFloat()*5 )+1F  ;
		           float p2 = (this.growrand .nextFloat()-.5F )/5 ;
		           //float p2 = (this.growrand .nextFloat()-.5F/5 ) ;
		           
		           float finX = i4 + offX +.3F-2;
		           float finY = j4 + offY -.1F;
		           float finZ = k4 + offZ +.1F-2;
		           
		           //this.worldObj.spawnParticle("cloud", finX+f-.5, finY+f1+.5, finZ+f2+.5, p, p1, p2);
		           //this.worldObj.spawnParticle("magicCrit", finX+f+.2, finY+.5+f1, finZ+f2+.3, 0, 0, 0);
		           this.worldObj.spawnParticle("reddust", finX+f+.2+2, finY+2+.5+f1, finZ+f2+.3+2, 0, 0, 0);
		           
		           //this.worldObj.spawnParticle("slime", finX-.5, finY+.5, finZ+.5, 0, 0, 0);
		           
		           for (int G2 = 0; G2 < 5; ++G2)
		           {
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+G2, finY+.5+f1, finZ+f2+.3, 0, p, p);		        	   
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+G2, finY+.5+f1, finZ+f2+.3+4, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+G2, finY+.5+f1+4, finZ+f2+.3+4, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+G2, finY+.5+f1+4, finZ+f2+.3, 0, p, p);
		           }
		           for (int G3 = 0; G3 < 3; ++G3)
		           {
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2, finY+.5+f1, finZ+f2+.3+G3+1, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+4, finY+.5+f1, finZ+f2+.3+G3+1, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2, finY+.5+f1+4, finZ+f2+.3+G3+1, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+4, finY+.5+f1+4, finZ+f2+.3+G3+1, 0, p, p);
		           
		        	   //verticals
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2, finY+.5+f1+G3+1, finZ+f2+.3, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+4, finY+.5+f1+G3+1, finZ+f2+.3, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2, finY+.5+f1+G3+1, finZ+f2+.3+4, 0, p, p);
		        	   this.worldObj.spawnParticle("reddust", finX+f+.2+4, finY+.5+f1+G3+1, finZ+f2+.3+4, 0, p, p);
		        	  
		           }
		           
		       }
        	//}
        }
    }
}