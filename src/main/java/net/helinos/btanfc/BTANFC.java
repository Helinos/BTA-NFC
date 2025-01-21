package net.helinos.btanfc;

import net.fabricmc.api.ModInitializer;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.item.NFCItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.ItemHelper;

public class BTANFC implements ModInitializer {
	public static final String MOD_ID = "btanfc";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		int minimumBlockID = BlockBuilder.Registry.findOpenIds(NFCBlocks.class.getDeclaredFields().length - 1);
		int minimumItemID = ItemHelper.Registry.findOpenIds(NFCItems.class.getDeclaredFields().length);
		NFCBlocks.init(minimumBlockID);
		NFCItems.init(minimumItemID);
		LOGGER.info("BTA-NFC initialized.");
	}
}
