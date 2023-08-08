package com.expense.manager.smith;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.expense.manager.R;
import com.expense.manager.Model.bean.CategoryBean;
import com.expense.manager.widget.CircleImageView;
import java.util.List;

public class SettingCategoryCustomAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryBean> data;
    private LayoutInflater influter;

    public SettingCategoryCustomAdapter(Context context2, List<CategoryBean> list) {
        this.context = context2;
        this.data = list;
        this.influter = (LayoutInflater) context2.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.data.size();
    }

    public CategoryBean getItem(int i) {
        return this.data.get(i);
    }

    public long getItemId(int i) {
        return (long) Integer.parseInt(getItem(i).getId());
    }

    private class ViewHolder {
        CircleImageView a;
        TextView b;
        TextView c;

        private ViewHolder(SettingCategoryCustomAdapter settingCategoryCustomAdapter) {
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.influter.inflate(R.layout.list_item_category_setting, (ViewGroup) null);
            viewHolder = new ViewHolder(this);
            viewHolder.b = (TextView) view.findViewById(R.id.cat_item_name);
            viewHolder.c = (TextView) view.findViewById(R.id.tv_spending_limit);
            viewHolder.a = (CircleImageView) view.findViewById(R.id.cat_item_color);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CategoryBean item = getItem(i);
        viewHolder.b.setText(item.getName());
        viewHolder.a.setBorderColor(Color.parseColor(item.getColor()));
        viewHolder.a.setColorFilter(Color.parseColor(item.getColor()));
        viewHolder.a.setCircleBackgroundColor(Color.parseColor(item.getColor()));
        if (!TextUtils.isEmpty(item.getCategoryLimit())) {
            if (item.getCategoryGroup() == 2) {
                view.findViewById(R.id.layout_limit).setVisibility(View.GONE);
            } else {
                view.findViewById(R.id.layout_limit).setVisibility(View.VISIBLE);
            }
            String currencySymbol = ((BaseActivity) this.context).getCurrencySymbol();
            if (currencySymbol.equals("¢") || currencySymbol.equals("₣") || currencySymbol.equals("₧") || currencySymbol.equals("﷼") || currencySymbol.equals("₨")) {
                TextView textView = viewHolder.c;
                textView.setText(item.getCategoryLimit() + currencySymbol);
            } else {
                TextView textView2 = viewHolder.c;
                textView2.setText(currencySymbol + item.getCategoryLimit());
            }
        }
        return view;
    }
}
