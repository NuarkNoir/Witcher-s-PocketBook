package xyz.nuark.witcherspocketbook.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.nuark.witcherspocketbook.Activities.InfoViewerActivity;
import xyz.nuark.witcherspocketbook.MainActivity;
import xyz.nuark.witcherspocketbook.Models.Ingredient;
import xyz.nuark.witcherspocketbook.R;

import static xyz.nuark.witcherspocketbook.Fragments.IngredientsListFragment.sendedIngredient;
import static xyz.nuark.witcherspocketbook.Utils.getDrawableFromAssets;

/**
 * Created by Nuark with love on 10.09.2017.
 * Protected by QPL-1.0
 */

public class IngredientsListFragment extends Fragment {

    Context context = MainActivity.INSTANCE.getApplication();
    ArrayList<Ingredient> Ingredients;
    public static Ingredient sendedIngredient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ingredient_list, container, false);
        ListView lv_ingrlist = view.findViewById(R.id.lv_ingrlist);
        Ingredients = generateCreaturesList();
        lv_ingrlist.setAdapter(new InLAdapter(context, Ingredients));
        return view;
    }

    private ArrayList<Ingredient> generateCreaturesList() {
        JSONArray ingredientsArray = MainActivity.INSTANCE.jaIngredients;
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredientsArray.length(); i++) {
            try {
                JSONObject ingredient = ingredientsArray.getJSONObject(i);
                int ID = ingredient.getInt("ID");
                String Name = ingredient.getString("Name");
                String Type = ingredient.getString("Type");
                String Image = ingredient.getString("Image");

                ArrayList<Ingredient.DeconstructedInto> DecInto = new ArrayList<>();
                if (ingredient.has("DeconstructedInto")) {
                    JSONArray dio = ingredient.getJSONArray("DeconstructedInto");
                    for (int j = 0; j < dio.length(); j++) {
                        JSONObject dioo = dio.getJSONObject(j);
                        String Nr = dioo.getString("Nr");
                        String Ingedient = dioo.getString("Ingedient");
                        String ImageIngedient = dioo.getString("ImageIngedient");
                        String TypeI = dioo.has("TypeI")? dioo.getString("TypeI"): "";
                        int cID = dioo.has("ID")? dioo.getInt("ID") : 0;
                        DecInto.add(new Ingredient.DeconstructedInto(
                                Nr,
                                Ingedient,
                                ImageIngedient,
                                TypeI,
                                cID
                        ));
                    }
                }

                ArrayList<Ingredient.DeconstructedFrom> DecFrom = new ArrayList<>();
                if (ingredient.has("DeconstructedFrom")) {
                    JSONArray ido = ingredient.getJSONArray("DeconstructedFrom");
                    for (int j = 0; j < ido.length(); j++) {
                        JSONObject idoo = ido.getJSONObject(j);
                        String Ingedient = idoo.getString("Ingedient");
                        String ImageIngedient = idoo.getString("ImageIngedient");
                        String TypeI = idoo.has("TypeI")? idoo.getString("TypeI"): "";
                        int cID = idoo.has("ID")? idoo.getInt("ID") : 0;
                        DecFrom.add(new Ingredient.DeconstructedFrom(
                                Ingedient,
                                ImageIngedient,
                                TypeI,
                                cID
                        ));
                    }
                }

                ArrayList<Ingredient.Used> UsedIn = new ArrayList<>();
                if (ingredient.has("Used")) {
                    JSONArray uio = ingredient.getJSONArray("Used");
                    for (int j = 0; j < uio.length(); j++) {
                        JSONObject uioo = uio.getJSONObject(j);
                        String Used = uioo.getString("Used");
                        String ImageUsed = uioo.getString("ImageUsed");
                        String TypeI = uioo.has("TypeI")? uioo.getString("TypeI"): "";
                        int cID = uioo.has("ID")? uioo.getInt("ID") : 0;
                        UsedIn.add(new Ingredient.Used(
                                Used,
                                ImageUsed,
                                TypeI,
                                cID
                        ));
                    }
                }

                ingredientsList.add(new Ingredient(
                        ID,
                        Name,
                        Type,
                        Image,
                        DecInto,
                        DecFrom,
                        UsedIn
                ));
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
                e.getCause();
            }
        }
        return ingredientsList;
    }
}

class InLAdapter extends BaseAdapter {

    private Context context;
    private List<Ingredient> data;

    InLAdapter(Context context, List<Ingredient> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Ingredient ingredient = data.get(i);
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.list_item, null);
        }
        TextView ingrTitle = view.findViewById(R.id.item_name);
        ingrTitle.setText(ingredient.getName());
        ImageView ingrImage = view.findViewById(R.id.item_image);
        ingrImage.setImageDrawable(getDrawableFromAssets(context, ingredient.getImage()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendedIngredient = ingredient;
                context.startActivity(new Intent(context, InfoViewerActivity.class)
                        .putExtra("WHO", "ingredient")
                );
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}