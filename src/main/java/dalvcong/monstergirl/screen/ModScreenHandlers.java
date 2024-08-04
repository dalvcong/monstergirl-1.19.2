package dalvcong.monstergirl.screen;

import dalvcong.monstergirl.MonsterGirl;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModScreenHandlers {
    public static ScreenHandlerType<MonsterGirlScreenHandler> MONSTER_GIRL_SCREEN_HANDLES;

    public static void setMonsterGirlScreenHandles() {
        MONSTER_GIRL_SCREEN_HANDLES = new ExtendedScreenHandlerType<>(MonsterGirlScreenHandler::new);
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MonsterGirl.MOD_ID,"monster_girl"), MONSTER_GIRL_SCREEN_HANDLES);
    }
}
