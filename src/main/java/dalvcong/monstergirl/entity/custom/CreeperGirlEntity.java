package dalvcong.monstergirl.entity.custom;

import dalvcong.monstergirl.entity.variant.CreeperGirlTexture;
import dalvcong.monstergirl.item.ModItems;
import dalvcong.monstergirl.screen.MonsterGirlScreenHandlerFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;


public class CreeperGirlEntity extends MonsterGirlEntity implements IAnimatable {

    private  final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public CreeperGirlEntity(EntityType<? extends TameableEntity> entityType, World world) {
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

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(VARIANT, nbt.getInt("Variant"));
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
                        } else {
                            this.setSitting(true);
                            player.sendMessage(Text.literal("待机模式"),true);
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
        return 1.42f;
    }

    @Override
    public double getHeightOffset() {
        return -0.48;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }//枚举材质

    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(CreeperGirlEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound nbt) {
        CreeperGirlTexture texture = Util.getRandom(CreeperGirlTexture.values(), this.random);
        setVariant(texture);
        return super.initialize(world, difficulty, spawnReason, entityData, nbt);
    }

    public CreeperGirlTexture getTexture() {
        return CreeperGirlTexture.byIndex(this.getVariant() & 255);
    }

    private int getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    private void setVariant(CreeperGirlTexture texture) {
        this.dataTracker.set(VARIANT,texture.getIndex() & 255);
    }
}
