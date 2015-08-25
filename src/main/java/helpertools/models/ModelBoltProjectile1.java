

package helpertools.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoltProjectile1 extends ModelBase
{
  //fields
    ModelRenderer Shaft1;
    ModelRenderer Fletchtop1;
    ModelRenderer Fletchbottom1;
  
  public ModelBoltProjectile1()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shaft1 = new ModelRenderer(this, 0, 0);
      Shaft1.addBox(0F, 0F, -3F, 1, 1, 6);
      Shaft1.setRotationPoint(0F, 0F, 0F);
      Shaft1.setTextureSize(64, 32);
      Shaft1.mirror = true;
      setRotation(Shaft1, 0F, 0F, 0.7853982F);
      Fletchtop1 = new ModelRenderer(this, 0, 15);
      Fletchtop1.addBox(-0.5F, -1.5F, -3F, 2, 2, 3);
      Fletchtop1.setRotationPoint(0F, 0F, 0.1F);
      Fletchtop1.setTextureSize(64, 32);
      Fletchtop1.mirror = true;
      setRotation(Fletchtop1, 0F, 0F, -0.7853982F);
      Fletchbottom1 = new ModelRenderer(this, 0, 8);
      Fletchbottom1.addBox(-1.5F, -0.5F, -3F, 2, 2, 3);
      Fletchbottom1.setRotationPoint(0F, 1.5F, 0.1F);
      Fletchbottom1.setTextureSize(64, 32);
      Fletchbottom1.mirror = true;
      setRotation(Fletchbottom1, 0F, 0F, -0.7853982F);
  }
  
  public void rendermodel(float f5)
  {
    Shaft1.render(f5);
    Fletchtop1.render(f5);
    Fletchbottom1.render(f5);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shaft1.render(f5);
    Fletchtop1.render(f5);
    Fletchbottom1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
   
  }
   
}
