package typicals.alchemicalexpansion.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.handler.BlockRegistrationHandler;
import typicals.alchemicalexpansion.handler.ModelRegistrationHandler;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTileEntity;

import javax.annotation.Nonnull;
import java.util.Random;

public class PillFurnaceBlock extends ModBlockTileEntity {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public static final int GUI_ID = 1;


    public static final String pathOff = "pill_furnace_block_off";
    public static final String pathOn = "pill_furnace_block_on";

    private static boolean updatingBlock;

    private static boolean active;

    public PillFurnaceBlock(boolean active) {
        super(pathOff);
        if (active) {
            this.path = pathOn;
            this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
            this.setLightLevel(0.900F);
        }
        PillFurnaceBlock.active = active;
        this.init();
    }

    private void init() {
        if (!PillFurnaceBlock.active) {
            this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(1.5f);
        this.setResistance(10f);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PillFurnaceTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (!(tileEntity instanceof PillFurnaceTileEntity)) {
            return false;
        }
        playerIn.openGui(AlchemicalExpansion.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.PILL_FURNACE_BLOCK_OFF);
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (!updatingBlock) {
            super.breakBlock(world, pos, state);
        }
    }

    public void updatePillFurnaceState(boolean active, World worldin, BlockPos pos) {
        IBlockState state = worldin.getBlockState(pos);
        TileEntity tile = worldin.getTileEntity(pos);
        active = PillFurnaceBlock.active;

        this.updatingBlock = true;
        if (active = true) {
            worldin.setBlockState(pos, ModBlocks.PILL_FURNACE_BLOCK_ON.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)));
        } else {
            worldin.setBlockState(pos, ModBlocks.PILL_FURNACE_BLOCK_OFF.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)));
        }
        updatingBlock = false;

        if (tile != null) {
            tile.validate();
            worldin.setTileEntity(pos, tile);
        }
    }


}
