package com.eitan.petsi.data;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eitan.petsi.App;
import com.eitan.petsi.R;

import com.eitan.petsi.data.dummy.DummyContent;
import com.google.android.gms.plus.model.people.Person;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.IOException;

/**
 * A fragment representing a single PetItem detail screen.
 * This fragment is either contained in a {@link PetItemListActivity}
 * in two-pane mode (on tablets) or a {@link PetItemDetailActivity}
 * on handsets.
 */
public class PetItemDetailFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    private Pet pet;
    private TextView petDesc;
    private ImageView petImage;

    private ImageView callImageButton;
    private ImageView emailImageButton;

    private TextView petSize;
    private TextView petGender;
    private TextView petAge;

    private TextView petStory;
    private TextView ownerName;
    private TextView ownerAddress;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PetItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            App app = (App)getActivity().getApplication();
            pet = app.petListProvider.getPetByAdID(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_petitem_detail, container, false);

        callImageButton = (ImageView)rootView.findViewById(R.id.det_call_btn);
        callImageButton.setOnClickListener(this);
        emailImageButton = (ImageView)rootView.findViewById(R.id.det_mail_btn);
        emailImageButton.setOnClickListener(this);

        petImage = (ImageView) rootView.findViewById(R.id.det_petimage);

        petSize = (TextView) rootView.findViewById(R.id.det_size_txt);
        petGender = (TextView) rootView.findViewById(R.id.det_gender_txt);
        petAge = (TextView) rootView.findViewById(R.id.det_age_txt);

        petStory = (TextView) rootView.findViewById(R.id.det_pet_story);

        ownerAddress = (TextView) rootView.findViewById(R.id.det_owner_address);
        ownerName = (TextView) rootView.findViewById(R.id.det_owner_name);

        // Show the dummy content as text in a TextView.
        if (pet != null) {

            //petDesc.setText(pet.getPetDetails().getStory());

            getActivity().getActionBar().setTitle(pet.getPetDetails().getName());

            petImage.setBackgroundColor(Color.BLUE);

            if (petAge == null)
                Toast.makeText(getActivity().getApplicationContext(),"NULL",Toast.LENGTH_LONG).show();

            petAge.setText(Integer.toString(pet.getPetDetails().getAge()));
            petSize.setText(pet.getPetDetails().getSize());
            petGender.setText(pet.getPetDetails().getGender());

            petStory.setText(pet.getPetDetails().getStory());

            ownerName.setText(pet.getOwnerDetails().getName());
            ownerAddress.setText(pet.getOwnerDetails().getAddress());

            Picasso.with(getActivity().getApplicationContext()).load(pet.getPetDetails().getPhotoUrl())
                    .placeholder(R.drawable.ic_dog)
                    .error(R.drawable.ic_launcher)
                    .centerCrop().fit()
                    .into(petImage);
        }

        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case (R.id.det_call_btn):
                sendMail();
                break;
            case (R.id.det_mail_btn):
                callOwner();
                break;
        }

    }

    private void sendMail(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + pet.getOwnerDetails().getTel()));
        startActivity(callIntent);
    }

    private void callOwner(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{pet.getOwnerDetails().getEmail()});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Adoption ad " + pet.getAdData().getAdID() + ": " + pet.getPetDetails().getName());

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

    }
}
