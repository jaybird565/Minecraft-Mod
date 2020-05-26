package typicals.alchemicalexpansion.item;

import net.minecraft.item.ItemFood;

public abstract class ModFood extends ItemFood {

    protected String path;

    public ModFood(int amount, float saturation, boolean isWolfFood, String path) {
        super(amount, saturation, isWolfFood);
        this.path = path;
    }

    public String getPath() {
        return path;
    }



}
