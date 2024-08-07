package dalvcong.monstergirl.util;

import dalvcong.monstergirl.MonsterGirl;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(MonsterGirl.MOD_ID, name));
        }

    }

    public static class Items {

        public static final TagKey<Item> MONSTERGIRL_CLOTHES = createTag("monstergirl_clothes");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(MonsterGirl.MOD_ID, name));
        }

    }
}
