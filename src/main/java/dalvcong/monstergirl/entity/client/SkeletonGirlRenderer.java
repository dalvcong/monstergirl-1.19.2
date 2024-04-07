package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.SkeletonGirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SkeletonGirlRenderer extends GeoEntityRenderer<SkeletonGirlEntity> {
    public SkeletonGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SkeletonGirlModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(SkeletonGirlEntity instance) {
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/skeletongirl.png");
    }

    @Override
    public RenderLayer getRenderType(SkeletonGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.59f, 0.59f, 0.59f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
