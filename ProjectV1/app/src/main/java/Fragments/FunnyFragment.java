package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huzdi.projectv1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FunnyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FunnyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FunnyFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button button;
    Button button2;
    ProgressBar progressBar;
    TextView textView;
    TextView textView2;

    ArrayList<String> textsBig = new ArrayList<>();
    ArrayList<String> textsSmall = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FunnyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FunnyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FunnyFragment newInstance(String param1, String param2) {
        FunnyFragment fragment = new FunnyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        readDataFromDatabaseBig();
        readDataFromDatabaseSmall();
        View rowView = inflater.inflate(R.layout.fragment_funny, container, false);
        button = rowView.findViewById(R.id.button_fun);
        button2 = rowView.findViewById(R.id.button2_fun);
        progressBar = rowView.findViewById(R.id.progressBar_fun);
        textView = rowView.findViewById(R.id.textView_fun);
        textView2 = rowView.findViewById(R.id.textView2_fun);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress() + 10);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(progressBar.getProgress() + 20);
            }
        });


        return rowView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Random random = new Random();
        for(int i = 0; i<textsBig.size();i++){
            textView.setText(textsBig.get(random.nextInt(textsBig.size())));
        }
        for(int i = 0; i<textsSmall.size();i++){
            textView2.setText(textsSmall.get(random.nextInt(textsSmall.size())));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void readDataFromDatabaseBig(){
        myRef =  database.getReference("Funny").child("Big");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    String clubkey = childSnaphot.getValue().toString();
                    Log.d("TAG","Values is " + clubkey);
                    textsBig.add(clubkey);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }
    public void readDataFromDatabaseSmall(){
        myRef =  database.getReference("Funny").child("Small");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    String clubkey = childSnaphot.getValue().toString();
                    Log.d("TAG","Values is " + clubkey);
                    textsSmall.add(clubkey);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
