package com.mz.cards;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.loopeer.cardstack.CardStackView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardAdapter extends com.loopeer.cardstack.StackAdapter<Person> {

    private Context context;
    private AnimationSet mTextExpandAnimation = new AnimationSet(false);
    private AnimationSet mTextCollapseAnimation = new AnimationSet(false);
    private ScaleAnimation mProfileIconExpandAnimation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f);
    private ScaleAnimation mProfileIconCollapseAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f);

    public CardAdapter(Context context) {
        super(context);
        this.context = context;
        initializeTextAnimations();
        initializeIconAnimations();
    }

    private void initializeTextAnimations() {
        mTextExpandAnimation.addAnimation(new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.125f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f));
        mTextExpandAnimation.addAnimation(new ScaleAnimation(1.0f, 1.12f, 1.0f, 1.12f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f));
        mTextExpandAnimation.setInterpolator(new LinearInterpolator());
        mTextExpandAnimation.setFillAfter(true);
        mTextExpandAnimation.setDuration(400);
        mTextCollapseAnimation.addAnimation(new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.125f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f));
        mTextCollapseAnimation.addAnimation(new ScaleAnimation(1.12f, 1.0f, 1.12f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f));
        mTextCollapseAnimation.setFillAfter(true);
        mTextCollapseAnimation.setDuration(300);
        mTextCollapseAnimation.setInterpolator(new LinearInterpolator());
    }

    private void initializeIconAnimations() {
        mProfileIconExpandAnimation.setDuration(400);
        mProfileIconExpandAnimation.setFillAfter(true);
        mProfileIconCollapseAnimation.setDuration(300);
        mProfileIconCollapseAnimation.setFillAfter(true);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.collegue_item, parent, false);
        return new ColorItemViewHolder(view);
    }

    @Override
    public void bindView(Person data, int position, CardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.collegue_item;
    }

    class ColorItemViewHolder extends CardStackView.ViewHolder {

        public LinearLayout innerLayout;
        public FlexboxLayout profileIconContainer;
        public CircleImageView profileIcon;
        public CircleImageView identifier;
        public CircleImageView companyIcon;
        public TextView colleagueName;
        public TextView companyName;
        public TextView position;
        private boolean firstTime = true;

        public ColorItemViewHolder(View view) {
            super(view);
            innerLayout = (LinearLayout) view.findViewById(R.id.inner);
            profileIconContainer = (FlexboxLayout) view.findViewById(R.id.profileIconContainer);
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
            if (b && !firstTime) {
                colleagueName.startAnimation(mTextExpandAnimation);
                companyName.startAnimation(mTextExpandAnimation);
                profileIconContainer.startAnimation(mProfileIconExpandAnimation);
            } else if (!firstTime) {
                colleagueName.startAnimation(mTextCollapseAnimation);
                companyName.startAnimation(mTextCollapseAnimation);
                profileIconContainer.startAnimation(mProfileIconCollapseAnimation);
            } else {
                firstTime = false;
            }
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