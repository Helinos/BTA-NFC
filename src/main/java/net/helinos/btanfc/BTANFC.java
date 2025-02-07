package net.helinos.btanfc;

import net.fabricmc.api.ModInitializer;
import net.helinos.btanfc.entity.fx.ParticleSpore;
// import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
// import java.net.URISyntaxException;
// import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class BTANFC implements ModInitializer, ClientStartEntrypoint {

	public static final String MOD_ID = "btanfc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TomlConfigHandler CONFIG =  new TomlConfigHandler(MOD_ID, new Toml("BTA + NFC configuration file."), false);;
	public static final String[] MUSIC_NAMES = new String[] {
		"fiasco1",
		"fiasco2",
		"nuance3",
		"snupp1",
		"zowja1"
	};
	
	public static final int BLUE_GLOWSTONE_CHANCE_1 = 125;
	public static final int BLUE_GLOWSTONE_CHANCE_2 = 150;

	static {
		File configFile = CONFIG.getConfigFile();
		if (configFile.exists()) {
			CONFIG.loadConfig();
            CONFIG.setDefaults(CONFIG.getRawParsed());
		} else {
			Toml defaultConfig = new Toml("BTA + NFC configuration file.");
			defaultConfig.addCategory("BlockIDs");
			defaultConfig.addCategory("ItemIDs");

			CONFIG.setDefaults(defaultConfig);

			try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                CONFIG.writeConfig();
                CONFIG.loadConfig();
            } catch (IOException e) {
                throw new RuntimeException("Failed to generate configuration file!", e);
            }
		}
	}

	public void onInitialize() {
		ParticleHelper.createParticle("sporePurple", (world, x, y, z, deltaX, deltaY, deltaZ, i) -> new ParticleSpore(world, x, y, z, deltaX, deltaY, deltaZ, 0.98F, 0.78F, 1.0F));
		ParticleHelper.createParticle("sporeBlue", (world, x, y, z, deltaX, deltaY, deltaZ, i) -> new ParticleSpore(world, x, y, z, deltaX, deltaY, deltaZ, 0.78F, 0.98F, 1.0F));

		LOGGER.info("BTA-NFC initialized.");
	}

	@Override
	public void beforeClientStart() {
		// BTANFC.LOGGER.info("Adding music.");
		// Minecraft minecraft = Minecraft.getMinecraft();
		// for (String musicName : MUSIC_NAMES) {
		// 	File music;
		// 	URL resource = getClass().getClassLoader().getResource("nfcmusic/" + musicName + ".ogg");
		// 	try {
		// 		music = new File(resource.toURI());
		// 	} catch (URISyntaxException e) {
		// 		throw new RuntimeException("File not found!");
		// 	}
		// 	minecraft.sndManager.addMusic(musicName + ".ogg", music);
		// }
		// BTANFC.LOGGER.info("Added " + MUSIC_NAMES.length + " pieces of music to the random music pool.");
	}

	@Override
	public void afterClientStart() {
	}
}
