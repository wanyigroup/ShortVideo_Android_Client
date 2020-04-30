package com.example.contract;

import android.Manifest;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ContactFacade {

    public static void getContactList(FragmentActivity activity, Consumer<ArrayList<MyContacts>> consumer) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.READ_CONTACTS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    Log.e("ContactFacade",aBoolean.toString());
                    if(aBoolean) {
                        ArrayList<MyContacts> myContacts = ContactUtils.getAllContacts(activity);
                       consumer.accept(myContacts);
                    }
                });
    }




}
