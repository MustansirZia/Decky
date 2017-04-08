package com.mz.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardAdapter extends com.loopeer.cardstack.StackAdapter<Person> {

    private Context context;

    public CardAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void bindView(Person data, int position, CardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
        }
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.collegue_item, parent, false);
        return new ColorItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.collegue_item;
    }

    class ColorItemViewHolder extends CardStackView.ViewHolder {

        public LinearLayout innerLayout;
        public CircleImageView profileIcon;
        public CircleImageView identifier;
        public CircleImageView companyIcon;
        public TextView colleagueName;
        public TextView companyName;
        public TextView position;

        public ColorItemViewHolder(View view) {
            super(view);
            innerLayout = (LinearLayout) view.findViewById(R.id.inner);
            profileIcon =  (CircleImageView) view.findViewById(R.id.profileIcon);
            colleagueName = (TextView) view.findViewById(R.id.colleagueName);
            companyName = (TextView) view.findViewById(R.id.companyName);
            identifier = (CircleImageView) view.findViewById(R.id.identifier);
            companyIcon = (CircleImageView) view.findViewById(R.id.companyIcon);
            position = (TextView) view.findViewById(R.id.position);
        }

        @Override
        public void onItemExpand(boolean b) {
            innerLayout.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Person data, int position) {
            colleagueName.setText(data.getName());
            companyName.setText(data.getCompany());
            this.position.setText(data.getPosition());
            switch (data.getType()) {
                case FRIEND:
                    identifier.setImageResource(R.color.green);
                    break;
                case COLLEAGUE:
                    identifier.setImageResource(R.color.yellow);
                    break;
                case BUYER:
                    identifier.setImageResource(R.color.pink);
                    break;
            }
            switch (data.getId() % 6) {
                case 0:
                    profileIcon.setImageResource(R.drawable.a_123);
                    companyIcon.setImageResource(R.drawable.dell);
                    break;
                case 1:
                    profileIcon.setImageResource(R.drawable.b_128);
                    companyIcon.setImageResource(R.drawable.bg);
                    break;
                case 2:
                    profileIcon.setImageResource(R.drawable.c_127);
                    companyIcon.setImageResource(R.drawable.star);
                    break;
                case 3:
                    profileIcon.setImageResource(R.drawable.e_126);
                    companyIcon.setImageResource(R.drawable.twitter_new);
                    break;
                case 4:
                    profileIcon.setImageResource(R.drawable.f_125);
                    companyIcon.setImageResource(R.drawable.lego);
                    break;
                case 5:
                    profileIcon.setImageResource(R.drawable.g_124);
                    companyIcon.setImageResource(R.drawable.br);
                    break;
            }

        }
    }
}