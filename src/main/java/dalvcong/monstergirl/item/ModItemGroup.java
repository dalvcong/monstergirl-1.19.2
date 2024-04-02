package dalvcong.monstergirl.item;

import dalvcong.monstergirl.MonsterGirl;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup MONSTERGIRL = FabricItemGroupBuilder.build(
            new Identifier(MonsterGirl.MOD_ID, "haoganjiejing"), () -> new ItemStack(ModItems.HAOGANJIEJING));
}
