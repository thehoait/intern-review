package intership.dev.contact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import intership.dev.contact.MainActivity;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by hoa on 7/22/15.
 */
public class EditContactFragment extends Fragment implements View.OnClickListener {
    private Contact mContact;
    private CircleImageView imgAvatar;
    private TextView tvName,tvTitle;
    private EditText edtName,edtDesc;
    private Button btnSave,btnCancel;
    private OnChangeItemListener mListenerOnChange;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.edit_contact_fragment, container, false);
        init(view);
        Bundle dataBundle=this.getArguments();
        mContact =(Contact) dataBundle.getSerializable("dataBundle");

        setValue();

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        return view;
    }

    /**
     * set values for layout edit contact
     */
    private void setValue() {
        imgAvatar.setImageResource(mContact.getAvatar());
        tvName.setText(mContact.getName());
        edtName.setText(mContact.getName());
        edtDesc.setText(mContact.getDescription());
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setTitle("Contact");
    }

    void init(View view) {
        imgAvatar=(CircleImageView) view.findViewById(R.id.imgAvatar);
        tvName=(TextView) view.findViewById(R.id.tvName);
        edtName=(EditText) view.findViewById(R.id.edtName);
        edtDesc=(EditText) view.findViewById(R.id.edtDesc);
        btnSave=(Button) view.findViewById(R.id.btnSave);
        btnCancel=(Button) view.findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==btnCancel.getId()){
            getActivity().onBackPressed();
        }
        if(id==btnSave.getId()){
            mContact.setName(edtName.getText().toString());
            mContact.setDescription(edtDesc.getText().toString());
            mListenerOnChange.onChange();
            getActivity().onBackPressed();
        }
    }

    /**
     * interface update contacts when edit contact
     */
    public interface OnChangeItemListener{
        void onChange();
    }

    public void setOnChangeItemListener(OnChangeItemListener listener){
        mListenerOnChange=listener;
    }
}
