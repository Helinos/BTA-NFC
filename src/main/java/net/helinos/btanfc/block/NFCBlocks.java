package net.helinos.btanfc.block;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.model.BlockModelVariants;
import net.helinos.btanfc.item.ItemBlockVariants;
import net.helinos.btanfc.item.NFCItems;
import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGlass;
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
        "side_cut",
        "upright_cut",
        "bricks_t_cut"
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
    public static Block brickMud;
    public static Block brickMudFired;
    public static Block planksAlternate;
    public static Block stoneSmooth;

    public static void init() {
        BTANFC.LOGGER.info("Initializing blocks.");

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
            .build(new Block("brick.mud", BTANFC.config.getInt("BlockIDs.brickMud"), Material.dirt));

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
            .build(new Block("brick.mud.fired", BTANFC.config.getInt("BlockIDs.brickMudFired"), Material.stone));

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
            .build(new Block("planks.alternate", BTANFC.config.getInt("BlockIDs.planksAlternate"), Material.wood));

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
            .build(new Block("stone.smooth", BTANFC.config.getInt("BlockIDs.stoneSmooth"), Material.stone));

        //CreativeHelper.setParent(new ItemStack(stoneSmooth, 1, 0), new ItemStack(Block.stonePolished.id, 1, 0, null));
        for (int index = 1; index < stoneSmoothVariants.length; index++) {
            CreativeHelper.setParent(new ItemStack(stoneSmooth, 1, index), new ItemStack(stoneSmooth, 1, index - 1));
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
