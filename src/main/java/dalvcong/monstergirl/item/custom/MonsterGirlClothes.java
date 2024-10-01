package dalvcong.monstergirl.item.custom;

import dalvcong.monstergirl.entity.variant.CreeperGirlTexture;
import net.minecraft.item.Item;

public class MonsterGirlClothes extends Item {
    private final CreeperGirlTexture texture;

    public MonsterGirlClothes(CreeperGirlTexture texture, Settings settings) {
        super(settings);
        this.texture = texture;
    }

    public CreeperGirlTexture getTexture() {
        return this.texture;
    }
}
