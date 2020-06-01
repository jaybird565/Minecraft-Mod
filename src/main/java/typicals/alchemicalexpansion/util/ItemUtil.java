package typicals.alchemicalexpansion.util;


import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static int getTotalBurnTime(ItemStack stack) {
        return getBurnTime(stack) * stack.getCount();
    }


    /** reduces the count of stack by stack0 if and only if they contain the same item.
     *
     *  If stack0 contains a greater than or equal amount of items in stack,
     *  then stack's count is set to 0.
     */
    public static void subtractStacks(ItemStack stack, ItemStack stack0) {
        if(!stack.isItemEqual(stack0)) {
            return;
        } else if(stack0.getCount() >= stack.getCount()) {
            stack.setCount(0);
        } else {
            stack.shrink(stack0.getCount());
        }

    }

    /** tells whether a list of ItemStacks contains another ItemStack
     *
     * @param items a list that may contain itemIn
     * @param itemIn a item that may be in items
     * @return whether itemIn's Item is in items and if itemIn's count is greater than or equal to the matching ItemStack in items
     */
    public static boolean containsItemStack(List<ItemStack> items, ItemStack itemIn) {

        for(ItemStack  item: items) {
            if(item.isItemEqual(itemIn) ) {
                if(item.getCount() >= itemIn.getCount()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    /** Same as {@link #containsItemStack(List, ItemStack)} but removes matching item in items */
    public static boolean removeItemStack(List<ItemStack> items, ItemStack itemIn ) {

        for(ItemStack  item: items) {
            if(item.isItemEqual(itemIn) ) {
                if(item.getCount() >= itemIn.getCount()) {
                    items.remove(item);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    /**  Same as {@link #containsItemStack(List, ItemStack)} but decreases matching item in items by
     * count of itemIn */
    public static boolean decreaseItemStack(List<ItemStack> items, ItemStack itemIn) {
        for(ItemStack  item: items) {
            if(item.isItemEqual(itemIn) ) {
                if(item.getCount() >= itemIn.getCount()) {
                    item.shrink(itemIn.getCount());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }



    public static boolean containsItem(List<ItemStack> items, ItemStack itemIn) {
        for(ItemStack item : items) {
            if(item.getItem() == itemIn.getItem()) {
                return true;
            }
        }
        return false;
    }

    /** takes ItemStacks and joins stacks with matching items compressing it
     *
     * @param items the list that will "compressed"
     * @return the compressed list of ItemStacks
     */
    public static List<ItemStack> compress(List<ItemStack> items) {
        Map<Item, Integer> map = new HashMap<Item, Integer>(items.size());

        for(ItemStack item: items) {
            if(map.containsKey(item.getItem())) {
                map.put(item.getItem(), item.getCount() + map.get(item));
            } else {
                map.put(item.getItem(), item.getCount());
            }
        }
        List<ItemStack> itemsOut = new ArrayList<ItemStack>();

        for(Map.Entry<Item, Integer> entry : map.entrySet()) {
            ItemStack stack = new ItemStack(entry.getKey());
            stack.setCount(entry.getValue());
            itemsOut.add(stack);
        }

        return itemsOut;

    }

    public static String stackListToString(List<ItemStack> stacks) {
        final String delim = "\n";
        String rv = "";


        for(ItemStack stack : stacks) {
            rv += stack.toString() + delim;
        }

        return rv;
    }

    public static boolean isEmpty(List<ItemStack> stacks) {
        for(ItemStack stack : stacks) {
            if(!stack.isEmpty() && !stack.getItem().equals(Items.AIR)) {
                return false;
            }
        }
        return true;
    }
}

