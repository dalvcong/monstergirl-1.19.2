package dalvcong.monstergirl.screen;

import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class MonsterGirlScreenHandlerFactory implements ExtendedScreenHandlerFactory {
    private final CreeperGirlEntity creeperGirlEntity;

    public MonsterGirlScreenHandlerFactory(CreeperGirlEntity creeperGirlEntity) {
        this.creeperGirlEntity = creeperGirlEntity;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        int id = this.creeperGirlEntity.getId();

        buf.writeInt(id);

    }

    @Override
    public Text getDisplayName() {
        return MonsterGirlScreenHandler.TITLE;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MonsterGirlScreenHandler(syncId, inv, this.creeperGirlEntity);
    }
}
