package com.mz.cards;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Person> basicPersonList = new ArrayList<>();
    private List<Person> filteredPersonList;
    private List<Map<String, Object>> headers = new ArrayList<>();
//    private CardsAdapter cardsAdapter;
//    private CardStackLayout cards;
    private CardStackView cardStackView;
    private CardAdapter cardAdapter;

    private ProgressDialog progressDialog;
    private ImageView sortArrow;
    private boolean sortOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadColleagues();
//        cardsAdapter = new CardsAdapter(this, R.layout.collegue_item, basicPersonList);
//        cards = (CardStackLayout) findViewById(R.id.cards);
//        cards.setAdapter(cardsAdapter);

        cardStackView = (CardStackView) findViewById(R.id.stackview_main);
        cardAdapter = new CardAdapter(this);
        cardAdapter.setData(filteredPersonList);
        cardStackView.setAdapter(cardAdapter);
        cardStackView.setItemExpendListener(new CardStackView.ItemExpendListener() {
            @Override
            public void onItemExpend(boolean expend) {

            }
        });
        cardStackView.setAnimationType(CardStackView.UP_DOWN_STACK);
        cardStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(cardStackView));

        Map<String, Object> allMap = new HashMap<>();
        allMap.put("id", "ALL");
        allMap.put("textView", findViewById(R.id.allText));
        allMap.put("imageView", findViewById(R.id.allImage));
        allMap.put("deActiveImage", R.drawable.blue_ellipse);
        allMap.put("activeImage", R.drawable.blue_ellipse_active);

        Map<String, Object> friendsMap = new HashMap<>();
        friendsMap.put("id", "FRIENDS");
        friendsMap.put("textView", findViewById(R.id.friendsText));
        friendsMap.put("imageView", findViewById(R.id.friendsImage));
        friendsMap.put("deActiveImage", R.drawable.green_ellipse);
        friendsMap.put("activeImage", R.drawable.green_ellipse_active);

        Map<String, Object> colleaguesMap = new HashMap<>();
        colleaguesMap.put("id", "COLLEAGUES");
        colleaguesMap.put("textView", findViewById(R.id.colleaguesText));
        colleaguesMap.put("imageView", findViewById(R.id.colleaguesImage));
        colleaguesMap.put("deActiveImage", R.drawable.yellow_ellipse);
        colleaguesMap.put("activeImage", R.drawable.yellow_ellipse_active);

        Map<String, Object> buyersMap = new HashMap<>();
        buyersMap.put("id", "BUYERS");
        buyersMap.put("textView", findViewById(R.id.buyersText));
        buyersMap.put("imageView", findViewById(R.id.buyersImage));
        buyersMap.put("deActiveImage", R.drawable.pink_ellipse);
        buyersMap.put("activeImage", R.drawable.pink_ellipse_active);

        headers.add(allMap);
        headers.add(friendsMap);
        headers.add(colleaguesMap);
        headers.add(buyersMap);

        sortArrow = (ImageView) findViewById(R.id.arrow);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
    }

    private void loadColleagues() {
        basicPersonList.add(new Person(1, "Wallace J. Alexander", "Apple Inc", "Quality Assurance", PersonType.COLLEAGUE));
        basicPersonList.add(new Person(2, "Timothy J. Foley", "Clemens Markets", "Vice President", PersonType.COLLEAGUE));
        basicPersonList.add(new Person(4, "Jenna V. Rivera", "Quest Technology Service", "UI/UX Designer", PersonType.BUYER));
        basicPersonList.add(new Person(5, "Johnny A. Ive", "Apple Inc", "Chief Designer", PersonType.BUYER));
        basicPersonList.add(new Person(6, "John D. Doe", "Ericsson", "Chief Engineer", PersonType.COLLEAGUE));
        basicPersonList.add(new Person(9, "Gregory K. Helms", "Block Distributors", "HR Lead", PersonType.BUYER));
        basicPersonList.add(new Person(10, "Tim A. Cook", "Apple Inc", "CEO", PersonType.COLLEAGUE));
        basicPersonList.add(new Person(12, "Alexander G. Bell", "Apple Inc", "Quality Assurance", PersonType.BUYER));
//        basicPersonList.add(new Person(13, "Albert J. Einstein", "Clemens Markets", "Vice President", PersonType.BUYER));
        basicPersonList.add(new Person(15, "Aiden G. Pierce", "4Chan Inc", "Hacker", PersonType.BUYER));
        basicPersonList.add(new Person(16, "Connor Kenway", "Aquila Inc", "Vice President", PersonType.COLLEAGUE));
        basicPersonList.add(new Person(19, "Abdul Maajid Zargar", "GraphicWeave", "Senior UI/UX Designer", PersonType.FRIEND));
        basicPersonList.add(new Person(17, "Salfi Farooq", "GraphicWeave", "Senior Mobile Developer", PersonType.FRIEND));
        basicPersonList.add(new Person(20, "Mudasir Ashraf", "GraphicWeave", "Senior Backend Developer", PersonType.FRIEND));
        basicPersonList.add(new Person(21, "Mustansir Zia", "GraphicWeave", "Software Intern", PersonType.FRIEND));
        Collections.sort(basicPersonList);
        filteredPersonList = basicPersonList;
    }

    public void onCategoryChoose(View v) {
        progressDialog.show();
        switch (v.getTag().toString()) {
            case "ALL":
                cardAdapter.updateData(basicPersonList);
                activateCategory("ALL");
                break;
            case "FRIENDS":
                cardAdapter.updateData(filterPersons(PersonType.FRIEND));
                activateCategory("FRIENDS");
                break;
            case "COLLEAGUES":
                cardAdapter.updateData(filterPersons(PersonType.COLLEAGUE));
                activateCategory("COLLEAGUES");
                break;
            case "BUYERS":
                cardAdapter.updateData(filterPersons(PersonType.BUYER));
                activateCategory("BUYERS");
                break;
        }
        resetAdapter();
        if (sortOrder) {
            sortArrow.setRotationX(0);
            sortOrder = !sortOrder;
        }
        progressDialog.dismiss();
    }

    private List<Person> filterPersons(PersonType withType) {
        List<Person> persons = new ArrayList<>();
        for (Person person: basicPersonList) {
            if (person.getType() == withType) {
                persons.add(person);
            }
        }
        filteredPersonList = persons;
        return persons;
    }

    public void onSortPersons(View v) {
        progressDialog.show();
        if (sortOrder) {
            Collections.sort(filteredPersonList);
            sortArrow.setRotationX(0);
        } else {
            Collections.sort(filteredPersonList, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            sortArrow.setRotationX(180f);
        }
        sortOrder = !sortOrder;
        cardAdapter.updateData(filteredPersonList);
        resetAdapter();
        progressDialog.dismiss();
    }

    private void resetAdapter() {
        cardStackView.setSelectPosition(-1);
        cardStackView.setScrollEnable(true);
//        cards.setShowInitAnimation(false);
//        cards.removeAdapter();
//        cards.setAdapter(cardsAdapter);
//        if (cards.isCardSelected()) {
//            cards.restoreCards();
//        }
    }

    private void activateCategory(String category) {
        for (Map header: headers) {
            if (header.get("id").equals(category)) {
                ((TextView) header.get("textView")).setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
                ((ImageView) header.get("imageView")).setImageResource((Integer) header.get("activeImage"));
            } else {
                ((TextView) header.get("textView")).setTextColor(ContextCompat.getColor(this, R.color.textLightGrey));
                ((ImageView) header.get("imageView")).setImageResource((Integer) header.get("deActiveImage"));
            }
        }
    }

    public void dummy(View v) {
        System.err.println("Yay!");
    }
}
