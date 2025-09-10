package com.bonc.dpi.entity;

public class VideoLabel {
    private String label_type;//标签分类

    private String label_name;//标签名称

    private String video_type_code;

    private String video_type_name;

    public String getLabel_type() {
        return label_type;
    }

    public void setLabel_type(String label_type) {
        this.label_type = label_type;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getVideo_type_name() {
        return video_type_name;
    }

    public void setVideo_type_name(String video_type_name) {
        this.video_type_name = video_type_name;
    }

    public String getVideo_type_code() {
        return video_type_code;
    }

    public void setVideo_type_code(String video_type_code) {
        this.video_type_code = video_type_code;
    }
}
