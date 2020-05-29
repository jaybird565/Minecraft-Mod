package typicals.alchemicalexpansion.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.Properties;
import typicals.alchemicalexpansion.item.Pill;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;

import java.util.Random;

public class PillFurnaceBlock extends ModBlockTile {

    public static final int GUI_ID = 1;

    public static final String pathOff = "pill_furnace";
    public static final String pathOn = "pill_furnace_lit";

    private boolean updatingBlock;

    private boolean lit;

    public PillFurnaceBlock(boolean lit) {
        super(pathOff);
        if (lit) {
            this.path = pathOn;
            this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
            this.setLightLevel(0.900F);
        }
        this.lit = lit;
        this.init();
    }

    private void init() {
        if (!this.lit) {
            this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(Properties.FACING, EnumFacing.NORTH));
        this.setHardness(1.5f);
        this.setResistance(10f);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PillFurnaceTile();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (!(tileEntity instanceof PillFurnaceTile)) {
            return false;
        }
        playerIn.openGui(AlchemicalExpansion.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(Properties.FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, Properties.FACING);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(Properties.FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(Properties.FACING)).getIndex();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.PILL_FURNACE);
    }

    @Override
    public void breakBlock(World world,  BlockPos pos, IBlockState state) {
        if (updatingBlock) {
            return;
        }

        TileEntity tileEntity = world.getTileEntity(pos);

        if(tileEntity instanceof PillFurnaceTile) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tileEntity);
        }

        super.breakBlock(world, pos, state);
    }

    public void updatePillFurnaceState(boolean lit, World worldin, BlockPos pos) {
        IBlockState state = worldin.getBlockState(pos);
        TileEntity tile = worldin.getTileEntity(pos);
        this.lit = lit;

        this.updatingBlock = true;
        if (lit) {
            worldin.setBlockState(pos, ModBlocks.PILL_FURNACE_LIT.getDefaultState().withProperty(Properties.FACING, state.getValue(Properties.FACING)));
            //AlchemicalExpansion.logger.debug("PillFurnaceBlock changed blockstate at: " + pos.toString() + "to lit");
        } else {
            worldin.setBlockState(pos, ModBlocks.PILL_FURNACE.getDefaultState().withProperty(Properties.FACING, state.getValue(Properties.FACING)));
            //AlchemicalExpansion.logger.debug("PillFurnaceBlock changed blockstate at: " + pos.toString() + " to default");
        }
        this.updatingBlock = false;

        if (tile != null) {
            tile.validate();
            worldin.setTileEntity(pos, tile);
        }
    }

}
