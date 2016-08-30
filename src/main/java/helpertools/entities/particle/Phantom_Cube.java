package helpertools.entities.particle;


import helpertools.util.Text;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Phantom_Cube extends EntityFX{

	Block block = Blocks.air;
	int meta = 0;
	
	public Phantom_Cube(World world, double x,
			double y, double z) {		
		super(world, x, y, z);
		this.particleMaxAge = 0;
	}
	
	public Phantom_Cube(World world, double x,
			double y, double z, Block block, int meta) {		
		super(world, x, y, z);
		this.particleMaxAge = 1;
		this.block = block;
		this.meta = meta;
	}

	
	public void onUpdate() {
		super.onUpdate();
	}
}
