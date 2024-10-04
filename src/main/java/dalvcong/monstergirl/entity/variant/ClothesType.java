package dalvcong.monstergirl.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum ClothesType {
    NUDE(0),
    CASUAL(1);

    private static final ClothesType[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(ClothesType::getIndex)).toArray(ClothesType[]::new);

    private final int index;

    ClothesType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static ClothesType byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}
