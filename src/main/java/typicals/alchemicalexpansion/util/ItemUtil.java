package typicals.alchemicalexpansion.util;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtil {


    public static boolean isFuel(ItemStack stack) {
        return getBurnTime(stack) > 0;
    }

    public static boolean isFuel(Item item) {
        return getBurnTime(item) > 0;
    }

    public static int getBurnTime(ItemStack stack) {
        if(stack.isEmpty()) {
            return 0;
        } else {
            return getBurnTime(stack.getItem());
        }
    }

    public static int getBurnTime(Item item) {
        return 200;
    }
}

