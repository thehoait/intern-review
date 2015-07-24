package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.fragment.EditContactFragment;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by hoa on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> implements EditContactFragment.OnChangeItemListener{
    private ArrayList<Contact> mContacts;
    private Activity mActivity;

    public ContactAdapter(Activity activity, int resource, ArrayList<Contact> objects) {
        super(activity, resource, objects);
        this.mActivity=activity;
        this.mContacts=objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final Contact contact=mContacts.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mActivity).inflate(R.layout.item_list_contact,parent,false);
            holder.imgAvatar =(CircleImageView) convertView.findViewById(R.id.imgAvatar);
            holder.tvName =(TextView) convertView.findViewById(R.id.tvName);
            holder.imgEdit =(ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete =(ImageView) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.imgAvatar.setImageResource(contact.getAvatar());
        holder.tvName.setText(contact.getName());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDeleteContactDialog(holder, contact);

            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEditContactDialog(holder, contact);

            }
        });
        return convertView;
    }

    /**
     * show dialog confirm edit contact
     * @param holder
     * @param contact
     */
    private void callEditContactDialog(final ViewHolder holder, final Contact contact) {
        holder.imgEdit.setSelected(true);
        final Dialog editDialog = new Dialog(mActivity, R.style.Dialog);
        editDialog.setContentView(R.layout.dialog_list_contact);
        TextView tvMessenger = (TextView) editDialog.findViewById(R.id.tvMessenger);
        tvMessenger.setText(Html.fromHtml("Are you sure you want to edit " + "<b>" +
                contact.getName() + "</b>" + "?"));
        editDialog.show();

        //Set event when click ok in dialog
        Button btnOk = (Button) editDialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEditContactFragment(contact);
                editDialog.cancel();
            }
        });

        //Set event when click ok in dialog
        Button btnCancel = (Button) editDialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog.cancel();
            }
        });
        editDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                holder.imgEdit.setSelected(false);
            }
        });
    }

    /**
     * show dialog confirm delete contact
     * @param holder
     * @param contact
     */
    private void callDeleteContactDialog(final ViewHolder holder, final Contact contact) {
        holder.imgDelete.setSelected(true);
        final Dialog deleteDialog = new Dialog(mActivity, R.style.Dialog);
        deleteDialog.setContentView(R.layout.dialog_list_contact);
        TextView tvMessenger = (TextView) deleteDialog.findViewById(R.id.tvMessenger);
        tvMessenger.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" +
                contact.getName() + "</b>" + "?"));
        deleteDialog.show();

        //Set event when click ok in dialog
        Button btnOk = (Button) deleteDialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContacts.remove(contact);
                notifyDataSetChanged();
                deleteDialog.cancel();
            }
        });

        //Set event when click ok in dialog
        Button btnCancel = (Button) deleteDialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.cancel();
            }
        });
        deleteDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                holder.imgDelete.setSelected(false);
            }
        });
    }

    /**
     * add fragment edit contact
     * @param contact
     */
    private void callEditContactFragment(Contact contact) {
        FragmentManager mFragmentManager = ((FragmentActivity)mActivity).getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        EditContactFragment mEditContactFragment=new EditContactFragment();
        mEditContactFragment.setOnChangeItemListener(this);
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("dataBundle", contact);

        mEditContactFragment.setArguments(dataBundle);
        mFragmentTransaction.replace(R.id.rlContainerFragment, mEditContactFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    static class ViewHolder{
        ImageView imgEdit, imgDelete;
        CircleImageView imgAvatar;
        TextView tvName;
    }

    /**
     * update list contact after edit contact
     */
    @Override
    public void onChange() {
        notifyDataSetChanged();
    }
}
