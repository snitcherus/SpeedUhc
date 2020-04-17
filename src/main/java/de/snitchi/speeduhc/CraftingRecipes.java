package de.snitchi.speeduhc;

import de.snitchi.someapi.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingRecipes {

  public static void InitialRecipes() {

    createRecipe(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 1, "Diamond_Sword");
    createRecipe(Material.IRON_SWORD, Enchantment.DAMAGE_ALL, 1, "Iron_Sword");
    createRecipe(Material.GOLDEN_SWORD, Enchantment.DAMAGE_ALL, 1, "Golden_Sword");
    createRecipe(Material.STONE_SWORD, Enchantment.DAMAGE_ALL, 1, "Stone_Sword");
    createRecipe(Material.WOODEN_SWORD, Enchantment.DAMAGE_ALL, 1, "Wooden_Sword");

  }

  /**
   * @param material needed Material
   * @param enchantment enchantment result
   * @param enchantmentLevel enchantment level
   * @param recipeName recipe name
   */
  public static void createRecipe(Material material, Enchantment enchantment, int enchantmentLevel, String recipeName){
    ItemBuilder builder = new ItemBuilder(material);
    builder.addEnchant(enchantment, enchantmentLevel);

    //Recipe Name
    NamespacedKey key = new NamespacedKey(SpeedUhcPlugin.getInstance(), recipeName);

    ShapedRecipe recipe = new ShapedRecipe(key, builder.build());

    //Line 1 - 3 in the Workbench
    recipe.shape("   ", " E ", "   ");

    recipe.setIngredient('E', material);

    Bukkit.addRecipe(recipe);

  }
}
