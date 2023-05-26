package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TheMostMainActivity extends AppCompatActivity {
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_most_main);


        final LinearLayout HomeLayout = findViewById(R.id.HomeLayout);
        final LinearLayout NotificationLayout = findViewById(R.id.NotificationLayout);
        final LinearLayout AddLayout = findViewById(R.id.AddLayout);
        final LinearLayout ChatLayout = findViewById(R.id.ChatLayout);
        final LinearLayout ProfileLayout = findViewById(R.id.ProfileLayout);



        final ImageView HomeImage = findViewById(R.id.HomeImage);
        final ImageView NotificationImage = findViewById(R.id.NotificationImage);
        final ImageView AddImage = findViewById(R.id.AddImage);
        final ImageView ChatImage = findViewById(R.id.ChatImage);
        final ImageView ProfileImage = findViewById(R.id.ProfileImage);


        final TextView HomeText = findViewById(R.id.HomeText);
        final TextView NotificationText = findViewById(R.id.NotificationText);
        final TextView AddText = findViewById(R.id.AddText);
        final TextView ChatText = findViewById(R.id.ChatText);
        final TextView ProfileText = findViewById(R.id.ProfileText);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, HomeFragment.class, null).commit();
        HomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 1){
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, HomeFragment.class, null).commit();

                    NotificationText.setVisibility(View.GONE);
                    AddText.setVisibility(View.GONE);
                    ChatText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);


                    NotificationImage.setImageResource(R.drawable.notificationicon);
                    AddImage.setImageResource(R.drawable.add);
                    ChatImage.setImageResource(R.drawable.messages_question);
                    ProfileImage.setImageResource(R.drawable.user);


                    NotificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    AddLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ChatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    HomeText.setVisibility(View.VISIBLE);
                    HomeImage.setImageResource(R.drawable.homeicon_selected);
                    HomeLayout.setBackgroundResource(R.drawable.icons_round);



                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    HomeLayout.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }
            }
        });
        NotificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 2){
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, NotificationFragment.class, null).commit();

                    HomeText.setVisibility(View.GONE);
                    AddText.setVisibility(View.GONE);
                    ChatText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);


                    HomeImage.setImageResource(R.drawable.homeicon);
                    AddImage.setImageResource(R.drawable.add);
                    ChatImage.setImageResource(R.drawable.messages_question);
                    ProfileImage.setImageResource(R.drawable.user);



                    HomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    AddLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ChatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    NotificationText.setVisibility(View.VISIBLE);
                    NotificationImage.setImageResource(R.drawable.notificationicon_selected);
                    NotificationLayout.setBackgroundResource(R.drawable.icons_round);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    NotificationLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }

            }
        });
        AddLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 3){
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, AddFragment.class, null).commit();

                    HomeText.setVisibility(View.GONE);
                    NotificationText.setVisibility(View.GONE);
                    ChatText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);


                    HomeImage.setImageResource(R.drawable.homeicon);
                    NotificationImage.setImageResource(R.drawable.notificationicon);
                    ChatImage.setImageResource(R.drawable.messages_question);
                    ProfileImage.setImageResource(R.drawable.user );



                    HomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    NotificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ChatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    AddText.setVisibility(View.VISIBLE);
                    AddImage.setImageResource(R.drawable.add_selected);
                    AddLayout.setBackgroundResource(R.drawable.icons_round);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    AddLayout.startAnimation(scaleAnimation);

                    selectedTab = 3;
                }

            }
        });

        ChatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 4){
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, ChatFragment.class, null).commit();

                    HomeText.setVisibility(View.GONE);
                    NotificationText.setVisibility(View.GONE);
                    AddText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);


                    HomeImage.setImageResource(R.drawable.homeicon);
                    NotificationImage.setImageResource(R.drawable.notificationicon);
                    AddImage.setImageResource(R.drawable.add);
                    ProfileImage.setImageResource(R.drawable.user);



                    HomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    NotificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    AddLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    ChatText.setVisibility(View.VISIBLE);
                    ChatImage.setImageResource(R.drawable.messages_question_selected);
                    ChatLayout.setBackgroundResource(R.drawable.icons_round);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    AddLayout.startAnimation(scaleAnimation);

                    selectedTab = 4;
                }

            }
        });

        ProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 5){
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, ProfileFragment.class, null).commit();

                    HomeText.setVisibility(View.GONE);
                    NotificationText.setVisibility(View.GONE);
                    AddText.setVisibility(View.GONE);
                    ChatText.setVisibility(View.GONE);


                    HomeImage.setImageResource(R.drawable.homeicon);
                    NotificationImage.setImageResource(R.drawable.notificationicon);
                    AddImage.setImageResource(R.drawable.add);
                    ChatImage.setImageResource(R.drawable.messages_question);



                    HomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    NotificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    AddLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ChatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    ProfileText.setVisibility(View.VISIBLE);
                    ProfileImage.setImageResource(R.drawable.user_selected);
                    ProfileLayout.setBackgroundResource(R.drawable.icons_round);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    AddLayout.startAnimation(scaleAnimation);

                    selectedTab = 5;
                }

            }
        });
    }

}