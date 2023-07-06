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

public class LostAnimalListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pet> pets = new ArrayList<>();


    public LostAnimalListAdapter(Context context, ArrayList<Pet> pets) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.adopt_dog_item, parent, false);
        }

        TextView tvSex = convertView.findViewById(R.id.pet_LBL_sex);
        TextView tvSize = convertView.findViewById(R.id.pet_LBL_size);
        ImageView ivdog = convertView.findViewById(R.id.pet_IMG_dog);
        Pet item = (Pet) getItem(position);

        tvSex.setText("Sex: "+item.getSex());
        tvSize.setText("Size: "+item.getSize());
        loadImage(item.getImagePet2(), ivdog); // image from url


        return convertView;
    }
}