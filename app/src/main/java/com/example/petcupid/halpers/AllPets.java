package com.example.petcupid.halpers;

import java.util.ArrayList;
import java.util.Objects;

public class AllPets {

    private ArrayList<Pet> Pets = new ArrayList<>();

    public AllPets() {

    }

    public AllPets(ArrayList<Pet> pets) {
        Pets = pets;
    }
    public ArrayList<Pet> RemovePetWithId(long Pid){
        for(int i = 0 ; i < Pets.size() ; i++)
            if(Pets.get(i).getPid() == Pid){
                Pets.remove(i);
            }
        return Pets;
    }

    public ArrayList<Pet> getPets() {
        return Pets;
    }

    public ArrayList<Pet> getAdoptPets() {
        ArrayList<Pet> adoptPets = new ArrayList<>();
        for(int i = 0 ; i < Pets.size() ; i++){
            if(Objects.equals(Pets.get(i).getOwnerName(), "")){
                adoptPets.add(Pets.get(i));
            }
        }
        return adoptPets;
    }

    public ArrayList<Pet> getOwnerPets(String OwnerName) {
        ArrayList<Pet> adoptPets = new ArrayList<>();
        for(int i = 0 ; i < Pets.size() ; i++){
            if(Objects.equals(Pets.get(i).getOwnerName(), OwnerName)){
                adoptPets.add(Pets.get(i));
            }
        }
        return adoptPets;
    }

    @Override
    public String toString() {
        return "AllPets{" +
                "Pets=" + Pets +
                '}';
    }
}
