package tecnodart.com.offlineonline;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private EditText signUpEmail, userid;
    private EditText passwordR ;
    private ProgressBar progressBar;
    ProgressDialog dialog;
    private Button  sign;
    UserDetails us;

    //Firebase Database

    FirebaseDatabase database ;
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpEmail= (EditText) findViewById(R.id.usernames);
        userid= (EditText) findViewById(R.id.name22);
        passwordR = (EditText) findViewById(R.id.passwords);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        sign = (Button) findViewById(R.id.sign);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        sign.setEnabled(false);





        passwordR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sign.setBackgroundColor(Color.parseColor("#FF9800"));
                sign.setEnabled(true);

            }
        });

    }



    public void goToMains(View view) {
        String email = signUpEmail.getText().toString().trim();
        String password = passwordR.getText().toString().trim();
        final String useridstr = userid.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(useridstr)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        us = new UserDetails();

        us.setUserid(useridstr);
        us.setEmailid(email);
        us.setPassword(password);
       // String key =myRef.push().getKey();

        myRef.child(useridstr).setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
                    Toast.makeText(SignupActivity.this , "Profile is successfully created. \n Now you redirecting to Login page ."
                            , Toast.LENGTH_LONG).show();

                    Intent i = new Intent(SignupActivity.this , SignUpDetails.class);
                    i.putExtra("userDetails", us);
                    startActivity(i);
                }
                else{
                    Toast.makeText(SignupActivity.this , "Something went wrong ." , Toast.LENGTH_LONG).show();

                }
            }
        });
/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot ) {
                if(!dataSnapshot.exists()){
                    startActivity(new Intent(SignupActivity.this , SignupActivity.class));

                    return;
                }
                for(DataSnapshot gr:dataSnapshot.getChildren()) {

                    us = gr.getValue(UserDetails.class);
                    if (us != null && us.getUserid().equals(usrString)) {
                        if(us.getAuth().equals("1")){
                            //logged in
                            startActivity(new Intent(SignupActivity.this, RationCardActivity.class));
                        }
                        else{
                            //not  logged in
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                        }
                    }
                    else {



                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void goToLogin (View view){
        Intent l = new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(l);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}