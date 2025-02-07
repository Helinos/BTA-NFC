package net.helinos.btanfc.recipe;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;

public class CarpentryRecipes {
    public void addRecipes(RecipeGroup<RecipeEntryCarpentry> group) {
        group.register(
            "mud_bricks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.MUD.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.BRICK_MUD, 1, 0),
                    new ItemStack(NFCBlocks.BRICK_MUD, 1, 1),
                    new ItemStack(NFCBlocks.BRICK_MUD, 1, 2),
                    new ItemStack(NFCBlocks.BRICK_MUD, 1, 3)
                }
            )
        );

        group.register(
            "fired_mud_bricks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.MUD_BAKED.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.BRICK_MUD_FIRED, 1, 0),
                    new ItemStack(NFCBlocks.BRICK_MUD_FIRED, 1, 1),
                    new ItemStack(NFCBlocks.BRICK_MUD_FIRED, 1, 2),
                    new ItemStack(NFCBlocks.BRICK_MUD_FIRED, 1, 3)
                }
            )
        );

        group.register(
            "planks", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.PLANKS_OAK.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.PLANKS_ALTERNATE, 1, 0),
                    new ItemStack(NFCBlocks.PLANKS_ALTERNATE, 1, 1),
                    new ItemStack(NFCBlocks.PLANKS_ALTERNATE, 1, 2),
                    new ItemStack(NFCBlocks.PLANKS_ALTERNATE, 1, 3)
                }
            )
        );

        group.register(
            "smooth_stone", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.STONE.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.STONE_SMOOTH, 1, 0),
                    new ItemStack(NFCBlocks.STONE_SMOOTH, 1, 1),
                    new ItemStack(NFCBlocks.STONE_SMOOTH_AXIS_ALIGNED, 1, 0),
                    new ItemStack(NFCBlocks.STONE_SMOOTH_AXIS_ALIGNED, 1, 1),
                    new ItemStack(Blocks.BRICK_STONE),
                    new ItemStack(NFCBlocks.STONE_SMOOTH, 1, 2)
                }
            )
        );

        group.register(
            "log", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.LOG_OAK.getDefaultStack())
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.LOG_ALTERNATE, 1, 0),
                    new ItemStack(NFCBlocks.LOG_ALTERNATE, 1, 1),
                    new ItemStack(NFCBlocks.LAMINATED_WOOD, 2, 0),
                    new ItemStack(NFCBlocks.LAMINATED_WOOD, 2, 1),
                    new ItemStack(NFCBlocks.LAMINATED_WOOD_AXIS_ALIGNED, 2, 0),
                    new ItemStack(NFCBlocks.LAMINATED_WOOD_AXIS_ALIGNED, 2, 1)
                }
            )
        );

        group.register(
            "pebble", 
            new RecipeEntryCarpentry(
                new RecipeSymbol[] {
                    new RecipeSymbol(new ItemStack(NFCBlocks.PEBBLE, 1, 0))
                },
                new ItemStack[] {
                    new ItemStack(NFCBlocks.PEBBLE, 1, 1),
                    new ItemStack(NFCBlocks.PEBBLE, 1, 2),
                    new ItemStack(NFCBlocks.PEBBLE, 1, 3),
                }
            )
        );
    }
}
