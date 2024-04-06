package dalvcong.monstergirl;

import dalvcong.monstergirl.entity.ModEntities;
import dalvcong.monstergirl.entity.client.CreeperGirlRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MonsterGirlClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.CREEPERGIRL, CreeperGirlRenderer::new);


    }


}
