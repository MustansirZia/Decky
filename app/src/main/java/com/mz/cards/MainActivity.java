package com.mz.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mutualmobile.cardstack.CardStackLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Colleague> colleagueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadColleagues();
        CardsAdapter cardsAdapter = new CardsAdapter(this, R.layout.collegue_item, colleagueList);
        CardStackLayout cards = (CardStackLayout) findViewById(R.id.cards);
        cards.setAdapter(cardsAdapter);
    }

    private void loadColleagues() {
        colleagueList.add(new Colleague("Wallace J. Alexander", "Apple Inc", "Quality Assurance"));
        colleagueList.add(new Colleague("Timothy J. Foley", "Clemens Markets", "Vice President"));
        colleagueList.add(new Colleague("Angela K. Sands", "Block Distributors", "HR Lead"));
        colleagueList.add(new Colleague("Jenna V. Rivera", "Quest Technology Service", "UI/UX Designer"));
        colleagueList.add(new Colleague("Johnny A. Ive", "Apple Inc", "Chief Designer"));
        colleagueList.add(new Colleague("John D. Doe", "Ericsson", "Chief Engineer"));
        colleagueList.add(new Colleague("Wallace J. Alexander", "Apple Inc", "Quality Assurance"));
        colleagueList.add(new Colleague("Timothy J. Foley", "Clemens Markets", "Vice President"));
        colleagueList.add(new Colleague("Angela K. Sands", "Block Distributors", "HR Lead"));
        colleagueList.add(new Colleague("Jenna V. Rivera", "Quest Technology Service", "UI/UX Designer"));
        colleagueList.add(new Colleague("Johnny A. Ive", "Apple Inc", "Chief Designer"));
        colleagueList.add(new Colleague("John D. Doe", "Ericsson", "Chief Engineer"));
        colleagueList.add(new Colleague("Mustansir Zia", "GraphicWeave", "Backend/Mobile Developer"));
    }
}
