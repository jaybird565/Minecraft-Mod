package typicals.alchemicalexpansion.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import typicals.alchemicalexpansion.recipe.Recipes;


public class ListRecipesCommand extends CommandBase {
    @Override
    public String getName() {
        return "listrecipes";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "listrecipes <machine>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 1) {
            sender.sendMessage(new TextComponentString(this.getUsage(sender)));
            return;
        }
        String machine = args[0];
        ITextComponent text = new TextComponentString("Recipes for " + machine + ":\n");
        String recipes = Recipes.recipeListToString(machine);
        if(recipes.isEmpty()) {
            text.appendText("None");
        } else {
            text.appendText(Recipes.recipeListToString(machine));
        }
        sender.sendMessage(text);
    }
}
