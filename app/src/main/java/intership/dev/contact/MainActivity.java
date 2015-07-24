package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;
import intership.dev.contact.fragment.ListContactFragment;


public class MainActivity extends FragmentActivity{
    private RelativeLayout rlContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListContactFragment();

    }

    private void addListContactFragment() {
        rlContainerFragment = (RelativeLayout) findViewById(R.id.rlContainerFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListContactFragment frag = new ListContactFragment();
        transaction.add(R.id.rlContainerFragment, frag);
        transaction.commit();
    }
}
