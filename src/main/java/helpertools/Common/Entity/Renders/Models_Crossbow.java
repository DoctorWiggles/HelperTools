package helpertools.Common.Entity.Renders;

import net.minecraft.client.resources.model.ModelResourceLocation;
import helpertools.Main;

import java.util.ArrayList;

/**
 * User: The Grey Ghost
 * Date: 13/01/2015
 * This class is used to encapsulate client-only code out of the ItemNBTAnimate.
 * If you try to define the ModelResourceLocation in ItemNBTAnimate, it will crash on a dedicated server.
 * ItemNBTModels uses "lazy initialisation" so that nothing is created until getInstance() is called for the first time.
 */

/**https://github.com/TheGreyGhost/MinecraftByExample/tree/master/src/main/java/minecraftbyexample/mbe12_item_nbt_animate **/
public class Models_Crossbow
{
  // get the instance
  public static Models_Crossbow getInstance() {
    if (instance == null) {
      instance = new Models_Crossbow();
    }
    return instance;
  }

  private static Models_Crossbow instance;
  private ArrayList<ModelResourceLocation> models = new ArrayList<ModelResourceLocation>();
  public static String modid = Main.MODID;

  private Models_Crossbow()
  {
    models.add(new ModelResourceLocation(modid + ":" + "crossbow_item_0", "inventory"));
    models.add(new ModelResourceLocation(modid + ":" + "crossbow_item_1", "inventory"));
    models.add(new ModelResourceLocation(modid + ":" + "crossbow_item_2", "inventory"));
    models.add(new ModelResourceLocation(modid + ":" + "crossbow_item_3", "inventory"));
    models.add(new ModelResourceLocation(modid + ":" + "crossbow_item_4", "inventory"));   
  }

  public ModelResourceLocation getModel(int modelIndex) {
    return models.get(modelIndex);
  }
}
