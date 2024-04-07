package dalvcong.monstergirl;

import dalvcong.monstergirl.block.ModBlocks;
import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.custom.CreeperGirlEntity;
import dalvcong.monstergirl.entity.custom.EndermanGirlEntity;
import dalvcong.monstergirl.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class MonsterGirl implements ModInitializer {
	public static final String MOD_ID = "monstergirl";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.CREEPERGIRL, CreeperGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ENDERMANGIRL, EndermanGirlEntity.setAttributes());
	}
}