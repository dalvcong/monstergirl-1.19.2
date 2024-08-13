package dalvcong.monstergirl;

import dalvcong.monstergirl.block.ModBlocks;
import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.custom.*;
import dalvcong.monstergirl.item.ModItems;
import dalvcong.monstergirl.screen.ModScreenHandlers;
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

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.CREEPERGIRL, CreeperGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ENDERMANGIRL, EndermanGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIEGIRL, ZombieGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SKELETONGIRL, SkeletonGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SPIDERGIRL, SpiderGirlEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CAVESPIDERGIRL, CaveSpiderGirlEntity.setAttributes());

		ModScreenHandlers.setMonsterGirlScreenHandles();
	}
}