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

        group.register(
            "smooth_stone", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.stone.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.stoneSmooth, 1, 0),
                    new ItemStack(NFCBlocks.stoneSmooth, 1, 1),
                    new ItemStack(NFCBlocks.stoneSmoothAA, 1, 0),
                    new ItemStack(NFCBlocks.stoneSmoothAA, 1, 1),
                    new ItemStack(Block.brickStone),
                    new ItemStack(NFCBlocks.stoneSmooth, 1, 2)
                }
            )
        );

        group.register(
            "log", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Block.logOak.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.logAlternate, 1, 0),
                    new ItemStack(NFCBlocks.logAlternate, 1, 1),
                    new ItemStack(NFCBlocks.laminatedWood, 2, 0),
                    new ItemStack(NFCBlocks.laminatedWood, 2, 1),
                    new ItemStack(NFCBlocks.laminatedWood, 2, 2),
                    new ItemStack(NFCBlocks.laminatedWood, 2, 3)
                }
            )
        );

        group.register(
            "pebble", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(new ItemStack(NFCBlocks.pebble, 1, 0))
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.pebble, 1, 1),
                    new ItemStack(NFCBlocks.pebble, 1, 2),
                    new ItemStack(NFCBlocks.pebble, 1, 3),
                }
            )
        );
    }
}
