package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.CreepergirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CreepergirlRenderer extends GeoEntityRenderer<CreepergirlEntity> {
    public CreepergirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CreepergirlModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(CreepergirlEntity instance) {
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/creepergirl1.png");
    }

    @Override
    public RenderLayer getRenderType(CreepergirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.8f, 0.8f, 0.8f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
