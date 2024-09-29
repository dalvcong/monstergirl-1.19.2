package dalvcong.monstergirl.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CreeperGirlTexture {
    NUDE(0),
    CASUAL(1);

    private static final CreeperGirlTexture[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(CreeperGirlTexture::getIndex)).toArray(CreeperGirlTexture[]::new);

    private final int index;

    CreeperGirlTexture(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static CreeperGirlTexture byIndex(int index) {
        return VALUES[index % VALUES.length];
    }
}
