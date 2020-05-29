package typicals.alchemicalexpansion.recipes;

import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.ModItems;
import typicals.alchemicalexpansion.item.Pill;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;

import java.util.ArrayList;

public class Recipes {

    public static ArrayList<PillFurnaceRecipe> pillFurnaceRecipes;


    public static ItemStack pillFurnaceResult(ItemStack[] inputs) {
        //TODO return empty ItemStack if invalid recipe else return output
        return new ItemStack(ModItems.PILL);
    }
}
