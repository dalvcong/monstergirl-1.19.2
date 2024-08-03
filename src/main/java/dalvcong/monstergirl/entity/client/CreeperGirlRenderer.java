package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CreeperGirlRenderer extends GeoEntityRenderer<CreeperGirlEntity> {
    public CreeperGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CreeperGirlModel());
        this.shadowRadius = 0.4f;


        //this.addLayer(new CreeperGirlaRenderer(this));
    }



    @Override
    public RenderLayer getRenderType(CreeperGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.65f, 0.65f, 0.65f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }


}
