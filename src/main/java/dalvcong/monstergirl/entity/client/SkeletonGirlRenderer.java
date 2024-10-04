package dalvcong.monstergirl.entity.client;

import com.google.common.collect.Maps;
import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.SkeletonGirlEntity;
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

public class SkeletonGirlRenderer extends GeoEntityRenderer<SkeletonGirlEntity> {

    public static final Map<ClothesType, Identifier> TEXTURE =
            Util.make(Maps.newEnumMap(ClothesType.class), (map) -> {
                map.put(ClothesType.NUDE, new Identifier(MonsterGirl.MOD_ID, "textures/entity/skeletongirl.png"));
                map.put(ClothesType.CASUAL, new Identifier(MonsterGirl.MOD_ID, "textures/entity/skeletongirl_casual.png"));
            });

    public static final Map<ClothesType, Identifier> MODEL =
            Util.make(Maps.newEnumMap(ClothesType.class), (map) -> {
                map.put(ClothesType.NUDE, new Identifier(MonsterGirl.MOD_ID, "geo/skeletongirl.geo.json"));
                map.put(ClothesType.CASUAL, new Identifier(MonsterGirl.MOD_ID, "geo/skeletongirl_casual.geo.json"));
            });

    public SkeletonGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SkeletonGirlModel());
        this.shadowRadius = 0.4f;
    }



    @Override
    public RenderLayer getRenderType(SkeletonGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.58f, 0.58f, 0.58f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
