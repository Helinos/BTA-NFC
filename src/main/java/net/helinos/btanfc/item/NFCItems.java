package net.helinos.btanfc.item;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;

public class NFCItems {
    public static ItemFood foodCheese;
    public static ItemPlaceable foodPizzaRaw;
    public static ItemPlaceable foodPizzaCooked;
    public static ItemFood foodEggCooked;
    public static Item dustGlowstoneBlue;
    
    public static void init() {
        BTANFC.LOGGER.info("Initializing items.");

        foodCheese = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/cheese")
            .build(new ItemFood("food.cheese", BTANFC.config.getInt("ItemIDs.foodCheese"), 5, 16, false, 4));
        
        CreativeHelper.setParent(new ItemStack(foodCheese, 1, 0), new ItemStack(Item.bucketMilk.id, 1, 0, null));

        foodPizzaRaw = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/pizza_raw")
            .build(new ItemPlaceable("food.pizza.raw", BTANFC.config.getInt("ItemIDs.foodPizzaRaw"), NFCBlocks.pizzaRaw));

        foodPizzaCooked = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/pizza_cooked")
            .build(new ItemPlaceable("food.pizza.cooked", BTANFC.config.getInt("ItemIDs.foodPizzaCooked"), NFCBlocks.pizzaCooked));
        
        foodEggCooked = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/egg_cooked")
            .build(new ItemFood("food.egg.cooked", BTANFC.config.getInt("ItemIDs.foodEggCooked"), 4, 16, false, 1));
    
        dustGlowstoneBlue = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/dust_glowstone_blue")
            .build(new Item("dust.glowstone.blue", BTANFC.config.getInt("ItemIDs.dustGlowstoneBlue")));

        CreativeHelper.setParent(new ItemStack(dustGlowstoneBlue, 1, 0), new ItemStack(Item.dustGlowstone.id, 1, 0, null));

            BTANFC.LOGGER.info("Initialized " + BTANFC.ITEM_FIELDS.size() + " items.");
    }
}
