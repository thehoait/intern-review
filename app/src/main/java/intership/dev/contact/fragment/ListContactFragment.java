package intership.dev.contact.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import intership.dev.contact.MainActivity;
import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.LoadMoreListView;

/**
 * Created by hoa on 7/24/15.
 */
public class ListContactFragment extends Fragment {
    private static final String[] NAMES={
            "Hugh Helbert",
            "Stevev Seo",
            "Dwight Pera",
            "Prancis cripriano",
            "Walter Chavis",
            "Wilbert Rowen",
            "Andrea Gruber",
            "Dario Bennington",
            "Fransico Chill",
            "Hugh Helbert",
            "Stevev Seo",
            "Dwight Pera",
            "Prancis cripriano",
            "Walter Chavis",
            "Wilbert Rowen",
            "Andrea Gruber",
            "Dario Bennington",
            "Fransico Chill"
    };
    private static final int[] AVATARS={
            R.drawable.ic_avt1,
            R.drawable.ic_avt2,
            R.drawable.ic_avt3,
            R.drawable.ic_avt4,
            R.drawable.ic_avt1,
            R.drawable.ic_avt2,
            R.drawable.ic_avt3,
            R.drawable.ic_avt4,
            R.drawable.ic_avt1,
            R.drawable.ic_avt2,
            R.drawable.ic_avt3,
            R.drawable.ic_avt4,
            R.drawable.ic_avt1,
            R.drawable.ic_avt2,
            R.drawable.ic_avt3,
            R.drawable.ic_avt4,
            R.drawable.ic_avt1,
            R.drawable.ic_avt2,
    };
    private static final String[] DESCRIPTIONS={"a", "b", "c", "d", "e", "f", "g", "h", "i", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t"
    };
    private ArrayList<Contact> mContacts;
    private ContactAdapter mContactAdapter;
    private LoadMoreListView lvContact;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_contact,container,false);
        mContactAdapter=new ContactAdapter(getActivity(),R.layout.item_list_contact,mContacts);
        lvContact=(LoadMoreListView) view.findViewById(R.id.lvContact);
        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContacts=new ArrayList<>();
        for(int i=0;i<NAMES.length;i++){
            Contact contact=new Contact(NAMES[i],AVATARS[i],DESCRIPTIONS[i]);
            mContacts.add(contact);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setTitle("Contacts");
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            // add Loadmore Item
            for (int i = 0; i < NAMES.length; i++) {
                Contact item = new Contact(NAMES[i], AVATARS[i], DESCRIPTIONS[i]);
                mContacts.add(item);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            mContactAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            lvContact.onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            lvContact.onLoadMoreComplete();
        }
    }
}
