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

    createRecipe(Material.IRON_PICKAXE, Enchantment.DIG_SPEED, 1, "Enchanted_Ciron_Pickaxe");
    createRecipe(Material.IRON_AXE, Enchantment.DIG_SPEED, 1, "Enchanted_Ciron_Axe");

    createUnenchantPickaxeRecipe(Material.IRON_PICKAXE, "Ciron_Pickaxe");
    createUnenchantAxeRecipe(Material.IRON_AXE, "Ciron_Axe");
  }

  /**
   * @param material         needed Material
   * @param enchantment      enchantment result
   * @param enchantmentLevel enchantment level
   * @param recipeName       recipe name
   */
  public static void createRecipe(Material material, Enchantment enchantment, int enchantmentLevel,
                                  String recipeName) {
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

  public static void createUnenchantPickaxeRecipe(Material material, String recipeName) {
    ItemBuilder builder = new ItemBuilder(material);

    //Recipe Name
    NamespacedKey key = new NamespacedKey(SpeedUhcPlugin.getInstance(), recipeName);

    ShapedRecipe recipe = new ShapedRecipe(key, builder.build());

    //Line 1 - 3 in the Workbench
    recipe.shape("EEE", " S ", " S ");

    recipe.setIngredient('E', Material.COBBLESTONE);
    recipe.setIngredient('S', Material.STICK);

    Bukkit.addRecipe(recipe);
  }

  public static void createUnenchantAxeRecipe(Material material, String recipeName) {
    ItemBuilder builder = new ItemBuilder(material);

    //Recipe Name
    NamespacedKey key = new NamespacedKey(SpeedUhcPlugin.getInstance(), recipeName);

    ShapedRecipe recipe = new ShapedRecipe(key, builder.build());

    //Line 1 - 3 in the Workbench
    recipe.shape(" EE", " SE", " S ");

    recipe.setIngredient('E', Material.COBBLESTONE);
    recipe.setIngredient('S', Material.STICK);

    Bukkit.addRecipe(recipe);
  }
}
