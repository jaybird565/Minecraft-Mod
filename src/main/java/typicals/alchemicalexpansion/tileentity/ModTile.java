package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import typicals.alchemicalexpansion.handler.PacketHandler;
import typicals.alchemicalexpansion.util.BlockUtil;


public abstract class ModTile extends TileEntity {

    public abstract Block[] validBlocks();

    ModTile() {
        super();
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && BlockUtil.canInteractWith(playerIn, this.getPos());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }



    public void updateContainingBlockState() {

    }


    public IBlockState getContainingBlockState() {
        return this.world.getBlockState(this.getPos());
    }

    public Block getContainingBlock() {
        return this.getContainingBlockState().getBlock();
    }

    public boolean validBlock(Block block) {
        for(Block validBlock : this.validBlocks()) {
            if(validBlock == block) {
                return true;
            }
        }
        return false;
    }

}
