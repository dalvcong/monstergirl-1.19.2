package dalvcong.monstergirl.item;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.variant.ClothesType;
import dalvcong.monstergirl.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item HAOGANJIEJING = regisierItem("haoganjiejing",
            new Item(new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item CREEPERGIRL_CASUAL = regisierItem("creepergirl_casual",
            new CreeperGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item ENDERMANGIRL_CASUAL = regisierItem("endermangirl_casual",
            new EndermanGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item ZOMBIEGIRL_CASUAL = regisierItem("zombiegirl_casual",
            new ZombieGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item SKELETONGIRL_CASUAL = regisierItem("skeletongirl_casual",
            new SkeletonGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item SPIDERGIRL_CASUAL = regisierItem("spidergirl_casual",
            new SpiderGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item CAVESPIDERGIRL_CASUAL = regisierItem("cavespidergirl_casual",
            new CaveSpiderGirlCasual(ClothesType.CASUAL, new FabricItemSettings().group(ModItemGroup.MONSTERGIRL).maxCount(1)));

    public static final Item CREEPERGIRL_SPAWN_EGG = regisierItem("creepergirl_spawn_egg",
            new SpawnEggItem(ModEntities.CREEPERGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item ENDERMANGIRL_SPAWN_EGG = regisierItem("endermangirl_spawn_egg",
            new SpawnEggItem(ModEntities.ENDERMANGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item ZOMBIEGIRL_SPAWN_EGG = regisierItem("zombiegirl_spawn_egg",
            new SpawnEggItem(ModEntities.ZOMBIEGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item SKELETONGIRL_SPAWN_EGG = regisierItem("skeletongirl_spawn_egg",
            new SpawnEggItem(ModEntities.SKELETONGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item SPIDERGIRL_SPAWN_EGG = regisierItem("spidergirl_spawn_egg",
            new SpawnEggItem(ModEntities.SPIDERGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item CAVESPIDERGIRL_SPAWN_EGG = regisierItem("cavespidergirl_spawn_egg",
            new SpawnEggItem(ModEntities.CAVESPIDERGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));
    


    private static Item regisierItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MonsterGirl.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterGirl.LOGGER.debug("Registering Mod Items for " + MonsterGirl.MOD_ID);
    }
}
