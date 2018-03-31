package tecnodart.com.offlineonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpDetails extends AppCompatActivity {

    Intent i;
    UserDetails us;
    Spinner incomespin, fourSpin, landSpin;
    String cardType;
    String[] income = {"Select","upto 15,000" , "15,001 to 1,00,000" , "above 1,00,001" };
    String[] fourwheeler = {"Select","Yes","No"};
    String[] land = {"Select","less than 4","greater than four", "land less"};
    EditText name , mobno;
    RationQuota rq;
    //Firebase Database

    FirebaseDatabase database ;
    DatabaseReference myRef ;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);
        i = getIntent();
        us =(UserDetails) i.getSerializableExtra("userDetails");

        rq =new RationQuota(4,1,10,7);
        name = findViewById(R.id.nameuser);
        mobno = findViewById(R.id.mobno);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        signup = findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            us.setMobileno(mobno.getText().toString());
            us.setName(name.getText().toString());
            us.setAuth("1");
                us.setCardType(cardType);
                myRef.child(us.getUserid()).setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(SignUpDetails.this , "Profile is successfully created. \n Now you redirecting to Login page ."
                                    , Toast.LENGTH_LONG).show();

                            Intent i = new Intent(SignUpDetails.this , RationCardActivity.class);
                            i.putExtra("userDetails", us);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(SignUpDetails.this , "Something went wrong ." , Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
        incomespin = findViewById(R.id.income);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,income);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        incomespin.setAdapter(bb);
        incomespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                us.setIncome(income[i]);
                if(i==1){
                    cardType = "Yellow";

                }else if(i==2){
                    cardType = "Saffron";
                }else if(i==3){
                    cardType = "White";
                }else{
                    Toast.makeText(SignUpDetails.this, "Please Select", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fourSpin = findViewById(R.id.fourwheel);
        ArrayAdapter ff = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fourwheeler);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        fourSpin.setAdapter(ff);
        fourSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    cardType = "Saffron";

                }else if(i==2){
                    cardType = "Yellow";

                }else {
                    Toast.makeText(SignUpDetails.this, "Please Select", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        landSpin = findViewById(R.id.land);
        ArrayAdapter ll = new ArrayAdapter(this,android.R.layout.simple_spinner_item,land);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        landSpin.setAdapter(ll);
        landSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1|| i==3){
                    cardType = "Yellow";
                }else if(i==2){
                    cardType = "Saffron";
                }else {
                    Toast.makeText(SignUpDetails.this, "Please Select", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
