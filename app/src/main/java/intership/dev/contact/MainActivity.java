package intership.dev.contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;


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
    private ListView lvContact;

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
        lvContact=(ListView) findViewById(R.id.lvContact);
        lvContact.setAdapter(mContactAdapter);
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
