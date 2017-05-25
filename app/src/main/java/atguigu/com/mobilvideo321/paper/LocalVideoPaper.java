package atguigu.com.mobilvideo321.paper;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.mobilvideo321.R;
import atguigu.com.mobilvideo321.adapter.LocalMediaAdapter;
import atguigu.com.mobilvideo321.domain.MediaInfo;
import atguigu.com.mobilvideo321.fragment.BaseFragment;

/**
 * 作者：李银庆 on 2017/5/25 11:33
 */
public class LocalVideoPaper extends BaseFragment {
    ListView lv;
    private TextView tv_content;
    private ArrayList<MediaInfo> mediaInfos;
    private final static int ADD_COMPLETE = 0;
    private LocalMediaAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ADD_COMPLETE:
                    if(mediaInfos != null && mediaInfos.size()>0){
                        tv_content.setVisibility(View.GONE);
                        adapter = new LocalMediaAdapter(context,mediaInfos);
                        lv.setAdapter(adapter);
                    }else {
                        tv_content.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.local_video,null);
        lv  = (ListView) view.findViewById(R.id.lv);
        tv_content = (TextView) view.findViewById(R.id.tv_content);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                String data = mediaInfos.get(position).getData();
                intent.setDataAndType(Uri.parse(data),"video/*");
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getData();


    }

    private void getData() {
        new Thread(){
            public void run(){
                mediaInfos = new ArrayList<MediaInfo>();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] datas = {
                        MediaStore.Video.Media.DATA,//播放地址
                        MediaStore.Video.Media.DURATION,//时长
                        MediaStore.Video.Media.DISPLAY_NAME,//姓名
                        MediaStore.Video.Media.SIZE//大小
                };
                Cursor cursor = resolver.query(uri, datas, null, null, null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        mediaInfos.add(new MediaInfo(name,duration,size,data));
                    }
                }
                cursor.close();
                handler.sendEmptyMessage(ADD_COMPLETE);
            }
        }.start();

    }


}
