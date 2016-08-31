package helpertools.Common.Blocks;

import java.util.Random;

import helpertools.Utils.HelpTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public class FalseBedrock extends Block {

    public FalseBedrock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel("pickaxe", 1); 
        //this.setStepSound(soundTypePiston);
        this.setSoundType(SoundType.STONE);
        //this.setTickRandomly (false);
        //this.setBlockUnbreakable();
        //this.setLightOpacity(16);
    }

    public FalseBedrock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.ROCK, hardness, resistance);
    }

    public FalseBedrock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
}