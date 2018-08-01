package com.dommy.loading.util;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>定时器</p>
 * <p>定时执行timerCallBack中的方法</p>
 *
 * @author Dommy
 */
public abstract class MyTimer {
    private Timer timer;// 定时工具
    private TimerTask timerTask;  // 定时任务
    private Activity activity;

    /**
     * 构造方法
     *
     * @param activity
     */
    public MyTimer(Activity activity) {
        this.activity = activity;
    }

    /**
     * <p>回调方法，实例化类需实现此方法</p>
     * <p>达到任务设定时间时，此方法被调用一次</p>
     * <p>单次执行的任务，在此方法实现的最后加入"this.stopTimer()"</p>
     */
    public abstract void timerCallBack();

    /**
     * <p>开启一次定时任务功能</p>
     * <p>达到任务设定时间时，调用且仅调用一次timerCallBack方法</p>
     *
     * @param period 任务开始时间
     */
    public void startTimer(long period) {
        initObject();
        timer.schedule(timerTask, period);
    }

    /**
     * <p>开启循环定时任务功能</p>
     * <p>每次达到任务设定时间时，调用一次timerCallBack方法</p>
     *
     * @param period 任务循环周期
     */
    public void startLoopTimer(long period) {
        initObject();
        timer.schedule(timerTask, 0, period);
    }

    /**
     * <p>开启循环定时任务功能</p>
     * <p>每次达到任务设定时间时，调用一次timerCallBack方法</p>
     *
     * @param delay  延迟
     * @param period 任务循环周期
     */
    public void startLoopTimerWithDelay(long delay, long period) {
        initObject();
        timer.schedule(timerTask, delay, period);
    }


    /**
     * 关闭定时功能
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 初始化对象
     */
    private void initObject() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask == null) {
            // 定时任务
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // 接口回调，处理UI更新
                                timerCallBack();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            };
        }
    }
}
