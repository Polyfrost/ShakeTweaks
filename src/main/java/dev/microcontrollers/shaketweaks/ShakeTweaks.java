package dev.microcontrollers.shaketweaks;

import dev.microcontrollers.shaketweaks.config.ShakeTweaksConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.Objects;

public class ShakeTweaks implements ModInitializer {
	@Override
	public void onInitialize() {
		ShakeTweaksConfig.CONFIG.load();

		// idea from https://github.com/jumpingpxl/NoBob-Fabric-1.17
		ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
			if(ShakeTweaksConfig.CONFIG.instance().disableHorizontalBobbing && Objects.nonNull(minecraft.player)) {
				minecraft.player.walkDist = 0.0F;
			}
        });
	}
}
