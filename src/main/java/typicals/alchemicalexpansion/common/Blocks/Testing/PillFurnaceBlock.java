package typicals.alchemicalexpansion.common.Blocks.Testing;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.common.ModBlock;
import typicals.alchemicalexpansion.common.ModBlocks;

import javax.annotation.Nullable;

public class PillFurnaceBlock extends ModBlock implements ITileEntityProvider {
    public static final String name = "pill_furnace_block";

    public static final Material material = Material.ROCK;

    public PillFurnaceBlock() {
        super(material);
        this.init();
    }

    private void init() {
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public Item itemBlock() {
        return new ItemBlock(ModBlocks.PILL_FURNACE_BLOCK).setRegistryName(ModBlocks.PILL_FURNACE_BLOCK.getRegistryName());
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
        playerIn.openGui(AlchemicalExpansion.alchemicalExpansion, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
