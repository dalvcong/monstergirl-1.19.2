package dalvcong.monstergirl.entity.custom;

import dalvcong.monstergirl.entity.variant.ClothesType;
import dalvcong.monstergirl.item.custom.MonsterGirlClothes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.UUID;

public class MonsterGirlEntity extends TameableEntity implements IAnimatable {

    private final SimpleInventory inventory = new SimpleInventory(1);//gui相关

    private  final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public MonsterGirlEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.05, 7.0F, 2.0F, false));//跟随模式下怪物娘可能会跑出gui的识别范围，问题不大
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

    }


    public SimpleInventory getInventory() {
        return this.inventory;
    }//gui相关

    public ItemStack getSlotItem() {
        return this.inventory.getStack(0);
    }



    //读写nbt
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        ItemStack itemStack = this.inventory.getStack(0);
        if (!itemStack.isEmpty()) {
            nbt.put("Slot0Item", itemStack.writeNbt(new NbtCompound()));
        }

        nbt.putBoolean("IsSitting", this.isSitting());
        nbt.putBoolean("Tame", this.isTamed());
        if (this.getOwnerUuid() != null) {
            nbt.putUuid("Owner", this.getOwnerUuid());
        }
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Slot0Item", 10)) {
            ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Slot0Item"));
            this.inventory.setStack(0, itemStack);
        }

        if (nbt.contains("IsSitting")) {
            this.setSitting(nbt.getBoolean("IsSitting"));
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

        this.updateClothes();
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

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }//初始化材质

    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(MonsterGirlEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //追踪材质（应该是这么叫的）


    public ClothesType getClothesType() {
        return ClothesType.byIndex(this.getVariant());
    }//获得材质，作用于Model和Renderer

    public int getVariant() {
        return this.dataTracker.get(VARIANT);
    }//获取材质

    private void setVariant(ClothesType texture) {
        this.dataTracker.set(VARIANT,texture.getIndex());
    }//赋予材质

    protected void updateClothes() {
        if (!this.world.isClient) {

            this.setVariant(getColorFromItem(this.getSlotItem()));
        }
    }//更新材质，抄的羊驼的更新地毯装饰的方法

    private static ClothesType getColorFromItem(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof MonsterGirlClothes ? ((MonsterGirlClothes) item).getType() : ClothesType.NUDE;
    }//检测实体当前物品的材质，当实体物品栏的物品是MonsterGirlClothes时就赋予服装的材质，如果不是就返回默认材质
}
