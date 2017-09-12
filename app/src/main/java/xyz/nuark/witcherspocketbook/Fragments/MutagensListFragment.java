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
import xyz.nuark.witcherspocketbook.Models.Mutagen;
import xyz.nuark.witcherspocketbook.R;

import static xyz.nuark.witcherspocketbook.Fragments.MutagensListFragment.sendedMutagen;
import static xyz.nuark.witcherspocketbook.Utils.getDrawableFromAssets;

/**
 * Created by Nuark with love on 10.09.2017.
 * Protected by QPL-1.0
 */

public class MutagensListFragment extends Fragment {

    Context context = MainActivity.INSTANCE.getApplication();
    ArrayList<Mutagen> ConsumableItems;
    public static Mutagen sendedMutagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ingredient_list, container, false);
        ListView lv_ingrlist = view.findViewById(R.id.lv_ingrlist);
        ConsumableItems = generateCreaturesList();
        lv_ingrlist.setAdapter(new MuLAdapter(context, ConsumableItems));
        return view;
    }

    private ArrayList<Mutagen> generateCreaturesList() {
        JSONArray mutagensArray = MainActivity.INSTANCE.jaMutagens;
        ArrayList<Mutagen> mutagensList = new ArrayList<>();
        for (int i = 0; i < mutagensArray.length(); i++) {
            try {
                JSONObject ingredient = mutagensArray.getJSONObject(i);
                int ID = ingredient.getInt("ID");
                String Usage = ingredient.has("Usage")? ingredient.getString("Usage") : "";
                String Name = ingredient.getString("Name");
                String Effect = ingredient.getString("Effect");
                String Obtain = ingredient.getString("Obtain");
                String Image = ingredient.getString("Image");
                String Category = ingredient.getString("Category");
                int BasePrice = ingredient.getInt("BasePrice");

                ArrayList<Mutagen.Creature> MuCreature = new ArrayList<>();
                if (ingredient.has("Description")) {
                    JSONArray dio = ingredient.getJSONArray("Description");
                    for (int j = 0; j < dio.length(); j++) {
                        JSONObject dioo = dio.getJSONObject(j);
                        String Variations = dioo.getString("Variations");
                        String ImageCreature = dioo.getString("ImageCreature");
                        String TypeI = dioo.getString("TypeI");
                        int cID = dioo.getInt("ID");
                        MuCreature.add(new Mutagen.Creature(
                                Variations,
                                ImageCreature,
                                TypeI,
                                cID
                        ));
                    }
                }

                ArrayList<Mutagen.Used> MuUsed = new ArrayList<>();
                if (ingredient.has("Ingedients")) {
                    JSONArray iio = ingredient.getJSONArray("Ingedients");
                    for (int j = 0; j < iio.length(); j++) {
                        JSONObject iioo = iio.getJSONObject(j);
                        String Used = iioo.getString("Used");
                        String ImageUsed = iioo.getString("ImageUsed");
                        String TypeI = iioo.getString("TypeI");
                        int cID = iioo.getInt("ID");
                        MuUsed.add(new Mutagen.Used(
                                Used,
                                ImageUsed,
                                TypeI,
                                cID
                        ));
                    }
                }

                mutagensList.add(new Mutagen(
                        ID,
                        Usage,
                        Name,
                        Effect,
                        Obtain,
                        Image,
                        Category,
                        BasePrice,
                        MuCreature,
                        MuUsed
                ));
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
                e.getCause();
            }
        }
        return mutagensList;
    }
}

class MuLAdapter extends BaseAdapter {

    private Context context;
    private List<Mutagen> data;

    MuLAdapter(Context context, List<Mutagen> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Mutagen mutagen = data.get(i);
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.list_consumableitem, null);
        }
        TextView ingrTitle = view.findViewById(R.id.item_name);
        ingrTitle.setText(mutagen.getName());
        TextView ingrEfffect = view.findViewById(R.id.item_effect);
        ingrEfffect.setText(mutagen.getEffect());
        ImageView ingrImage = view.findViewById(R.id.item_image);
        ingrImage.setImageDrawable(getDrawableFromAssets(context, mutagen.getImage()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendedMutagen = mutagen;
                context.startActivity(new Intent(context, InfoViewerActivity.class)
                        .putExtra("WHO", "mutagen")
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