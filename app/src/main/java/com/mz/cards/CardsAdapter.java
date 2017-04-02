package com.mz.cards;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mutualmobile.cardstack.CardStackAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Written with ❤! By M on 02/04/17.
 */


class CardsAdapter extends CardStackAdapter {

    private int resource;
    private List<Person> persons;
    private Context context;

    CardsAdapter(Context context, int resource, List<Person> objects) {
        super(context);
        this.context = context;
        this.resource = resource;
        this.persons = objects;
    }

    @Override
    public View createView(int position, ViewGroup container) {
        View view = LayoutInflater.from(context).inflate(resource, container, false);
        TextView name = (TextView) view.findViewById(R.id.colleagueName);
        name.setText(persons.get(position).getName());
        TextView company = (TextView) view.findViewById(R.id.companyName);
        company.setText(persons.get(position).getCompany());
        TextView desig = (TextView) view.findViewById(R.id.position);
        desig.setText(persons.get(position).getPosition());
        CircleImageView circle = (CircleImageView) view.findViewById(R.id.identifier);
        switch (persons.get(position).getType()) {
            case FRIEND:
                circle.setImageResource(R.color.green);
                break;
            case COLLEAGUE:
                circle.setImageResource(R.color.yellow);
                break;
            case BUYER:
                circle.setImageResource(R.color.pink);
                break;
        }
        return view;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
