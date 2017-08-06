package com.mz.cards

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.loopeer.cardstack.CardStackView
import com.loopeer.cardstack.UpDownStackAnimatorAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val basicPersonList = ArrayList<Person>()
    private var filteredPersonList: MutableList<Person>? = null
    private val headers = ArrayList<Map<String, Any>>()
    // private CardsAdapter cardsAdapter;
    // private CardStackLayout cards;
    private var cardStackView: CardStackView? = null
    private var cardAdapter: CardAdapter? = null

    private var progressDialog: ProgressDialog? = null
    private var sortArrow: ImageView? = null
    private var toolbar: Toolbar? = null
    private var categories: LinearLayout? = null
    private var stackLayout: RelativeLayout? = null
    private val mStackLayoutAnimationUp = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, -0.2f)
    private val mCategoriesAnimationUp = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, -0.2f)
    private val mCategoriesAnimationDown = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, -0.2f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f)

    private var sortOrder = false
    private var showUpperLayoutEnable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadColleagues()
        //        cardsAdapter = new CardsAdapter(this, R.layout.collegue_item, basicPersonList);
        //        cards = (CardStackLayout) findViewById(R.id.cards);
        //        cards.setAdapter(cardsAdapter);

        cardStackView = findViewById(R.id.stackview_main) as CardStackView
        cardAdapter = CardAdapter(this)
        cardAdapter!!.setData(filteredPersonList)
        cardStackView!!.setAdapter(cardAdapter)
        cardStackView!!.numBottomShow = 0
        cardStackView!!.itemExpendListener = CardStackView.ItemExpendListener { expand ->
            if (expand) {
                hideUpperLayout()
            } else {
                if (showUpperLayoutEnable) {
                    showUpperLayout()
                    cardStackView!!.requestLayout()
                } else {
                    showUpperLayoutEnable = true
                }
            }
        }
        cardStackView!!.setAnimationType(CardStackView.ALL_DOWN)
        cardStackView!!.setAnimatorAdapter(UpDownStackAnimatorAdapter(cardStackView))

        val allMap = HashMap<String, Any>()
        allMap.put("id", "ALL")
        allMap.put("textView", findViewById(R.id.allText))
        allMap.put("imageView", findViewById(R.id.allImage))
        allMap.put("deActiveImage", R.drawable.blue_ellipse)
        allMap.put("activeImage", R.drawable.blue_ellipse_active)

        val friendsMap = HashMap<String, Any>()
        friendsMap.put("id", "FRIENDS")
        friendsMap.put("textView", findViewById(R.id.friendsText))
        friendsMap.put("imageView", findViewById(R.id.friendsImage))
        friendsMap.put("deActiveImage", R.drawable.green_ellipse)
        friendsMap.put("activeImage", R.drawable.green_ellipse_active)

        val colleaguesMap = HashMap<String, Any>()
        colleaguesMap.put("id", "COLLEAGUES")
        colleaguesMap.put("textView", findViewById(R.id.colleaguesText))
        colleaguesMap.put("imageView", findViewById(R.id.colleaguesImage))
        colleaguesMap.put("deActiveImage", R.drawable.yellow_ellipse)
        colleaguesMap.put("activeImage", R.drawable.yellow_ellipse_active)

        val buyersMap = HashMap<String, Any>()
        buyersMap.put("id", "BUYERS")
        buyersMap.put("textView", findViewById(R.id.buyersText))
        buyersMap.put("imageView", findViewById(R.id.buyersImage))
        buyersMap.put("deActiveImage", R.drawable.pink_ellipse)
        buyersMap.put("activeImage", R.drawable.pink_ellipse_active)

        headers.add(allMap)
        headers.add(friendsMap)
        headers.add(colleaguesMap)
        headers.add(buyersMap)

        sortArrow = findViewById(R.id.arrow) as ImageView
        toolbar = findViewById(R.id.toolbar) as Toolbar
        categories = findViewById(R.id.categories) as LinearLayout
        stackLayout = findViewById(R.id.stackLayout) as RelativeLayout
        mStackLayoutAnimationUp.duration = 350
        mStackLayoutAnimationUp.isFillEnabled = true
        mStackLayoutAnimationUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                stackLayout!!.translationY = (-1 * (toolbar!!.measuredHeight + categories!!.measuredHeight)).toFloat()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        mCategoriesAnimationUp.duration = 350
        mCategoriesAnimationUp.fillAfter = true
        mCategoriesAnimationDown.duration = 350
        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Loading...")
    }

    private fun loadColleagues() {
        basicPersonList.add(Person(1, "Wallace J. Alexander", "Apple Inc", "Quality Assurance", PersonType.COLLEAGUE))
        basicPersonList.add(Person(2, "Timothy J. Foley", "Clemens Markets", "Vice President", PersonType.COLLEAGUE))
        basicPersonList.add(Person(4, "Jenna V. Rivera", "Quest Technology Service", "UI/UX Designer", PersonType.BUYER))
        basicPersonList.add(Person(5, "Johnny A. Ive", "Apple Inc", "Chief Designer", PersonType.BUYER))
        basicPersonList.add(Person(6, "John D. Doe", "Ericsson", "Chief Engineer", PersonType.COLLEAGUE))
        basicPersonList.add(Person(9, "Gregory K. Helms", "Block Distributors", "HR Lead", PersonType.BUYER))
        basicPersonList.add(Person(10, "Tim A. Cook", "Apple Inc", "CEO", PersonType.COLLEAGUE))
        basicPersonList.add(Person(12, "Alexander G. Bell", "Apple Inc", "Quality Assurance", PersonType.BUYER))
        //        basicPersonList.add(new Person(13, "Albert J. Einstein", "Clemens Markets", "Vice President", PersonType.BUYER));
        basicPersonList.add(Person(15, "Aiden G. Pierce", "4Chan Inc", "Hacker", PersonType.BUYER))
        basicPersonList.add(Person(16, "Connor Kenway", "Aquila Inc", "Vice President", PersonType.COLLEAGUE))
        basicPersonList.add(Person(19, "Abdul Maajid Zargar", "GraphicWeave", "Senior UI/UX Designer", PersonType.FRIEND))
        basicPersonList.add(Person(17, "Salfi Farooq", "GraphicWeave", "Senior Mobile Developer", PersonType.FRIEND))
        basicPersonList.add(Person(20, "Mudasir Ashraf", "GraphicWeave", "Senior Backend Developer", PersonType.FRIEND))
        basicPersonList.add(Person(21, "Mustansir Zia", "GraphicWeave", "Software Intern", PersonType.FRIEND))
        Collections.sort(basicPersonList)
        filteredPersonList = basicPersonList
    }

    fun onCategoryChoose(v: View) {
        progressDialog!!.show()
        when (v.tag.toString()) {
            "ALL" -> {
                filteredPersonList = ArrayList<Person>()
                filteredPersonList!!.addAll(basicPersonList)
                cardAdapter!!.updateData(filteredPersonList)
                activateCategory("ALL")
            }
            "FRIENDS" -> {
                cardAdapter!!.updateData(filterPersons(PersonType.FRIEND))
                activateCategory("FRIENDS")
            }
            "COLLEAGUES" -> {
                cardAdapter!!.updateData(filterPersons(PersonType.COLLEAGUE))
                activateCategory("COLLEAGUES")
            }
            "BUYERS" -> {
                cardAdapter!!.updateData(filterPersons(PersonType.BUYER))
                activateCategory("BUYERS")
            }
        }
        resetAdapter()
        if (sortOrder) {
            sortArrow!!.rotationX = 0f
            sortOrder = !sortOrder
        }
        progressDialog!!.dismiss()
    }

    private fun filterPersons(withType: PersonType): List<Person> {
        val persons = ArrayList<Person>()
        for (person in basicPersonList) {
            if (person.type == withType) {
                persons.add(person)
            }
        }
        filteredPersonList = persons
        return persons
    }

    fun onSortPersons(v: View) {
        progressDialog!!.show()
        if (sortOrder) {
            Collections.sort(filteredPersonList!!)
            sortArrow!!.rotationX = 0f
        } else {
            Collections.sort(filteredPersonList!!) { o1, o2 -> o2.name.compareTo(o1.name) }
            sortArrow!!.rotationX = 180f
        }
        sortOrder = !sortOrder
        cardAdapter!!.updateData(filteredPersonList)
        resetAdapter()
        progressDialog!!.dismiss()
    }

    private fun resetAdapter() {
        showUpperLayoutEnable = false
        cardStackView!!.selectPosition = -1
        cardStackView!!.viewScrollY = 0
        cardStackView!!.setScrollEnable(true)
        //        cards.setShowInitAnimation(false);
        //        cards.removeAdapter();
        //        cards.setAdapter(cardsAdapter);
        //        if (cards.isCardSelected()) {
        //            cards.restoreCards();
        //        }
    }

    private fun activateCategory(category: String) {
        for (header in headers) {
            if (header["id"] == category) {
                (header["textView"] as TextView).setTextColor(ContextCompat.getColor(this, R.color.darkGrey))
                (header["imageView"] as ImageView).setImageResource(header["activeImage"] as Int)
            } else {
                (header["textView"] as TextView).setTextColor(ContextCompat.getColor(this, R.color.textLightGrey))
                (header["imageView"] as ImageView).setImageResource(header["deActiveImage"] as Int)
            }
        }
    }

    private fun hideUpperLayout() {
        if (categories!!.translationY == 0.0f) {
            stackLayout!!.startAnimation(mStackLayoutAnimationUp)
            categories!!.startAnimation(mCategoriesAnimationUp)
            toolbar!!.startAnimation(mCategoriesAnimationUp)
        }
    }

    private fun showUpperLayout() {
        //        categories.setVisibility(View.VISIBLE);
        //        toolbar.setVisibility(View.VISIBLE);
        //        stackLayout.startAnimation(mStackLayoutAnimationDown);
        stackLayout!!.translationY = 0f
        //        stackLayout.startAnimation(mStackLayoutAnimationDown);
        //        cardStackView.startAnimation(mStackLayoutAnimationDown);
        categories!!.startAnimation(mCategoriesAnimationDown)
        toolbar!!.startAnimation(mCategoriesAnimationDown)
    }

    internal fun expandCards() {
        cardStackView!!.animate().scaleX(1.05f).scaleY(1.05f)
    }

    internal fun shrinkCards() {
        cardStackView!!.animate().scaleX(1.0f).scaleY(1.0f)
    }

    fun dummy(v: View) {
        System.err.println("Yay!")
    }
}
