package typicals.alchemicalexpansion.item.crafting.Machines;


import net.minecraft.item.ItemStack;

public class PillFurnaceRecipe extends MachineRecipe {

    //sets all the inputs for a recipe
    public boolean setInputs(ItemStack[] inputs){
        //TODO turn this into a try catch
        for(int i = 0; i < inputs.length - 1; i++ ){
            if(inputs[i] != null){
                this.inputs.add(inputs[i]);
            } else {
                return true;
            }
        }
        return true;
    }
    //sets all the outputs in a recipe though there technically can only be one.... for now.
    public boolean setOutputs(ItemStack[] outputs){
        //TODO turn this into a try catch
        for(int i = 0; i < outputs.length - 1; i++ ){
            if(outputs[i] != null){
                this.outputs.add(outputs[i]);
            } else {
                return true;
            }
        }
        return true;
    }

}



