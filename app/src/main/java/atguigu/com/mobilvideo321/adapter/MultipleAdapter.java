package atguigu.com.mobilvideo321.adapter;

import android.content.Context;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.mobilvideo321.R;
import atguigu.com.mobilvideo321.Utils.Utils;
import atguigu.com.mobilvideo321.domain.NetAudioBean;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static atguigu.com.mobilvideo321.R.id.tv_context;


public class MultipleAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private ArrayList<NetAudioBean.ListBean> datas;
    private final static int TYPE_VIDEO = 0;
    private final static int TYPE_IMAGE = 1;
    private final static int TYPE_TEXT = 2;
    private final static int TYPE_GIF = 3;
    private final static int TYPE_AD = 4;
    private OnItemClickListener listener;

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(v, (int) v.getTag());
        }
    }

    public NetAudioBean.ListBean getItem(int position) {

        return datas.get(position);
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MultipleAdapter(Context context, ArrayList<NetAudioBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = -1;
        if ("video".equals(datas.get(position).getType())) {
            itemType = TYPE_VIDEO;
        } else if ("image".equals(datas.get(position).getType())) {
            itemType = TYPE_IMAGE;
        } else if ("text".equals(datas.get(position).getType())) {
            itemType = TYPE_TEXT;
        } else if ("gif".equals(datas.get(position).getType())) {
            itemType = TYPE_GIF;
        } else {
            itemType = TYPE_AD;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_VIDEO:
                view = View.inflate(context, R.layout.all_video_item, null);
                view.setOnClickListener(this);
                return new VideoHolder(view);
            case TYPE_IMAGE:
                view = View.inflate(context, R.layout.all_image_item, null);
                view.setOnClickListener(this);
                return new ImageHolder(view);
            case TYPE_TEXT:
                view = View.inflate(context, R.layout.all_text_item, null);
                view.setOnClickListener(this);
                return new TextHolder(view);
            case TYPE_GIF:
                view = View.inflate(context, R.layout.all_gif_item, null);
                view.setOnClickListener(this);
                return new GifHolder(view);
            case TYPE_AD:
                view = View.inflate(context, R.layout.all_ad_item, null);
                view.setOnClickListener(this);
                return new ADHolder(view);

        }
        return new BaseViewHolder(View.inflate(context, R.layout.all_video_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof VideoHolder) {
            VideoHolder videoHolder = (VideoHolder) holder;
            videoHolder.setData(datas.get(position));
        } else if (holder instanceof ImageHolder) {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.setData(datas.get(position));
        } else if (holder instanceof TextHolder) {
            TextHolder textHolder = (TextHolder) holder;
            textHolder.setData(datas.get(position));
        } else if (holder instanceof GifHolder) {
            GifHolder gifHolder = (GifHolder) holder;
            gifHolder.setData(datas.get(position));
        }
        holder.itemView.setTag(position);
    }

    public void setDatas(ArrayList<NetAudioBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();

    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        ImageView ivRightMore;
        ImageView ivVideoKind;
        TextView tvVideoKindText;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        LinearLayout llDownload;

        public BaseViewHolder(View convertView) {
            super(convertView);

            ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
            ivRightMore = (ImageView) convertView.findViewById(R.id.iv_right_more);

            ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
            tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
            tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
            llDownload = (LinearLayout) convertView.findViewById(R.id.ll_download);
        }

        public void setData(NetAudioBean.ListBean mediaItem) {
            if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
                x.image().bind(ivHeadpic, mediaItem.getU().getHeader().get(0));
            }
            if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
                tvName.setText(mediaItem.getU().getName() + "");
            }

            tvTimeRefresh.setText(mediaItem.getPasstime());

            List<NetAudioBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
                tvVideoKindText.setText(buffer.toString());
            }

            tvShenheDingNumber.setText(mediaItem.getUp());
            tvShenheCaiNumber.setText(mediaItem.getDown() + "");
            tvPostsNumber.setText(mediaItem.getForward() + "");

        }
    }

    class VideoHolder extends BaseViewHolder {
        Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;
        TextView tvCommantContext;

        VideoHolder(View convertView) {
            super(convertView);

            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            utils = new Utils(context);
            tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
            tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
            jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
        }

        public void setData(NetAudioBean.ListBean mediaItem) {
            super.setData(mediaItem);

            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());

            boolean setUp = jcvVideoplayer.setUp(
                    mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            if (setUp) {
                Picasso.with(context).load(mediaItem.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(mediaItem.getVideo().getDuration() * 1000) + "");
        }

    }

    private class ImageHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(tv_context);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }

        @Override
        public void setData(NetAudioBean.ListBean mediaItem) {
            super.setData(mediaItem);
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
            if (mediaItem.getImage() != null && mediaItem.getImage().getDownload_url() != null && mediaItem.getImage().getDownload_url().get(0) != null) {
                Picasso.with(context).load(mediaItem.getImage().getDownload_url().get(0)).placeholder(R.drawable.bg_item).error(R.drawable.bg_item).into(imageView);
            }
        }
    }

    private class TextHolder extends BaseViewHolder {
        private final TextView tvContext;

        public TextHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(tv_context);
        }

        @Override
        public void setData(NetAudioBean.ListBean mediaItem) {
            super.setData(mediaItem);
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
        }
    }

    private class GifHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView imageView;
        private ImageOptions imageOption;

        public GifHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image_gif);
            imageOption = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(5))
                    .setSize(ActionMenuView.LayoutParams.WRAP_CONTENT, -2)
                    .setIgnoreGif(false)
                    .setLoadingDrawableId(R.drawable.video_default)
                    .setFailureDrawableId(R.drawable.video_default)
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .build();
        }

        @Override
        public void setData(NetAudioBean.ListBean mediaItem) {
            super.setData(mediaItem);
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
            if (mediaItem.getGif() != null && mediaItem.getGif().getImages() != null && mediaItem.getGif().getImages().get(0) != null) {
                x.image().bind(imageView, mediaItem.getGif().getImages().get(0), imageOption);
                Log.e("TAG", "gifurl -----" + mediaItem.getGif().getImages().get(0));
            }
        }
    }

    private class ADHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;
        Button btnInstall;

        public ADHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            btnInstall = (Button) itemView.findViewById(R.id.btn_install);
            ivImageIcon = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }
    }
}
