package com.example.koki.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      //  check status
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null ) {
            startActivity(new Intent(getActivity(), ServiceActivity.class));
            getActivity().finish();
        }


        //    Register Controller
        registerController();

    }  // Main Method

    private void registerController() {
        TextView textView = getView().findViewById(R.id.textViewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     Replace Flagment

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layoutMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public MainFragment() {
        // Required empty public constructor
    }// Constructor เชื่อมต่อข้อมูล


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    } // Create View

} //main Class
