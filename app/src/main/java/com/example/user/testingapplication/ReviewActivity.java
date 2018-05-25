package com.example.user.testingapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ReviewActivity extends AppCompatActivity {
    private EditText editTextSearchName;
    private TextView textViewName;
    private TextView textViewPassword;
    private TextView textViewEmail;
    private TextView textViewPhone;

    private FirebaseFirestore mFirebaseFirestore;
    private CollectionReference mCollectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mCollectionReference = mFirebaseFirestore.collection("Customer");

        editTextSearchName = (EditText)findViewById(R.id.editTextSearchName);
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewPassword = (TextView)findViewById(R.id.textViewPassword);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewPhone = (TextView)findViewById(R.id.textViewPhone);
    }

    public void fetchData(View view)
    {
        String name = editTextSearchName.getText().toString();
        mCollectionReference.whereEqualTo("name",name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    Customer customer = documentSnapshot.toObject(Customer.class);
                    textViewName.setText(customer.getName());
                    textViewPassword.setText(customer.getPassword());
                    textViewEmail.setText(customer.getEmail());
                    textViewPhone.setText(customer.getPhone());
                }
            }
        });
    }
}
