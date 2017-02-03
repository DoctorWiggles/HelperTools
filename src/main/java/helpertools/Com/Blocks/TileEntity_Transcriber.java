package helpertools.Com.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntity_Transcriber extends TileEntity implements ITickable {
 
    public static final String publicName = "tileEntityTranscriber";
    private String name = publicName;
    
 
    public String getName() {
 
        return name;
    }
    
    
    public int offX = 0;
    public int offY = 1;
    public int offZ = 0;  
    
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
  		NBTTagCompound nbtTagCompound = new NBTTagCompound();
  		writeToNBT(nbtTagCompound);
  		int metadata = getBlockMetadata();
  		return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
  	}

  	@Override
  	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
  		readFromNBT(pkt.getNbtCompound());
  	}

    
    @Override
    public NBTTagCompound getUpdateTag()
    {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      writeToNBT(nbtTagCompound);
      return nbtTagCompound;
    }
    
    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
      this.readFromNBT(tag);
    }
    
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) { 	   
    	super.readFromNBT(nbt);
    	
    	this.offX = nbt.getInteger("offX");
    	this.offY = nbt.getInteger("offY");
    	this.offZ = nbt.getInteger("offZ");
    }
    
    
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {	super.writeToNBT(nbt);
    
    	nbt.setInteger("offX", this.offX);
    	nbt.setInteger("offY", this.offY);
    	nbt.setInteger("offZ", this.offZ);
    	
		return nbt;
    }
    
	
    protected static Random growrand = new Random();
    
    @Override
    public void update() {
    	
    	int offy_X = offX;
    	int offy_Y = offY;
    	int offy_Z = offZ;   	
    	BlockPos pos1 =  this.pos;

	    	int i4 = pos.getX();
	    	int j4 = pos.getY();
	    	int k4 = pos.getZ();

        if(world.isRemote){
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
		           		       
		           //RED 
		           float s_X = 0F;
		           //Blue
		           float s_Y = -0.05F;
		           //Green
		           float s_Z = -0.05F;      
		           
		           
		           float finX = i4 + offy_X +.3F-2;
		           float finY = j4 + offy_Y -.1F;
		           float finZ = k4 + offy_Z +.1F-2;
		           
		           EnumParticleTypes center = EnumParticleTypes.REDSTONE;
		           EnumParticleTypes outside = EnumParticleTypes.REDSTONE;
		           
		           
		           this.world.spawnParticle(center, finX+f+.2+2, finY+2+.5+f1, finZ+f2+.3+2, 2F, 0, 0); 
		           
		           
		           for (int G2 = 0; G2 < 5; ++G2)
		           {
		        	   this.world.spawnParticle(outside, finX+f+.2+G2, finY+.5+f1, finZ+f2+.3, s_X, s_Y, s_Z);		        	   
		        	   this.world.spawnParticle(outside, finX+f+.2+G2, finY+.5+f1, finZ+f2+.3+4, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+G2, finY+.5+f1+4, finZ+f2+.3+4,s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+G2, finY+.5+f1+4, finZ+f2+.3, s_X, s_Y, s_Z);
		           }
		           for (int G3 = 0; G3 < 3; ++G3)
		           {
		        	   this.world.spawnParticle(outside, finX+f+.2, finY+.5+f1, finZ+f2+.3+G3+1, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+4, finY+.5+f1, finZ+f2+.3+G3+1, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2, finY+.5+f1+4, finZ+f2+.3+G3+1, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+4, finY+.5+f1+4, finZ+f2+.3+G3+1, s_X, s_Y, s_Z);
		           
		        	   //verticals
		        	   this.world.spawnParticle(outside, finX+f+.2, finY+.5+f1+G3+1, finZ+f2+.3, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+4, finY+.5+f1+G3+1, finZ+f2+.3, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2, finY+.5+f1+G3+1, finZ+f2+.3+4, s_X, s_Y, s_Z);
		        	   this.world.spawnParticle(outside, finX+f+.2+4, finY+.5+f1+G3+1, finZ+f2+.3+4, s_X, s_Y, s_Z);
		        	  
		           }
		           
		       }
        	
        }
    }
}