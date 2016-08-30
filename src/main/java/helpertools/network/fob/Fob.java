package helpertools.network.fob;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class Fob {
    public int id;
    public int x, y, z, dimension;
    public String name;
    public int linked_id;
    public boolean powered;

    static HashMap<String, Fob> waypointsLocationMap = new HashMap<String, Fob>();
    static Fob waypoints[] = new Fob[0x400];
    static int nextId = 0;
    public static ArrayList<Fob> existingWaypoints = new ArrayList<Fob>();
    public static boolean changed;

    public Fob() {
        id = -1;
    }

    protected Fob(int id) {
        this.id = id;
        waypoints[id] = this;
        changed = true;
    }

    public Fob(ByteBuf stream) throws IOException {
        id = -1;
        read(stream);
    }

    public void write(ByteBuf stream) throws IOException {
        stream.writeInt(id);
    }

    void read(ByteBuf stream) throws IOException {
        if (id != -1) return;

        id = stream.readInt();
    }

    void write(NBTTagCompound tag) {
        tag.setInteger("id", id);
    }

    void read(NBTTagCompound tag) {
        if (id != -1) return;

        initialize(tag.getInteger("id"));
        name = tag.getString("name");
        linked_id = tag.getInteger("linked_id");
        powered = tag.getBoolean("powered");
    }

    public static String locKey(int x, int y, int z, int dimension) {
        return x + "|" + y + "|" + z + ":" + dimension;
    }

    public static Fob getWaypoint(int id) {
        if (id < 0 || id >= waypoints.length)
            return null;

        return waypoints[id];
    }

    public static void removeWaypoint(Fob wp) {
        waypoints[wp.id] = null;
        waypointsLocationMap.remove(locKey(wp.x, wp.y, wp.z, wp.dimension));
        existingWaypoints.remove(wp);

        changed = true;
    }

    void initialize(int id) {
        String key = locKey(x, y, z, dimension);

        this.id = id;
        waypoints[id] = this;
    }

    public static Fob getWaypoint(int x, int y, int z, int dimension) {
        String key = locKey(x, y, z, dimension);
        Fob wp = waypointsLocationMap.get(key);

        if (wp == null) {
            int startId = nextId;
            while (waypoints[nextId] != null) {
                nextId = (nextId + 1) % waypoints.length;
                if (nextId == startId) return null;
            }

            wp = new Fob();
            wp.initialize(nextId);
            wp.name = "";
        }

        return wp;
    }

    public static void write(File file) throws IOException {
        if (!changed) return;
        changed = false;

        int index = 0;
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("count", existingWaypoints.size());
        for (Fob w : existingWaypoints) {
            NBTTagCompound wtag = new NBTTagCompound();
            w.write(wtag);
            tag.setTag("" + (index++), wtag);
        }

        ByteBuf buffer = Unpooled.buffer();
        ByteBufUtils.writeTag(buffer, tag);
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        Files.write(file.toPath(), bytes);
    }

    public static void read(File file) throws IOException {
        existingWaypoints.clear();
        waypointsLocationMap.clear();
        nextId = 0;


        byte[] bytes = Files.readAllBytes(file.toPath());
        ;
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(bytes);
        NBTTagCompound tag = ByteBufUtils.readTag(buffer);

        int count = tag.getInteger("count");
        for (int i = 0; i < count; i++) {
            Fob w = new Fob();
            w.read(tag.getCompoundTag("" + i));
            if (nextId <= w.id) nextId = w.id + 1;
        }
    }
}
