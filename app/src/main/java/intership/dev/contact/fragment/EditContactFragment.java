package intership.dev.contact.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by hoa on 7/22/15.
 */
public class EditContactFragment extends Fragment implements View.OnClickListener {
    Intent intent;
    Contact contact;
    int position;
    CircleImageView imgAvatar;
    TextView tvName;
    EditText edtName,edtDesc;
    Button btnSave,btnCancel;
    ImageView imgBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.edit_contact_fragment,container,false);
        init(view);
        intent=getActivity().getIntent();
        contact=(Contact) intent.getSerializableExtra("contact");
        position=intent.getIntExtra("position",-1);

        imgAvatar.setImageResource(contact.getAvatar());
        tvName.setText(contact.getName());
        edtName.setText(contact.getName());
        edtDesc.setText(contact.getDescription());

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        return view;
    }

    void init(View view) {
        imgAvatar=(CircleImageView) view.findViewById(R.id.imgAvatar);
        tvName=(TextView) view.findViewById(R.id.tvName);
        edtName=(EditText) view.findViewById(R.id.edtName);
        edtDesc=(EditText) view.findViewById(R.id.edtDesc);
        btnSave=(Button) view.findViewById(R.id.btnSave);
        btnCancel=(Button) view.findViewById(R.id.btnCancel);
        imgBack=(ImageView) view.findViewById(R.id.imgBack);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==btnCancel.getId()){
            getActivity().finish();
        }
        if(id==btnSave.getId()){
            contact.setName(edtName.getText().toString());
            contact.setDescription(edtDesc.getText().toString());
            intent.putExtra("position",position);
            intent.putExtra("contact", contact);
            getActivity().setResult(Activity.RESULT_OK,intent);
            getActivity().finish();
        }
        if(id==imgBack.getId()){
            getActivity().finish();
        }
    }
}
