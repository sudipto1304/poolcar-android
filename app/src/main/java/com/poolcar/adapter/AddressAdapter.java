package com.poolcar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.component.AppDialog;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends BaseAdapter {

    private final static String TAG = AddressAdapter.class.getName();
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    private String searchText;


    public String getSelectedString(int position){
        return (String)data.get(position);
    }


    public AddressAdapter(Activity a, ArrayList d, String searchText) {
        activity = a;
        data=d;
        this.searchText=searchText;
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if(convertView==null){
            vi = inflater.inflate(R.layout.simple_list_view, null);
            holder = new ViewHolder();
            holder.addressText = vi.findViewById(R.id.label);
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();
        if(data.size()<=0)
        {
            holder.addressText.setText("No Data");

        }
        else
        {
            SpannableStringBuilder sb = new SpannableStringBuilder((String) data.get(position));
            try {
                int index = ((String) data.get(position)).toLowerCase().indexOf(searchText.toLowerCase());
                if (((String) data.get(position)).toLowerCase().contains(searchText.toLowerCase())) {
                    final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
                    sb.setSpan(bss, index, index + searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                }
            }catch(Exception e){
                Log.e(TAG, e.getMessage());
            }
            holder.addressText.setText(sb);
        }
        return vi;

    }


    public static class ViewHolder{

        public TextView addressText;

    }
}
