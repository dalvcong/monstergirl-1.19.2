package dalvcong.monstergirl.screen;

import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

public class MonsterGirlScreenHandler extends ScreenHandler {

    public static final Text TITLE = Text.translatable("container.monstergirl.monster_girl");

    private final CreeperGirlEntity maid;

    public MonsterGirlScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf packet) {
        this(syncId, inventory, (CreeperGirlEntity) inventory.player.getWorld().getEntityById(packet.readInt()));
    }

    public MonsterGirlScreenHandler(int syncId, PlayerInventory playerInventory, CreeperGirlEntity maid) {
        super(ModScreenHandlers.MONSTER_GIRL_SCREEN_HANDLES, syncId);
        this.maid = maid;

        this.addSlot(this.new MaidHeldItemSlot(8, 18));

        Inventory maidInventory = this.maid.getInventory();
        int size = maidInventory.size(); // 15
        for (int i = 0; i < 3 && (i + 1) * 5 <= size; i++) {
            for (int j = 0; j < 5 && i * 5 + j + 1 <= size; j++) {
                this.addSlot(new Slot(maidInventory, i * 5 + j, 80 + j * 18, 18 + i * 18));
            }
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack ItemStack2 = slot.getStack();
            itemStack = ItemStack2.copy();
            int size = this.maid.getInventory().size();

            if (index >= 0 && index < 3 + size) {
                if (!this.insertItem(ItemStack2, 3 + size, 39 + size, false)) return ItemStack.EMPTY;
            } else if (index >= 3 + size && index < 39 + size) {
                if (!this.insertItem(ItemStack2, 1, 2, false)) {
                    if (!this.insertItem(ItemStack2, 2, 3, false)) {
                        if (!this.insertItem(ItemStack2, 3, 3 + size, false)) return ItemStack.EMPTY;
                    }
                }
            }

            if (ItemStack2.isEmpty()) {
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
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
