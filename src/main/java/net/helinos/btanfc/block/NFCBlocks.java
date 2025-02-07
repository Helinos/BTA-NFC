package net.helinos.btanfc.block;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.BlockInitEntrypoint;
import net.helinos.btanfc.ItemInitEntrypoint;
import net.helinos.btanfc.item.ItemBlockVariantsPainted;
import net.helinos.btanfc.item.ItemBlockVariants;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.block.BlockLogicSlab;
import net.minecraft.core.block.BlockLogicStairs;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.util.toml.Toml;

public class NFCBlocks implements BlockInitEntrypoint, ItemInitEntrypoint {
    private static Toml rawConfig;
	private static boolean configChanged = false;

    public static final String[] WINDOW_VARIANTS = new String[] {
        "",
        "double_vertical",
        "small",
        "double_horizontal",
        "mini",
        "quadruple",
        "gothic"
    };

    public static final String[] MUD_BRICK_VARIANTS = new String[] {
        "",
        "large",
        "random",
        "wide",
    };

    public static final String[] PLANKS_VARIANTS = new String[] {
        "parquet",
        "smooth",
        "vertical",
        "crossed",
    };

    public static final String[] STONE_SMOOTH_VARIANTS = new String[] {
        "",
        "cross_cut",
        "bricks_t_cut"
    };

    public static final String[] STONE_SMOOTH_AXIS_ALIGNED_VARIANTS = new String[] {
        "side_cut",
        "upright_cut",
    };

    public static final String[] LAMINATED_WOOD_VARIANTS = new String[] {
        "bordered",
        "smooth",
    };

    public static final String[] LAMINATED_WOOD_AXIS_ALIGNED_VARIANTS = new String[] {
        "horizontal",
        "vertical",
    };

    public static final String[] LOG_VARIANTS = new String[] {
        "rounded",
        "small",
    };

    public static final String[] PEBBLE_VARIANTS = new String[] {
        "",
        "small",
        "medium",
        "large"
    };

    public static Block<?> WINDOW;
    public static Block<?> SCORCHED_SAND;
    public static Block<?> SCORCHED_SANDSTONE;
    public static Block<BlockLogicSlab> SLAB_SCORCHED_SANDSTONE;
    public static Block<BlockLogicStairs> STAIRS_SCORCHED_SANDSTONE;
    public static Block<?> BRICK_SCORCHED_SANDSTONE;
    public static Block<BlockLogicSlab> SLAB_BRICK_SCORCHED_SANDSTONE;
    public static Block<BlockLogicStairs> STAIRS_BRICK_SCORCHED_SANDSTONE;
    public static Block<?> PIZZA_RAW;
    public static Block<?> PIZZA_COOKED;
    public static Block<?> GLOWSTONE_BLUE;
    public static Block<?> CARPENTRY_WORKSTATION;
    public static Block<?> BRICK_MUD;
    public static Block<?> BRICK_MUD_FIRED;
    public static Block<?> PLANKS_ALTERNATE;
    public static Block<?> STONE_SMOOTH;
    public static Block<?> STONE_SMOOTH_AXIS_ALIGNED;
    public static Block<?> LAMINATED_WOOD;
    public static Block<?> LAMINATED_WOOD_AXIS_ALIGNED;
    public static Block<?> LOG_ALTERNATE;
    public static Block<?> PEBBLE;
    public static Block<?> MUSHROOM_FIRE;
    public static Block<?> MUSHROOM_GLOWING;
    public static Block<?> MUSHROOM_PURPLE;
    public static Block<?> MUSHROOM_BLUE;

    @Override
    public void afterBlockInit() {
        BTANFC.LOGGER.info("Initializing blocks.");

        rawConfig = BTANFC.CONFIG.getRawParsed();

        String key = "mushroom_purple";
        MUSHROOM_PURPLE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .build(key, getID(key), block -> new BlockLogicMushroomBiolum(block, "sporePurple"));

        key = "mushroom_blue";
        MUSHROOM_BLUE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .build(key, getID(key), block -> new BlockLogicMushroomBiolum(block, "sporeBlue"));

        key = "mushroom_glowing";
        MUSHROOM_GLOWING = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(13)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .build(key, getID(key), BlockLogicMushroom::new);

        key = "mushroom_fire";
        MUSHROOM_FIRE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .build(key, getID(key), BlockLogicMushroomFire::new);

        key = "pebble";
        PEBBLE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(3.0F)
            .setResistance(3.0F)
            //.setBlockModel(block -> new BlockModelVariants<>(pebble, "pebble", pebbleVariants))
            .setBlockItem(block -> new ItemBlockVariants<>(block, PEBBLE_VARIANTS))
            .build(key, getID(key), BlockLogicPebble::new);

        key = "laminated_wood";
        LAMINATED_WOOD = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, LAMINATED_WOOD_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.wood));

        key = "laminated_wood_axis_aligned";
        LAMINATED_WOOD_AXIS_ALIGNED = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, LAMINATED_WOOD_AXIS_ALIGNED_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicAxisAlignedVariants(block, Material.wood));

        key = "log_alternate";
        LOG_ALTERNATE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, LOG_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicAxisAlignedVariants(block, Material.wood));
        
        key = "window";
        WINDOW = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
            .setBlockItem(block -> new ItemBlockVariantsPainted<>(block, WINDOW_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.glass));

        key = "brick_mud";
        BRICK_MUD = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(0.6F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL)
            .setBlockItem(block -> new ItemBlockVariants<>(block, MUD_BRICK_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.dirt));

        key = "brick_mud_fired";
        BRICK_MUD_FIRED = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.5F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, MUD_BRICK_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.stone));

        key = "planks_alternate";
        PLANKS_ALTERNATE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, PLANKS_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.wood));

        key = "stone_smooth";
        STONE_SMOOTH = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, STONE_SMOOTH_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicVariants(block, Material.stone));

        key = "stone_smooth_axis_aligned";
        STONE_SMOOTH_AXIS_ALIGNED = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockVariants<>(block, STONE_SMOOTH_AXIS_ALIGNED_VARIANTS))
            .build(key, getID(key), block -> new BlockLogicAxisAlignedVariants(block, Material.stone));

        key = "scorched_sand";
        SCORCHED_SAND = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.SAND)
            .setHardness(0.5F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL, BlockTags.GROWS_SUGAR_CANE, BlockTags.GROWS_CACTI, BlockTags.CAVES_CUT_THROUGH, BlockTags.INFINITE_BURN)
            .build(key, getID(key), BlockLogicScorchedSand::new);

        key = "scorched_sandstone";
        SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setTags(BlockTags.CAVES_CUT_THROUGH, BlockTags.MINEABLE_BY_PICKAXE)
            .build(key, getID(key), block -> new BlockLogic(block, Material.stone));

        key = "slab_scorched_sandstone";
        SLAB_SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockSlab<>(block))
            .build(key, getID(key), block -> new BlockLogicSlab(block, SCORCHED_SANDSTONE));

        key = "stairs_scorched_sandstone";
        STAIRS_SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(key, getID(key), block -> new BlockLogicStairs(block, SCORCHED_SANDSTONE));

        key = "brick_scorched_sandstone";
        BRICK_SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(key, getID(key), block -> new BlockLogic(block, Material.stone));
        
        key = "slab_brick_scorched_sandstone";
        SLAB_BRICK_SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockItem(block -> new ItemBlockSlab<>(block))
            .build(key, getID(key), block -> new BlockLogicSlab(block, BRICK_SCORCHED_SANDSTONE));

        key = "stairs_brick_scorched_sandstone";
        STAIRS_BRICK_SCORCHED_SANDSTONE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(key, getID(key), block -> new BlockLogicStairs(block, BRICK_SCORCHED_SANDSTONE));

        key = "pizza_raw";
        PIZZA_RAW = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .build(key, getID(key), block -> new BlockLogicPizza(block, 3, () -> (Item) NFCItems.FOOD_PIZZA_RAW));

        key = "pizza_cooked";
        PIZZA_COOKED = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .build(key, getID(key), block -> new BlockLogicPizza(block, 5, () -> (Item) NFCItems.FOOD_PIZZA_COOKED));

        key = "glowstone_blue";
        GLOWSTONE_BLUE = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setLuminance(15)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(key, getID(key), block -> new BlockLogicGlowStoneBlue(block));

        key = "carpentry_workstation";
        CARPENTRY_WORKSTATION = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .build(key, getID(key), BlockLogicCarpentryWorkstation::new);

		if (configChanged) {
			BTANFC.CONFIG.setDefaults(rawConfig);
			BTANFC.CONFIG.writeConfig();
			BTANFC.CONFIG.loadConfig();
		}

        BTANFC.LOGGER.info("Initialized blocks.");
    }

    @Override
    public void afterItemInit() {
        // If you do this too early, halplib will try to get data from items before they're initalized.
        for (int index = 255; index >= 1; index--) {
            if (index >> 7 != 1) {
                break;
            }

            int variantIndex = index & 0b111;
            if (variantIndex == 7 || variantIndex == 6 ) {
                continue;
            }

            int nextVariantIndex = (index - 1) & 0b111;
            if (nextVariantIndex == 7) {
                int colorIndex = (index >> 3) & 0b1111;
                if (colorIndex == 0) {
                    CreativeHelper.setParent(WINDOW, index, WINDOW, 6);
                    break;
                } else {
                    CreativeHelper.setParent(WINDOW, index, WINDOW, index - 3);
                }  
            } else {
                CreativeHelper.setParent(WINDOW, index, WINDOW, index - 1);
            }
        }
        setParents(WINDOW_VARIANTS, Blocks.GLASS_TINTED, WINDOW);
        
        setParents(LAMINATED_WOOD_AXIS_ALIGNED_VARIANTS, LAMINATED_WOOD, LAMINATED_WOOD_AXIS_ALIGNED, LAMINATED_WOOD_VARIANTS.length - 1);
        setParents(LAMINATED_WOOD_VARIANTS, LOG_ALTERNATE, LAMINATED_WOOD, LOG_VARIANTS.length - 1);
        setParents(LOG_VARIANTS, Blocks.LOG_PALM, LOG_ALTERNATE);

        setParents(STONE_SMOOTH_AXIS_ALIGNED_VARIANTS, STONE_SMOOTH, STONE_SMOOTH_AXIS_ALIGNED, NFCBlocks.STONE_SMOOTH_VARIANTS.length - 1);
        setParents(STONE_SMOOTH_VARIANTS, Blocks.STONE_POLISHED, STONE_SMOOTH);

        setParents(PEBBLE_VARIANTS, Blocks.GRAVEL, PEBBLE);
        setParents(MUD_BRICK_VARIANTS, Blocks.MUD, BRICK_MUD);
        setParents(MUD_BRICK_VARIANTS, Blocks.MUD_BAKED, BRICK_MUD_FIRED);
        setParents(PLANKS_VARIANTS, Blocks.PLANKS_OAK, PLANKS_ALTERNATE);
        
        CreativeHelper.setParent(MUSHROOM_FIRE, MUSHROOM_GLOWING);
        CreativeHelper.setParent(MUSHROOM_GLOWING, MUSHROOM_PURPLE);
        CreativeHelper.setParent(MUSHROOM_PURPLE, MUSHROOM_BLUE);
        CreativeHelper.setParent(MUSHROOM_BLUE, Blocks.MUSHROOM_RED);

        CreativeHelper.setParent(SCORCHED_SAND, Blocks.SAND);
        CreativeHelper.setParent(SCORCHED_SANDSTONE, Blocks.SANDSTONE);
        CreativeHelper.setParent(SLAB_SCORCHED_SANDSTONE, Blocks.SLAB_SANDSTONE);
        CreativeHelper.setParent(STAIRS_SCORCHED_SANDSTONE,Blocks.STAIRS_SANDSTONE);
        CreativeHelper.setParent(BRICK_SCORCHED_SANDSTONE, Blocks.BRICK_SANDSTONE);
        CreativeHelper.setParent(SLAB_BRICK_SCORCHED_SANDSTONE, Blocks.SLAB_BRICK_SANDSTONE);
        CreativeHelper.setParent(STAIRS_BRICK_SCORCHED_SANDSTONE, Blocks.STAIRS_BRICK_SANDSTONE);
        CreativeHelper.setParent(GLOWSTONE_BLUE, Blocks.GLOWSTONE);
        CreativeHelper.setParent(CARPENTRY_WORKSTATION, Blocks.WORKBENCH);
    }

    private void setParents(String[] variants, IItemConvertible parent, IItemConvertible children) {
        setParents(variants, parent, children, 0);
    }

    private void setParents(String[] variants, IItemConvertible parent, IItemConvertible children, int parentMetadata) {
        for (int index = variants.length - 1; index >= 1; index--) {
            CreativeHelper.setParent(children, index, children, index - 1);
        }
        CreativeHelper.setParent(children, 0, parent, parentMetadata);
    }

    private static int nextBlockID = 0;

	private static int getID(String key) {
		boolean containsBlock;
		String category = "BlockIDs.";
		try {
			containsBlock = rawConfig.contains(category + key);
		} catch (NullPointerException e) {
			containsBlock = false;
		}
		
		if (containsBlock) {
			return BTANFC.CONFIG.getInt(category + key);
		}

		while (Blocks.blocksList[++nextBlockID] != null) {}
		
		rawConfig.addEntry(category + key, nextBlockID);
		configChanged = true;

		return nextBlockID;
	}
}
