package dalvcong.monstergirl;

import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.client.CreeperGirlRenderer;
import dalvcong.monstergirl.entity.client.EndermanGirlRenderer;
import dalvcong.monstergirl.entity.client.SkeletonGirlRenderer;
import dalvcong.monstergirl.entity.client.ZombieGirlRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MonsterGirlClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.CREEPERGIRL, CreeperGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENDERMANGIRL, EndermanGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.ZOMBIEGIRL, ZombieGirlRenderer::new);
        EntityRendererRegistry.register(ModEntities.SKELETONGIRL, SkeletonGirlRenderer::new);


    }


}