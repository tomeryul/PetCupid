package com.example.petcupid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

public class MenuActivity extends AppCompatActivity {

    Button register_animal;
    Button adopt_pet;
    Button register_pet;
    Button track_pets;
    String username;
    private AppCompatImageView menu_IMG_dog;
    private MaterialTextView menu_LBL_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        findView();
        initView();


        Glide
                .with(this)
                .load(R.drawable.ic_icon_dog1)
                .placeholder(R.drawable.ic_launcher_background)
                .into(menu_IMG_dog);

    }

    private void initView() {
        register_animal.setOnClickListener(v -> lostAnimalScreen());
        adopt_pet.setOnClickListener(v -> adoptPetScreen());
        register_pet.setOnClickListener(v -> registerPetScreen());
        track_pets.setOnClickListener(v -> myPetsScreen());
    }

    private void myPetsScreen() {
        Intent intent = new Intent(this, MyPetsActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void registerPetScreen() {
        Intent intent = new Intent(this, RegisterPetActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void adoptPetScreen() {
        Intent intent = new Intent(this, AdoptPetActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void lostAnimalScreen() {
        Intent intent = new Intent(this, RegisterLostAnimalActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void findView() {
        register_animal = findViewById(R.id.menu_BTN_register_animal);
        adopt_pet = findViewById(R.id.menu_BTN_adopt_pet);
        register_pet = findViewById(R.id.menu_BTN_register_pet);
        track_pets = findViewById(R.id.menu_BTN_track_pets);
        menu_IMG_dog = findViewById(R.id.menu_IMG_dog);
        menu_LBL_title = findViewById(R.id.menu_LBL_title);
    }

}