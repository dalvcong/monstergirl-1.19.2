package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class CreeperGirlaRenderer extends GeoLayerRenderer<CreeperGirlEntity> {
    private static final Identifier Layer = new Identifier(MonsterGirl.MOD_ID, "textures/entity/endermangirl.png");
    private static final Identifier Model = new Identifier(MonsterGirl.MOD_ID, "geo/endermangirl.geo.json");
    //本体文件CreeperGirlModel.json中定义的模型文件是"geo/creepergirl.geo.json"，目前渲染层模型和材质正常渲染，动画丢失
    //将本文件中的"geo/endermangirl.geo.json"改为"geo/creepergirl.geo.json"，则动画才会正常渲染

    public CreeperGirlaRenderer(IGeoRenderer<CreeperGirlEntity> entityRendererIn) {
        super(entityRendererIn);

    }



    @Override
    public void render(MatrixStack PoseStackIn, VertexConsumerProvider bufferIn, int packedLightIn, CreeperGirlEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderLayer cameo = RenderLayer.getArmorCutoutNoCull(Layer);



        this.getRenderer().render(this.getEntityModel().getModel(Model), entitylivingbaseIn, partialTicks, cameo, PoseStackIn, bufferIn, bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.DEFAULT_UV,1f,1f,1f,1f);

    }


}
