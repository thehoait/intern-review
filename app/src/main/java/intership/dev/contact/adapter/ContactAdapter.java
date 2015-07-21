package intership.dev.contact.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.model.Contact;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Contact itemContact=mContacts.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mActivity).inflate(R.layout.item_list_contact,parent,false);
            holder.imgAvatar =(ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.tvName =(TextView) convertView.findViewById(R.id.tvName);
            holder.imgEdit =(ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete =(ImageView) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.imgAvatar.setImageResource(itemContact.getAvatar());
        holder.tvName.setText(itemContact.getName());
        return convertView;
    }
    static class ViewHolder{
        ImageView imgAvatar, imgEdit, imgDelete;
        TextView tvName;
    }
}