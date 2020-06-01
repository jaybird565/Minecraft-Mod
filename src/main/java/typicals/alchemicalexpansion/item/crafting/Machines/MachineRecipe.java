package typicals.alchemicalexpansion.item.crafting.Machines;

import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.util.ItemUtil;
import typicals.alchemicalexpansion.util.LoggerUtil;

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

    public boolean canCraft(List<ItemStack> inputsIn) {
        LoggerUtil.dev("Validating that recipe:\n" + this.toString() + " works with inputs:\n" + ItemUtil.stackListToString(inputsIn) );
        if(this.inputs.isEmpty() || this.outputs.isEmpty()) {
            LoggerUtil.dev("Recipe is invalid since it's inputs or outputs are empty...");
            return false;
        }

        boolean valid = true;
        //invalidate if inputsIn doesn't contain a input in this recipe
        for(ItemStack input : this.inputs) {
            if(!ItemUtil.containsItemStack(inputsIn, input)) {
                valid = false;
                break;
            }
        }

        if(!valid) {
            return false;
        }

        //invalidate if inputsIn contains an item not in this recipe
        for(ItemStack inputIn : inputsIn) {
            if(!ItemUtil.containsItem(this.inputs, inputIn)) {
                valid = false;
                break;
            }
        }

        return valid;
    }

    public boolean isValid(){
        return !this.inputs.isEmpty() && !this.outputs.isEmpty();
    }

    public boolean containsInput(ItemStack inputIn) {
        return ItemUtil.containsItemStack(this.inputs, inputIn);
    }

    public boolean containsOutput(ItemStack outputIn) {
        return ItemUtil.containsItemStack(this.outputs, outputIn);
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

    @Override
    public String toString() {
        String rv = "MachineRecipe with inputs:\n";
        for(ItemStack input : this.inputs) {
            rv += input.toString() + "\n";
        }
        rv += "and outputs:\n";
        for(ItemStack output : this.outputs) {
            rv += output.toString() + "\n";
        }

        return rv;

    }

}
