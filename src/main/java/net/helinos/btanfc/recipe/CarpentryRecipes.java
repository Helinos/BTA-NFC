package net.helinos.btanfc.recipe;

import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;

public class CarpentryRecipes {
    public void addRecipes(RecipeGroup<RecipeEntryCarpentry> group) {
        group.register(
            "planks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.planksOak.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(Block.stone)
                }
            )
        );
    }
}
