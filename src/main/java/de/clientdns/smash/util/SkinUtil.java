package de.clientdns.smash.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
public class SkinUtil {

    private final PlayerProfile profile;

    public SkinUtil(PlayerProfile profile) {
        this.profile = profile;
    }

    public void setSkin(PlayerTextures textures) {
        if (profile == null || textures == null || textures.isEmpty()) {
            return;
        }
        profile.getTextures().setSkin(textures.getSkin());
    }
}
