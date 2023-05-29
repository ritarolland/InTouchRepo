package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.SplashScreen;


public class TheMostMainActivity extends AppCompatActivity {

    private LinearLayout HomeLayout, NotificationLayout, AddLayout, ChatLayout, ProfileLayout;
    private ImageView HomeImage, NotificationImage, AddImage, ChatImage, ProfileImage;
    private TextView HomeText, NotificationText, AddText, ChatText, ProfileText;

    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_most_main);
        init();
        setListeners();
        setInitialData();

    }
    private void init() {
        HomeLayout = findViewById(R.id.HomeLayout);
        NotificationLayout = findViewById(R.id.NotificationLayout);
        AddLayout = findViewById(R.id.AddLayout);
        ChatLayout = findViewById(R.id.ChatLayout);
        ProfileLayout = findViewById(R.id.ProfileLayout);

        HomeImage = findViewById(R.id.HomeImage);
        NotificationImage = findViewById(R.id.NotificationImage);
        AddImage = findViewById(R.id.AddImage);
        ChatImage = findViewById(R.id.ChatImage);
        ProfileImage = findViewById(R.id.ProfileImage);

        HomeText = findViewById(R.id.HomeText);
        NotificationText = findViewById(R.id.NotificationText);
        AddText = findViewById(R.id.AddText);
        ChatText = findViewById(R.id.ChatText);
        ProfileText = findViewById(R.id.ProfileText);
    }

    private void setListeners() {
        ChatLayout.setOnClickListener(v -> {
            if(selectedTab != 4) onClickMessenger();
        });
        HomeLayout.setOnClickListener(v -> onClickHome());
        NotificationLayout.setOnClickListener(v -> onClickNotification());
        ProfileLayout.setOnClickListener(v -> onClickProfile());
        AddLayout.setOnClickListener(v -> onClickAdd());

    }

    public void onClickMessenger() {

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

    public void onClickHome() {
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

    public void onClickNotification() {
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

    public void onClickProfile() {
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

    public void onClickAdd() {
        if (selectedTab != 3){
//                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.FragmentContainer, AddFragment.class, null).commit();

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

            Intent intent = new Intent(TheMostMainActivity.this, WelcomeAddScreen.class);
            startActivity(intent);


        }
    }

    private void setInitialData() {
        String fragmentTag = getIntent().getStringExtra("FRAGMENT_TAG");
        if (fragmentTag != null) {
            switch (fragmentTag) {
                case "Messenger":
                    onClickMessenger();
                    break;
                default:
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.FragmentContainer, HomeFragment.class, null).commit();
                    break;

            }
        } else {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.FragmentContainer, HomeFragment.class, null).commit();
        }

    }


}
