package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.entity.custom.SpiderGirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpiderGirlRenderer extends GeoEntityRenderer<SpiderGirlEntity> {
    public SpiderGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SpiderGirlModel());
        this.shadowRadius = 0.4f;

    }



    @Override
    public RenderLayer getRenderType(SpiderGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.66f, 0.66f, 0.66f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }


}
