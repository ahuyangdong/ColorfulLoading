package com.dommy.loading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dommy.loading.listener.BtnBackListener;
import com.dommy.loading.util.MyTimer;
import com.dommy.loading.util.RandomUtil;
import com.dommy.loading.widget.SimpleRoundProgress;

/**
 * 简单环形进度条
 */
public class SimpleRoundActivity extends AppCompatActivity {
    private static final int MSG_REFRESH_PROGRESS = 1001;
    private static final int TIME_REFRESH = 8000;

    private TextView tvTiltle; // 标题
    private ImageButton btnBack; // 返回按钮

    private SimpleRoundProgress srpStroke0; // 线条型进度-起始角度设置为0
    private SimpleRoundProgress srpStroke135; // 线条型进度-起始角度设置为135
    private SimpleRoundProgress srpFill; // 填充型进度
    private SimpleRoundProgress srpStroke0Inner; // 线条型进度-起始角度设置为0-内环
    private SimpleRoundProgress srpStroke135Inner; // 线条型进度-起始角度设置为135-内环
    private SimpleRoundProgress srpFillInner; // 填充型进度-内环
    private TextView tvProgress; // 进度提示

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REFRESH_PROGRESS:
                    srpStroke0.setProgress(msg.arg1);
                    srpStroke135.setProgress(msg.arg1);
                    srpFill.setProgress(msg.arg1);
                    srpStroke0Inner.setProgress(msg.arg1);
                    srpStroke135Inner.setProgress(msg.arg1);
                    srpFillInner.setProgress(msg.arg1);

                    tvProgress.setText("进度值：" + msg.arg1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_round);

        initView();

        timer.startLoopTimer(TIME_REFRESH);
    }

    private void initView() {
        tvTiltle = (TextView) findViewById(R.id.txt_title);
        tvTiltle.setText("简单环形进度条");
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new BtnBackListener(this));

        srpStroke0 = (SimpleRoundProgress) findViewById(R.id.srp_stroke_0);
        srpStroke135 = (SimpleRoundProgress) findViewById(R.id.srp_stroke_135);
        srpFill = (SimpleRoundProgress) findViewById(R.id.srp_fill);
        srpStroke0Inner = (SimpleRoundProgress) findViewById(R.id.srp_stroke_0_inner);
        srpStroke135Inner = (SimpleRoundProgress) findViewById(R.id.srp_stroke_135_inner);
        srpFillInner = (SimpleRoundProgress) findViewById(R.id.srp_fill_inner);

        tvProgress = (TextView) findViewById(R.id.txt_progress);
    }

    private void refresh() {
        final int percent = RandomUtil.getRandomPercent();
        new Thread(new Runnable() {
            Message msg = null;

            @Override
            public void run() {
                int start = 0;
                while (start <= percent) {
                    msg = new Message();
                    msg.what = MSG_REFRESH_PROGRESS;
                    msg.arg1 = start;
                    handler.sendMessage(msg);
                    start++;
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private MyTimer timer = new MyTimer(this) {
        @Override
        public void timerCallBack() {
            refresh();
        }
    };
}
