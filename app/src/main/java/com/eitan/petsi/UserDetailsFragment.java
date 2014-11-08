package com.eitan.petsi;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.eitan.petsi.com.eitan.petsi.services.GetUserDetailsTask;
import com.eitan.petsi.com.eitan.petsi.services.PostActionResponse;
import com.eitan.petsi.com.eitan.petsi.services.UpdateUserListener;
import com.eitan.petsi.com.eitan.petsi.services.UpdateUserTask;
import com.eitan.petsi.com.eitan.petsi.services.UserDetails;
import com.eitan.petsi.com.eitan.petsi.services.UserDetailsRespond;

import info.hoang8f.widget.FButton;
import retrofit.RetrofitError;


public class UserDetailsFragment extends Fragment implements View.OnClickListener, UpdateUserListener,UserDetailsRespond{

    private OnUserDetailsFragmentListener mListener;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;

    private String birthDate;

    private App app;

    private FButton saveButton;

    public UserDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App)getActivity().getApplication();
        GetUserDetailsTask getUserDetailsTask = new GetUserDetailsTask(app.getCurrentUser(),this);
        getUserDetailsTask.getUserDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View frag = inflater.inflate(R.layout.fragment_user_details, container, false);

        firstNameEditText = (EditText)frag.findViewById(R.id.user_details_first_name);
        lastNameEditText = (EditText)frag.findViewById(R.id.user_details_last_name);
        addressEditText = (EditText) frag.findViewById(R.id.user_details_address);
        phoneEditText = (EditText)frag.findViewById(R.id.user_details_phone_number);

        saveButton = (FButton)frag.findViewById(R.id.save_user_details);
        saveButton.setOnClickListener(this);

        return frag;
    }

    public void onSave() {

        UpdateUserTask updateUserTask = new UpdateUserTask(this,app.getCurrentUser(),
                                                           firstNameEditText.getText().toString(),
                                                           lastNameEditText.getText().toString(),
                                                           phoneEditText.getText().toString(),
                                                           addressEditText.getText().toString(),
                                                           birthDate);

        updateUserTask.updateUser();


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnUserDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_user_details){
            onSave();
        }
    }

    @Override
    public void onUpdateSuccess(PostActionResponse PostActionResponse) {

        Toast.makeText(getActivity(),getString(R.string.user_update_successfully),Toast.LENGTH_LONG).show();

        if (mListener != null) {
            mListener.onSave();
        }
    }

    @Override
    public void onUpdateFailed(PostActionResponse PostActionResponse) {

        Toast.makeText(getActivity(),getString(R.string.user_update_failed),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetDetailsSuccess(UserDetails userDetails) {

        firstNameEditText.setText(userDetails.getName());
        lastNameEditText.setText(userDetails.getLastName());
        phoneEditText.setText(userDetails.getPhoneNum());
        addressEditText.setText(userDetails.getAddress());

        birthDate = userDetails.getBirthDate();
    }

    @Override
    public void onGetDetailsFail() {

    }

    @Override
    public void onRestCallError(RetrofitError error) {
        Toast.makeText(getActivity(),getString(R.string.user_update_failed),Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnUserDetailsFragmentListener {
        public void onSave();
    }

}
