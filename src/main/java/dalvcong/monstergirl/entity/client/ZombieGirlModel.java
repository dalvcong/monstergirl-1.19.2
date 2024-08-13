package dalvcong.monstergirl.entity.client;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.ZombieGirlEntity;
import dalvcong.monstergirl.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ZombieGirlModel extends AnimatedGeoModel<ZombieGirlEntity> {
    @Override
    public Identifier getModelResource(ZombieGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.ZOMBIEGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "geo/zombiegirl_casual.geo.json");
        }
        return new Identifier(MonsterGirl.MOD_ID, "geo/zombiegirl.geo.json");
    }

    @Override
    public Identifier getTextureResource(ZombieGirlEntity object) {
        ItemStack itemStack = object.getSlotItem();
        if (itemStack.getItem() == ModItems.ZOMBIEGIRL_CASUAL) {
            return new Identifier(MonsterGirl.MOD_ID, "textures/entity/zombiegirl_casual.png");
        }
        return new Identifier(MonsterGirl.MOD_ID, "textures/entity/zombiegirl.png");
    }

    @Override
    public Identifier getAnimationResource(ZombieGirlEntity animatable) {
        return new Identifier(MonsterGirl.MOD_ID, "animations/zombiegirl.animation.json");
    }

    @Override
    public void setCustomAnimations(ZombieGirlEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = getAnimationProcessor().getBone("headd");

        if (head != null) {
            EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * MathHelper.RADIANS_PER_DEGREE);
            head.setRotationY(extraData.netHeadYaw * MathHelper.RADIANS_PER_DEGREE);
        }


    }
}
