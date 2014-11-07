package com.eitan.petsi.data;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.eitan.petsi.App;
import com.eitan.petsi.R;
import com.eitan.petsi.aws.FileDownloadCallBack;
import com.eitan.petsi.com.eitan.petsi.services.*;
import com.eitan.petsi.com.eitan.petsi.services.UserDetails;
import com.eitan.petsi.views.FavImage;
import com.squareup.picasso.Picasso;
import java.io.File;

import retrofit.RetrofitError;

/**
 * A fragment representing a single PetItem detail screen.
 * This fragment is either contained in a {@link PetItemListActivity}
 * in two-pane mode (on tablets) or a {@link PetItemDetailActivity}
 * on handsets.
 */
public class PetItemDetailFragment extends Fragment implements View.OnClickListener, UserDetailsRespond {
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
    private TextView ownerPhone;

    private FavImage favButton;
    private TextView likesTex;
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
        favButton = (FavImage) rootView.findViewById(R.id.det_fav_btn);
        favButton.setOnClickListener(this);
        petImage = (ImageView) rootView.findViewById(R.id.det_petimage);

        petSize = (TextView) rootView.findViewById(R.id.det_size_txt);
        petGender = (TextView) rootView.findViewById(R.id.det_gender_txt);
        petAge = (TextView) rootView.findViewById(R.id.det_age_txt);

        petStory = (TextView) rootView.findViewById(R.id.det_pet_story);

        ownerAddress = (TextView) rootView.findViewById(R.id.det_owner_address);
        ownerName = (TextView) rootView.findViewById(R.id.det_owner_name);
        ownerPhone = (TextView) rootView.findViewById(R.id.det_owner_phone);

        likesTex = (TextView)rootView.findViewById(R.id.det_likes_txt);


        // Show the dummy content as text in a TextView.
        if (pet != null) {

            //petDesc.setText(pet.getPetDetails().getStory());

            getActivity().getActionBar().setTitle(pet.getPetDetails().getName());

            if (petAge == null)
                Toast.makeText(getActivity().getApplicationContext(),"NULL",Toast.LENGTH_LONG).show();

            petAge.setText(Integer.toString(pet.getPetDetails().getAge()));
            petSize.setText(pet.getPetDetails().getSize().toLowerCase());
            petGender.setText(pet.getPetDetails().getGender().toLowerCase());

            petStory.setText(pet.getPetDetails().getStory());

            likesTex.setText(String.valueOf(pet.getAdData().getNumOfLikes()));

            App app = (App)getActivity().getApplication();
//            app.getFileForS3Key(pet.getPetDetails().getPhotoUrl(),this);
            Picasso.with(getActivity().getApplicationContext()).load(pet.getPetDetails().getPhotoUrl())
                    .placeholder(R.drawable.ic_dog)
                    .error(R.drawable.ic_launcher)
                    .centerCrop().fit()
                    .into(petImage);

            //Get owners data
            GetUserDetailsTask getUserDetailsTask = new GetUserDetailsTask(pet.getOwnerDetails().getEmail(),this);
            getUserDetailsTask.getUserDetails();
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
            case (R.id.det_fav_btn):
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

    @Override
    public void onGetDetailsSuccess(UserDetails userDetails) {
        pet.setOwnerDetails(new OwnerDetails(userDetails.getName(),userDetails.getPhoneNumber(),userDetails.getAddress(),userDetails.getMailAddress()));

        System.out.println("@@@@@@@@@@@@ Owner data came back @@@@@@@@@@@@@@@@@");
        System.out.println("name: " + pet.getOwnerDetails().getName());
        ownerName.setText(pet.getOwnerDetails().getName());
        ownerAddress.setText(pet.getOwnerDetails().getAddress());
        ownerPhone.setText((pet.getOwnerDetails().getTel()));
    }

    @Override
    public void onGetDetailsFail() {}

    @Override
    public void onRestCallError(RetrofitError error) {
        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG);
    }

}
