package typicals.alchemicalexpansion;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import typicals.alchemicalexpansion.AlchemicalExpansion;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static KeyBinding example;

    public static void init() {
        //instantiate KeyBinding(s)
        //and register them at the ClientRegistry
        /*
        example = new KeyBinding("key.example", Keyboard.KEY_T, "key.categories" + AlchemicalExpansion.MODID);
        ClientRegistry.registerKeyBinding(example);
         */
    }
}
