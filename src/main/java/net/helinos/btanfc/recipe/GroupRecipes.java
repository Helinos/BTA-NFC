package net.helinos.btanfc.recipe;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.recipe.entry.RecipeEntryCraftingGroup;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockPainted;

public class GroupRecipes {
    public void addRecipes(RecipeGroup<RecipeEntryCrafting<?, ?>> group) {
        group.register(
            "window", 
            new RecipeEntryCraftingGroup(
                2, 1,
                new RecipeSymbol[] {
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack())
                },
                NFCBlocks.WINDOW.asItem().getDefaultStack(),
                (inventoryCrafting, stackSize) -> {
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();
                    
                    for (int index = 0; index < 9; index++) {
                        ItemStack inputStack = inventoryCrafting.getItem(index);
                        if (inputStack == null)
                            continue;

                        if (inputStack.getItem() instanceof ItemBlockPainted) {
                            int metadata = inputStack.getMetadata();
                            window.setMetadata(0b10000000 | metadata << 3);
                            return window;
                        }
                    }

                    return window;
                }
            )    
        );
        ItemStack verticalDoubleWindow = NFCBlocks.WINDOW.asItem().getDefaultStack();
        verticalDoubleWindow.setMetadata(1);
        group.register(
            "vertical_double_window", 
            new RecipeEntryCraftingGroup(
                3, 1,
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack())
                },
                verticalDoubleWindow,
                (inventoryCrafting, stackSize) -> {
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();
                    
                    for (int index = 0; index < 9; index++) {
                        ItemStack inputStack = inventoryCrafting.getItem(index);
                        if (inputStack == null)
                            continue;

                        if (inputStack.getItem() instanceof ItemBlockPainted) {
                            int metadata = inputStack.getMetadata();
                            window.setMetadata(0b10000001 | metadata << 3);
                            return window;
                        }
                    }

                    return window;
                }
            )
        );
        ItemStack smallWindow = NFCBlocks.WINDOW.asItem().getDefaultStack();
        verticalDoubleWindow.setMetadata(2);
        group.register(
            "small_window", 
            new RecipeEntryCraftingGroup(
                3, 1,
                new RecipeSymbol[] {
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks")
                },
                smallWindow,
                (inventoryCrafting, stackSize) -> {
                    ItemStack inputPlank1 = inventoryCrafting.getItem(0);
                    ItemStack inputPlank2 = inventoryCrafting.getItem(2);
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();

                    for (int index = 0; index < 9; index++) {
                        ItemStack inputStack = inventoryCrafting.getItem(index);
                        ItemStack inputStack2 = inventoryCrafting.getItem(index + 2);
                        if (inputStack == null || inputStack2 == null)
                            continue;

                        if (inputStack.getItem() instanceof ItemBlockPainted && inputStack2.getItem() instanceof ItemBlockPainted) {
                            if (inputStack.getMetadata() != inputPlank2.getMetadata())
                                return null;

                            int metadata = inputPlank1.getMetadata();
                            window.setMetadata(0b10000010 | metadata << 3);
                            return window;
                        } else if (!(inputPlank1.getItem() instanceof ItemBlockPainted) && !(inputPlank2.getItem() instanceof ItemBlockPainted)) {
                            return window;
                        }
                    }

                    return null;
                }
            )
        );
        ItemStack horizontalDoubleWindow = NFCBlocks.WINDOW.asItem().getDefaultStack();
        horizontalDoubleWindow.setMetadata(3);
        group.register(
            "horizontal_double_window", 
            new RecipeEntryCraftingGroup(
                1, 3,
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack())
                },
                horizontalDoubleWindow,
                (inventoryCrafting, stackSize) -> {
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();
                    
                    for (int index = 0; index < 9; index++) {
                        ItemStack inputStack = inventoryCrafting.getItem(index);
                        if (inputStack == null)
                            continue;

                        if (inputStack.getItem() instanceof ItemBlockPainted) {
                            int metadata = inputStack.getMetadata();
                            window.setMetadata(0b10000011 | metadata << 3);
                            return window;
                        }
                    }

                    return window;
                }
            )    
        );
        ItemStack miniWindow = NFCBlocks.WINDOW.asItem().getDefaultStack();
        verticalDoubleWindow.setMetadata(4);
        group.register(
            "mini_window", 
            new RecipeEntryCraftingGroup(
                3, 3,
                new RecipeSymbol[] {
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks")
                },
                miniWindow,
                (inventoryCrafting, stackSize) -> {
                    ItemStack inputPlank = inventoryCrafting.getItem(0);
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();

                    boolean allSameColor = true;
                    boolean allSameBlock = true;
                    for (int index = 1; index < 9; index++) {
                        if (index == 4)
                            continue;
                        allSameColor &= inputPlank.getMetadata() == inventoryCrafting.getItem(index).getMetadata();
                        allSameBlock &= inputPlank.itemID == inventoryCrafting.getItem(index).itemID;
                    }

                    if (inputPlank.getItem() instanceof ItemBlockPainted) {
                        if (!allSameColor || !allSameBlock) {
                            return null;
                        }

                        int metadata = inputPlank.getMetadata();
                        window.setMetadata(0b10000100 | metadata << 3);
                        return window;
                    } else if (allSameBlock) {
                        return window;
                    }

                    return null;
                }
            )
        );
        ItemStack quadrupleWindow = NFCBlocks.WINDOW.asItem().getDefaultStack();
        quadrupleWindow.setMetadata(5);
        group.register(
            "quadruple_window", 
            new RecipeEntryCraftingGroup(
                3, 3,
                new RecipeSymbol[] {
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack()),
                    new RecipeSymbol("minecraft:planks"),
                    new RecipeSymbol(Blocks.GLASS.getDefaultStack())
                },
                quadrupleWindow,
                (inventoryCrafting, stackSize) -> {
                    ItemStack inputPlank = inventoryCrafting.getItem(1);
                    ItemStack window = NFCBlocks.WINDOW.getDefaultStack();

                    boolean allSameColor = true;
                    boolean allSameBlock = true;
                    for (int index = 1; index < 9; index++) {
                        if (index == 2 || index == 6 || index == 8)
                            continue;
                        allSameColor &= inputPlank.getMetadata() == inventoryCrafting.getItem(index).getMetadata();
                        allSameBlock &= inputPlank.itemID == inventoryCrafting.getItem(index).itemID;
                    }

                    if (inputPlank.getItem() instanceof ItemBlockPainted) {
                        if (!allSameColor || !allSameBlock)
                            return null;

                        int metadata = inputPlank.getMetadata();
                        window.setMetadata(0b10000101 | metadata << 3);
                        return window;
                    } else if (allSameBlock) {
                        return window;
                    }

                    return null;
                }
            )
        );
    }
}
