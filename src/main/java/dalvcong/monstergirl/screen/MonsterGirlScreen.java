package dalvcong.monstergirl.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dalvcong.monstergirl.MonsterGirl;
import dalvcong.monstergirl.entity.custom.MonsterGirlEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MonsterGirlScreen extends HandledScreen<MonsterGirlScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(MonsterGirl.MOD_ID, "textures/gui/monster_girl.png");

    private float mouseX;
    private float mouseY;

    public MonsterGirlScreen(MonsterGirlScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        this.passEvents = false;
        this.backgroundHeight = 168;
        this.playerInventoryTitleY = backgroundHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        titleX = 34;

    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        MonsterGirlEntity entity = this.getScreenHandler().getMonsterGirlEntity();

        InventoryScreen.drawEntity(x + 52, y + 68, 25, (float)(x + 52) - this.mouseX, (float)(y + 68 - 38) - this.mouseY, entity);
    }

    

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        this.mouseX = (float)mouseX;
        this.mouseY = (float)mouseY;
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
