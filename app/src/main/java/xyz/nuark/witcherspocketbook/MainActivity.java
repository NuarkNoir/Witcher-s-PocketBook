package xyz.nuark.witcherspocketbook;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;

import xyz.nuark.witcherspocketbook.Fragments.CreaturesListFragment;
import xyz.nuark.witcherspocketbook.Fragments.IngredientsListFragment;
import xyz.nuark.witcherspocketbook.Fragments.ItemsListFragment;
import xyz.nuark.witcherspocketbook.Fragments.MutagensListFragment;

import static xyz.nuark.witcherspocketbook.Utils.getJsonString;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity INSTANCE;
    public JSONArray jaCreatures, jaIngredients, jaItems, jaMutagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        INSTANCE = this;

        TextView textV = findViewById(R.id.textV);
        textV.setText(generateClasses());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_creatures:
                getSupportFragmentManager().beginTransaction().replace(R.id.includer, new CreaturesListFragment()).commit();
                break;
            case R.id.nav_ingredients:
                getSupportFragmentManager().beginTransaction().replace(R.id.includer, new IngredientsListFragment()).commit();
                break;
            case R.id.nav_items:
                getSupportFragmentManager().beginTransaction().replace(R.id.includer, new ItemsListFragment()).commit();
                break;
            case R.id.nav_mutagens:
                getSupportFragmentManager().beginTransaction().replace(R.id.includer, new MutagensListFragment()).commit();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String generateClasses() {
        String summary = "Краткая сводка:\n" +
                "\tСуществ: {cr}\n" +
                "\tИнгредиентов: {in}\n" +
                "\tПредметов: {it}\n" +
                "\tМутагенов: {mu}";
        try {
            jaCreatures = new JSONArray(getJsonString(INSTANCE, "bases/creatures.json"));
            jaIngredients = new JSONArray(getJsonString(INSTANCE, "bases/ingredients.json"));
            jaItems = new JSONArray(getJsonString(INSTANCE, "bases/items.json"));
            jaMutagens = new JSONArray(getJsonString(INSTANCE, "bases/mutagens.json"));
            summary = summary.replace("{cr}", String.valueOf(jaCreatures.length()));
            summary = summary.replace("{in}", String.valueOf(jaIngredients.length()));
            summary = summary.replace("{it}", String.valueOf(jaItems.length()));
            summary = summary.replace("{mu}", String.valueOf(jaMutagens.length()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }
}
