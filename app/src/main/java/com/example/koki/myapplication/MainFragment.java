package com.example.koki.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      //  check status
        checkStatus();


        //    Register Controller
        registerController();


        // Login Controller

        loginController();


    }  // Main Method

    private void loginController() {
        Button button =getView().findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEdiText =getView().findViewById(R.id.edtEmail);
                EditText passwordEditText =getView().findViewById(R.id.edtPassword);

                String emailString = emailEdiText.getText().toString().trim();
                String passworsString=passwordEditText.getText().toString().trim();
                final MyAlert myAlert=new MyAlert(getActivity());
                if (emailString.isEmpty() || passworsString.isEmpty()) {

                       myAlert.normalDialog(getString(R.string.title_have_space),
                               getString(R.string.messege_have_space));
                }
                else {

                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emailString,passworsString)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        moveToService();
                                    } else {
                                        myAlert.normalDialog("Cannot Authen ",
                                        task.getException().toString());
                                    }
                                }

                            });

                }
            }
        });
    }

    private void checkStatus() {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null ) {

            moveToService();
        }
    }

    private void moveToService() {
        startActivity(new Intent(getActivity(), ServiceActivity.class));
        getActivity().finish();
    }

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
