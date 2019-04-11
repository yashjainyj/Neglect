package com.example.neglect.Login_Register;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neglect.Database.insert_new_user;
import com.example.neglect.Navigation_Items.NotificationNav_bar;
import com.example.neglect.R;
import com.example.neglect.Services.NotificationService;
import com.example.neglect.Utility.My_Utility;

public class Login_Activity extends AppCompatActivity {
    Toolbar toolbar;
    EditText email,password;
    Button login;
    Button register;
    insert_new_user insert_new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        toolbar = findViewById(R.id.toolbarlogin);
        setSupportActionBar(toolbar);
        getSupportActionBar();

        email = findViewById(R.id.Email);
        password  = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        if(!VerifyNotificationPermission())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Grant Permission").setMessage("Notification Access Permission").setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(
                            "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    startActivity(intent);
                }
            });
            builder.show();
        }
        if(My_Utility.Login_Check)
        {
            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            intent.putExtra("id",My_Utility.id);
            startActivity(intent);
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this,RegisterActivity.class);
                intent.putExtra("email",email.getText().toString());
                intent.putExtra("pass",password.getText().toString());
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_new_user = new insert_new_user(Login_Activity.this);
                Cursor res = insert_new_user.getData();
               // StringBuffer buffer = new StringBuffer();
                if(email.getText().toString().equalsIgnoreCase("") || password.getText().toString().equalsIgnoreCase("") )
                {
                    Snackbar.make(v, "Field Can't be Empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    if(isValidEmail(email.getText().toString()))
                    {
                        int f = 0;
                        String s = "";
                        while(res.moveToNext())
                        {
//                    buffer.append("EMAIL : " + res.getString(2)+"\n");
//                    buffer.append("PASSWORD : "+ res.getString(3)+ "\n\n\n" );
                            if(res.getString(2).equalsIgnoreCase(email.getText().toString()))
                            {
                                if (res.getString(3).equalsIgnoreCase(password.getText().toString()))
                                {
                                    s = res.getString(0);
                                    f=1;
                                    break;
                                }
                                else
                                {
                                    f=2;
                                }
                            }
                        }
                        if(f==1)
                        {
                            Toast.makeText(Login_Activity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                            intent.putExtra("id",s);
                            startActivity(intent);
                            finish();
                        }
                        else if(f==2)
                        {
                            Snackbar.make(v,"Incorrect Password",Snackbar.LENGTH_SHORT).show();
                        }
                        else
                            Snackbar.make(v, "Register", Snackbar.LENGTH_LONG)
                                    .setAction("Click here", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Login_Activity.this,RegisterActivity.class);
                                            intent.putExtra("email",email.getText().toString());
                                            intent.putExtra("pass",password.getText().toString());
                                            startActivity(intent);
                                        }
                                    }).show();
                    }
                    else
                        Snackbar.make(v, "Enter Valid Email", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                }
               // showMessage("Data",buffer.toString());
            }

        });
    }
    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches());
    }
    public Boolean VerifyNotificationPermission() {
        String theList = android.provider.Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        String[] theListList = theList.split(":");
        String me = (new ComponentName(this, NotificationService.class)).flattenToString();
        for ( String next : theListList ) {
            if ( me.equals(next) ) return true;
        }
        return false;
    }
}