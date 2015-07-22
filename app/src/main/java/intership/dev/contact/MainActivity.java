package intership.dev.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.LoadMoreListView;


public class MainActivity extends Activity{
    public static final String[] NAMES={
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
    public static final int[] AVATARS={
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
    public static final String[] DESCRIPTIONS={"a", "b", "c", "d", "e", "f", "g", "h", "i", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t"
    };
    private ArrayList<Contact> mContacts;
    private ContactAdapter mContactAdapter;
    private LoadMoreListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContacts=new ArrayList<>();
        for(int i=0;i<NAMES.length;i++){
            Contact contact=new Contact(NAMES[i],AVATARS[i],DESCRIPTIONS[i]);
            mContacts.add(contact);
        }
        mContactAdapter=new ContactAdapter(this,R.layout.item_list_contact,mContacts);
        lvContact=(LoadMoreListView) findViewById(R.id.lvContact);
        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
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
            ((LoadMoreListView) lvContact).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((LoadMoreListView) lvContact).onLoadMoreComplete();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            Contact contact=(Contact) data.getSerializableExtra("contact");
            int position=data.getIntExtra("position",-1);
            mContacts.set(position,contact);
            mContactAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
