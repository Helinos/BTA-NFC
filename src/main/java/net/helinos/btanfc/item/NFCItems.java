package net.helinos.btanfc.item;

import net.helinos.btanfc.BTANFC;
import net.minecraft.core.item.ItemFood;
import turniplabs.halplibe.helper.ItemBuilder;

public class NFCItems {
    public static ItemFood cheese;
    
    public static void init(int minimumID) {
        cheese = new ItemBuilder(BTANFC.MOD_ID)
            .setIcon("btanfc:item/cheese")
            .build(new ItemFood("cheese", minimumID++, 5, 16, false, 4));
    }
}
