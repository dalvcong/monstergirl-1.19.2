package dalvcong.monstergirl.entity.event;

import dalvcong.monstergirl.entity.ModEntities;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface ClothesEvent {

    Event<ClothesEvent> EVENT = EventFactory.createArrayBacked(ClothesEvent.class,
            (listeners) -> (entity, itemStack) -> {
                for (ClothesEvent event : listeners) {
                    ActionResult result = event.interact(entity, itemStack);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult interact(ModEntities entity, ItemStack stack);
}
