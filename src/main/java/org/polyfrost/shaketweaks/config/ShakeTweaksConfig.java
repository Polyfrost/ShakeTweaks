package org.polyfrost.shaketweaks.config;

import org.polyfrost.shaketweaks.ShakeTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class ShakeTweaksConfig extends Config {
    public ShakeTweaksConfig() {
        super(ShakeTweaks.ID + ".json", ShakeTweaks.NAME, Category.QOL);

        loadFrom("patcher.toml");
    }

    @Switch(
        title = "Remove Screen Bobbing",
        description = "While using View Bobbing, only remove the view aspect but have the hand still bounce around."
    )
    public boolean removeViewBobbing;

    @Switch(
        title = "Remove Map Bobbing",
        description = "While using View Bobbing, remove the hand bobbing when holding a map."
    )
    public boolean mapBobbing;
}
