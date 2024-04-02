package dalvcong.monstergirl.block;

import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block haogankuang = registerBlock("haogankuang",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), ModItemGroup.MONSTERGIRL);

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBloclItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(MonsterGirl.MOD_ID, name), block);
    }

    private  static Item registerBloclItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(MonsterGirl.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        MonsterGirl.LOGGER.debug("Registering ModBlocks for" + MonsterGirl.MOD_ID);
    }
}
