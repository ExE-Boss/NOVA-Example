package nova.sample.item;

import nova.core.item.ItemFactory;
import nova.core.item.ItemManager;
import nova.core.loader.Loadable;
import nova.core.loader.Mod;
import nova.core.recipes.Recipe;
import nova.core.recipes.RecipeManager;
import nova.core.recipes.crafting.ItemIngredient;
import nova.core.recipes.crafting.ShapedCraftingRecipe;
import nova.core.render.RenderManager;
import nova.core.render.texture.ItemTexture;

/**
 * Created by magik6k on 5/29/15.
 */
@Mod(id = NovaItem.id, name = "Nova Example Item", version = "0.0.1", novaVersion = "0.0.1")
public class NovaItem implements Loadable {
	public static final String id = "novaitem";

	public static ItemFactory itemScrewdriver;
	public static ItemTexture screwTexture;

	public final ItemManager itemManager;
	public final RenderManager renderManager;
	public final RecipeManager recipeManager;

	public NovaItem(ItemManager itemManager, RenderManager renderManager, RecipeManager recipeManager) {
        this.itemManager = itemManager;
        this.renderManager = renderManager;
        this.recipeManager = recipeManager;
    }

	@Override
	public void preInit() {
		screwTexture = renderManager.registerTexture(new ItemTexture(id, "screwdriver"));
		itemScrewdriver = itemManager.register(id + ":testscrewdriver", ItemScrewdriver::new);

		//ItemIngredient stickIngredient = ItemIngredient.forItem("minecraft:stick"); //TODO: This should be obtained from some dictonary too
		ItemIngredient stickIngredient = ItemIngredient.forDictionary("stickWood");
		ItemIngredient ingotIngredient = ItemIngredient.forDictionary("ingotIron");
		recipeManager.addRecipe(new ShapedCraftingRecipe(itemScrewdriver.build(), "A- B", true, ingotIngredient, stickIngredient));
	}
}
