package net.helinos.btanfc.item;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.ItemInitEntrypoint;
import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.util.toml.Toml;

public class NFCItems implements ItemInitEntrypoint {
    private static Toml rawConfig;
	private static boolean configChanged = false;

    public static ItemFood FOOD_CHEESE;
    public static ItemPlaceable FOOD_PIZZA_RAW;
    public static ItemPlaceable FOOD_PIZZA_COOKED;
    public static ItemFood FOOD_EGG_COOKED;
    public static Item DUST_GLOWSTONE_BLUE;
    
    @Override
    public void afterItemInit() {
        BTANFC.LOGGER.info("Initializing items.");

        rawConfig = BTANFC.CONFIG.getRawParsed();

        String key = "food_cheese";
        FOOD_CHEESE = new ItemBuilder(BTANFC.MOD_ID)
            .build(new ItemFood(getTranslationKey(key), getNamespaceID(key), getID(key), 5, 16, false, 4));

        key = "food_pizza_raw";
        FOOD_PIZZA_RAW = new ItemBuilder(BTANFC.MOD_ID)
            .build(new ItemPlaceable(getTranslationKey(key), getNamespaceID(key), getID(key), NFCBlocks.PIZZA_RAW));

        key = "food_pizza_cooked";
        FOOD_PIZZA_COOKED = new ItemBuilder(BTANFC.MOD_ID)
            .build(new ItemPlaceable(getTranslationKey(key), getNamespaceID(key), getID(key), NFCBlocks.PIZZA_COOKED));
        
        key = "food_egg_cooked";
        FOOD_EGG_COOKED = new ItemBuilder(BTANFC.MOD_ID)
            .build(new ItemFood(getTranslationKey(key), getNamespaceID(key), getID(key), 4, 16, false, 1));
    
        key = "dust_glowstone_blue";
        DUST_GLOWSTONE_BLUE = new ItemBuilder(BTANFC.MOD_ID)
            .build(new Item(getTranslationKey(key), getNamespaceID(key), getID(key)));

        CreativeHelper.setParent(new ItemStack(FOOD_CHEESE, 1, 0), new ItemStack(Items.BUCKET_MILK.id, 1, 0, null));
        CreativeHelper.setParent(new ItemStack(DUST_GLOWSTONE_BLUE, 1, 0), new ItemStack(Items.DUST_GLOWSTONE.id, 1, 0, null));    

        if (configChanged) {
			BTANFC.CONFIG.setDefaults(rawConfig);
			BTANFC.CONFIG.writeConfig();
			BTANFC.CONFIG.loadConfig();
		}

        BTANFC.LOGGER.info("Initialized items.");
    }

    private static int nextItemID = 16384;

	private static int getID(String key) {
		boolean containsItem;
		String category = "ItemIDs.";
		try {
			containsItem = rawConfig.contains(category + key);
		} catch (NullPointerException e) {
			containsItem = false;
		}
		
		if (containsItem) {
			return BTANFC.CONFIG.getInt(category + key);
		}

		while (Item.itemsList[++nextItemID] != null) {}
		
		rawConfig.addEntry(category + key, nextItemID);
		configChanged = true;

		return nextItemID;
	}

    private static String getTranslationKey(String key) {
        return key.replaceAll("_", ".");
    }

    private static String getNamespaceID(String key) {
        return BTANFC.MOD_ID + ":item/" + key;
    }
}
