package com.example.petcupid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.petcupid.Fragments.AdoptPetListFragment;
import com.example.petcupid.Fragments.myPetsListFragment;
import com.example.petcupid.Interfaces.CallBack_SendClick;
import com.example.petcupid.halpers.Pet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class MyPetsActivity extends AppCompatActivity {

    private myPetsListFragment listFragment;
    private FloatingActionButton score_FAB_return;
    private long Pid;
    static String username;

    private CallBack_SendClick callBack_sendClick = new CallBack_SendClick() {
        @Override
        public void PetChosen(Pet pet) {
            Log.d("the Pet Chose", "PetChosen: "+pet);
            goToSingleMyPet(pet);
        }
    };

    public static String getUsername() {
        return username;
    }

    private void goToSingleMyPet(Pet pet) {
        Intent intent = new Intent(this, SingleMyPetActivity.class);
        String PetJson = new Gson().toJson(pet); // converting the pet to a string
        intent.putExtra("StringPet", PetJson);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);


        initFragments();
        beginTransactions();
        initView();
        myIntent();

    }
    private void myIntent() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Pid = intent.getLongExtra("Pid",0);
        if (Pid != 0) {
            myPetsListFragment.TakePetWithId(Pid);
        }
    }


    private void initView() {
        score_FAB_return.setOnClickListener(v -> backToMenu());
    }


    private void backToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.score_FRAME_list, listFragment).commit();
    }

    private void initFragments() {
        score_FAB_return = findViewById(R.id.score_FAB_return);
        listFragment = new myPetsListFragment();
        listFragment.setCallBack_sendClick(callBack_sendClick);

    }

}