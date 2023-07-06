package com.example.petcupid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.petcupid.Logic.SignalGenerator;
import com.example.petcupid.halpers.AllPets;
import com.example.petcupid.halpers.Pet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

public class SingleAdoptPetActivity extends AppCompatActivity {

    Button adopt_pet;
    private FloatingActionButton SAP_FAB_Return;
    private AppCompatImageView SAP_IMG_Dog;
    private MaterialTextView SAP_LBL_Title;
    private MaterialTextView SAP_LBL_Sex;
    private MaterialTextView SAP_LBL_Size;
    private MaterialTextView SAP_LBL_Aggressive;
    private MaterialTextView SAP_LBL_Color;
    String StringPet;
    String username;
    Pet thePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_adopt_pet);

        myIntent();
        findView();
        initView();
        SignalGenerator.init(this);
    }

    private void initView() {
        SAP_LBL_Sex.setText("Sex: "+thePet.getSex());
        SAP_LBL_Size.setText("Size: "+thePet.getSize());
        SAP_LBL_Color.setText("Color: "+thePet.getPetColor());
        SAP_LBL_Aggressive.setText("Aggressive: "+thePet.getAggressive());
        loadImage(thePet.getImagePet2(),SAP_IMG_Dog);
        adopt_pet.setOnClickListener(v -> adoptThePet());
        SAP_FAB_Return.setOnClickListener(v -> returnToListDogs());

    }
    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    private void returnToListDogs() {
        Intent intent = new Intent(this, AdoptPetActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    private void adoptThePet() {
        Intent intent = new Intent(this, AdoptPetActivity.class);
        intent.putExtra("Pid", thePet.getPid());
        intent.putExtra("username",username);
        SignalGenerator.getInstance().toast("Pet Adopted!!", Toast.LENGTH_SHORT);
        startActivity(intent);
        finish();
    }

    private void myIntent() {
        Intent intent = getIntent();
        StringPet = intent.getStringExtra("StringPet");
        username = intent.getStringExtra("username");
        thePet = new Gson().fromJson(StringPet, Pet.class);
    }

    private void findView() {
        adopt_pet = findViewById(R.id.SAP_BTN_adopt_pet);
        SAP_FAB_Return = findViewById(R.id.SAP_FAB_return);
        SAP_IMG_Dog= findViewById(R.id.SAP_IMG_dog);
        SAP_LBL_Title = findViewById(R.id.SAP_LBL_title);
        SAP_LBL_Sex = findViewById(R.id.SAP_LBL_sex);
        SAP_LBL_Size= findViewById(R.id.SAP_LBL_size);
        SAP_LBL_Aggressive = findViewById(R.id.SAP_LBL_aggressive);
        SAP_LBL_Color = findViewById(R.id.SAP_LBL_color);

    }
}