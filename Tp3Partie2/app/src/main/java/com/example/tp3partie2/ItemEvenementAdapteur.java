package com.example.tp3partie2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemEvenementAdapteur extends BaseAdapter
{
    private ArrayList<Evenement> listEvenements;

    private Context context;

    public ItemEvenementAdapteur(Context context, ArrayList<Evenement> listEvenements)
    {
        this.context = context;
        this.listEvenements = listEvenements;
    }

    @Override
    public int getCount() {
        return listEvenements.size();
    }

    @Override
    public Object getItem(int position) {
        return listEvenements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evenement, parent, false);
        }

        Evenement evenement = listEvenements.get(position);

        TextView outputTitre = convertView.findViewById(R.id.itemEvenementTitre);
        TextView outputDescription = convertView.findViewById(R.id.itemEvenementDescription);
        TextView outputDate = convertView.findViewById(R.id.itemEvenementDate);
        TextView outputEquipe = convertView.findViewById(R.id.itemEvenementEquipe);
        TextView outputType = convertView.findViewById(R.id.itemEvenementType);
        TextView outputCoordonees = convertView.findViewById(R.id.itemEvenementCoordonee);

        outputTitre.setText(evenement.getTitre());
        outputDescription.setText(evenement.getDescription());
        outputDate.setText(evenement.getDate());
        outputEquipe.setText(evenement.getEquipe());
        outputType.setText(evenement.getType());
        outputCoordonees.setText(evenement.getFormatedCoordinates());

        return convertView;
    }
}
