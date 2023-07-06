package com.example.petcupid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.petcupid.Logic.SignalGenerator;
import com.example.petcupid.halpers.AllPets;
import com.example.petcupid.halpers.Pet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class RegisterPetActivity extends AppCompatActivity {

    AllPets fromJsonPets;
    private FloatingActionButton RP_FAB_return;
    private AppCompatImageView RP_IMG_dog;
    private AppCompatEditText RP_ET_color;
    private AppCompatEditText RP_ET_name;
    private AppCompatEditText RP_ET_image;
    Button register_animal;
    String username;

    //////////////////////////////////////////////////

    String[] size = {"small", "medium", "large"};
    AutoCompleteTextView ACTV_size;
    private TextInputLayout TIL_size;
    String sizeChose;
    ArrayAdapter<String> adapterSize;


    String[] sex = {"male", "female"};
    AutoCompleteTextView ACTV_sex;
    private TextInputLayout TIL_sex;
    String sexChose;
    ArrayAdapter<String> adapterSex;

    String[] aggressive = {"yes", "no"};
    AutoCompleteTextView ACTV_aggressive;
    private TextInputLayout TIL_aggressive;
    String aggressiveChose;
    ArrayAdapter<String> adapterAggressive;

    //////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        findView();
        initView();
        myIntent();
        loadPetsFromDB();
        SignalGenerator.init(this);

        Glide
                .with(this)
                .load(R.drawable.ic_icon_dog3)
                .placeholder(R.drawable.ic_launcher_background)
                .into(RP_IMG_dog);

    }

    private void myIntent() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    private void initView() {
        register_animal.setOnClickListener(v -> checkAndRegister());
        RP_FAB_return.setOnClickListener(v -> backToMenu());
    }

    private void backToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void checkAndRegister() {
        if(sizeChose != null && sexChose != null && aggressiveChose != null){
            String theColor = RP_ET_color.getText().toString();
            String theName = RP_ET_name.getText().toString();
            String theImage = RP_ET_image.getText().toString();
            if(!theColor.equals("") && !theName.equals("")){
                loadPetsFromDB();
                Pet pet = new Pet()
                        .setAggressive(aggressiveChose)
                        .setPetColor(theColor)
                        .setTimeRegistered(System.currentTimeMillis())
                        .setOwnerName(username)
                        .setPetName(theName)
                        .setImagePet2(theImage)
                        .setSize(sizeChose)
                        .setType(sexChose);
                // register a new pet on the dataBase
                fromJsonPets.getPets().add(pet);
                addPets(fromJsonPets);
                SignalGenerator.getInstance().toast("Pet Registered!!", Toast.LENGTH_SHORT);
                backToMenu();
            }
        }
    }

    private void addPets(AllPets Pets) {
        // converting the Pets to a string
        String AllPetsJson = new Gson().toJson(Pets);
        // update the firebase with the string
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("all Pets");
        titleRef.setValue(AllPetsJson);
    }

    private void loadPetsFromDB() {
        // loading the AllPets from the firebase
        // it start by a String and we convert it to AllPets class
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("all Pets");
        titleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String PetsJson = snapshot.getValue(String.class); // a String is loaded
                fromJsonPets = new Gson().fromJson(PetsJson,AllPets.class); // converting to AllPets class
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Pass
            }
        });
    }

    private void findView() {
        RP_IMG_dog = findViewById(R.id.RP_IMG_dog);
        RP_ET_color = findViewById(R.id.RP_ET_color);
        RP_ET_name = findViewById(R.id.RP_ET_name);
        RP_ET_image = findViewById(R.id.RP_ET_image);
        RP_FAB_return = findViewById(R.id.RP_FAB_return);
        register_animal = findViewById(R.id.RP_BTN_register_animal);
        viewSize();
        viewSex();
        viewAggressive();
    }

    private void viewAggressive() {
        TIL_aggressive = findViewById(R.id.RP_TIL_aggressive);
        ACTV_aggressive = findViewById(R.id.RP_TXT_aggressive);
        adapterAggressive = new ArrayAdapter<String>(this,R.layout.dog_item,aggressive);
        ACTV_aggressive.setAdapter(adapterAggressive);
        ACTV_aggressive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aggressiveChose = parent.getItemAtPosition(position).toString();
                TIL_aggressive.setHint("aggressive: "+aggressiveChose);
            }
        });
    }

    private void viewSex() {
        TIL_sex = findViewById(R.id.RP_TIL_sex);
        ACTV_sex = findViewById(R.id.RP_TXT_sex);
        adapterSex = new ArrayAdapter<String>(this,R.layout.dog_item,sex);
        ACTV_sex.setAdapter(adapterSex);
        ACTV_sex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexChose = parent.getItemAtPosition(position).toString();
                TIL_sex.setHint("dog sex: "+sexChose);
            }
        });
    }

    private void viewSize() {
        TIL_size = findViewById(R.id.RP_TIL_size);
        ACTV_size = findViewById(R.id.RP_TXT_size);
        adapterSize = new ArrayAdapter<String>(this,R.layout.dog_item,size);
        ACTV_size.setAdapter(adapterSize);
        ACTV_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sizeChose = parent.getItemAtPosition(position).toString();
                TIL_size.setHint("dog size: "+sizeChose);
            }
        });
    }
}