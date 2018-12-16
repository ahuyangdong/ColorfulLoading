package com.dommy.loading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dommy.loading.listener.BtnBackListener;
import com.dommy.loading.util.MyTimer;
import com.dommy.loading.util.RandomUtil;
import com.dommy.loading.widget.GradientRoundProgress;

/**
 * 渐变色环形进度条
 */
public class GradientRoundActivity extends AppCompatActivity {
    private static final int MSG_REFRESH_PROGRESS = 1001;
    private static final int TIME_REFRESH = 8000;

    private TextView tvTiltle; // 标题
    private ImageButton btnBack; // 返回按钮

    private GradientRoundProgress grp0; // 起始角度设置为0
    private GradientRoundProgress grp135; // 起始角度设置为135
    private GradientRoundProgress grp135Empty; // 起始角度设置为135-不带文字
    private GradientRoundProgress grp135Custom; // 起始角度设置为135-使用自定义字体
    private TextView tvProgress; // 进度提示

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REFRESH_PROGRESS:
                    grp0.setProgress(msg.arg1);
                    grp135.setProgress(msg.arg1);
                    grp135Empty.setProgress(msg.arg1);
                    grp135Custom.setProgress(msg.arg1);

                    tvProgress.setText("进度值：" + msg.arg1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_round);

        initView();

        timer.startLoopTimer(TIME_REFRESH);
    }

    private void initView() {
        tvTiltle = (TextView) findViewById(R.id.txt_title);
        tvTiltle.setText("渐变色环形进度条");
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new BtnBackListener(this));

        grp0 = (GradientRoundProgress) findViewById(R.id.grp_0);
        grp135 = (GradientRoundProgress) findViewById(R.id.grp_135);
        grp135Empty = (GradientRoundProgress) findViewById(R.id.grp_135_empty);
        grp135Custom = (GradientRoundProgress) findViewById(R.id.grp_135_custom);

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
