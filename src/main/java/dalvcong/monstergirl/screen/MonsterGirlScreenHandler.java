package dalvcong.monstergirl.screen;

import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

public class MonsterGirlScreenHandler extends ScreenHandler {

    public static final Text TITLE = Text.translatable("container.monstergirl.menu");

    private final CreeperGirlEntity maid;

    public MonsterGirlScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf packet) {
        this(syncId, inventory, (CreeperGirlEntity) inventory.player.getWorld().getEntityById(packet.readInt()));
    }

    public MonsterGirlScreenHandler(int syncId, PlayerInventory playerInventory, CreeperGirlEntity maid) {
        super(ModScreenHandlers.MONSTER_GIRL_SCREEN_HANDLES, syncId);
        this.maid = maid;

        this.addSlot(this.new MaidHeldItemSlot(8, 18));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            itemStack = originalStack.copy();
            if (index < this.maid.getInventory().size()) {
                if (!this.insertItem(originalStack, this.maid.getInventory().size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.maid.getInventory().size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.maid.getInventory().canPlayerUse(player) && this.maid.isAlive() && this.maid.distanceTo(player) < 8.0f;
    }

    public CreeperGirlEntity getMain() {
        return this.maid;
    }

    private class MaidHeldItemSlot extends Slot {
        public MaidHeldItemSlot(int x, int y) {
            super(new SimpleInventory(1), 0, x, y);

            this.setStack(MonsterGirlScreenHandler.this.getMain().getMainHandStack());
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0;l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
