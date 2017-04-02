package com.mz.cards;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mutualmobile.cardstack.CardStackLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList = new ArrayList<>();
    private List<TextView> headerTexts = new ArrayList<>();
    private CardsAdapter cardsAdapter;
    private CardStackLayout cards;
    private ProgressDialog progressDialog;
    private ImageView sortArrow;
    private boolean sortOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadColleagues();
        cardsAdapter = new CardsAdapter(this, R.layout.collegue_item, personList);
        cards = (CardStackLayout) findViewById(R.id.cards);
        cards.setAdapter(cardsAdapter);
        headerTexts.add(((TextView) findViewById(R.id.allText)));
        headerTexts.add(((TextView) findViewById(R.id.friendsText)));
        headerTexts.add(((TextView) findViewById(R.id.colleaguesText)));
        headerTexts.add(((TextView) findViewById(R.id.buyersText)));
        sortArrow = (ImageView) findViewById(R.id.arrow);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
    }

    private void loadColleagues() {
        personList.add(new Person(1, "Wallace J. Alexander", "Apple Inc", "Quality Assurance", PersonType.COLLEAGUE));
        personList.add(new Person(2, "Timothy J. Foley", "Clemens Markets", "Vice President", PersonType.COLLEAGUE));
        personList.add(new Person(3, "Angela K. Sands", "Block Distributors", "HR Lead", PersonType.COLLEAGUE));
        personList.add(new Person(4, "Jenna V. Rivera", "Quest Technology Service", "UI/UX Designer", PersonType.FRIEND));
        personList.add(new Person(5, "Johnny A. Ive", "Apple Inc", "Chief Designer", PersonType.FRIEND));
        personList.add(new Person(6, "John D. Doe", "Ericsson", "Chief Engineer", PersonType.BUYER));
        personList.add(new Person(9, "Gregory K. Helms", "Block Distributors", "HR Lead", PersonType.FRIEND));
        personList.add(new Person(10, "Tim A. Cook", "Apple Inc", "CEO", PersonType.COLLEAGUE));
        personList.add(new Person(11, "Agatha Christie", "Harper Collins", "Writer", PersonType.BUYER));
        personList.add(new Person(12, "Alexander G. Bell", "Apple Inc", "Quality Assurance", PersonType.BUYER));
        personList.add(new Person(13, "Albert J. Einstein", "Clemens Markets", "Vice President", PersonType.BUYER));
        personList.add(new Person(15, "Aiden G. Pierce", "4Chan Inc", "Hacker", PersonType.BUYER));
        personList.add(new Person(16, "Connor Kenway", "Aquila Inc", "Vice President", PersonType.COLLEAGUE));
        personList.add(new Person(19, "Abdul Maajid Zargar", "GraphicWeave", "Senior UI/UX Designer", PersonType.FRIEND));
        personList.add(new Person(17, "Salfi Farooq", "GraphicWeave", "Senior Mobile Developer", PersonType.FRIEND));
        personList.add(new Person(20, "Mudasir Ashraf", "GraphicWeave", "Senior Backend Developer", PersonType.FRIEND));
        personList.add(new Person(21, "Mustansir Zia", "GraphicWeave", "Software Intern", PersonType.FRIEND));
        Collections.sort(personList);
    }

    public void onCategoryChoose(View v) {
        progressDialog.show();
        switch (v.getTag().toString()) {
            case "ALL":
                cardsAdapter.setPersons(personList);
                activateCategory(R.id.allText);
                break;
            case "FRIENDS":
                cardsAdapter.setPersons(filterPersons(PersonType.FRIEND));
                activateCategory(R.id.friendsText);
                break;
            case "COLLEAGUES":
                cardsAdapter.setPersons(filterPersons(PersonType.COLLEAGUE));
                activateCategory(R.id.colleaguesText);
                break;
            case "BUYERS":
                cardsAdapter.setPersons(filterPersons(PersonType.BUYER));
                activateCategory(R.id.buyersText);
                break;
        }
        resetAdapter();
        progressDialog.dismiss();
    }

    private List<Person> filterPersons(PersonType withType) {
        List<Person> persons = new ArrayList<>();
        for (Person person: personList) {
            if (person.getType() == withType) {
                persons.add(person);
            }
        }
        return persons;
    }

    public void onSortPersons(View v) {
        progressDialog.show();
        if (sortOrder) {
            Collections.sort(cardsAdapter.getPersons());
            sortArrow.setRotationX(0);
        } else {
            Collections.sort(cardsAdapter.getPersons(), new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            sortArrow.setRotationX(180f);
        }
        sortOrder = !sortOrder;
        resetAdapter();
        progressDialog.dismiss();
    }

    private void resetAdapter() {
        cards.setShowInitAnimation(false);
        cards.removeAdapter();
        cards.setAdapter(cardsAdapter);
        if (cards.isCardSelected()) {
            cards.restoreCards();
        }
    }

    private void activateCategory(int categoryID) {
        for (TextView category: headerTexts) {
            if (category.getId() == categoryID) {
                category.setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
            } else {
                category.setTextColor(ContextCompat.getColor(this, R.color.textLightGrey));
            }
        }
    }
}
