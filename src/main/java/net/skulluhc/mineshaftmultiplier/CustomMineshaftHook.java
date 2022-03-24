package net.skulluhc.mineshaftmultiplier;

import lombok.SneakyThrows;
import net.minecraft.server.v1_7_R4.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.generator.NormalChunkGenerator;

import java.lang.reflect.Field;

public class CustomMineshaftHook {

    private final MineshaftMultiplierPlugin plugin;
    private final World world;
    private final double multiplier;

    public CustomMineshaftHook(MineshaftMultiplierPlugin plugin, double multiplier, World world) {
        this.plugin = plugin;
        this.multiplier = multiplier;
        this.world = world;
        this.hook();
    }


    private void hook() {
        WorldServer worldServer = ((CraftWorld) world).getHandle();
        IChunkProvider provider = worldServer.chunkProvider;

        System.out.println("Checking if provider is ChunkProviderGEnerate");

        if (provider instanceof ChunkProviderServer) {
            ChunkProviderServer providerServer = (ChunkProviderServer) provider;
            Field field = null;
            try {
                field = providerServer.getClass().getDeclaredField("chunkProvider");
                field.setAccessible(true);
            } catch (NoSuchFieldException exception) {
                exception.printStackTrace();
            }

            try{
                NormalChunkGenerator normalChunkGenerator = (NormalChunkGenerator) field.get(providerServer);

                Field normalChunkProviderField = normalChunkGenerator.getClass().getDeclaredField("provider");
                normalChunkProviderField.setAccessible(true);

                ChunkProviderGenerate providerGenerate = (ChunkProviderGenerate) normalChunkProviderField.get(normalChunkGenerator);

                Field mineshaftField = providerGenerate.getClass().getDeclaredField("w");
                mineshaftField.setAccessible(true);
                WorldGenMineshaft mineshaft = (WorldGenMineshaft) mineshaftField.get(providerGenerate);
                Field chanceField = mineshaft.getClass().getDeclaredField("e");

                chanceField.setAccessible(true);
                double e = (Double) chanceField.get(mineshaft);
                System.out.println("e: " + e);
                chanceField.set(mineshaft, e * multiplier);
                System.out.println("e: " + chanceField.get(mineshaft));
            } catch (NoSuchFieldException ignored) {} catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

}
