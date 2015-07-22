package intership.dev.contact.fragment;

import android.app.Activity;
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
public class EditContactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.edit_contact_fragment,container,false);
        final Intent intent=getActivity().getIntent();
        final Contact contact=(Contact) intent.getSerializableExtra("contact");
        final int position=intent.getIntExtra("position",-1);
        CircleImageView imgAvatar=(CircleImageView) view.findViewById(R.id.imgAvatar);
        TextView tvName=(TextView) view.findViewById(R.id.tvName);
        final EditText edtName=(EditText) view.findViewById(R.id.edtName);
        final EditText edtDesc=(EditText) view.findViewById(R.id.edtDesc);
        Button btnSave=(Button) view.findViewById(R.id.btnSave);
        Button btnCancel=(Button) view.findViewById(R.id.btnCancel);
        imgAvatar.setImageResource(contact.getAvatar());
        tvName.setText(contact.getName());
        edtName.setText(contact.getName());
        edtDesc.setText(contact.getDescription());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setName(edtName.getText().toString());
                contact.setDescription(edtDesc.getText().toString());
                intent.putExtra("position",position);
                intent.putExtra("contact", contact);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
