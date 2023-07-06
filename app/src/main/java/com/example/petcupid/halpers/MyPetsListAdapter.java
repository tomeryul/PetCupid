package com.example.petcupid.halpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.petcupid.R;

import java.util.ArrayList;

public class MyPetsListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pet> pets = new ArrayList<>();


    public MyPetsListAdapter(Context context, ArrayList<Pet> pets) {
        this.context = context;
        this.pets = pets;
    }

    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this.context)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public int getCount() {
        return pets.size();
    }

    @Override
    public Object getItem(int position) {
        return pets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_pet_item, parent, false);
        }

        TextView tvname = convertView.findViewById(R.id.pet_LBL_name);
        TextView tvtime = convertView.findViewById(R.id.pet_LBL_time);
        ImageView ivdog = convertView.findViewById(R.id.pet_IMG_dog);
        Pet item = (Pet) getItem(position);

        tvname.setText("name: "+item.getPetName());
        tvtime.setText(daysIn(item));
        loadImage(item.getImagePet2(), ivdog); // image from url

        return convertView;
    }

    private String daysIn(Pet pet) {
        long currentTime = System.currentTimeMillis();
        long registeredTime = pet.getTimeRegistered();
        long milSecIn = currentTime-registeredTime;
        long secIn = milSecIn/1000;
        long minIn = secIn/60;
        long hourIn = minIn/60;
        long DaysIn = hourIn/24;

        return "time in: "+DaysIn+" Days";
    }

}
