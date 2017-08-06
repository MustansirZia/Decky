package com.mz.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.mutualmobile.cardstack.CardStackAdapter

import de.hdodenhof.circleimageview.CircleImageView

/**
 * Written with ❤! By M on 02/04/17.
 */


internal class CardsAdapter(private val context: Context, private val resource: Int, var persons: List<Person>?) : CardStackAdapter(context) {

    override fun createView(position: Int, container: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, container, false)
        val name = view.findViewById(R.id.colleagueName) as TextView
        name.text = persons!![position].name
        val company = view.findViewById(R.id.companyName) as TextView
        company.text = persons!![position].company
        val desig = view.findViewById(R.id.position) as TextView
        desig.text = persons!![position].position
        val circle = view.findViewById(R.id.identifier) as CircleImageView
        when (persons!![position].type) {
            PersonType.FRIEND -> circle.setImageResource(R.color.green)
            PersonType.COLLEAGUE -> circle.setImageResource(R.color.yellow)
            PersonType.BUYER -> circle.setImageResource(R.color.pink)
        }
        val profileIcon = view.findViewById(R.id.profileIcon) as ImageView
        val companyIcon = view.findViewById(R.id.companyIcon) as ImageView
        when (persons!![position].id % 6) {
            0 -> {
                profileIcon.setImageResource(R.drawable.a_123)
                companyIcon.setImageResource(R.drawable.dell)
            }
            1 -> {
                profileIcon.setImageResource(R.drawable.b_128)
                companyIcon.setImageResource(R.drawable.bg)
            }
            2 -> {
                profileIcon.setImageResource(R.drawable.c_127)
                companyIcon.setImageResource(R.drawable.star)
            }
            3 -> {
                profileIcon.setImageResource(R.drawable.e_126)
                companyIcon.setImageResource(R.drawable.twitter_new)
            }
            4 -> {
                profileIcon.setImageResource(R.drawable.f_125)
                companyIcon.setImageResource(R.drawable.lego)
            }
            5 -> {
                profileIcon.setImageResource(R.drawable.g_124)
                companyIcon.setImageResource(R.drawable.br)
            }
        }
        return view
    }

    override fun getCount(): Int {
        return persons!!.size
    }
}
