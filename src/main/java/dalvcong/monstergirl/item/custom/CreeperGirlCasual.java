package dalvcong.monstergirl.item.custom;

import dalvcong.monstergirl.entity.variant.CreeperGirlTexture;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreeperGirlCasual extends MonsterGirlClothes {
    public CreeperGirlCasual(CreeperGirlTexture texture, Settings settings) {
        super(texture, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.monstergirl.creepergirl_casual.tooltip").formatted(Formatting.GRAY));
    }
}
