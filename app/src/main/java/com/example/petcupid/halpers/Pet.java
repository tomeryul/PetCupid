package com.example.petcupid.halpers;

import com.example.petcupid.R;

public class Pet {

    public enum eSex {
        MALE,
        FEMALE
    }
    public enum eSize {
        SMALL,
        MEDIUM,
        LARGE
    }
    public enum eAggressive {
        YES,
        NO,
    }
    private eSex sex = eSex.MALE;
    private eSize size = eSize.MEDIUM;
    private eAggressive aggressive = eAggressive.NO;
    private long timeRegistered = 0;
    private String OwnerName = "";
    private String PetName = "";
    private String PetColor = "";

    private int imagePet = R.drawable.ic_icon_dog2;
    private String imagePet2 = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2015/08/28/15/rexfeatures_845638j.jpg";
    private final long Pid = System.currentTimeMillis();

    public Pet() {
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public Pet setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
        return this;
    }

    public String getPetName() {
        return PetName;
    }

    public Pet setPetName(String petName) {
        this.PetName = petName;
        return this;
    }

    public String getPetColor() {
        return PetColor;
    }

    public Pet setPetColor(String PetColor) {
        this.PetColor = PetColor;
        return this;
    }

    public long getTimeRegistered() {
        return timeRegistered;
    }

    public Pet setTimeRegistered(long timeRegistered) {
        this.timeRegistered = timeRegistered;
        return this;
    }

    public String getSex() {
        if(sex == eSex.MALE)
            return "Male";
        return "Female";
    }

    public Pet setType(String sex) {
        if(sex.equals("male"))
            this.sex = eSex.MALE;
        else if(sex.equals("female"))
            this.sex = eSex.FEMALE;

        return this;
    }

    public String getSize() {
        if(size == eSize.LARGE)
            return "Large";
        else if (size == eSize.MEDIUM) {
            return "Medium";
        }
        return "Small";

    }

    public Pet setSize(String size) {
        switch (size) {
            case "small":
                this.size = eSize.SMALL;
                break;
            case "medium":
                this.size = eSize.MEDIUM;
                break;
            case "large":
                this.size = eSize.LARGE;
                break;
        }
        return this;
    }

    public String getAggressive() {
        if(aggressive == eAggressive.YES)
            return "yes";
        return "no";
    }

    public Pet setAggressive(String aggressive) {
        if(aggressive.equals("yes"))
            this.aggressive = eAggressive.YES;
        else if(aggressive.equals("no"))
            this.aggressive = eAggressive.NO;

        return this;
    }

    public int getImagePet() {
        return imagePet;
    }

    public Pet setImagePet(int imagePet) {
        this.imagePet = imagePet;
        return this;
    }

    public String getImagePet2() {
        return imagePet2;
    }

    public Pet setImagePet2(String imagePet2) {
        if(!imagePet2.equals("")) {
            this.imagePet2 = imagePet2;
        }
        return this;
    }

    public long getPid() {
        return Pid;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "sex=" + sex +
                ", size=" + size +
                ", aggressive=" + aggressive +
                ", timeRegistered=" + timeRegistered +
                ", OwnerName='" + OwnerName + '\'' +
                ", PetName='" + PetName + '\'' +
                ", PetColor='" + PetColor + '\'' +
                ", Pid=" + Pid +
                '}';
    }
}