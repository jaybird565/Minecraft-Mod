package typicals.alchemicalexpansion.util;


import net.minecraft.item.ItemStack;

public class ItemUtil {


    public static boolean isFuel(ItemStack stack) {
        return stack.getItem().getItemBurnTime(stack) > 0;
    }
}
