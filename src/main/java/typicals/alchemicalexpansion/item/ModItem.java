package typicals.alchemicalexpansion.item;

import net.minecraft.item.Item;

public abstract class ModItem extends Item {

    protected String path;

    public ModItem(String path) {
        super();
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
