package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.CaveSpiderGirlEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CaveSpiderGirlModel extends AnimatedGeoModel<CaveSpiderGirlEntity> {
    @Override
    public Identifier getModelResource(CaveSpiderGirlEntity object) {
        return CaveSpiderGirlRenderer.MODEL.get(object.getClothesType());
    }

    @Override
    public Identifier getTextureResource(CaveSpiderGirlEntity object) {
        return CaveSpiderGirlRenderer.TEXTURE.get(object.getClothesType());
    }

    @Override
    public Identifier getAnimationResource(CaveSpiderGirlEntity animatable) {
        return new Identifier(MonsterGirl.MOD_ID, "animations/cavespidergirl.animation.json");
    }


    @Override
    public void setCustomAnimations(CaveSpiderGirlEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = getAnimationProcessor().getBone("headd");

        if (head != null) {
            EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * MathHelper.RADIANS_PER_DEGREE);
            head.setRotationY(extraData.netHeadYaw * MathHelper.RADIANS_PER_DEGREE);
        }


    }
}
