package com.dommy.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dommy.loading.adapter.HomeFuncAdapter;
import com.dommy.loading.util.DividerGridItemDecoration;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    private int[] ICONS = new int[]{R.drawable.home_icon_1, R.drawable.home_icon_2, R.drawable.home_icon_3, R.drawable.home_icon_4, R.drawable.building
            , R.drawable.building, R.drawable.building, R.drawable.building, R.drawable.building, R.drawable.building, R.drawable.building
            , R.drawable.building};

    private String[] TITLES = new String[]{"网页加载进度条", "简单环形进度条", "配文字环形进度条", "渐变色环形进度条", "圆弧形进度条", "刻度盘圆弧型进度条"
            , "刻度盘圆型进度条", "多图组合型不规则进度条", "平滑绘制型不规则进度条", "简易水波纹进度条", "组合水波纹进度条", "多层综合型进度条"};

    private TextView tvTiltle; // 标题
    private RecyclerView recyclerView; // 功能网格
    private HomeFuncAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tvTiltle = (TextView) findViewById(R.id.txt_title);
        tvTiltle.setText("首页");

        recyclerView = (RecyclerView) findViewById(R.id.func_grid);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeFuncAdapter(this, ICONS, TITLES);
        recyclerView.setAdapter(adapter);
        // 设置分隔线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        adapter.addOnItemClickLister(onItemClickListener);
    }

    private HomeFuncAdapter.OnItemClickListener onItemClickListener = new HomeFuncAdapter.OnItemClickListener() {
        @Override
        public void OnClick(View view, int postition) {
            switch (postition) {
                case 0:
                    // 网页加载进度条
                    startActivity(new Intent(MainActivity.this, WebLoadingActivity.class));
                    break;
                case 1:
                    // 简单环形进度条
                    startActivity(new Intent(MainActivity.this, SimpleRoundActivity.class));
                    break;
                case 2:
                    // 配文字环形进度条
                    startActivity(new Intent(MainActivity.this, TextRoundActivity.class));
                    break;
                case 3:
                    // 渐变色环形进度条
                    startActivity(new Intent(MainActivity.this, GradientRoundActivity.class));
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
            }
        }
    };
}
