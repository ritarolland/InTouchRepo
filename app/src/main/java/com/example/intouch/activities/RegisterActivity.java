package com.example.intouch.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityRegisterBinding;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding activityRegisterBinding;
    private FirebaseDatabase inTouchDataBase;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private String encodedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);
        init();
        setListeners();
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            activityRegisterBinding.profileImage.setImageBitmap(bitmap);
                            activityRegisterBinding.textAddImage.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

    );

    private void setListeners() {
        activityRegisterBinding.enter.setOnClickListener(v -> onClickSignUp());
        activityRegisterBinding.imageFrame.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(this, TheMostMainActivity.class));
        }
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        inTouchDataBase = FirebaseDatabase.getInstance();
        users = inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS);
    }

    public void onClickSignInNow(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void onClickSignUp() {
        String email, password, name;
        email = activityRegisterBinding.emailEdittext.getText().toString();
        password = activityRegisterBinding.passwordEdittext.getText().toString();
        name = activityRegisterBinding.nameEdittext.getText().toString();
        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(name)) {

            mAuth.createUserWithEmailAndPassword(activityRegisterBinding.emailEdittext.getText().toString(),
                            activityRegisterBinding.passwordEdittext.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if(currentUser != null) {
                                User newUser = new User(currentUser.getUid(), email, password, name);
                                newUser.profileImage = encodedImage;
                                users.child(currentUser.getUid()).setValue(newUser);
                                startActivity(new Intent(getApplicationContext(), TheMostMainActivity.class));
                            }
                            Toast.makeText(getApplicationContext(), "Signed up successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}