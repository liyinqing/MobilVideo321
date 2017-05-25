package atguigu.com.mobilvideo321.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.mobilvideo321.R;
import atguigu.com.mobilvideo321.Utils.Utils;
import atguigu.com.mobilvideo321.domain.MediaInfo;

/**
 * 作者：李银庆 on 2017/5/25 13:12
 */
public class LocalMediaAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<MediaInfo> datas;
    private Utils utils;

    public LocalMediaAdapter(Context context, ArrayList<MediaInfo> mediaInfos) {
        this.context = context;
        this.datas = mediaInfos;
        utils = new Utils(context);

    }

    @Override
    public int getCount() {
        return datas ==null ? 0 : datas.size();
    }

    @Override
    public MediaInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_media,null);
            viewHodler = new ViewHodler();
           viewHodler.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHodler.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHodler.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            convertView.setTag(viewHodler);
        }else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        MediaInfo mediaInfo = datas.get(position);
        viewHodler.tv_name.setText(mediaInfo.getName());
        viewHodler.tv_duration.setText(utils.stringForTime((int) mediaInfo.getDuration()));
        viewHodler.tv_size.setText(Formatter.formatFileSize(context,mediaInfo.getSize()));
        return convertView;
    }
    static class ViewHodler{
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }
}
