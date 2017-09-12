package xyz.nuark.witcherspocketbook.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.nuark.witcherspocketbook.Activities.InfoViewerActivity;
import xyz.nuark.witcherspocketbook.MainActivity;
import xyz.nuark.witcherspocketbook.Models.Creature;
import xyz.nuark.witcherspocketbook.R;

import static xyz.nuark.witcherspocketbook.Fragments.CreaturesListFragment.sendedCreature;
import static xyz.nuark.witcherspocketbook.Utils.getDrawableFromAssets;

/**
 * Created by Nuark with love on 09.09.2017.
 * Protected by QPL-1.0
 */

public class CreaturesListFragment extends Fragment {

    Context context = MainActivity.INSTANCE.getApplication();
    ArrayList<Creature> Creatures,
            Beasts = new ArrayList<>(),
            Cursed = new ArrayList<>(),
            Draconid = new ArrayList<>(),
            Elementa = new ArrayList<>(),
            Hybrid = new ArrayList<>(),
            Insectoid = new ArrayList<>(),
            Necrophage = new ArrayList<>(),
            Ogroid = new ArrayList<>(),
            Relicts = new ArrayList<>(),
            Specter = new ArrayList<>(),
            Vampires = new ArrayList<>();
    public static Creature sendedCreature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.creature_list, container, false);
        ExpandableListView exl_crlist = view.findViewById(R.id.exl_crlist);
        Creatures = generateCreaturesList();
        exl_crlist.setAdapter(new CELAdapter(context, prepareGroups()));
        return view;
    }

    private ArrayList<Creature> generateCreaturesList() {
        JSONArray creaturesArray = MainActivity.INSTANCE.jaCreatures;
        ArrayList<Creature> creaturesList = new ArrayList<>();
        for (int i = 0; i < creaturesArray.length(); i++) {
            try {
                JSONObject creature = creaturesArray.getJSONObject(i);
                int ID = creature.getInt("ID");
                String Name = creature.getString("Name");
                String Class = creature.getString("Class");
                String Image = creature.getString("Image");

                ArrayList<Creature.Occurrence> Occurences = new ArrayList<>();
                if (creature.has("Occurrence")) {
                    JSONArray cgo = creature.getJSONArray("Occurrence");
                    JSONObject cgoo = cgo.getJSONObject(0);
                    Occurences.add(new Creature.Occurrence(cgoo.getString("Occurrence")));
                }

                ArrayList<Creature.Susceptibility> Susceptibility = new ArrayList<>();
                if (creature.has("Susceptibility")) {
                    JSONArray cso = creature.getJSONArray("Susceptibility");
                    for (int j = 0; j < cso.length(); j++) {
                        JSONObject csoo = cso.getJSONObject(j);
                        String cSusceptibility = csoo.getString("Susceptibility");
                        String ImageSusceptibility = csoo.getString("ImageSusceptibility");
                        String TypeI = null;
                        if (csoo.has("TypeI"))
                            TypeI = csoo.getString("TypeI");
                        int cID = 0;
                        if (csoo.has("ID"))
                            cID = csoo.getInt("ID");
                        Susceptibility.add(new Creature.Susceptibility(
                                cSusceptibility,
                                ImageSusceptibility,
                                TypeI,
                                cID
                        ));
                    }
                }

                ArrayList<Creature.Variations> Variations = new ArrayList<>();
                if (creature.has("Variations")) {
                    JSONArray cvo = creature.getJSONArray("Variations");
                    for (int j = 0; j < cvo.length(); j++) {
                        JSONObject cvoo = cvo.getJSONObject(j);
                        String cVariations = cvoo.getString("Variations");
                        String ImageCreature = cvoo.getString("ImageCreature");
                        String TypeI = null;
                        if (cvoo.has("TypeI"))
                            TypeI = cvoo.getString("TypeI");
                        int cID = 0;
                        if (cvoo.has("ID"))
                            cID = cvoo.getInt("ID");
                        Variations.add(new Creature.Variations(
                                cVariations,
                                ImageCreature,
                                TypeI,
                                cID
                        ));
                    }
                }

                ArrayList<Creature.Drop> Drop = new ArrayList<>();
                if (creature.has("Drop")) {
                    JSONArray cdo = creature.getJSONArray("Drop");
                    for (int j = 0; j < cdo.length(); j++) {
                        JSONObject cdoo = cdo.getJSONObject(j);
                        String cVariations = cdoo.getString("Drop");
                        String ImageCreature = cdoo.getString("ImageDrop");
                        String TypeI = null;
                        if (cdoo.has("TypeI"))
                            TypeI = cdoo.getString("TypeI");
                        int cID = 0;
                        if (cdoo.has("ID"))
                            cID = cdoo.getInt("ID");
                        Drop.add(new Creature.Drop(
                                cVariations,
                                ImageCreature,
                                TypeI,
                                cID
                        ));
                    }
                }

                creaturesList.add(new Creature(
                        ID,
                        Name,
                        Class,
                        Image,
                        Occurences,
                        Susceptibility,
                        Variations,
                        Drop
                ));
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
                e.getCause();
            }
        }
        return creaturesList;
    }

    private HashMap<String, List<Creature>> prepareGroups(){
        HashMap<String, List<Creature>> expDetails = new HashMap<>();

        for (Creature creature : Creatures) {
            switch (creature.getCreatureClass()){
                case "Beasts":
                    Beasts.add(creature);
                    break;
                case "Cursed Ones":
                    Cursed.add(creature);
                    break;
                case "Draconid":
                    Draconid.add(creature);
                    break;
                case "Elementa":
                    Elementa.add(creature);
                    break;
                case "Hybrid":
                    Hybrid.add(creature);
                    break;
                case "Insectoid":
                    Insectoid.add(creature);
                case "Necrophage":
                    Necrophage.add(creature);
                case "Ogroid":
                    Ogroid.add(creature);
                case "Relicts":
                    Relicts.add(creature);
                case "Specter":
                    Specter.add(creature);
                case "Vampires":
                    Vampires.add(creature);
                    break;
            }
        }

        expDetails.put("Чудовища", Beasts);
        expDetails.put("Проклятые", Cursed);
        expDetails.put("Дракониды", Draconid);
        expDetails.put("Элементали", Elementa);
        expDetails.put("Гибриды", Hybrid);
        expDetails.put("Инсектоиды", Insectoid);
        expDetails.put("Трупоеды", Necrophage);
        expDetails.put("Огры", Ogroid);
        expDetails.put("Реликты", Relicts);
        expDetails.put("Призраки", Specter);
        expDetails.put("Вампиры", Vampires);

        return expDetails;
    }
}

class CELAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expListTitle;
    private HashMap<String, List<Creature>> data;

    CELAdapter(Context context, HashMap<String, List<Creature>> data) {
        this.context = context;
        expListTitle = new ArrayList<>(data.keySet());
        this.data = data;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = getGroup(i);
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.group_item, null);
        }
        TextView listTitleTextView = view.findViewById(R.id.groupTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        TextView itemsCountTextView = view.findViewById(R.id.groupCount);
        itemsCountTextView.setText("Существ: " + data.get(getGroup(i)).size());
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final Creature creature = data.get(getGroup(i)).get(i1);
        String expListText = getChild(i, i1).toString();
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.list_item, null);
        }
        TextView expListTextView = view.findViewById(R.id.item_name);
        ImageView imageView = view.findViewById(R.id.item_image);
        imageView.setImageDrawable(getDrawableFromAssets(context, creature.getImage()));
        expListTextView.setText(expListText);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendedCreature = creature;
                context.startActivity(new Intent(context, InfoViewerActivity.class)
                        .putExtra("WHO", "creature")
                );
            }
        });
        return view;
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(expListTitle.get(i)).size()-1;
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(expListTitle.get(i)).get(i1).getName();
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public int getGroupCount() {
        return expListTitle.size() - 1;
    }

    @Override
    public String getGroup(int i) {
        return expListTitle.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}