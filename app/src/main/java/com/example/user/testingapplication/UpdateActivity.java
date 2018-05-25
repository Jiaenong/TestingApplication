package com.example.user.testingapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextCustomer;
    private EditText editTextNewValue;
    private TextView textViewName;
    private TextView textViewPassword;
    private TextView textViewEmail;
    private TextView textViewPhone;
    private Spinner spinnerField;
    private Button buttonInsert;

    private FirebaseFirestore mFirebaseFirestore;
    private DocumentReference mDocumentReference;
    private CollectionReference mCollectionReference;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mCollectionReference = mFirebaseFirestore.collection("Customer");

        editTextCustomer = (EditText)findViewById(R.id.editTextCustomerName);
        editTextNewValue = (EditText)findViewById(R.id.editTextNewValue);
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewPassword = (TextView)findViewById(R.id.textViewPassword);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewPhone = (TextView)findViewById(R.id.textViewPhone);
        spinnerField = (Spinner)findViewById(R.id.spinnerField);
        buttonInsert = (Button)findViewById(R.id.buttonUpdate);

        ArrayAdapter<CharSequence> adpater = ArrayAdapter.createFromResource(this,R.array.field,android.R.layout.simple_spinner_dropdown_item);
        adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerField.setOnItemSelectedListener(this);
        spinnerField.setAdapter(adpater);

    }

    public void searchCustomer(View view)
    {
        String name = editTextCustomer.getText().toString();
        mCollectionReference.whereEqualTo("name", name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    Customer customer = documentSnapshot.toObject(Customer.class);
                    textViewName.setText(customer.getName());
                    textViewPassword.setText(customer.getPassword());
                    textViewEmail.setText(customer.getEmail());
                    textViewPhone.setText(customer.getPhone());
                    key = documentSnapshot.getId();
                    Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void selectCustomer(View view) {
        String input = editTextNewValue.getText().toString();
        String text = spinnerField.getSelectedItem().toString();
        mDocumentReference = mFirebaseFirestore.document("Customer/"+key);
        if(text.equals("Name"))
        {
            mDocumentReference.update("name",input);
        }
        else if(text.equals("Password"))
        {
            mDocumentReference.update("password",input);
        }
        else if(text.equals("Email"))
        {
            mDocumentReference.update("email",input);
        }
        else if(text.equals("Phone"))
        {
            mDocumentReference.update("phone",input);
        }
        mDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Customer customer = documentSnapshot.toObject(Customer.class);
                    textViewName.setText(customer.getName());
                    textViewPassword.setText(customer.getPassword());
                    textViewEmail.setText(customer.getEmail());
                    textViewPhone.setText(customer.getPhone());
                }
            });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
