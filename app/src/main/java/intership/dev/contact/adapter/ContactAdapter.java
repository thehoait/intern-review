package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import intership.dev.contact.EditContactActivity;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.widget.CircleImageView;

/**
 * Created by hoa on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> mContacts;
    private Activity mActivity;

    public ContactAdapter(Activity activity, int resource, ArrayList<Contact> objects) {
        super(activity, resource, objects);
        this.mActivity=activity;
        this.mContacts=objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Contact itemContact=mContacts.get(position);
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
        holder.imgAvatar.setImageResource(itemContact.getAvatar());
        holder.tvName.setText(itemContact.getName());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog deleteDialog = new Dialog(mActivity, R.style.Dialog);
                deleteDialog.setContentView(R.layout.dialog_list_contact);
                TextView tvMessenger = (TextView) deleteDialog.findViewById(R.id.tvMessenger);
                tvMessenger.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" +
                        itemContact.getName() + "</b>" + "?"));
                deleteDialog.show();

                //Set event when click ok in dialog
                Button btnOk = (Button) deleteDialog.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContacts.remove(itemContact);
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

            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog editDialog = new Dialog(mActivity, R.style.Dialog);
                editDialog.setContentView(R.layout.dialog_list_contact);
                TextView tvMessenger = (TextView) editDialog.findViewById(R.id.tvMessenger);
                tvMessenger.setText(Html.fromHtml("Are you sure you want to edit " + "<b>" +
                        itemContact.getName() + "</b>" + "?"));
                editDialog.show();

                //Set event when click ok in dialog
                Button btnOk = (Button) editDialog.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mActivity, EditContactActivity.class);
                        intent.putExtra("contact",itemContact);
                        intent.putExtra("position", position);
                        mActivity.startActivityForResult(intent, 1);
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

            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView imgEdit, imgDelete;
        CircleImageView imgAvatar;
        TextView tvName;
    }
}
