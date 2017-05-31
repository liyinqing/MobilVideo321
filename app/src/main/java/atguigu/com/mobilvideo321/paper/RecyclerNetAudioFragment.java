package atguigu.com.mobilvideo321.paper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import atguigu.com.mobilvideo321.fragment.BaseFragment;

public class RecyclerNetAudioFragment extends BaseFragment {

    private final static String NET_AUDIO_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private final static String LAST_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-";
    private final static String NEXT_URL = ".json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8\\";
    private int count = 20;
    private boolean isRefresh = true;

    private TextView textView;
    private RecyclerView recyclerView;

    @Override
    public View initView() {
      return null;
    }



    @Override
    public void initData() {
        super.initData();
    }





}
