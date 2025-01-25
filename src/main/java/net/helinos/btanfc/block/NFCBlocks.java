package net.helinos.btanfc.block;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.model.BlockModelAxisAlignedVariants;
import net.helinos.btanfc.block.model.BlockModelVariants;
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
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;

public class NFCBlocks {
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

    public static BlockGlass window;
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

        CreativeHelper.setParent(new ItemStack(pebble, 1, 0), new ItemStack(Block.gravel.id, 1, 0, null));
        for (int index = 1; index < pebbleVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(pebble, 1, index), new ItemStack(pebble, 1, index - 1));
        }

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
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0F)
            .setResistance(10.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelAxisAlignedVariants<Block>(logAlternate, "log", logVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, logVariants))
            .build(new BlockAxisAlignedVariants("log.alternate", BTANFC.config.getInt("BlockIDs.logAlternate"), Material.wood));

        CreativeHelper.setParent(new ItemStack(logAlternate, 1, 0), new ItemStack(Block.logOak.id, 1, 0, null));
        for (int index = 1; index < logVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(logAlternate, 1, index), new ItemStack(logAlternate, 1, index - 1));
        }
        CreativeHelper.setParent(new ItemStack(laminatedWood, 1, 0), new ItemStack(logAlternate, 1, logVariants.length - 1, null));
        for (int index = 1; index < laminatedWoodVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(laminatedWood, 1, index), new ItemStack(laminatedWood, 1, index - 1));
        }
        CreativeHelper.setParent(new ItemStack(laminatedWoodAA, 1, 0), new ItemStack(laminatedWood, 1, laminatedWoodVariants.length - 1, null));
        for (int index = 1; index < laminatedWoodAAVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(laminatedWoodAA, 1, index), new ItemStack(laminatedWoodAA, 1, index - 1));
        }
        
        window = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
            .setBlockModel(block -> new BlockModelVariants<BlockGlass>(window, "window", windowVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, windowVariants))
            .build(new BlockGlass("window", BTANFC.config.getInt("BlockIDs.window"), Material.glass));

        // I don't know why but this only works for 2 blocks and then it "[Client-Main/WARN] (halplibe) Could not find parent stack of '1 * tile.btanfc.window:2' in the list! does it exist? adding stack to end of list!"
        CreativeHelper.setParent(new ItemStack(window, 1, 0), new ItemStack(Block.glass.id, 1, 0, null));
        for (int index = 1; index < windowVariants.length; index++) {
           CreativeHelper.setParent(new ItemStack(window, 1, index), new ItemStack(window, 1, index - 1));
        }

        brickMud = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(0.6F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL)
            .setBlockModel(block -> new BlockModelVariants<Block>(brickMud, "mud_bricks", mudBrickVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, mudBrickVariants))
            .build(new BlockVariants("brick.mud", BTANFC.config.getInt("BlockIDs.brickMud"), Material.dirt));

        CreativeHelper.setParent(new ItemStack(brickMud, 1, 0), new ItemStack(Block.mud.id, 1, 0, null));
        for (int index = 1; index < mudBrickVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(brickMud, 1, index), new ItemStack(brickMud, 1, index - 1));
        }

        brickMudFired = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.5F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(brickMudFired, "mud_fired_bricks", mudBrickVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, mudBrickVariants))
            .build(new BlockVariants("brick.mud.fired", BTANFC.config.getInt("BlockIDs.brickMudFired"), Material.stone));

        CreativeHelper.setParent(new ItemStack(brickMudFired, 1, 0), new ItemStack(Block.mudBaked.id, 1, 0, null));
        for (int index = 1; index < mudBrickVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(brickMudFired, 1, index), new ItemStack(brickMudFired, 1, index - 1));
        }

        planksAlternate = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(5.0F)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .setBlockModel(block -> new BlockModelVariants<Block>(planksAlternate, "planks", plankVariants))
            .setItemBlock(block -> new ItemBlockVariants(block, plankVariants))
            .build(new BlockVariants("planks.alternate", BTANFC.config.getInt("BlockIDs.planksAlternate"), Material.wood));

        CreativeHelper.setParent(new ItemStack(planksAlternate, 1, 0), new ItemStack(Block.planksOak.id, 1, 0, null));
        for (int index = 1; index < plankVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(planksAlternate, 1, index), new ItemStack(planksAlternate, 1, index - 1));
        }

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

        //CreativeHelper.setParent(new ItemStack(stoneSmooth, 1, 0), new ItemStack(Block.stonePolished.id, 1, 0, null));
        for (int index = 1; index < stoneSmoothVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(stoneSmooth, 1, index), new ItemStack(stoneSmooth, 1, index - 1));
        }
        CreativeHelper.setParent(new ItemStack(stoneSmoothAA, 1, 0), new ItemStack(stoneSmooth, 1, stoneSmoothVariants.length - 1, null));
        for (int index = 1; index < stoneSmoothAAVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(stoneSmoothAA, 1, index), new ItemStack(stoneSmoothAA, 1, index - 1));
        }

        scorchedSand = (BlockScorchedSand) new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.SAND)
            .setHardness(0.5F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL, BlockTags.GROWS_SUGAR_CANE, BlockTags.GROWS_CACTI, BlockTags.CAVES_CUT_THROUGH, BlockTags.INFINITE_BURN)
            .setTextures("btanfc:block/scorched_sand")
            .build(new BlockScorchedSand("scorched_sand", BTANFC.config.getInt("BlockIDs.scorchedSand")));
        
        CreativeHelper.setParent(new ItemStack(scorchedSand, 1, 0), new ItemStack(Block.sand.id, 1, 0, null));

        scorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setTags(BlockTags.CAVES_CUT_THROUGH, BlockTags.MINEABLE_BY_PICKAXE)
            .setTopTexture("btanfc:block/scorched_sandstone_top")
            .setSideTextures("btanfc:block/scorched_sandstone_side")
            .setBottomTexture("btanfc:block/scorched_sandstone_bottom")
            .build(new Block("scorched_sandstone", BTANFC.config.getInt("BlockIDs.scorchedSandstone"), Material.stone));

        CreativeHelper.setParent(new ItemStack(scorchedSandstone, 1, 0), new ItemStack(Block.sandstone.id, 1, 0, null));

        slabScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelSlab::new)
            .setItemBlock(ItemBlockSlab::new)
            .build(new BlockSlab(scorchedSandstone, BTANFC.config.getInt("BlockIDs.slabScorchedSandstone")));
        
        CreativeHelper.setParent(new ItemStack(slabScorchedSandstone, 1, 0), new ItemStack(Block.slabSandstone.id, 1, 0, null));

        stairsScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(scorchedSandstone, BTANFC.config.getInt("BlockIDs.stairsScorchedSandstone")));

        CreativeHelper.setParent(new ItemStack(stairsScorchedSandstone, 1, 0), new ItemStack(Block.stairsSandstone.id, 1, 0, null));

        brickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setTextures("btanfc:block/scorched_sandstone_bricks")
            .build(new Block("brick.scorched_sandstone", BTANFC.config.getInt("BlockIDs.brickScorchedSandstone"), Material.stone));

        CreativeHelper.setParent(new ItemStack(brickScorchedSandstone, 1, 0), new ItemStack(Block.brickSandstone.id, 1, 0, null));
        
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

        CreativeHelper.setParent(new ItemStack(slabBrickScorchedSandstone, 1, 0), new ItemStack(Block.slabBrickSandstone.id, 1, 0, null));

        stairsBrickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(brickScorchedSandstone, BTANFC.config.getInt("BlockIDs.stairsBrickScorchedSandstone")));

        CreativeHelper.setParent(new ItemStack(stairsBrickScorchedSandstone, 1, 0), new ItemStack(Block.stairsBrickSandstone.id, 1, 0, null));

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

        CreativeHelper.setParent(new ItemStack(glowstoneBlue, 1, 0), new ItemStack(Block.glowstone.id, 1, 0, null));

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

        CreativeHelper.setParent(new ItemStack(carpentryWorkstation, 1, 0), new ItemStack(Block.workbench.id, 1, 0, null));

        BTANFC.LOGGER.info("Initialized " + BTANFC.BLOCK_FIELDS.size() + " blocks.");
    }
}
