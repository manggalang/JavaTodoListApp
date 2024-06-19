package org.galangcode.todolistapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.galangcode.todolistapp.R;
import org.galangcode.todolistapp.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null && inflater != null) {
            convertView = inflater.inflate(R.layout.list_page, null);
        }

        if (convertView != null) {
            TextView title = convertView.findViewById(R.id.list_title);
            TextView desc = convertView.findViewById(R.id.list_desc);

            Data data = lists.get(position);
            title.setText(data.getTitle());
            desc.setText(data.getDescription());

        }

        return convertView;
    }
}
