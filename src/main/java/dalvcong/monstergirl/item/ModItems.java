package dalvcong.monstergirl.item;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item HAOGANJIEJING = regisierItem("haoganjiejing",
            new Item(new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    public static final Item CREEPERGIRL_SPAWN_EGG = regisierItem("creepergirl_spawn_egg",
            new SpawnEggItem(ModEntities.CREEPERGIRL, 0xffffff, 0x000000,
                    new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));





    private static Item regisierItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MonsterGirl.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterGirl.LOGGER.debug("Registering Mod Items for " + MonsterGirl.MOD_ID);
    }
}
