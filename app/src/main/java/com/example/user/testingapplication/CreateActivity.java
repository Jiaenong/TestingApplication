package com.example.user.testingapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private Button btnInsert;

    private FirebaseFirestore mFirebaseFirestore;
    private CollectionReference mCollectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mCollectionReference = mFirebaseFirestore.collection("Customer");

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        btnInsert = (Button)findViewById(R.id.buttonInsert);
    }

    public void insertTesting(View view)
    {
        String name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();

        Customer customer = new Customer(name, password, email, phone);
        mCollectionReference.add(customer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Document Saved", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Document not Saved", Toast.LENGTH_LONG).show();
            }
        });
        editTextName.setText("");
        editTextPassword.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
    }
}
