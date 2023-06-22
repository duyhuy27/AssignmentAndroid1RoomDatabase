package huyndph30375.fpoly.huyndph30375_assignment.models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.DetailsStaffActivity;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.StaffActivity;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.UpdateInformationActivity;

@Entity(tableName = "staff")
public class Staff implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int idStaff;
    private String maStaff;
    private String nameStaff;
    private String departmentStaff;
    private String emailStaff;
    private String phoneStaff;
    private String birthDateStaff;
    private String bank;
    private String address;
    private String numberBank;
    private int imageStaff;

    public Staff() {
    }

    public Staff(String maStaff, String nameStaff, String departmentStaff, int imageStaff) {
        this.maStaff = maStaff;
        this.nameStaff = nameStaff;
        this.departmentStaff = departmentStaff;
        this.imageStaff = imageStaff;
    }

    public Staff(String maStaff, String nameStaff, String departmentStaff, String emailStaff, String phoneStaff, String birthDateStaff, String bank, String address, String numberBank, int imageStaff) {
        this.maStaff = maStaff;
        this.nameStaff = nameStaff;
        this.departmentStaff = departmentStaff;
        this.emailStaff = emailStaff;
        this.phoneStaff = phoneStaff;
        this.birthDateStaff = birthDateStaff;
        this.bank = bank;
        this.address = address;
        this.numberBank = numberBank;
        this.imageStaff = imageStaff;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void goToDetailsStaff(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GlobalFunction.KEY_WORD_DETAILS, this);
        GlobalFunction.startActivity(view.getContext(), DetailsStaffActivity.class, bundle);
    }

    public void goToDetailsStafftoUpdate(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GlobalFunction.KEY_WORD_DETAILS, this);
        GlobalFunction.startActivity(view.getContext(), UpdateInformationActivity.class, bundle);
    }

    public void backStaff(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GlobalFunction.KEY_WORD_DETAILS, this);
        GlobalFunction.startActivity(view.getContext(), StaffActivity.class, bundle);
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getMaStaff() {
        return maStaff;
    }

    public void setMaStaff(String maStaff) {
        this.maStaff = maStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getDepartmentStaff() {
        return departmentStaff;
    }

    public void setDepartmentStaff(String departmentStaff) {
        this.departmentStaff = departmentStaff;
    }

    public String getEmailStaff() {
        return emailStaff;
    }

    public void setEmailStaff(String emailStaff) {
        this.emailStaff = emailStaff;
    }

    public String getPhoneStaff() {
        return phoneStaff;
    }

    public void setPhoneStaff(String phoneStaff) {
        this.phoneStaff = phoneStaff;
    }

    public String getBirthDateStaff() {
        return birthDateStaff;
    }

    public void setBirthDateStaff(String birthDateStaff) {
        this.birthDateStaff = birthDateStaff;
    }


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberBank() {
        return numberBank;
    }

    public void setNumberBank(String numberBank) {
        this.numberBank = numberBank;
    }

    public int getImageStaff() {
        return imageStaff;
    }

    public void setImageStaff(int imageStaff) {
        this.imageStaff = imageStaff;
    }

}
