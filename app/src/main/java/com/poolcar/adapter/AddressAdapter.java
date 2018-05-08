package com.poolcar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.poolcar.R;

import java.util.ArrayList;

public class AddressAdapter extends BaseAdapter {


    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    private String searchText;



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
            int index = ((String) data.get(position)).indexOf(searchText);
            if(((String) data.get(position)).contains(searchText)){
                final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
                sb.setSpan(bss, index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }

            holder.addressText.setText(sb);
        }
        return vi;

    }


    public static class ViewHolder{

        public TextView addressText;

    }
}
