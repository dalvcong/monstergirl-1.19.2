package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.SpiderGirlEntity;
import dalvcong.monstergirl.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SpiderGirlModel extends AnimatedGeoModel<SpiderGirlEntity> {
    @Override
    public Identifier getModelResource(SpiderGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.SPIDERGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "geo/spidergirl_casual.geo.json");
        }
        return new Identifier(MonsterGirl.MOD_ID, "geo/spidergirl.geo.json");
    }

    @Override
    public Identifier getTextureResource(SpiderGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.SPIDERGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "textures/entity/spidergirl_casual.png");
        }
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/spidergirl.png");
    }

    @Override
    public Identifier getAnimationResource(SpiderGirlEntity animatable) {
        return new Identifier(MonsterGirl.MOD_ID, "animations/spidergirl.animation.json");
    }


    @Override
    public void setCustomAnimations(SpiderGirlEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = getAnimationProcessor().getBone("headd");

        if (head != null) {
            EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * MathHelper.RADIANS_PER_DEGREE);
            head.setRotationY(extraData.netHeadYaw * MathHelper.RADIANS_PER_DEGREE);
        }


    }
}
