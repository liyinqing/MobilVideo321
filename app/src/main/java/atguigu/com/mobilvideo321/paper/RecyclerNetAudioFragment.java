package atguigu.com.mobilvideo321.paper;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import atguigu.com.mobilvideo321.R;
import atguigu.com.mobilvideo321.fragment.BaseFragment;


public class RecyclerNetAudioFragment extends BaseFragment {

    private final static String NET_AUDIO_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private final static String LAST_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-";
    private final static String NEXT_URL = ".json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8\\";
    private int count = 20;
    private boolean isRefresh = true;

    private TextView textView;
    private RecyclerView recyclerView;
    private MaterialRefreshLayout refresh;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.recycler_view, null);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        textView = (TextView) view.findViewById(R.id.tv_nodata);
        recyclerView = (RecyclerView) view.findViewById(R.id.net_recycler_audio);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isRefresh = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromNet();
                    }
                }, 2000);

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                isRefresh = false;
                getMoreData();
            }
        });
//        adapter = new MultipleAdapter(mContext, datas);
//        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new MultipleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                NetAudioBean.ListBean listEntity = adapter.getItem(position);
//                if (listEntity != null) {
//                    Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
//                    if (listEntity.getType().equals("gif")) {
//                        String url = listEntity.getGif().getImages().get(0);
//                        intent.putExtra("url", url);
//                        mContext.startActivity(intent);
//                    } else if (listEntity.getType().equals("image")) {
//                        String url = listEntity.getImage().getBig().get(0);
//                        intent.putExtra("url", url);
//                        mContext.startActivity(intent);
//                    }
//
//                }
//            }
//        });
      return view;
    }
    private void getDataFromNet() {
//        RequestParams request = new RequestParams(NET_AUDIO_URL);
//        x.http().get(request, new ItemTouchHelper.Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("TAG", "onSuccess--result---" + result);
//                parseJson(result);
//                refresh.finishRefresh();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(mContext, "onerror-----" + ex.getStackTrace(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }



    @Override
    public void initData() {
        super.initData();
    }
    private void getMoreData() {
//        String newUrl = LAST_URL + count + NEXT_URL;
//        RequestParams request = new RequestParams(newUrl);
//        x.http().get(request, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("TAG", "onSuccess--result---" + result);
//                parseJson(result);
//                refresh.finishRefreshLoadMore();
//                count += 20;
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(mContext, "onerror-----" + ex.getStackTrace(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }




}
