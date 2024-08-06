package dalvcong.monstergirl.entity.custom;

import dalvcong.monstergirl.item.ModItems;
import dalvcong.monstergirl.screen.MonsterGirlScreenHandlerFactory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

import java.util.UUID;

public class CreeperGirlEntity extends TameableEntity implements IAnimatable {

    private final SimpleInventory inventory = new SimpleInventory(15);

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
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.05, 8.0F, 2.0F, false));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1D));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 5.0F));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    public void mobTick() {
        if (this.getMoveControl().isMoving()) {
            double S = this.getMoveControl().getSpeed();
            this.setSprinting(S == 1.05);

        } else {
            this.setSprinting(false);
        }
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

                    player.openHandledScreen(new MonsterGirlScreenHandlerFactory(this));
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


    public SimpleInventory getInventory() {
        return this.inventory;
    }



    private static final String KET_SLOT = "Slot";
    private static final String KEY_INVENTORY = "Inventory";

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        NbtList inventoryNbt = new NbtList();
        for (int i = 0; i < this.inventory.size(); i++) {
            ItemStack itemStack = this.inventory.getStack(i);

            if (!itemStack.isEmpty()) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt(CreeperGirlEntity.KET_SLOT, i);
                itemStack.writeNbt(nbtCompound);
                inventoryNbt.add(nbtCompound);

            }
        }
        nbt.put(CreeperGirlEntity.KEY_INVENTORY, inventoryNbt);

        nbt.putBoolean("Tame", this.isTamed());
        if (this.getOwnerUuid() != null) {
            nbt.putUuid("Owner", this.getOwnerUuid());
        }
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        NbtList inventoryNbt = nbt.getList(CreeperGirlEntity.KEY_INVENTORY, NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < inventoryNbt.size(); i++) {
            NbtCompound nbtCompound = inventoryNbt.getCompound(i);
            this.inventory.setStack(nbtCompound.getInt("Slot"), ItemStack.fromNbt(nbtCompound));
        }
        this.setTamed(nbt.getBoolean("Tame"));
        UUID uUID;
        if (nbt.containsUuid("Owner")) {
            uUID = nbt.getUuid("Owner");
        } else {
            String string = nbt.getString("Owner");
            uUID = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
        }

        if (uUID != null) {
            this.setOwnerUuid(uUID);
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

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
