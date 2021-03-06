package atguigu.com.mobilvideo321.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import atguigu.com.mobilvideo321.R;
import atguigu.com.mobilvideo321.Utils.Utils;
import atguigu.com.mobilvideo321.domain.MediaInfo;

public class SystemMediaPlayer extends AppCompatActivity implements View.OnClickListener {

    private VideoView vv;
    private ArrayList<MediaInfo> mediaInfos;
    private int position;
    private LinearLayout llTop;
    private TextView tv_name;
    private ImageView ivBattery;
    private TextView tvTime;
    private Button btnVoice;
    private SeekBar seekbarVoice;
    private Button btnSwitch;
    private LinearLayout llBottom;
    private TextView tvProgress;
    private SeekBar seekbarProgress;
    private TextView tvDuration;
    private Button btnExit;
    private Button btnPre;
    private Button btnPause;
    private Button btnNext;
    private Button btnFullScreen;
    private Uri uri;
    private Utils utils;

    private GestureDetector detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            setPlaying();
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);

        }
    }) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化
        findViews();
        //得到意图中数据
        getData();
        //播放监听
        listenter();
    }

    private final static int CONTROL = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CONTROL:
                    //得到系统时间并且刷新
                    tvTime.setText(getSystemTime());
                    int currentPosition = vv.getCurrentPosition();
                    tvProgress.setText(utils.stringForTime(currentPosition));
                    seekbarProgress.setProgress(currentPosition);
                    handler.sendEmptyMessageDelayed(CONTROL,1000);
                    break;
            }
        }
    };

    private String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }

    private void listenter() {
        seekbarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    vv.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //播放视频的三个监听
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                MediaInfo mediaInfo = mediaInfos.get(position);
                String name = mediaInfo.getName();
                long duration = mediaInfo.getDuration();
                tv_name.setText(name);
                seekbarProgress.setMax((int) duration);
                tvDuration.setText(utils.stringForTime((int) duration));
                setButton();

                vv.start();
                handler.sendEmptyMessage(CONTROL);
            }
        });
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                setNextPlayer();
                return true;
            }
        });
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                setNextPlayer();
            }
        });
    }

    private void getData() {
        mediaInfos = (ArrayList<MediaInfo>) getIntent().getSerializableExtra("mediaInfos");
        position = getIntent().getIntExtra("position", 0);
        uri = getIntent().getData();
        setData();
    }

    private void setData() {
        if(mediaInfos != null && mediaInfos.size()>0){
            if(position < mediaInfos.size()) {
                MediaInfo mediaInfo = mediaInfos.get(position);
                String data = mediaInfo.getData();
//        vv.setVideoPath(data);
                vv.setVideoURI(Uri.parse(data));
            }
        }else if(uri != null) {
            vv.setVideoURI(uri);
        }
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-25 14:56:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_media_player);
        utils = new Utils(this);
        vv = (VideoView) findViewById(R.id.vv);
        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tv_name = (TextView)findViewById( R.id.name );
        ivBattery = (ImageView)findViewById( R.id.iv_battery );
        tvTime = (TextView)findViewById( R.id.tv_time );
        btnVoice = (Button)findViewById( R.id.btn_voice );
        seekbarVoice = (SeekBar)findViewById( R.id.seekbar_voice );
        btnSwitch = (Button)findViewById( R.id.btn_switch );
        llBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        tvProgress = (TextView)findViewById( R.id.tv_progress );
        seekbarProgress = (SeekBar)findViewById( R.id.seekbar_progress );
        tvDuration = (TextView)findViewById( R.id.tv_duration );
        btnExit = (Button)findViewById( R.id.btn_exit );
        btnPre = (Button)findViewById( R.id.btn_pre );
        btnPause = (Button)findViewById( R.id.btn_pause );
        btnNext = (Button)findViewById( R.id.btn_next );
        btnFullScreen = (Button)findViewById( R.id.btn_full_screen );

        btnVoice.setOnClickListener( this );
        btnSwitch.setOnClickListener( this );
        btnExit.setOnClickListener( this );
        btnPre.setOnClickListener( this );
        btnPause.setOnClickListener( this );
        btnNext.setOnClickListener( this );
        btnFullScreen.setOnClickListener( this );

        //注册广播接收电量变化
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver,filter);
    }
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            Log.e("TAG","level========="+level);
            setBatteryChanged(level);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void setBatteryChanged(int level) {
        if(level <= 0){
            ivBattery.setImageResource(R.drawable.ic_battery_0);
        }else if(level <= 10){
            ivBattery.setImageResource(R.drawable.ic_battery_10);
        }
        else if(level <= 20){
            ivBattery.setImageResource(R.drawable.ic_battery_20);
        }
        else if(level <= 40){
            ivBattery.setImageResource(R.drawable.ic_battery_40);
        }
        else if(level <= 60){
            ivBattery.setImageResource(R.drawable.ic_battery_60);
        }
        else if(level <= 80){
            ivBattery.setImageResource(R.drawable.ic_battery_80);
        }
        else if(level <= 100){
            ivBattery.setImageResource(R.drawable.ic_battery_100);
        }else {
            ivBattery.setImageResource(R.drawable.ic_battery_100);
        }
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-05-25 14:56:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnVoice ) {
            // Handle clicks for btnVoice
        } else if ( v == btnSwitch ) {
            // Handle clicks for btnSwitch
        } else if ( v == btnExit ) {
            // Handle clicks for btnExit
        } else if ( v == btnPre ) {
            btnPause.setBackgroundResource(R.drawable.btn_pause_selector);
            setPrePlayer();
        } else if ( v == btnPause ) {
            setPlaying();
            // Handle clicks for btnPause
        } else if ( v == btnNext ) {
            btnPause.setBackgroundResource(R.drawable.btn_pause_selector);
            setNextPlayer();

        } else if ( v == btnFullScreen ) {
            // Handle clicks for btnFullScreen
        }
    }

    private void setPlaying() {
        if(vv.isPlaying()){
            vv.pause();
            btnPause.setBackgroundResource(R.drawable.btn_start_selector);
        }else{
            vv.start();
            btnPause.setBackgroundResource(R.drawable.btn_pause_selector);
        }
    }

    private void setPrePlayer() {
        if(mediaInfos != null && mediaInfos.size()>0){
            position--;
            if(position >=0){
                vv.setVideoPath(mediaInfos.get(position).getData());
            }
        }
        setButton();
    }

    private void setNextPlayer() {
        if(mediaInfos != null && mediaInfos.size()>0){
            position++;
            if(position < mediaInfos.size()){
                vv.setVideoPath(mediaInfos.get(position).getData());
            }
        }
        setButton();
    }

    private void setButton() {
        if(mediaInfos != null && mediaInfos.size()>0){
            setEnabled(true);
            if(position == 0){
                btnPre.setBackgroundResource(R.drawable.btn_pre_gray);
                btnPre.setEnabled(false);
            }
            if(position == mediaInfos.size()-1){
                btnNext.setBackgroundResource(R.drawable.btn_next_gray);
                btnNext.setEnabled(false);
            }
        }else if(uri != null){
            setEnabled(false);
        }
    }

    private void setEnabled(boolean b) {
        if(b){
            btnPre.setBackgroundResource(R.drawable.btn_pre_selector);
            btnNext.setBackgroundResource(R.drawable.btn_next_selector);
        }else {
            btnPre.setBackgroundResource(R.drawable.btn_pre_gray);
            btnNext.setBackgroundResource(R.drawable.btn_next_gray);
        }
        btnNext.setEnabled(b);
        btnPre.setEnabled(b);
    }


}
