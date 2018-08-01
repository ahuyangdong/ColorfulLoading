package com.dommy.loading.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dommy.loading.R;

/**
 * 首页功能点适配器
 */
public class HomeFuncAdapter extends RecyclerView.Adapter<HomeFuncAdapter.ViewHolder> {
    private Context context;
    private int[] icons; // 图标
    private String[] titles; // 标题
    private OnItemClickListener itemClickListener;

    public HomeFuncAdapter(Context context, int[] icons, String[] titles) {
        this.context = context;
        this.icons = icons;
        this.titles = titles;
    }

    /**
     * 注册点击事件
     *
     * @param itemClickListener
     */
    public void addOnItemClickLister(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_home_func, parent, false);
        // 设置动态高度
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(viewItem.getLayoutParams());
        lp.height = parent.getHeight() / 4; // 均分4行
        viewItem.setLayoutParams(lp);

        if (itemClickListener != null) {
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnClick(view, (int) view.getTag());
                }
            });
        }
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position); // 保存位置

        int imgResId = icons[position];
        String title = titles[position];
        holder.imgIcon.setImageResource(imgResId);
        holder.tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.img_icon);
            tvTitle = itemView.findViewById(R.id.txt_title);
        }
    }

    public interface OnItemClickListener {
        public void OnClick(View view, int postition);
    }
}
