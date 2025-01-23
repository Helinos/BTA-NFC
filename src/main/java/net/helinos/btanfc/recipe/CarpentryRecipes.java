package net.helinos.btanfc.recipe;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;

public class CarpentryRecipes {
    public void addRecipes(RecipeGroup<RecipeEntryCarpentry> group) {
        group.register(
            "mud_bricks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.mud.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.brickMud, 1, 0),
                    new ItemStack(NFCBlocks.brickMud, 1, 1),
                    new ItemStack(NFCBlocks.brickMud, 1, 2),
                    new ItemStack(NFCBlocks.brickMud, 1, 3)
                }
            )
        );
        group.register(
            "fired_mud_bricks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.mudBaked.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.brickMudFired, 1, 0),
                    new ItemStack(NFCBlocks.brickMudFired, 1, 1),
                    new ItemStack(NFCBlocks.brickMudFired, 1, 2),
                    new ItemStack(NFCBlocks.brickMudFired, 1, 3)
                }
            )
        );
        group.register(
            "planks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.planksOak.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.planksAlternate, 1, 0),
                    new ItemStack(NFCBlocks.planksAlternate, 1, 1),
                    new ItemStack(NFCBlocks.planksAlternate, 1, 2),
                    new ItemStack(NFCBlocks.planksAlternate, 1, 3)
                }
            )
        );
    }
}
