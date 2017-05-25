package atguigu.com.mobilvideo321.domain;

import java.io.Serializable;

/**
 * 作者：李银庆 on 2017/5/25 13:04
 */
public class MediaInfo implements Serializable{
    private String name;
    private long duration;
    private long size;
    private String data;

    public MediaInfo(String name, long duration, long size, String data) {
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
