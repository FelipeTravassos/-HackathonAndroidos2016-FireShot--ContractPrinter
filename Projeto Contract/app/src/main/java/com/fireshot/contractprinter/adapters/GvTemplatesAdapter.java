package com.fireshot.contractprinter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireshot.contractprinter.R;
import com.fireshot.contractprinter.models.Template;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Felipe on 01/07/2016.
 */
public class GvTemplatesAdapter extends BaseAdapter {

    Template[] mTemplates;
    LayoutInflater mInflater;

    public GvTemplatesAdapter(Template[] mTemplates, Context context) {
        this.mTemplates = mTemplates;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mTemplates.length;
    }

    @Override
    public Object getItem(int position) {
        return mTemplates[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSupport itemHolder;
        View v = null;

        if (convertView == null) {
            v = mInflater.inflate(R.layout.gv_item_template, null);
            itemHolder = new ItemSupport(v);
            v.setTag(itemHolder);
        } else {
            itemHolder = (ItemSupport) convertView.getTag();
        }

        Template item = mTemplates[position];
        itemHolder.mTitle.setText(item.getTitle());

        return v == null ? convertView : v;
    }

    class ItemSupport {

        @Bind(R.id.iv_template_icon)
        ImageView mIcon;

        @Bind((R.id.tv_template_title))
        TextView mTitle;

        public ItemSupport(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
