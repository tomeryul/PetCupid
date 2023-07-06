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
import com.example.petcupid.halpers.Pet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

public class SingleMyPetActivity extends AppCompatActivity {

    Button take_pet;
    private FloatingActionButton SMP_FAB_Return;
    private AppCompatImageView SMP_IMG_Dog;
    private MaterialTextView SMP_LBL_Title;
    private MaterialTextView SMP_LBL_Name;
    private MaterialTextView SMP_LBL_Time;
    String StringPet;
    String username;
    Pet thePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_my_pet);

        myIntent();
        findView();
        initView();
        SignalGenerator.init(this);
    }

    private void initView() {
        SMP_LBL_Name.setText("Name: "+thePet.getPetName());
        SMP_LBL_Time.setText(daysIn(thePet));
        loadImage(thePet.getImagePet2(), SMP_IMG_Dog);
        take_pet.setOnClickListener(v -> takeThePet());
        SMP_FAB_Return.setOnClickListener(v -> returnToListDogs());

    }
    private String daysIn(Pet pet) {
        long currentTime = System.currentTimeMillis();
        long registeredTime = pet.getTimeRegistered();
        long milSecIn = currentTime-registeredTime;
        long secIn = milSecIn/1000;
        long minIn = secIn/60;
        long hourIn = minIn/60;
        long DaysIn = hourIn/24;

        return "time in:"+DaysIn+" Days and "+hourIn%24+" Hours";
    }
    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    private void returnToListDogs() {
        Intent intent = new Intent(this, MyPetsActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    private void takeThePet() {
        Intent intent = new Intent(this, MyPetsActivity.class);
        intent.putExtra("Pid", thePet.getPid());
        intent.putExtra("username",username);
        SignalGenerator.getInstance().toast("Pet Taken!!", Toast.LENGTH_SHORT);
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
        take_pet = findViewById(R.id.SMP_BTN_take_out);
        SMP_FAB_Return = findViewById(R.id.SMP_FAB_return);
        SMP_IMG_Dog = findViewById(R.id.SMP_IMG_dog);
        SMP_LBL_Title = findViewById(R.id.SMP_LBL_title);
        SMP_LBL_Name = findViewById(R.id.SMP_LBL_name);
        SMP_LBL_Time = findViewById(R.id.SMP_LBL_time);


    }}