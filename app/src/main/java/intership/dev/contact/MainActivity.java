package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import intership.dev.contact.fragment.ListContactFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private RelativeLayout rlContainerFragment;
    private static TextView tvTitle;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        imgBack=(ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        addListContactFragment();

    }

    /**
     * add fragment ListContactFragment in MainActivity
     */
    private void addListContactFragment() {
        rlContainerFragment = (RelativeLayout) findViewById(R.id.rlContainerFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListContactFragment frag = new ListContactFragment();
        transaction.add(R.id.rlContainerFragment, frag);
        transaction.commit();
    }

    /**
     * event imgBack
     * @param view
     */
    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    /**
     * set title fragment
     * @param title of fragment
     */
    public static void setTitle(String title){
        tvTitle.setText(title);
    }
}
