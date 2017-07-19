package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huzdi.projectv1.R;
import com.example.huzdi.projectv1.StartActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EcologistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EcologistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EcologistFragment extends Fragment {

    ProgressBar progressBar;
    StartActivity startActivity;
    Button button;
    Button button2;
    TextView textView;
    TextView textView2;

    ArrayList<String> texts= new ArrayList<>();
    ArrayList<String> textsSmall = new ArrayList<>();



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;


    Map<Object,Object> myMap;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "eco1";
    private static final String ARG_PARAM2 = "eco2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public EcologistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EcologistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EcologistFragment newInstance(String param1, String param2) {
        EcologistFragment fragment = new EcologistFragment();
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
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        startActivity = new StartActivity();
        readDataFromDatabaseBig();
        readDataFromDatabaseSmall();

        View rootView = inflater.inflate(R.layout.fragment_ecologist, container, false);


        textView2 = rootView.findViewById(R.id.textView2_eco);
        textView = rootView.findViewById(R.id.textView_eco);
        button =  rootView.findViewById(R.id.button_eco);
        button2 = rootView.findViewById(R.id.button2_eco);
        progressBar = rootView.findViewById(R.id.progressBar_eco);

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


        return rootView;
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
        for(int i = 0; i<texts.size();i++){
            textView.setText(texts.get(random.nextInt(texts.size())));
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
                    + OnFragmentInteractionListener.class + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            progressBar.getProgress();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        progressBar.getProgress();
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
        myRef =  database.getReference("Ecologist").child("Big");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    String clubkey = childSnaphot.getValue().toString();
//                    Log.d("TAG","Values is " + clubkey);
                    texts.add(clubkey);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }
    public void readDataFromDatabaseSmall(){
        myRef =  database.getReference("Ecologist").child("Small");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    String clubkey = childSnaphot.getValue().toString();
//                    Log.d("TAG","Values is " + clubkey);
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

