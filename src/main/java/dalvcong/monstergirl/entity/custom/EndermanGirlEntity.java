package dalvcong.monstergirl.entity.custom;

import dalvcong.monstergirl.item.ModItems;
import dalvcong.monstergirl.screen.MonsterGirlScreenHandlerFactory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EndermanGirlEntity extends MonsterGirlEntity implements IAnimatable {

    private  final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public EndermanGirlEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller",
                0, this::predicate));
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (this.isTouchingWater() && !this.hasVehicle() && !event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim_stand",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isTouchingWater() && !this.hasVehicle()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (!this.isOnGround() && !this.hasVehicle()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isSprinting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("run",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.hasVehicle()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit",ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }



    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {

        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.world.isClient) {
            if (this.isTamed() && this.isOwner(player)) {
                return ActionResult.SUCCESS;
            } else {
                return !itemStack.isOf(ModItems.HAOGANJIEJING) || !(this.getHealth() < this.getMaxHealth()) && this.isTamed() ? ActionResult.PASS : ActionResult.SUCCESS;
            }
        } else {
            if (this.isTamed()) {
                if (itemStack.isOf(ModItems.HAOGANJIEJING) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    this.heal(6);
                    return ActionResult.SUCCESS;
                }

                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted() && this.isOwner(player)) {
                    if (player.isSneaking()) {
                        player.openHandledScreen(new MonsterGirlScreenHandlerFactory(this));
                    } else {
                        if (this.isSitting()) {
                            this.setSitting(false);
                            player.sendMessage(Text.literal("跟随模式"),true);
                            this.updateClothes();
                        } else {
                            this.setSitting(true);
                            player.sendMessage(Text.literal("待机模式"),true);
                            this.updateClothes();
                        }
                    }
                    return ActionResult.SUCCESS;
                }
                return actionResult;

            } else if (itemStack.isOf(ModItems.HAOGANJIEJING)) {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.navigation.stop();
                    this.setSitting(true);
                    this.world.sendEntityStatus(this, (byte)7);
                } else {
                    this.world.sendEntityStatus(this, (byte)6);
                }

                return ActionResult.SUCCESS;
            }
            return super.interactMob(player, hand);
        }

    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.61f;
    }

    @Override
    public double getHeightOffset() {
        return -0.61;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

}
