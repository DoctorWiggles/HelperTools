package helpertools.network.fob;


import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import helpertools.util.Text;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// constants in this class are based on the vanilla day/night cycle: http://minecraft.gamepedia.com/Day-night_cycle
public class Forward_Operating_Base extends WorldSavedData {
	
    private static final String KEY = "mailboxdelivery";

    private static final String MORNING = "morning";
    private static final String AFTERNOON = "afternoon";

    private static Forward_Operating_Base instance;

    private World world;
    private int prev = -1;
    private String state = MORNING;
    private int x;
    private int y;
    private int z;

    private NBTTagList boxes;

    public Forward_Operating_Base(String name, World world) {
        super(name);
        this.world = world;
    }

    public void tick(EntityPlayer player) {
        int hour = timeToHours(getDayTime(((EntityPlayerMP)player).worldObj));
        if (hour != prev) {
          
            doDelivery(player);
            prev = hour;
        }
    }
    
    public void debug_list(){
    	
    	    	
    	 for(int i = 0; i <= boxes.tagCount(); i++) {
             NBTTagCompound tag = boxes.getCompoundTagAt(i);
             
             String name = tag.getString("name");
             //Text.out(name);
             System.out.println(name);
             int x = tag.getInteger("x");
             if(x != 0)//Text.out(x);
             System.out.println(x);
             int y = tag.getInteger("y");
             if(y != 0)//Text.out(y);
             System.out.println(y);
             int z = tag.getInteger("z");
             if(z != 0)//Text.out(z);
             System.out.println(z);
             
    	 }
    	
    }
    
    

    private static long getDayTime(World world) {
        return (world.getTotalWorldTime() % 24000);
    }

    private void doDelivery(EntityPlayer player) {
        markDirty();
        world.perWorldStorage.saveAllData();
    }
    public static int minutesUntilDelivery(World world) {
        long mins = getDayTime(world) % 1000;
        if (mins < 250) {
            return 0;
        }
        return 60 - ((int)Math.round((float)mins / 250.0) * 15);
    }

    public static int hoursUntilDelivery(World world) {
        return 1;
    }

    private static int timeToHours(long time) {
        return (int)(time / 1000.0f);
    }

    public static Forward_Operating_Base forWorld(World world) {
        if (Forward_Operating_Base.instance != null) {
            return Forward_Operating_Base.instance;
        }
        MapStorage storage = world.perWorldStorage;
        Forward_Operating_Base result = (Forward_Operating_Base)storage.loadData(Forward_Operating_Base.class, KEY);
        if (result == null) {
            result = new Forward_Operating_Base(KEY, world);
            storage.setData(KEY, result);
        }
        Forward_Operating_Base.instance = result;
        return result;
    }

    private void setWorld(World world) {
        this.world = world;
    }

    public void saveAllData() {
        markDirty();
        world.perWorldStorage.saveAllData();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        state = tag.getString("state");
        boxes = tag.getTagList("boxes", Constants.NBT.TAG_COMPOUND);
        x = tag.getInteger("x");
        y = tag.getInteger("y");
        z = tag.getInteger("z");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setString("state", state);
        tag.setTag("boxes", boxes);
        tag.setInteger("x", x);
        tag.setInteger("y", y);
        tag.setInteger("z", z);
    }

    public static boolean isNameFree(String name) {
        NBTTagList boxes = instance.boxes;
        if (boxes == null)
            return true;
        for(int i = 0; i <= boxes.tagCount(); i++) {
            if (boxes.getCompoundTagAt(i).getString("name").equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean registerMailbox(String name, int x, int y, int z) {
        if (boxes == null) {
            boxes = new NBTTagList();
        }
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("x", x);
        tag.setInteger("y", y);
        tag.setInteger("z", z);
        tag.setString("name", name);
        boxes.appendTag(tag);
        saveAllData();
        return true;
    }

    public void unregisterMailbox(String name) {
        if (boxes == null) {
           System.out.println("a mailbox tried to unregister on null list");
            return;
        }
        for(int i = 0; i <= boxes.tagCount(); i++) {
            if (boxes.getCompoundTagAt(i).getString("name").equals(name)) {
                boxes.removeTag(i);
                return;
            }
        }
    }

    public int getMailboxesCount() {
        if (boxes != null)
            return boxes.tagCount();
        return -1;
    }
}