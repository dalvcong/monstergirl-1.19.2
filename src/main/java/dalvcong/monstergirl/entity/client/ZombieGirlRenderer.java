package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.ZombieGirlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZombieGirlRenderer extends GeoEntityRenderer<ZombieGirlEntity> {
    public ZombieGirlRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ZombieGirlModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(ZombieGirlEntity instance) {
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/zombiegirl.png");
    }

    @Override
    public RenderLayer getRenderType(ZombieGirlEntity animatable, float partialTick, MatrixStack stack,
                                     VertexConsumerProvider bufferSource, VertexConsumer buffer,
                                     int packedLight, Identifier texture) {
        stack.scale(0.64f, 0.64f, 0.64f);

        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
