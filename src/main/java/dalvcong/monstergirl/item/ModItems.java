package dalvcong.monstergirl.item;

import dalvcong.monstergirl.MonsterGirl;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item HAOGANJIEJING = regisierItem("haoganjiejing",
            new Item(new FabricItemSettings().group(ModItemGroup.MONSTERGIRL)));

    private static Item regisierItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MonsterGirl.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonsterGirl.LOGGER.debug("Registering Mod Items for " + MonsterGirl.MOD_ID);
    }
}
