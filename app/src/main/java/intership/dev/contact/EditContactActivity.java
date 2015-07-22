package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import intership.dev.contact.fragment.EditContactFragment;

/**
 * Created by hoa on 7/22/15.
 */
public class EditContactActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        EditContactFragment fragment=new EditContactFragment();
        transaction.add(R.id.lnContain,fragment);
        transaction.commit();
    }
}
