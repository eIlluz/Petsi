package com.eitan.petsi.data;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;
import com.eitan.petsi.App;
import com.eitan.petsi.R;
import com.eitan.petsi.com.eitan.petsi.services.GetAdsRespond;
import com.eitan.petsi.com.eitan.petsi.services.GetAdsTask;
import java.util.ArrayList;
import java.util.List;
import retrofit.RetrofitError;

/**
 * An activity representing a list of Pet Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PetItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PetItemListFragment} and the item details
 * (if present) is a {@link PetItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link PetItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PetItemListActivity extends Activity
        implements PetItemListFragment.Callbacks, GetAdsRespond {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ArrayList<Pet> mPetList;
    private PetItemListFragment mPetListFrag;

    private FilterData filterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petitem_list);

        mPetListFrag = ((PetItemListFragment) getFragmentManager()
                .findFragmentById(R.id.petitem_list));

        if (findViewById(R.id.petitem_detail_container) != null) {

            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;


            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            mPetListFrag.setActivateOnItemClick(true);

        }

        if (filterData == null) {
            if (savedInstanceState == null) {
                Bundle extras = getIntent().getExtras();

                if (extras != null) {

                    filterData = new FilterData(
                                                extras.getString(App.GENDER),
                                                extras.getString(App.ANIMAL),
                                                extras.getString(App.SIZE),
                                                extras.getInt(App.FROM_AGE),
                                                extras.getInt(App.TO_AGE));
                }else{
                    filterData = new FilterData();
                }

            }
            else {

                filterData = new FilterData(savedInstanceState.getString(App.GENDER),
                                            savedInstanceState.getString(App.ANIMAL),
                                            savedInstanceState.getString(App.SIZE),
                                            savedInstanceState.getInt(App.FROM_AGE),
                                            savedInstanceState.getInt(App.TO_AGE));

            }
        }

        GetAdsTask getAdsTask = new GetAdsTask(this,filterData.fromAge,filterData.toAge,filterData.animal,filterData.gender,filterData.size,null);
        getAdsTask.getAds();

//        GetPetsTask taskPetList = new GetPetsTask(filterData.age, filterData.gender, filterData.size, filterData.animal);
//        taskPetList.execute((Void) null);

        //petListFrag.setFilters(extras.getString(App.AGE),extras.getString(App.ANIMAL),extras.getString(App.SIZE),extras.getString(App.GENDER));
    }

    /**
     * Callback method from {@link PetItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PetItemDetailFragment.ARG_ITEM_ID, id);
            PetItemDetailFragment fragment = new PetItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.petitem_detail_container, fragment)
                    .commit();

        } else {

            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PetItemDetailActivity.class);
            detailIntent.putExtra(PetItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(App.ANIMAL,filterData.animal);
        outState.putString(App.SIZE,filterData.size);
        outState.putString(App.GENDER,filterData.gender);
        outState.putInt(App.FROM_AGE,filterData.fromAge);
        outState.putInt(App.TO_AGE,filterData.toAge);

        Toast.makeText(getApplicationContext(),"Instance saved",Toast.LENGTH_LONG).show();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAdsLoaded(List<Pet> ads) {
        mPetList = (ArrayList<Pet>)ads;
        App app = (App)getApplication();
        app.petListProvider.setPetList(mPetList);
        mPetListFrag.showList();
    }

    @Override
    public void onRestCallError(RetrofitError error) {

        Toast.makeText(this.getApplicationContext(),getString(R.string.get_ads_error),Toast.LENGTH_LONG).show();
    }

    private class FilterData{

        public String gender;
        public String animal;
        public String size;
        public int toAge;
        public int fromAge;

        private FilterData(){}

        private FilterData(String gender, String animal, String size, int fromAge, int toAge) {
            this.gender = gender.toUpperCase();
            this.animal = animal.toUpperCase();
            this.size = size.toUpperCase();
            this.toAge = toAge;
            this.fromAge = fromAge;
        }
    }

//    private class GetPetsTask extends AsyncTask<Void,Void,GetPetsRespond> {
//
//        private String mAge;
//        private String mGender;
//        private String mSize;
//        private String mAnimal;
//
//        private GetPetsTask(String mAge, String mGender, String mSize, String mAnimal) {
//            this.mAge = mAge;
//            this.mGender = mGender;
//            this.mSize = mSize;
//            this.mAnimal = mAnimal;
//        }
//
//        @Override
//        protected GetPetsRespond doInBackground(Void... voids) {
//
//            ArrayList<Pet> mPetsList;
//
//            try {
//                mPetsList = new GetPetsByFilter(mAnimal, mAge, mSize, mGender).GetPets();
//            } catch (GetPetsException e) {
//                e.printStackTrace();
//                return new GetPetsRespond(false, e.getMessage(), null);
//            }
//            return new GetPetsRespond(true, null, mPetsList);
//        }
//
//        @Override
//        protected void onPostExecute(GetPetsRespond getPetsRespond) {
//
//            if (getPetsRespond.isSuccess()) {
//
//                mPetList = getPetsRespond.getPetlist();
//                App app = (App)getApplication();
//                app.petListProvider.setPetList(mPetList);
//                mPetListFrag.showList();
//            } else {
//                Toast.makeText(getApplicationContext(), getPetsRespond.getMessage(), Toast.LENGTH_LONG);
//            }
//        }
//    }

}
