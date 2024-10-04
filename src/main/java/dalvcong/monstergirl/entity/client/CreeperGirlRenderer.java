package dalvcong.monstergirl.entity.client;

import com.google.common.collect.Maps;
import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import dalvcong.monstergirl.entity.variant.ClothesType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class CreeperGirlRenderer extends GeoEntityRenderer<CreeperGirlEntity> {

    public static final Map<ClothesType, Identifier> TEXTURE =
        Util.make(Maps.newEnumMap(ClothesType.class), (map) -> {
            map.put(ClothesType.NUDE, new Identifier(MonsterGirl.MOD_ID, "textures/entity/creepergirl.png"));
            map.put(ClothesType.CASUAL, new Identifier(MonsterGirl.MOD_ID, "textures/entity/creepergirl_casual.png"));
        });

    public static final Map<ClothesType, Identifier> MODEL =
            Util.make(Maps.newEnumMap(ClothesType.class), (map) -> {
                map.put(ClothesType.NUDE, new Identifier(MonsterGirl.MOD_ID, "geo/creepergirl.geo.json"));
                map.put(ClothesType.CASUAL, new Identifier(MonsterGirl.MOD_ID, "geo/creepergirl_casual.geo.json"));
            });

    public CreeperGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CreeperGirlModel());
        this.shadowRadius = 0.4f;


        //this.addLayer(new CreeperGirlaRenderer(this));原本打算做渲染层的，未实现，先留着
    }

    @Override
    public Identifier getTexture(CreeperGirlEntity entity) {
        return TEXTURE.get(entity.getClothesType());
    }//没搞懂这个方法的用法，好像删去也没问题


    @Override
    public RenderLayer getRenderType(CreeperGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.65f, 0.65f, 0.65f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }


}
