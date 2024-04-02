package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.CreepergirlEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CreepergirlModel extends AnimatedGeoModel<CreepergirlEntity> {
    @Override
    public Identifier getModelResource(CreepergirlEntity object) {
        return new Identifier(MonsterGirl.MOD_ID, "geo/creepergirl.geo.json");
    }

    @Override
    public Identifier getTextureResource(CreepergirlEntity object) {
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/creepergirl1.png");
    }

    @Override
    public Identifier getAnimationResource(CreepergirlEntity animatable) {
        return new Identifier(MonsterGirl.MOD_ID, "animations/creepergirl.animation.json");
    }

    @Override
    public void setCustomAnimations(CreepergirlEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = getAnimationProcessor().getBone("head2");
        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        head.setPositionX(extraData.headPitch * MathHelper.RADIANS_PER_DEGREE);
        head.setRotationY(extraData.netHeadYaw * MathHelper.RADIANS_PER_DEGREE);


    }
}
