package com.example.intouch.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intouch.activities.AuthActivity;
import com.example.intouch.activities.MyEventsActivity;
import com.example.intouch.databinding.FragmentProfileBinding;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    FragmentProfileBinding fragmentProfileBinding;
    FirebaseDatabase inTouchDatabase;
    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        init();
        setListeners();
        setInitialData();
        return fragmentProfileBinding.getRoot();

    }

    private void setInitialData() {
        String currentUserId = mAuth.getCurrentUser().getUid();
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User user = task.getResult().child(currentUserId).getValue(User.class);
                fragmentProfileBinding.userName.setText(user.userName);
                fragmentProfileBinding.profileImage.setImageBitmap(getProfileImage(user.profileImage));
            }
        });
        fragmentProfileBinding.textViewemail.setText(Html.fromHtml("<a href = \"mailto:megatroxx@mail.ru\"> Send Feedback </a>"));
        fragmentProfileBinding.textViewemail.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    private void setListeners() {
        fragmentProfileBinding.buttonSignOut.setOnClickListener(v -> onClickSignOut());
        fragmentProfileBinding.myEventsButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyEventsActivity.class)));
    }

    private Bitmap getProfileImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public void onClickSignOut() {
        mAuth.signOut();
        startActivity(new Intent(getActivity(), AuthActivity.class));
    }


}