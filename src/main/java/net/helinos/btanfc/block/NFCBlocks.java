package net.helinos.btanfc.block;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.model.BlockModelAxisAlignedVariants;
import net.helinos.btanfc.block.model.BlockModelVariants;
import net.helinos.btanfc.block.model.BlockModelVariantsPainted;
import net.helinos.btanfc.item.ItemBlockVariantsPainted;
import net.helinos.btanfc.item.ItemBlockVariants;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.block.BlockMushroom;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class NFCBlocks implements RecipeEntrypoint {
    public static final String[] windowVariants = new String[] {
        "",
        "double_vertical",
        "small",
        "double_horizontal",
        "mini",
        "quadruple",
        "gothic"
    };

    public static final String[] mudBrickVariants = new String[] {
        "",
        "large",
        "random",
        "wide",
    };

    public static final String[] plankVariants = new String[] {
        "parquet",
        "smooth",
        "vertical",
        "crossed",
    };

    public static final String[] stoneSmoothVariants = new String[] {
        "",
        "cross_cut",
        "bricks_t_cut"
    };

    public static final String[] stoneSmoothAAVariants = new String[] {
        "side_cut",
        "upright_cut",
    };

    public static final String[] laminatedWoodVariants = new String[] {
        "bordered",
        "smooth",
    };

    public static final String[] laminatedWoodAAVariants = new String[] {
        "horizontal",
        "vertical",
    };

    public static final String[] logVariants = new String[] {
        "rounded",
        "small",
    };

    public static final String[] pebbleVariants = new String[] {
        "",
        "small",
        "medium",
        "large"
    };

    public static BlockVariants window;
    public static BlockScorchedSand scorchedSand;
    public static Block scorchedSandstone;
    public static BlockSlab slabScorchedSandstone;
    public static BlockStairs stairsScorchedSandstone;
    public static Block brickScorchedSandstone;
    public static BlockSlab slabBrickScorchedSandstone;
    public static BlockStairs stairsBrickScorchedSandstone;
    public static BlockPizza pizzaRaw;
    public static BlockPizza pizzaCooked;
    public static BlockGlowStoneBlue glowstoneBlue;
    public static BlockCarpentryWorkstation carpentryWorkstation;
    public static BlockVariants brickMud;
    public static BlockVariants brickMudFired;
    public static BlockVariants planksAlternate;
    public static BlockVariants stoneSmooth;
    public static BlockAxisAlignedVariants stoneSmoothAA;
    public static BlockVariants laminatedWood;
    public static BlockAxisAlignedVariants laminatedWoodAA;
    public static BlockAxisAlignedVariants logAlternate;
    public static BlockPebble pebble;
    public static BlockMushroomFire mushroomFire;
    public static BlockMushroom mushroomGlowing;
    public static BlockMushroomBiolum mushroomPurple;
    public static BlockMushroomBiolum mushroomBlue;

    public static void init() {
        BTANFC.LOGGER.info("Initializing blocks.");

        mushroomPurple = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .setTextures("btanfc:block/mushroom_purple")
            .setBlockModel(BlockModelCrossedSquares::new)
            .build(new BlockMushroomBiolum("mushroom.purple", BTANFC.config.getInt("BlockIDs.mushroomPurple"), "sporePurple"));

        mushroomBlue = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .setTextures("btanfc:block/mushroom_blue")
            .setBlockModel(BlockModelCrossedSquares::new)
            .build(new BlockMushroomBiolum("mushroom.blue", BTANFC.config.getInt("BlockIDs.mushroomBlue"), "sporeBlue"));

        mushroomGlowing = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(13)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .setTextures("btanfc:block/mushroom_glowing")
            .setBlockModel(BlockModelCrossedSquares::new)
            .build(new BlockMushroom("mushroom.glowing", BTANFC.config.getInt("BlockIDs.mushroomGlowing")));

        mushroomFire = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0F)
            .setLuminance(11)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
            .setTextures("btanfc:block/mushroom_fire")
            .setBlockModel(BlockModelCrossedSquares::new)
            .build(new BlockMushroomFire("mushroom.fire", BTANFC.config.getInt("BlockIDs.mushroomFire")));

        pebble = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(3.0F)
            .setResistance(3.0F)
            .setBlockModel(block -> new BlockModelVariants<>(pebble, "pebble", pebbleVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, pebbleVariants))
            .build(new BlockPebble("pebble", BTANFC.config.getInt("BlockIDs.pebble")));

        laminatedWood = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(laminatedWood, "laminated_wood", laminatedWoodVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, laminatedWoodVariants))
            .build(new BlockVariants("laminated_wood", BTANFC.config.getInt("BlockIDs.laminatedWood"), Material.wood));

        laminatedWoodAA = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelAxisAlignedVariants<Block>(laminatedWoodAA, "laminated_wood_directional", laminatedWoodAAVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, laminatedWoodAAVariants))
            .build(new BlockAxisAlignedVariants("laminated_wood.aa", BTANFC.config.getInt("BlockIDs.laminatedWoodAA"), Material.wood));

        logAlternate = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelAxisAlignedVariants<Block>(logAlternate, "log", logVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, logVariants))
            .build(new BlockAxisAlignedVariants("log.alternate", BTANFC.config.getInt("BlockIDs.logAlternate"), Material.wood));
        
        window = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
            .setBlockModel(block -> new BlockModelVariantsPainted<BlockGlass>(window, "window", windowVariants))
            .setItemBlock(block -> new ItemBlockVariantsPainted(block, windowVariants))
            .build(new BlockVariants("window", BTANFC.config.getInt("BlockIDs.window"), Material.glass));

        brickMud = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(0.6F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL)
            .setBlockModel(block -> new BlockModelVariants<Block>(brickMud, "mud_bricks", mudBrickVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, mudBrickVariants))
            .build(new BlockVariants("brick.mud", BTANFC.config.getInt("BlockIDs.brickMud"), Material.dirt));

        brickMudFired = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.5F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(brickMudFired, "mud_fired_bricks", mudBrickVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, mudBrickVariants))
            .build(new BlockVariants("brick.mud.fired", BTANFC.config.getInt("BlockIDs.brickMudFired"), Material.stone));

        planksAlternate = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(planksAlternate, "planks", plankVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, plankVariants))
            .build(new BlockVariants("planks.alternate", BTANFC.config.getInt("BlockIDs.planksAlternate"), Material.wood));

        stoneSmooth = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(stoneSmooth, "stone_smooth", stoneSmoothVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, stoneSmoothVariants))
            .build(new BlockVariants("stone.smooth", BTANFC.config.getInt("BlockIDs.stoneSmooth"), Material.stone));

        stoneSmoothAA = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelAxisAlignedVariants<Block>(stoneSmoothAA, "stone_smooth", stoneSmoothAAVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, stoneSmoothAAVariants))
            .build(new BlockAxisAlignedVariants("stone.smooth.aa", BTANFC.config.getInt("BlockIDs.stoneSmoothAA"), Material.stone));

        scorchedSand = (BlockScorchedSand) new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.SAND)
            .setHardness(0.5F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL, BlockTags.GROWS_SUGAR_CANE, BlockTags.GROWS_CACTI, BlockTags.CAVES_CUT_THROUGH, BlockTags.INFINITE_BURN)
            .setTextures("btanfc:block/scorched_sand")
            .build(new BlockScorchedSand("scorched_sand", BTANFC.config.getInt("BlockIDs.scorchedSand")));

        scorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setTags(BlockTags.CAVES_CUT_THROUGH, BlockTags.MINEABLE_BY_PICKAXE)
            .setTopTexture("btanfc:block/scorched_sandstone_top")
            .setSideTextures("btanfc:block/scorched_sandstone_side")
            .setBottomTexture("btanfc:block/scorched_sandstone_bottom")
            .build(new Block("scorched_sandstone", BTANFC.config.getInt("BlockIDs.scorchedSandstone"), Material.stone));

        slabScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelSlab::new)
            .setItemBlock(ItemBlockSlab::new)
            .build(new BlockSlab(scorchedSandstone, BTANFC.config.getInt("BlockIDs.slabScorchedSandstone")));

        stairsScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(scorchedSandstone, BTANFC.config.getInt("BlockIDs.stairsScorchedSandstone")));

        brickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setTextures("btanfc:block/scorched_sandstone_bricks")
            .build(new Block("brick.scorched_sandstone", BTANFC.config.getInt("BlockIDs.brickScorchedSandstone"), Material.stone));
        
        slabBrickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelSlab::new)
            .setItemBlock(ItemBlockSlab::new)
            .build(new BlockSlab(brickScorchedSandstone, BTANFC.config.getInt("BlockIDs.slabBrickScorchedSandstone")));

        stairsBrickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(brickScorchedSandstone, BTANFC.config.getInt("BlockIDs.stairsBrickScorchedSandstone")));

        pizzaRaw = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .setTopTexture("btanfc:block/pizza_raw_top")
            .setSideTextures("btanfc:block/pizza_raw_side")
            .setBottomTexture("btanfc:block/pizza_raw_bottom")
            .build(new BlockPizza("pizza.raw", BTANFC.config.getInt("BlockIDs.pizzaRaw"), 3, () -> (Item) NFCItems.foodPizzaRaw));

        pizzaCooked = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .setTopTexture("btanfc:block/pizza_cooked_top")
            .setSideTextures("btanfc:block/pizza_cooked_side")
            .setBottomTexture("btanfc:block/pizza_cooked_bottom")
            .build(new BlockPizza("pizza.cooked", BTANFC.config.getInt("BlockIDs.pizzaCooked"), 5, () -> (Item) NFCItems.foodPizzaCooked));

        glowstoneBlue = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setLuminance(15)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setTextures("btanfc:block/glowstone_blue")
            .build(new BlockGlowStoneBlue("glowstone.blue", BTANFC.config.getInt("BlockIDs.glowstoneBlue")));

        carpentryWorkstation = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.5F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .setSideTextures("btanfc:block/carpentry_workstation_side")
            .setNorthTexture("btanfc:block/carpentry_workstation_front")
            .setTopTexture("btanfc:block/carpentry_workstation_top")
            .setBottomTexture("btanfc:block/carpentry_workstation_bottom")
            .setBlockModel(BlockModelHorizontalRotation::new)
            .build(new BlockCarpentryWorkstation("carpentry_workstation", BTANFC.config.getInt("BlockIDs.carpentryWorkstation")));

        BTANFC.LOGGER.info("Initialized " + BTANFC.BLOCK_FIELDS.size() + " blocks.");
    }

    @Override
    public void onRecipesReady() {
        // If you do this too early, halplib will try to get data from items before they're initalized.
        // That's why this is in the recipe entrypoint.

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
                    CreativeHelper.setParent(window, index, window, 6);
                    break;
                } else {
                    CreativeHelper.setParent(window, index, window, index - 3);
                }  
            } else {
                CreativeHelper.setParent(window, index, window, index - 1);
            }
        }
        setParents(windowVariants, Block.glassTinted, window);
        
        setParents(laminatedWoodAAVariants, laminatedWood, laminatedWoodAA, laminatedWoodVariants.length - 1);
        setParents(laminatedWoodVariants, logAlternate, laminatedWood, logVariants.length - 1);
        setParents(logVariants, Block.logPalm, logAlternate);

        setParents(stoneSmoothAAVariants, stoneSmooth, stoneSmoothAA, NFCBlocks.stoneSmoothVariants.length - 1);
        setParents(stoneSmoothVariants, Block.stonePolished, stoneSmooth);

        setParents(pebbleVariants, Block.gravel, pebble);
        setParents(mudBrickVariants, Block.mud, brickMud);
        setParents(mudBrickVariants, Block.mudBaked, brickMudFired);
        setParents(plankVariants, Block.planksOak, planksAlternate);
        
        CreativeHelper.setParent(mushroomFire, mushroomGlowing);
        CreativeHelper.setParent(mushroomGlowing, mushroomPurple);
        CreativeHelper.setParent(mushroomPurple, mushroomBlue);
        CreativeHelper.setParent(mushroomBlue, Block.mushroomRed);

        CreativeHelper.setParent(scorchedSand, Block.sand);
        CreativeHelper.setParent(scorchedSandstone, Block.sandstone);
        CreativeHelper.setParent(slabScorchedSandstone, Block.slabSandstone);
        CreativeHelper.setParent(stairsScorchedSandstone,Block.stairsSandstone);
        CreativeHelper.setParent(brickScorchedSandstone, Block.brickSandstone);
        CreativeHelper.setParent(slabBrickScorchedSandstone, Block.slabBrickSandstone);
        CreativeHelper.setParent(stairsBrickScorchedSandstone, Block.stairsBrickSandstone);
        CreativeHelper.setParent(glowstoneBlue, Block.glowstone);
        CreativeHelper.setParent(carpentryWorkstation, Block.workbench);
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

    @Override
    public void initNamespaces() {
    }
}
