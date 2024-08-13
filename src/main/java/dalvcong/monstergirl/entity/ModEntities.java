package dalvcong.monstergirl.entity;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<CreeperGirlEntity> CREEPERGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "creepergirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CreeperGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.7f)).build());
    public static final EntityType<EndermanGirlEntity> ENDERMANGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "endermangirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EndermanGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.88f)).build());
    public static final EntityType<ZombieGirlEntity> ZOMBIEGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "zombiegirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ZombieGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.68f)).build());
    public static final EntityType<SkeletonGirlEntity> SKELETONGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "skeletongirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkeletonGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.81f)).build());
    public static final EntityType<SpiderGirlEntity> SPIDERGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "spidergirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpiderGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.73f)).build());
    public static final EntityType<CaveSpiderGirlEntity> CAVESPIDERGIRL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MonsterGirl.MOD_ID, "cavespidergirl"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CaveSpiderGirlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.65f)).build());
}
