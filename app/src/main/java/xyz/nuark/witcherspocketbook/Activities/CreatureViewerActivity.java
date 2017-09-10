package xyz.nuark.witcherspocketbook.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.nuark.witcherspocketbook.Fragments.CreaturesListFragment;
import xyz.nuark.witcherspocketbook.Models.Creature;
import xyz.nuark.witcherspocketbook.R;
import xyz.nuark.witcherspocketbook.Utils;

public class CreatureViewerActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView creatureSummary;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_viewer);
        initView();
        Creature creature = CreaturesListFragment.sendedCreature;
        imageView.setImageDrawable(Utils.getDrawableFromAssets(this, creature.getImage()));
        String text = generateText(creature);
        creatureSummary.setText(Html.fromHtml(text));
        webView.loadDataWithBaseURL(null, text, "text/html; charset=utf-8", "UTF-8", null);
    }

    private String generateText(Creature c) {
        StringBuilder text = new StringBuilder();
        text.append("<img src=\"file:///android_asset/images/").append(c.getImage()).append("\"/><br><br>\n");
        text.append("ID: ").append(c.getId()).append("<br>\n");
        text.append("Название существа: ").append(c.getName()).append("<br>\n");
        text.append("Класс существа: ").append(c.getCreatureClass()).append("<br>\n");
        if (c.getOccurrence().size() > 0) {
            text.append("Среда обитания: ");
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
                text.append("\t ")
                        .append("<img src=\"file:///android_asset/images/").append(oc.getImageCreature()).append("\"/>")
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

    private void initView() {
        imageView = findViewById(R.id.imageView);
        creatureSummary = findViewById(R.id.creatureSummary);
        webView = findViewById(R.id.webView);
    }
}
