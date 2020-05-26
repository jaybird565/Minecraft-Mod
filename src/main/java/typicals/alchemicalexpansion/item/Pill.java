package typicals.alchemicalexpansion.item;



public class Pill extends ModFood {

    public static final String path = "pill";

    public Pill() {
        super(0,0,false, path);
        this.setAlwaysEdible();
    }

}
