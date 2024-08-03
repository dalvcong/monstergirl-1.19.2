package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.entity.custom.EndermanGirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EndermanGirlRenderer extends GeoEntityRenderer<EndermanGirlEntity> {
    public EndermanGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new EndermanGirlModel());
        this.shadowRadius = 0.4f;
    }



    @Override
    public RenderLayer getRenderType(EndermanGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.6f, 0.6f, 0.6f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
