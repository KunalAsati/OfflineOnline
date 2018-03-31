package tecnodart.com.offlineonline;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RationCardManagement extends Fragment {
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    //  Button nearShop;
    FirebaseDatabase database ;
    DatabaseReference myRef ;

    int f=0;
    String key;
    Button submit;
    EditText userid;
    String usrString ;
    UserDetails us;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.rationcardmanagement , container , false) ;
        submit = v.findViewById(R.id.submit);
        userid = v.findViewById(R.id.userid);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        us = new UserDetails();
        key = myRef.getKey();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrString = userid.getText().toString();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot ) {
                        if(!dataSnapshot.exists()){
                            startActivity(new Intent(getActivity() , SignupActivity.class));

                            return;
                        }
                        for(DataSnapshot gr:dataSnapshot.getChildren()) {

                            us = gr.getValue(UserDetails.class);
                            if (us.getUserid().equals(usrString)) {
                             //   Toast.makeText(getActivity(), "You ", Toast.LENGTH_SHORT).show();
                                if(us.getAuth().equals("1")){
                                    //logged in
                                    startActivity(new Intent(getActivity(), RationCardActivity.class));
                                }
                                else{
                                    //not  logged in
                                    startActivity(new Intent(getActivity(), LoginActivity.class));

                                }
                            }

                        }

                            AlertDialog.Builder h = new AlertDialog.Builder(getContext());
                            h.setTitle("Please select")
                                    .setMessage("You don't have not an account in UBIQuotes or incorrect user id")
                                    .setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(getActivity(), SignupActivity.class));
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // h.

                                        }
                                    })
                                    .show();




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        getActivity().setTitle("RationCardManagement");
    }


}