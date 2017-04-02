package com.mz.cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mutualmobile.cardstack.CardStackAdapter;

import java.util.List;

/**
 * Written with ❤! By M on 02/04/17.
 */


class CardsAdapter extends CardStackAdapter {

    private int resource;
    private List<Colleague> colleagues;
    private Context context;

    CardsAdapter(Context context, int resource, List<Colleague> objects) {
        super(context);
        this.context = context;
        this.resource = resource;
        this.colleagues = objects;
    }

    @Override
    public View createView(int position, ViewGroup container) {
        View view = LayoutInflater.from(context).inflate(resource, container, false);
        TextView name = (TextView) view.findViewById(R.id.colleagueName);
        name.setText(colleagues.get(position).getName());
        TextView company = (TextView) view.findViewById(R.id.companyName);
        company.setText(colleagues.get(position).getCompany());
        return view;
    }

    @Override
    public int getCount() {
        return colleagues.size();
    }
}
