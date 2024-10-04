package dalvcong.monstergirl.item.custom;

import dalvcong.monstergirl.entity.variant.ClothesType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkeletonGirlCasual extends MonsterGirlClothes {
    public SkeletonGirlCasual(ClothesType texture, Settings settings) {
        super(texture, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.monstergirl.skeletongirl_casual.tooltip").formatted(Formatting.GRAY));
    }
}
