package net.helinos.btanfc.gui.guidebook.carpentry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ItemElement;
import net.minecraft.client.gui.TooltipElement;
import net.minecraft.client.gui.guidebook.RecipePage;
import net.minecraft.client.gui.guidebook.SlotGuidebook;
import net.minecraft.client.render.Font;
import net.minecraft.client.render.TextureManager;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.slot.Slot;

public class RecipePageCarpentry extends RecipePage<RecipeEntryCarpentry> {
    private static final int SLOT_SIDE_LENGTH = 18;
    private static final int ARROW_WIDTH = 32;
    private static final int HALF_PAGE_WIDTH = 158 / 2;
    
    public ArrayList<SlotGuidebook> slots = new ArrayList<>();
    public HashMap<RecipeEntryCarpentry, ArrayList<SlotGuidebook>> recipeToSlotsMap;
    private final TooltipElement tooltipElement;
    private final ItemElement itemElement;
    private static final Minecraft minecraft = Minecraft.getMinecraft();

    public RecipePageCarpentry(GuidebookSectionCarpentry section, ArrayList<RecipeEntryCarpentry> recipes) {
        super(section);
        this.recipes = recipes;
        this.tooltipElement = new TooltipElement(minecraft);
        this.itemElement = new ItemElement(minecraft);
        this.recipeToSlotsMap = new HashMap<>();

        int yOffset = 39;
        
        for (RecipeEntryCarpentry recipe : recipes) {
            ItemStack[] outputs = recipe.getOutput();
            ArrayList<SlotGuidebook> recipeSlots = new ArrayList<>();
            int halfRecipeWidth = (ARROW_WIDTH + SLOT_SIDE_LENGTH + SLOT_SIDE_LENGTH * (outputs.length > 4 ? 4 : outputs.length)) / 2;
            int halfRecipeHeight = SLOT_SIDE_LENGTH * (int) Math.ceil(outputs.length / 4.0) / 2;
            int xPosition = HALF_PAGE_WIDTH + halfRecipeWidth;
            int yPosition = yOffset - halfRecipeHeight;

            for (int row = 0; row < (int) Math.ceil(outputs.length / 4.0); row++) {
                xPosition -= SLOT_SIDE_LENGTH * (outputs.length > 4 ? 4 : outputs.length);
                for (int column = 0; column < (outputs.length > 4 ? 4 : outputs.length); column++) {
                    int index = column + row * 4;
                    ItemStack[] output = recipe.getOutput();

                    if (index < output.length) {
                        recipeSlots.add(new SlotGuidebook(column + row * 4, xPosition, yPosition, new RecipeSymbol(output[index]), false, recipe).setAsOutput());
                    } else {
                        recipeSlots.add(new SlotGuidebook(column + row * 4, xPosition, yPosition, null, false, recipe).setAsOutput());
                    }
                    
                    xPosition += SLOT_SIDE_LENGTH;
                }
                yPosition += SLOT_SIDE_LENGTH;
            }

            recipeSlots.add(new SlotGuidebook(outputs.length, HALF_PAGE_WIDTH - halfRecipeWidth, yOffset - SLOT_SIDE_LENGTH / 2, recipe.getInput()[0], false, recipe));
            this.recipeToSlotsMap.put(recipe, recipeSlots);
            this.slots.addAll(recipeSlots);

            yOffset += 64;
        }
    }

    @Override
    protected void renderForeground(TextureManager textureManager, Font font, int x, int y, int mouseX, int mouseY, float partialTicks) {
        if (this.recipes.isEmpty()) {
            this.drawStringCenteredNoShadow(font, I18n.getInstance().translateKey("guidebook.section.search.error.no_recipes"), x + 79, y + 110, -8355712);
        }

        SlotGuidebook mouseOverSlot = null;
        ++partialTicks;

        for (SlotGuidebook slot : this.slots) {
            if (partialTicks > 150L) {
                slot.showRandomItem();
                if (this.slots.get(this.slots.size() -1) == slot)
                    partialTicks = 0L;
            }

            this.drawSlot(x + slot.x - 1, y + slot.y - 1, -1);
            if (this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) {
                mouseOverSlot = slot;
            }

            this.itemElement.render(slot.getItemStack(), x + slot.x, y + slot.y, mouseOverSlot == slot, slot);
        }
    }
    
    public boolean getIsMouseOverSlot(Slot slot, int x, int y, int mouseX, int mouseY) {
        return mouseX >= x + slot.x - 1 && mouseX < x + slot.x + 16 + 1 && mouseY >= y + slot.y - 1 && mouseY < y + slot.y + 16 + 1;
    }

    @Override
    protected void renderBackground(TextureManager textureManager, int x, int y) {
        super.renderBackground(textureManager, x, y);

        for(RecipeEntryCarpentry recipe : this.recipes) {
            ArrayList<SlotGuidebook> slots = this.recipeToSlotsMap.get(recipe);
            
            int displayX = x + slots.get(slots.size() - 1).x + SLOT_SIDE_LENGTH + 1;
            int displayY = y + slots.get(slots.size() - 1).y;
            // This is awful but I don't know how to get my own texture here
            this.drawTexturedModalRect(displayX, displayY, 255, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 1, displayY, 254, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 2, displayY, 253, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 3, displayY, 252, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 4, displayY, 251, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 5, displayY, 250, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 6, displayY, 249, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 7, displayY, 248, 0, 1, 15);
            this.drawTexturedModalRect(displayX + 8, displayY, 236, 0, 20, 15);
        }
   }

    @Override
    protected void renderOverlay(TextureManager textureManager, Font font, int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.renderOverlay(textureManager, font, x, y, mouseX, mouseY, partialTicks);
        SlotGuidebook mouseOverSlot = null;
        Iterator<SlotGuidebook> slotsIterator = this.slots.iterator();

        while(true) {
            do {
                do {
                if (!slotsIterator.hasNext()) {
                    return;
                }

                SlotGuidebook slot = slotsIterator.next();
                if (this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) {
                    mouseOverSlot = slot;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                } while (mouseOverSlot == null);
            } while (!mouseOverSlot.hasItem());

            boolean showDescription = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157) || (Boolean)minecraft.gameSettings.alwaysShowDescriptions.value;
            String str = this.tooltipElement.getTooltipText(mouseOverSlot.getItemStack(), showDescription, mouseOverSlot);
            if (!str.isEmpty()) {
                this.tooltipElement.render(str, mouseX, mouseY, 8, -8);
            }
        }
    }
}
