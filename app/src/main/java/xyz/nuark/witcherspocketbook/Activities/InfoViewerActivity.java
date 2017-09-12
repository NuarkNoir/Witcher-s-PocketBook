package xyz.nuark.witcherspocketbook.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import xyz.nuark.witcherspocketbook.Fragments.CreaturesListFragment;
import xyz.nuark.witcherspocketbook.Fragments.IngredientsListFragment;
import xyz.nuark.witcherspocketbook.Fragments.ItemsListFragment;
import xyz.nuark.witcherspocketbook.Fragments.MutagensListFragment;
import xyz.nuark.witcherspocketbook.MainActivity;
import xyz.nuark.witcherspocketbook.Models.Creature;
import xyz.nuark.witcherspocketbook.Models.Ingredient;
import xyz.nuark.witcherspocketbook.Models.Item;
import xyz.nuark.witcherspocketbook.Models.Mutagen;
import xyz.nuark.witcherspocketbook.R;

public class InfoViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_viewer);
        String who = getIntent().getExtras().getString("WHO");
        String text = "";
        if (who != null)
        switch (who){
            case "creature":
                Creature creature = CreaturesListFragment.sendedCreature;
                text = generateText(creature);
                break;
            case "ingredient":
                Ingredient ingredient = IngredientsListFragment.sendedIngredient;
                text = generateText(ingredient);
                break;
            case "item":
                Item item = ItemsListFragment.sendedItem;
                text = generateText(item);
                break;
            case "mutagen":
                Mutagen mutagen = MutagensListFragment.sendedMutagen;
                text = generateText(mutagen);
                break;
            default:
                finishActivity(0);
                break;
        }
        WebView webView = findViewById(R.id.webView);
        webView.loadDataWithBaseURL(null, text, "text/html; charset=utf-8", "UTF-8", null);
    }

    private String generateText(Creature c) {
        StringBuilder text = new StringBuilder();
        text.append("<img src=\"file:///android_asset/images/").append(c.getImage()).append("\"/><br><br>\n");
        text.append("ID: ").append(c.getId()).append("<br>\n");
        text.append("Название существа: ").append(c.getName()).append("<br>\n");
        text.append("Класс существа: ").append(c.getCreatureClass()).append("<br>\n");
        if (c.getOccurrence().size() > 0) {
            text.append("Среда обитания: <br>\n");
            for (Creature.Occurrence oc : c.getOccurrence()) {
                text.append("\t ").append(oc.getOccurrence()).append("<br>\n");
            }
        }
        if (c.getSusceptibility().size() > 0) {
            text.append("Уязвимо к: <br>\n");
            for (Creature.Susceptibility oc : c.getSusceptibility()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(oc.getImageSusceptibility()).append("\"/>")
                        .append(oc.getSusceptibility())
                        .append("<br>\n");
            }
        }
        if (c.getVariations().size() > 0) {
            text.append("Вариации: <br>\n");
            for (Creature.Variations oc : c.getVariations()) {
                text.append("\t- ")
                        .append(oc.getVariations())
                        .append("<br>\n");
            }
        }
        if (c.getDrop().size() > 0) {
            text.append("Дроп: <br>\n");
            for (Creature.Drop oc : c.getDrop()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(oc.getImageDrop()).append("\"/>")
                        .append(oc.getDrop())
                        .append("<br>\n");
            }
        }

        return text.toString();
    }

    private String generateText(Ingredient i) {
        StringBuilder text = new StringBuilder();
        text.append("<img src=\"file:///android_asset/images/").append(i.getImage()).append("\"/><br><br>\n");
        text.append("ID: ").append(i.getId()).append("<br>\n");
        text.append("Название: ").append(i.getName()).append("<br>\n");
        if (i.getDeconstructedFrom().size() > 0) {
            text.append("Добывается при разборке: <br>\n");
            for (Ingredient.DeconstructedFrom id : i.getDeconstructedFrom()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(id.getImageIngedient()).append("\"/>")
                        .append(id.getIngedient())
                        .append("<br>\n");
            }
        }
        if (i.getDeconstructedInto().size() > 0) {
            text.append("Разбирается на: <br>\n");
            for (Ingredient.DeconstructedInto id : i.getDeconstructedInto()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(id.getImageIngedient()).append("\"/>")
                        .append(id.getIngedient())
                        .append("<br>\n");
            }
        }
        if (i.getUsed().size() > 0) {
            text.append("Используется в: <br>\n");
            for (Ingredient.Used iu : i.getUsed()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(iu.getImageUsed()).append("\"/>")
                        .append(iu.getUsed())
                        .append("<br>\n");
            }
        }

        return text.toString();
    }

    private String generateText(Item i) {
        StringBuilder text = new StringBuilder();
        text.append("<img src=\"file:///android_asset/images/").append(i.getImage()).append("\"/><br><br>\n");
        text.append("ID: ").append(i.getId()).append("<br>\n");
        text.append("Название: ").append(i.getName()).append("<br>\n");
        if (i.getEffect().length() > 0)
            text.append("Эффект: ").append(i.getEffect()).append("<br>\n");
        if (i.getRecipeLocation().length() > 0)
            text.append("Местонахождение рецепта: ").append(i.getRecipeLocation()).append("<br>\n");
        if (i.getDescription().size() > 0) {
            text.append("Описание: <br>\n");
            for (Item.Description id : i.getDescription()) {
                text.append("\t- ")
                        .append(id.getDescription())
                        .append("<br>\n");
            }
        }
        if (i.getIngredients().size() > 0) {
            text.append("Ингредиенты: <br>\n");
            for (Item.Ingredients id : i.getIngredients()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(id.getImageIngredient()).append("\"/>")
                        .append(id.getIngedient())
                        .append(" x")
                        .append(id.getNr())
                        .append("<br>\n");
            }
        }

        return text.toString();
    }

    private String generateText(Mutagen m) {
        StringBuilder text = new StringBuilder();
        text.append("<img src=\"file:///android_asset/images/").append(m.getImage()).append("\"/><br><br>\n");
        text.append("ID: ").append(m.getId()).append("<br>\n");
        text.append("Название: ").append(m.getName()).append("<br>\n");
        text.append("Базовая цена: ").append(m.getBasePrice()).append("<br>\n");
        text.append("Категория: ").append(m.getCategory()).append("<br>\n");
        text.append("Эффект: ").append(m.getEffect()).append("<br>\n");
        text.append("Использование: ").append(m.getUsage()).append("<br>\n");
        text.append("Способ добычи: ").append(m.getObtain()).append("<br>\n");
        if (m.getCreature().size() > 0) {
            text.append("Выпадает из: <br>\n");
            for (Mutagen.Creature mc : m.getCreature()) {
                text.append("\t- ")
                        .append(mc.getVariations())
                        .append("<br>\n");
            }
        }
        if (m.getUsed().size() > 0) {
            text.append("Используется: <br>\n");
            for (Mutagen.Used mu : m.getUsed()) {
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(mu.getImageUsed()).append("\"/>")
                        .append(mu.getUsed())
                        .append("<br>\n");
            }
        }

        return text.toString();
    }
}
