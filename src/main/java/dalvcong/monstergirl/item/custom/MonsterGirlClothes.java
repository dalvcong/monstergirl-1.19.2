package dalvcong.monstergirl.item.custom;

import dalvcong.monstergirl.entity.variant.ClothesType;
import net.minecraft.item.Item;

public class MonsterGirlClothes extends Item {
    private final ClothesType type;

    public MonsterGirlClothes(ClothesType type, Settings settings) {
        super(settings);
        this.type = type;
    }

    public ClothesType getType() {
        return this.type;
    }
}
