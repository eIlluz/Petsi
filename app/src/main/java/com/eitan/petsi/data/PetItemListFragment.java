package com.eitan.petsi.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.eitan.petsi.R;

import com.eitan.petsi.com.eitan.petsi.services.GetPetsByFilter;
import com.eitan.petsi.com.eitan.petsi.services.GetPetsException;
import com.eitan.petsi.com.eitan.petsi.services.GetPetsRespond;
import com.eitan.petsi.data.dummy.DummyContent;

import java.util.ArrayList;

/**
 * A list fragment representing a list of Pet Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link PetItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class PetItemListFragment extends ListFragment {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    private String mAge;
    private String mGender;
    private String mSize;
    private String mAnimal;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PetItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
//        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
//                getActivity(),
//                android.R.layout.simple_list_item_activated_1,
//                android.R.id.text1,
//                DummyContent.ITEMS));
//        setListAdapter(new ArrayAdapter<Pet>(getActivity(),android.R.layout.simple_list_item_activated_1,));

    }

    public void setFilters(String age,String animal,String size,String gender){

        mAge = age;
        mAnimal = animal;
        mSize = size;
        mGender = gender;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Toast.makeText(this.getActivity().getApplicationContext(),"Fragment pressed",Toast.LENGTH_LONG);
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private class GetPetsTask extends AsyncTask<Void,Void,GetPetsRespond>{

        private String mAge;
        private String mGender;
        private String mSize;
        private String mAnimal;

        private GetPetsTask(String mAge, String mGender, String mSize, String mAnimal) {
            this.mAge = mAge;
            this.mGender = mGender;
            this.mSize = mSize;
            this.mAnimal = mAnimal;
        }

        @Override
        protected GetPetsRespond doInBackground(Void... voids) {

            ArrayList<Pet> mPetsList;

            try {
                mPetsList = new GetPetsByFilter(mAnimal,mAge,mSize,mGender).GetPets();
            } catch (GetPetsException e) {
                e.printStackTrace();
                return new GetPetsRespond(false,e.getMessage(),null);
            }
            return new GetPetsRespond(true,null,mPetsList);
        }

        @Override
        protected void onPostExecute(GetPetsRespond getPetsRespond) {

            if (getPetsRespond.isSuccess()) {
                setListAdapter(new petListAdapter(getActivity(), getPetsRespond.getPetlist()));
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(),getPetsRespond.getMessage(),Toast.LENGTH_LONG);
            }
        }
    }


}
