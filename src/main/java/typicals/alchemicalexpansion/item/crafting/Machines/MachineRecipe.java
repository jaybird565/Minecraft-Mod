package typicals.alchemicalexpansion.item.crafting.Machines;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineRecipe {

    protected List<ItemStack> inputs;

    protected List<ItemStack> outputs;

    public MachineRecipe() {
        this.inputs = new ArrayList<ItemStack>();
        this.outputs = new ArrayList<ItemStack>();
    }

    public void setInputs(List<ItemStack> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(List<ItemStack> outputs) {
        this.outputs = outputs;
    }

    public void setOutputs(ItemStack[] outputs){
        this.outputs = Arrays.asList(outputs);
    }

    public void setInputs(ItemStack[] inputs) {
        this.inputs = Arrays.asList(inputs);
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

}
