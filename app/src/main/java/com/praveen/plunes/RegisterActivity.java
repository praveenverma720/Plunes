package com.praveen.plunes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PRAVEEN on 31/08/2017.
 */

public class RegisterActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {
    private static final int REQUEST_SIGNUP = 0;
    EditText firstname;
    EditText lastname;
    EditText phoneText;
   EditText emailText;
    EditText passwordText;
    EditText confirmpassword;
    Button signupButton;
    TextView login;
    RadioButton r1,r2;
    TextView dob;
    Spinner signupas;
    DatePickerDialog datePickerDialog;
    String[] signupitem = { "Student", "Professior", "Doctor", "Engineer", "Other",  };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        firstname = (EditText) findViewById(R.id.fn);
        lastname = (EditText) findViewById(R.id.ln);
        emailText = (EditText) findViewById(R.id.email);
        phoneText = (EditText) findViewById(R.id.phone);
        passwordText = (EditText) findViewById(R.id.pass);
        confirmpassword = (EditText) findViewById(R.id.cpass);
        signupButton = (Button) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.login);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        dob = (TextView) findViewById(R.id.dob);
        signupas = (Spinner) findViewById(R.id.signupas);
        signupas.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,signupitem);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        signupas.setAdapter(aa);
        final ImageButton dobimv = (ImageButton) findViewById(R.id.dobimageView);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();



            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        dobimv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
    }

    private void signup() {
        Log.d("Error", "Signin");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        // Start the Dashboard activity
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_SHORT).show();
    }

    private boolean validate() {
        boolean valid = true;
        // Retrieve the text entered from the EditText
        String fn = firstname.getText().toString();
        String ln = lastname.getText().toString();
        String useremail = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String passwordtxt = passwordText.getText().toString();
        String cpass = confirmpassword.getText().toString();
        String DOB = dob.getText().toString();

        //Validation
        if (fn.isEmpty() ) {
            firstname.setError("Enter your First Name");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (ln.isEmpty() ) {
            lastname.setError("Enter your Last Name");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (useremail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (passwordtxt.isEmpty() || passwordtxt.length() < 4 || passwordtxt.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (phone.isEmpty() || phone.length() < 10) {
            passwordText.setError("Minimum 10 Digit Number");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (cpass.isEmpty() || phone.length() < 10 || phone != cpass) {
            confirmpassword.setError("Password not match");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (DOB.isEmpty() ) {
            dob.setError("Select Your DOB");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        return valid;

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(),signupitem[position] ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}