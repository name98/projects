package com.flowerworld.fragments;


import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flowerworld.MainActivity;
import com.flowerworld.R;
import com.flowerworld.connections.FlowerFragmentHalper;
import com.flowerworld.items.FlowerItem;


public class Router {
    private FragmentManager fragmentManager;
    public Router(final FragmentManager fragmentManager) {
        this.fragmentManager=fragmentManager;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addFragment(String tag, String data){
        switch (tag){
            case "test":
                TestFragment testFragment = new TestFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,testFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
            case "flowerPage":
                FlowerFragment flowerFragment = new FlowerFragment(Integer.valueOf(data));
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,flowerFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
            case "shopPage":
                ShopFragment shopFragment = new ShopFragment(data);

                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,shopFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
            case "gridFragment":
                GridFragment gridFragment = GridFragment.newInstance(data);
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,gridFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
            case "aboutOrderFragment":
                AboutOrderFragment aboutOrderFragment = new AboutOrderFragment(data);
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,aboutOrderFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
                case "createCommentFragment":
                CreateCommentFragment createCommentFragment= new CreateCommentFragment(data);
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,createCommentFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }
    public void addFragment(String tag){
        switch (tag){
            case "mainFragment":
                MainFragment mainFragment = new MainFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,mainFragment,tag)
                        .commit();
                break;
            case "loginFragment":
                LoginFragment loginFragment= new LoginFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,loginFragment,tag)
                        .commit();
                break;

        }
    }
    public static void addCharterFragment (Context context, int id){
        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
        CharterFragment charterFragment = CharterFragment.newInstance(id);
        fragmentManager.beginTransaction()
                .add(R.id.activity_fragment_contaner,charterFragment,"charterFragment")
                .addToBackStack(null)
                .commit();
    }
    public void remove(String tag){
        if(isAdded(tag)){
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(tag))
                    .disallowAddToBackStack()
                    .commit();
            fragmentManager.popBackStack();
        }
    }
    private boolean isAdded(String tag){
        if(fragmentManager.findFragmentByTag(tag)==null)
            return false;
        else return true;

    }

    public void addFragment(String tag, boolean flag){
        switch (tag){
            case "ordersFragment":
                OrdersFragment ordersFragment = new OrdersFragment(flag);
                fragmentManager.beginTransaction()
                        .add(R.id.activity_fragment_contaner,ordersFragment,tag)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
    public void reload(String tag){
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        fragmentManager.beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }



}
