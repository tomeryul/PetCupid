package com.example.petcupid.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.petcupid.Interfaces.CallBack_SendClick;
import com.example.petcupid.R;
import com.example.petcupid.halpers.AllPets;
import com.example.petcupid.halpers.Pet;
import com.example.petcupid.halpers.LostAnimalListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


public class AdoptPetListFragment extends Fragment {

    LostAnimalListAdapter adapter;
    private static AllPets Pets;
    private CallBack_SendClick callBack_sendClick;

    public static void AdoptPetWithId(Long Pid) {
        Pets = new AllPets(Pets.RemovePetWithId(Pid));
        addPets(Pets);
    }


    public void setCallBack_sendClick(CallBack_SendClick callBack_sendClick) {
        this.callBack_sendClick = callBack_sendClick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = view.findViewById(R.id.listView);
        loadPetsFromDB(listView);
        myOnClick(view,listView);


        return view;
    }

    private void loadPetsFromDB(ListView listView) {
        // loading the AllPets from the firebase
        // it start by a String and we convert it to AllPets class
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("all Pets");
        titleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String PetsJson = snapshot.getValue(String.class); // a String is loaded
                Pets = new Gson().fromJson(PetsJson,AllPets.class); // converting to AllPets class
                // i think that i need to call "setAdapter" in order than the itemList will change on DataChange
                Log.d("listPet", "allPets: "+Pets);
                adapter = new LostAnimalListAdapter(getContext(), Pets.getAdoptPets());
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Pass
            }
        });
    }

    private static void addPets(AllPets Pets) {
        // converting the AllPets to a string
        String vehiclesJson = new Gson().toJson(Pets);
        // update the firebase with the string
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference("all Pets");
        titleRef.setValue(vehiclesJson);
    }

    private void myOnClick(View view,ListView listView ) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pet pet = (Pet) parent.getItemAtPosition(position);
                callBack_sendClick.PetChosen(pet);
            }
        });
    }
    

}