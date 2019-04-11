package com.example.neglect.Login_Register;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neglect.Database.insert_new_user;
import com.example.neglect.R;
public class RegisterActivity extends AppCompatActivity {
    EditText name,email,pass,cnfmpass;
    Button register;
    insert_new_user insert_new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.NAME);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.password);
        cnfmpass  = findViewById(R.id.cnfmpassword);
        register = findViewById(R.id.register);
        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));
        pass.setText(intent.getStringExtra("pass"));
        cnfmpass.setText(intent.getStringExtra("pass"));
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_new_user = new insert_new_user(RegisterActivity.this);
                if(pass.getText().toString().equals(cnfmpass.getText().toString()))
                {
                    if(name.getText().toString().equalsIgnoreCase("") || email.getText().toString().equalsIgnoreCase("") || pass.getText().toString().equalsIgnoreCase("") )
                    {
                        Snackbar.make(v, "Field Can't be Empty", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {
                        if(isValidEmail(email.getText().toString()))
                        {
                            Cursor c =  insert_new_user.getData();
                            boolean f=true;
                            while (c.moveToNext())
                            {
                                if(email.getText().toString().equalsIgnoreCase(c.getString(2)))
                                {
                                    f=false;
                                    break;
                                }
                            }
                            if(f)
                            {
                                boolean c1 = insert_new_user.insertdata(name.getText().toString(),email.getText().toString(),pass.getText().toString());
                                if (c1)
                                {
                                    Toast.makeText(RegisterActivity.this, "Registerd Successfully Please Login Once", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                    Snackbar.make(v, "Not Register Please Try Once Again", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "You are already Registerd Please Login", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else
                            Snackbar.make(v, "Enter Valid Email", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                    }
                }
                else
                    Snackbar.make(v, "Password Not Matched", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            }
        });

    }
    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches());
    }
}
