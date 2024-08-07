package dalvcong.monstergirl.screen;

import dalvcong.monstergirl.entity.custom.MonsterGirlEntity;
import dalvcong.monstergirl.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

public class MonsterGirlScreenHandler extends ScreenHandler {

    public static final Text TITLE = Text.translatable("container.monstergirl.monster_girl");

    private final MonsterGirlEntity monsterGirlEntity;

    public MonsterGirlScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf packet) {
        this(syncId, inventory, (MonsterGirlEntity) inventory.player.getWorld().getEntityById(packet.readInt()));
    }

    public MonsterGirlScreenHandler(int syncId, PlayerInventory playerInventory, MonsterGirlEntity maid) {
        super(ModScreenHandlers.MONSTER_GIRL_SCREEN_HANDLES, syncId);
        this.monsterGirlEntity = maid;

        Inventory inventory = this.monsterGirlEntity.getInventory();
        this.addSlot(new Slot(inventory, 0, 14, 18) {
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.MONSTERGIRL_CLOTHES);
            }
        });


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            if (index == 0 ) {
                if (!this.insertItem(itemStack2, 1, 37, false))
                    return ItemStack.EMPTY;
            } else if (!this.insertItem(itemStack2, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.monsterGirlEntity.getInventory().canPlayerUse(player) && this.monsterGirlEntity.isAlive() && this.monsterGirlEntity.distanceTo(player) < 8.0f;
    }

    public MonsterGirlEntity getMonsterGirlEntity() {
        return this.monsterGirlEntity;
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
