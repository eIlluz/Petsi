package com.eitan.petsi.data;

import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eitan.petsi.App;
import com.eitan.petsi.R;

import com.eitan.petsi.data.dummy.DummyContent;
import com.google.android.gms.plus.model.people.Person;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single PetItem detail screen.
 * This fragment is either contained in a {@link PetItemListActivity}
 * in two-pane mode (on tablets) or a {@link PetItemDetailActivity}
 * on handsets.
 */
public class PetItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    private Pet pet;
    private TextView petDesc;
    private TextView petName;
    private ImageView petImage;

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

        petName = (TextView) rootView.findViewById(R.id.det_petName);
        petDesc = (TextView) rootView.findViewById(R.id.det_petdesc);
        petImage = (ImageView) rootView.findViewById(R.id.det_petimage);


        // Show the dummy content as text in a TextView.
        if (pet != null) {

            petDesc.setText(pet.getPetDetails().getDescription());
            petName.setText(pet.getPetDetails().getName());
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int width = displaymetrics.widthPixels;
            int height = displaymetrics.heightPixels;


            Picasso.with(getActivity().getApplicationContext()).load(pet.getPetDetails().getPhotoUrl())
//                    .resize(petImage.getWidth(),petImage.getHeight())
                    .placeholder(R.drawable.ic_dog)
                    .error(R.drawable.ic_launcher)
                    .fit().centerCrop()
                    .into(petImage);


        }

        return rootView;
    }
}
