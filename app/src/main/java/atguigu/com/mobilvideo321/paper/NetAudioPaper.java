package atguigu.com.mobilvideo321.paper;

import android.view.View;
import android.widget.TextView;

import atguigu.com.mobilvideo321.fragment.BaseFragment;

/**
 * 作者：李银庆 on 2017/5/25 11:33
 */
public class NetAudioPaper extends BaseFragment {
    @Override
    public View initView() {
       TextView tv =  new TextView(context);
        tv.setText("网络音乐");
        return tv;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
