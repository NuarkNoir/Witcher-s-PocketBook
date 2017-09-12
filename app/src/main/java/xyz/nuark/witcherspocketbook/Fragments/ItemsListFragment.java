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
import xyz.nuark.witcherspocketbook.Models.Item;
import xyz.nuark.witcherspocketbook.R;

import static xyz.nuark.witcherspocketbook.Fragments.ItemsListFragment.sendedItem;
import static xyz.nuark.witcherspocketbook.Utils.getDrawableFromAssets;

/**
 * Created by Nuark with love on 10.09.2017.
 * Protected by QPL-1.0
 */

public class ItemsListFragment extends Fragment {

    Context context = MainActivity.INSTANCE.getApplication();
    ArrayList<Item> ConsumableItems;
    public static Item sendedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ingredient_list, container, false);
        ListView lv_ingrlist = view.findViewById(R.id.lv_ingrlist);
        ConsumableItems = generateCreaturesList();
        lv_ingrlist.setAdapter(new ItLAdapter(context, ConsumableItems));
        return view;
    }

    private ArrayList<Item> generateCreaturesList() {
        JSONArray ingredientsArray = MainActivity.INSTANCE.jaItems;
        ArrayList<Item> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredientsArray.length(); i++) {
            try {
                JSONObject ingredient = ingredientsArray.getJSONObject(i);
                int ID = ingredient.getInt("ID");
                String Name = ingredient.getString("Name");
                String Type = ingredient.getString("Type");
                String Image = ingredient.getString("Image");
                String Effect = ingredient.has("Effect")? ingredient.getString("Effect") : "";
                String RecipeLocation = ingredient.has("RecipeLocation")? ingredient.getString("RecipeLocation") : "";

                ArrayList<Item.Description> ItemDesc = new ArrayList<>();
                if (ingredient.has("Description")) {
                    JSONArray dio = ingredient.getJSONArray("Description");
                    for (int j = 0; j < dio.length(); j++) {
                        JSONObject dioo = dio.getJSONObject(j);
                        String Description = dioo.getString("Description");
                        ItemDesc.add(new Item.Description(Description));
                    }
                }

                ArrayList<Item.Ingredients> ItemIng = new ArrayList<>();
                if (ingredient.has("Ingedients")) {
                    JSONArray iio = ingredient.getJSONArray("Ingedients");
                    for (int j = 0; j < iio.length(); j++) {
                        JSONObject iioo = iio.getJSONObject(j);
                        String Nr = iioo.getString("Nr");
                        String Ingedient = iioo.getString("Ingedient");
                        String ImageIngredient = iioo.getString("ImageIngredient");
                        String TypeI = iioo.has("TypeI")? iioo.getString("TypeI"): "";
                        int cID = iioo.has("ID")? iioo.getInt("ID") : 0;
                        ItemIng.add(new Item.Ingredients(
                                Nr,
                                Ingedient,
                                ImageIngredient,
                                TypeI,
                                cID
                        ));
                    }
                }

                ingredientsList.add(new Item(
                        ID,
                        Name,
                        Type,
                        Image,
                        Effect,
                        RecipeLocation,
                        ItemDesc,
                        ItemIng
                ));
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
                e.getCause();
            }
        }
        return ingredientsList;
    }
}

class ItLAdapter extends BaseAdapter {

    private Context context;
    private List<Item> data;

    ItLAdapter(Context context, List<Item> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Item item = data.get(i);
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.list_consumableitem, null);
        }
        TextView ingrTitle = view.findViewById(R.id.item_name);
        ingrTitle.setText(item.getName());
        TextView ingrEfffect = view.findViewById(R.id.item_effect);
        ingrEfffect.setText(item.getEffect());
        ImageView ingrImage = view.findViewById(R.id.item_image);
        ingrImage.setImageDrawable(getDrawableFromAssets(context, item.getImage()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendedItem = item;
                context.startActivity(new Intent(context, InfoViewerActivity.class)
                        .putExtra("WHO", "item")
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