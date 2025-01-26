package net.helinos.btanfc;

import net.fabricmc.api.ModInitializer;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.entity.fx.EntitySporeFX;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class BTANFC implements ModInitializer, ClientStartEntrypoint {

	public static final String MOD_ID = "btanfc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TomlConfigHandler config;
	public static final String[] MUSIC_NAMES = new String[] {
		"fiasco1",
		"fiasco2",
		"nuance3",
		"snupp1",
		"zowja1"
	};
	public static final List<Field> BLOCK_FIELDS = Arrays.stream(NFCBlocks.class.getDeclaredFields()).filter((field) -> Block.class.isAssignableFrom(field.getType())).collect(Collectors.toList());
	public static final List<Field> ITEM_FIELDS = Arrays.stream(NFCItems.class.getDeclaredFields()).filter((field) -> Item.class.isAssignableFrom(field.getType())).collect(Collectors.toList());
	
	public static final int BLUE_GLOWSTONE_CHANCE_1 = 125;
	public static final int BLUE_GLOWSTONE_CHANCE_2 = 150;

	static {
		config = new TomlConfigHandler(MOD_ID, new Toml("BTA + NFC configuration file."), false);

		File configFile = config.getConfigFile();
		if (configFile.exists()) {
			config.loadConfig();
			config.setDefaults(config.getRawParsed());
			Toml rawConfig = config.getRawParsed();
			boolean changed = false;

			ArrayList<String> missingBlocks = new ArrayList<>();
			for (Field blockField : BLOCK_FIELDS) {
				if (!rawConfig.contains("BlockIDs." + blockField.getName())) {
					missingBlocks.add(blockField.getName());
				}
			}
			if (missingBlocks.size() != 0) {
				int newNextBlockID = BLOCK_FIELDS.size() - missingBlocks.size() + BlockBuilder.Registry.findOpenIds(BLOCK_FIELDS.size() + missingBlocks.size());
				for (String missingBlockName : missingBlocks) {
					rawConfig.addEntry("BlockIDs." + missingBlockName, newNextBlockID++);
				}
				changed = true;
			}

			ArrayList<String> missingItems = new ArrayList<>();
			for (Field itemField : ITEM_FIELDS) {
				if (!rawConfig.contains("ItemIDs." + itemField.getName())) {
					missingItems.add(itemField.getName());
				}
			}
			if (missingItems.size() != 0) {
				int newNextItemID = ITEM_FIELDS.size() - missingItems.size() + ItemHelper.Registry.findOpenIds(ITEM_FIELDS.size() + missingItems.size());
				for (String missingItemName : missingItems) {
					rawConfig.addEntry("ItemIDs." + missingItemName, newNextItemID++);
				}
				changed = true;
			}

			if (changed) {
				config.setDefaults(rawConfig);
				config.writeConfig();
				config.loadConfig();
			}
		} else {
			Toml defaultConfig = new Toml("BTA + NFC configuration file.");
			int blockIDStart = BlockBuilder.Registry.findOpenIds(BLOCK_FIELDS.size());
			defaultConfig.addCategory("BlockIDs");
			int itemIDStart = ItemHelper.Registry.findOpenIds(ITEM_FIELDS.size());
			defaultConfig.addCategory("ItemIDs");
	
			int blockID = blockIDStart;
			int itemID = itemIDStart;
			for (Field blockField : BLOCK_FIELDS) {
				defaultConfig.addEntry("BlockIDs." + blockField.getName(), blockID++);
			}
			for (Field itemField : ITEM_FIELDS) {
				defaultConfig.addEntry("ItemIDs." + itemField.getName(), itemID++);
			}

			config.setDefaults(defaultConfig);
			try {
				configFile.getParentFile().mkdirs();
				configFile.createNewFile();
				config.writeConfig();
				config.loadConfig();
			} catch (IOException e) {
                throw new RuntimeException("Failed to generate config!", e);
            }
		}
	}

	public void onInitialize() {
		NFCBlocks.init();
		NFCItems.init();
		
		ParticleHelper.createParticle("sporePurple", (world, x, y, z, deltaX, deltaY, deltaZ, i) -> new EntitySporeFX(world, x, y, z, deltaX, deltaY, deltaZ, 0.98F, 0.78F, 1.0F));
		ParticleHelper.createParticle("sporeBlue", (world, x, y, z, deltaX, deltaY, deltaZ, i) -> new EntitySporeFX(world, x, y, z, deltaX, deltaY, deltaZ, 0.78F, 0.98F, 1.0F));

		LOGGER.info("BTA-NFC initialized.");
	}

	@Override
	public void beforeClientStart() {
		BTANFC.LOGGER.info("Adding music.");
		Minecraft minecraft = Minecraft.getMinecraft(getClass());
		for (String musicName : MUSIC_NAMES) {
			File music;
			URL resource = getClass().getClassLoader().getResource("nfcmusic/" + musicName + ".ogg");
			try {
				music = new File(resource.toURI());
			} catch (URISyntaxException e) {
				throw new RuntimeException("File not found!");
			}
			minecraft.sndManager.addMusic(musicName + ".ogg", music);
		}
		BTANFC.LOGGER.info("Added " + MUSIC_NAMES.length + " pieces of music to the random music pool.");
	}

	@Override
	public void afterClientStart() {
	}
}
