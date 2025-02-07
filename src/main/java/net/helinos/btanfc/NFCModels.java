package net.helinos.btanfc;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.block.model.BlockModelAxisAlignedVariants;
import net.helinos.btanfc.block.model.BlockModelPizza;
import net.helinos.btanfc.block.model.BlockModelVariants;
import net.helinos.btanfc.block.model.BlockModelVariantsPainted;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

public class NFCModels implements ModelEntrypoint {
    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {
        ModelHelper.setBlockModel(NFCBlocks.MUSHROOM_PURPLE, () -> new BlockModelCrossedSquares<>(NFCBlocks.MUSHROOM_PURPLE).setAllTextures(0, "btanfc:block/mushroom_purple"));
        ModelHelper.setBlockModel(NFCBlocks.MUSHROOM_BLUE, () -> new BlockModelCrossedSquares<>(NFCBlocks.MUSHROOM_BLUE).setAllTextures(0, "btanfc:block/mushroom_blue"));
        ModelHelper.setBlockModel(NFCBlocks.MUSHROOM_GLOWING, () -> new BlockModelCrossedSquares<>(NFCBlocks.MUSHROOM_GLOWING).setAllTextures(0, "btanfc:block/mushroom_glowing"));
        ModelHelper.setBlockModel(NFCBlocks.MUSHROOM_FIRE, () -> new BlockModelCrossedSquares<>(NFCBlocks.MUSHROOM_FIRE).setAllTextures(0, "btanfc:block/mushroom_fire"));
        ModelHelper.setBlockModel(NFCBlocks.SCORCHED_SAND, () -> new BlockModelStandard<>(NFCBlocks.SCORCHED_SAND).setAllTextures(0, "btanfc:block/scorched_sand"));
        ModelHelper.setBlockModel(NFCBlocks.BRICK_SCORCHED_SANDSTONE, () -> new BlockModelStandard<>(NFCBlocks.BRICK_SCORCHED_SANDSTONE).setAllTextures(0, "btanfc:block/scorched_sandstone_bricks"));
        ModelHelper.setBlockModel(NFCBlocks.SLAB_BRICK_SCORCHED_SANDSTONE, () -> new BlockModelSlab<>(NFCBlocks.SLAB_BRICK_SCORCHED_SANDSTONE));
        ModelHelper.setBlockModel(NFCBlocks.STAIRS_BRICK_SCORCHED_SANDSTONE, () -> new BlockModelStairs<>(NFCBlocks.STAIRS_BRICK_SCORCHED_SANDSTONE));
        ModelHelper.setBlockModel(NFCBlocks.GLOWSTONE_BLUE, () -> new BlockModelStandard<>(NFCBlocks.GLOWSTONE_BLUE).setAllTextures(0, "btanfc:block/glowstone_blue"));

        ModelHelper.setBlockModel(NFCBlocks.SCORCHED_SANDSTONE, () -> new BlockModelStandard<>(NFCBlocks.SCORCHED_SANDSTONE)
            .setTex(0, "btanfc:block/scorched_sandstone_side", Side.sides)
            .setTex(0, "btanfc:block/scorched_sandstone_top", Side.TOP)
            .setTex(0, "btanfc:block/scorched_sandstone_bottom", Side.BOTTOM)    
        );

        ModelHelper.setBlockModel(NFCBlocks.SLAB_SCORCHED_SANDSTONE, () -> new BlockModelSlab<>(NFCBlocks.SLAB_SCORCHED_SANDSTONE));
        ModelHelper.setBlockModel(NFCBlocks.STAIRS_SCORCHED_SANDSTONE, () -> new BlockModelStairs<>(NFCBlocks.STAIRS_SCORCHED_SANDSTONE));

        ModelHelper.setBlockModel(NFCBlocks.PIZZA_RAW, () -> new BlockModelPizza<>(NFCBlocks.PIZZA_RAW)
            .setTex(0, "btanfc:block/pizza_raw_side", Side.sides)
            .setTex(0, "btanfc:block/pizza_raw_top", Side.TOP)
            .setTex(0, "btanfc:block/pizza_raw_bottom", Side.BOTTOM)
        );

        ModelHelper.setBlockModel(NFCBlocks.PIZZA_COOKED, () -> new BlockModelPizza<>(NFCBlocks.PIZZA_COOKED)
            .setTex(0, "btanfc:block/pizza_cooked_side", Side.sides)
            .setTex(0, "btanfc:block/pizza_cooked_top", Side.TOP)
            .setTex(0, "btanfc:block/pizza_cooked_bottom", Side.BOTTOM)
        );

        ModelHelper.setBlockModel(NFCBlocks.CARPENTRY_WORKSTATION, () -> new BlockModelHorizontalRotation<>(NFCBlocks.CARPENTRY_WORKSTATION)
            .setTex(0, "btanfc:block/carpentry_workstation_side", Side.sides)
            .setTex(0, "btanfc:block/carpentry_workstation_front", Side.NORTH)
            .setTex(0, "btanfc:block/carpentry_workstation_top", Side.TOP)
            .setTex(0, "btanfc:block/carpentry_workstation_bottom", Side.BOTTOM)
        );

        ModelHelper.setBlockModel(NFCBlocks.PEBBLE, () -> new BlockModelVariants<>(NFCBlocks.PEBBLE, "pebble", NFCBlocks.PEBBLE_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.LAMINATED_WOOD, () -> new BlockModelVariants<>(NFCBlocks.LAMINATED_WOOD, "laminated_wood", NFCBlocks.LAMINATED_WOOD_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.LAMINATED_WOOD_AXIS_ALIGNED, () -> new BlockModelAxisAlignedVariants<>(NFCBlocks.LAMINATED_WOOD_AXIS_ALIGNED, "laminated_wood_directional", NFCBlocks.LAMINATED_WOOD_AXIS_ALIGNED_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.LOG_ALTERNATE, () -> new BlockModelAxisAlignedVariants<>(NFCBlocks.LOG_ALTERNATE, "log", NFCBlocks.LOG_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.WINDOW, () -> new BlockModelVariantsPainted<>(NFCBlocks.WINDOW, "window", NFCBlocks.WINDOW_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.BRICK_MUD, () -> new BlockModelVariants<>(NFCBlocks.BRICK_MUD, "mud_bricks", NFCBlocks.MUD_BRICK_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.BRICK_MUD_FIRED, () -> new BlockModelVariants<>(NFCBlocks.BRICK_MUD_FIRED, "mud_fired_bricks", NFCBlocks.MUD_BRICK_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.PLANKS_ALTERNATE, () -> new BlockModelVariants<>(NFCBlocks.PLANKS_ALTERNATE, "planks", NFCBlocks.PLANKS_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.STONE_SMOOTH, () -> new BlockModelVariants<>(NFCBlocks.STONE_SMOOTH, "stone_smooth", NFCBlocks.STONE_SMOOTH_VARIANTS));
        ModelHelper.setBlockModel(NFCBlocks.STONE_SMOOTH_AXIS_ALIGNED, () -> new BlockModelAxisAlignedVariants<>(NFCBlocks.STONE_SMOOTH_AXIS_ALIGNED, "stone_smooth", NFCBlocks.STONE_SMOOTH_AXIS_ALIGNED_VARIANTS));
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        ModelHelper.setItemModel(NFCItems.FOOD_CHEESE, () -> {
            ItemModelStandard model = new ItemModelStandard(NFCItems.FOOD_CHEESE, null);
            model.icon = TextureRegistry.getTexture("btanfc:item/cheese");
            return model;
        });
        ModelHelper.setItemModel(NFCItems.FOOD_PIZZA_RAW, () -> {
            ItemModelStandard model = new ItemModelStandard(NFCItems.FOOD_PIZZA_RAW, null);
            model.icon = TextureRegistry.getTexture("btanfc:item/pizza_raw");
            return model;
        });
        ModelHelper.setItemModel(NFCItems.FOOD_PIZZA_COOKED, () -> {
            ItemModelStandard model = new ItemModelStandard(NFCItems.FOOD_PIZZA_COOKED, BTANFC.MOD_ID);
            model.icon = TextureRegistry.getTexture("btanfc:item/pizza_cooked");
            return model;
        });
        ModelHelper.setItemModel(NFCItems.FOOD_EGG_COOKED, () -> {
            ItemModelStandard model = new ItemModelStandard(NFCItems.FOOD_EGG_COOKED, BTANFC.MOD_ID);
            model.icon = TextureRegistry.getTexture("btanfc:item/egg_cooked");
            return model;
        });
        ModelHelper.setItemModel(NFCItems.DUST_GLOWSTONE_BLUE, () -> {
            ItemModelStandard model = new ItemModelStandard(NFCItems.DUST_GLOWSTONE_BLUE, BTANFC.MOD_ID);
            model.icon = TextureRegistry.getTexture("btanfc:item/dust_glowstone_blue");
            return model;
        });
    }

    @Override
    public void initEntityModels(EntityRenderDispatcher dispatcher) {
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {
    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {
    }

}
