package net.helinos.btanfc;

import net.fabricmc.api.ModInitializer;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class BTANFC implements ModInitializer {

	public static final String MOD_ID = "btanfc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TomlConfigHandler config;

	static {
		List<Field> blockFields = Arrays.stream(NFCBlocks.class.getDeclaredFields()).filter((field) -> Block.class.isAssignableFrom(field.getType())).collect(Collectors.toList());
        List<Field> itemFields = Arrays.stream(NFCItems.class.getDeclaredFields()).filter((field) -> Item.class.isAssignableFrom(field.getType())).collect(Collectors.toList());

		config = new TomlConfigHandler(MOD_ID, new Toml("BTA + NFC configuration file."), false);

		File configFile = config.getConfigFile();
		if (configFile.exists()) {
			config.loadConfig();
			config.setDefaults(config.getRawParsed());
			Toml rawConfig = config.getRawParsed();
			boolean changed = false;

			ArrayList<String> missingBlocks = new ArrayList<>();
			for (Field blockField : blockFields) {
				if (!rawConfig.contains("BlockIDs." + blockField.getName())) {
					missingBlocks.add(blockField.getName());
				}
			}
			if (missingBlocks.size() != 0) {
				int newNextBlockID = blockFields.size() - missingBlocks.size() + BlockBuilder.Registry.findOpenIds(blockFields.size() + missingBlocks.size());
				for (String missingBlockName : missingBlocks) {
					rawConfig.addEntry("BlockIDs." + missingBlockName, newNextBlockID++);
				}
				changed = true;
			}

			ArrayList<String> missingItems = new ArrayList<>();
			for (Field itemField : itemFields) {
				if (!rawConfig.contains("ItemIDs." + itemField.getName())) {
					missingItems.add(itemField.getName());
				}
			}
			if (missingItems.size() != 0) {
				int newNextItemID = itemFields.size() - missingItems.size() + ItemHelper.Registry.findOpenIds(itemFields.size() + missingItems.size());
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
			int blockIDStart = BlockBuilder.Registry.findOpenIds(blockFields.size());
			defaultConfig.addCategory("BlockIDs");
			int itemIDStart = ItemHelper.Registry.findOpenIds(itemFields.size());
			defaultConfig.addCategory("ItemIDs");
	
			int blockID = blockIDStart;
			int itemID = itemIDStart;
			for (Field blockField : blockFields) {
				defaultConfig.addEntry("BlockIDs." + blockField.getName(), blockID++);
			}
			for (Field itemField : itemFields) {
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

		LOGGER.info("BTA-NFC initialized.");
	}
}
