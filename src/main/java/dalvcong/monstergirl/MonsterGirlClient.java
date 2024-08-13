package dalvcong.monstergirl;

import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.client.CaveSpiderGirlRenderer;
import dalvcong.monstergirl.entity.client.CreeperGirlRenderer;
import dalvcong.monstergirl.entity.client.EndermanGirlRenderer;
import dalvcong.monstergirl.entity.client.SkeletonGirlRenderer;
import dalvcong.monstergirl.entity.client.SpiderGirlRenderer;
import dalvcong.monstergirl.entity.client.ZombieGirlRenderer;
import dalvcong.monstergirl.screen.ModScreenHandlers;
import dalvcong.monstergirl.screen.MonsterGirlScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MonsterGirlClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.CREEPERGIRL, CreeperGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENDERMANGIRL, EndermanGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.ZOMBIEGIRL, ZombieGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.SKELETONGIRL, SkeletonGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPIDERGIRL, SpiderGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.CAVESPIDERGIRL, CaveSpiderGirlRenderer::new);

        HandledScreens.register(ModScreenHandlers.MONSTER_GIRL_SCREEN_HANDLES, MonsterGirlScreen::new);


    }


}
