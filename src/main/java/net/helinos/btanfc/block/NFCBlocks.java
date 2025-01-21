package net.helinos.btanfc.block;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.model.BlockModelWindow;
import net.helinos.btanfc.item.ItemBlockWindow;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;

public class NFCBlocks {
    public static final String[] windowTypes = new String[] {
            "window",
            "vertical_double_window",
            "small_window",
            "horizontal_double_window",
            "mini_window",
            "quadruple_window",
            "gothic_window"
    };

    public static BlockGlass window;
    public static BlockScorchedSand scorchedSand;
    public static Block scorchedSandstone;
    public static BlockSlab slabScorchedSandstone;
    public static BlockStairs stairsScorchedSandstone;
    public static Block brickScorchedSandstone;
    public static BlockSlab slabBrickScorchedSandstone;
    public static BlockStairs stairsBrickScorchedSandstone;

    public static void init(int minimumID) {
        BTANFC.LOGGER.info("Initializing Blocks.");

        window = (BlockGlass) new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.GLASS)
            .setHardness(0.3F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
            .setBlockModel(BlockModelWindow::new)
            .setItemBlock(ItemBlockWindow::new)
            .build(new BlockGlass("window", minimumID++, Material.glass));

        // I don't know why but this only works for 2 blocks and then it "[Client-Main/WARN] (halplibe) Could not find parent stack of '1 * tile.btanfc.window:2' in the list! does it exist? adding stack to end of list!"
        CreativeHelper.setParent(new ItemStack(window, 1, 0), new ItemStack(Block.glass.id, 1, 0, null));
        for (int index = 1; index < windowTypes.length; index++) {
           CreativeHelper.setParent(new ItemStack(window, 1, index), new ItemStack(window, 1, index - 1));
        }

        scorchedSand = (BlockScorchedSand) new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.SAND)
            .setHardness(0.5F)
            .setTags(BlockTags.MINEABLE_BY_SHOVEL, BlockTags.GROWS_SUGAR_CANE, BlockTags.GROWS_CACTI, BlockTags.CAVES_CUT_THROUGH, BlockTags.INFINITE_BURN)
            .setTextures("btanfc:block/scorched_sand")
            .build(new BlockScorchedSand("scorched_sand", minimumID++));

        scorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setTags(BlockTags.CAVES_CUT_THROUGH, BlockTags.MINEABLE_BY_PICKAXE)
            .setTopTexture("btanfc:block/scorched_sandstone_top")
            .setSideTextures("btanfc:block/scorched_sandstone_side")
            .setBottomTexture("btanfc:block/scorched_sandstone_bottom")
            .build(new Block("scorched_sandstone", minimumID++, Material.stone));

        slabScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelSlab::new)
            .setItemBlock(ItemBlockSlab::new)
            .build(new BlockSlab(scorchedSandstone, minimumID++));

        stairsScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setUseInternalLight()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(scorchedSandstone, minimumID++));

        brickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setTextures("btanfc:block/scorched_sandstone_bricks")
            .build(new Block("brick.scorched_sandstone", minimumID++, Material.stone));
        
        slabBrickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelSlab::new)
            .setItemBlock(ItemBlockSlab::new)
            .build(new BlockSlab(brickScorchedSandstone, minimumID++));

        stairsBrickScorchedSandstone = new BlockBuilder(BTANFC.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.8F)
            .setResistance(10.0F)
            .setUseInternalLight()
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setBlockModel(BlockModelStairs::new)
            .build(new BlockStairs(brickScorchedSandstone, minimumID++));

        BTANFC.LOGGER.info("Initialized Blocks.");
    }
}
