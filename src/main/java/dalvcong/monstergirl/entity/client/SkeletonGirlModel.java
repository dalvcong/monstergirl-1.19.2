package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.SkeletonGirlEntity;
import dalvcong.monstergirl.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SkeletonGirlModel extends AnimatedGeoModel<SkeletonGirlEntity> {
    @Override
    public Identifier getModelResource(SkeletonGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.SKELETONGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "geo/skeletongirl_casual.geo.json");
        }
        return new Identifier(MonsterGirl.MOD_ID, "geo/skeletongirl.geo.json");
    }

    @Override
    public Identifier getTextureResource(SkeletonGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.SKELETONGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "textures/entity/skeletongirl_casual.png");
        }
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/skeletongirl.png");
    }

    @Override
    public Identifier getAnimationResource(SkeletonGirlEntity animatable) {
        return new Identifier(MonsterGirl.MOD_ID, "animations/skeletongirl.animation.json");
    }

    @Override
    public void setCustomAnimations(SkeletonGirlEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = getAnimationProcessor().getBone("headd");

        if (head != null) {
            EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * MathHelper.RADIANS_PER_DEGREE);
            head.setRotationY(extraData.netHeadYaw * MathHelper.RADIANS_PER_DEGREE);
        }


    }
}
