package com.example.yolov5tfliteandroid.utils;

import android.graphics.RectF;

public class BoundingBox {
    public int x1, y1, x2, y2;
    public double conf;

    public BoundingBox(int x1, int y1, int x2, int y2, double conf) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.conf = conf;
    }
    public String getLabelName() {
        return "box";
    }
    public Double getConfidence() {
        return this.conf;
    }
    public RectF getLocation() {
        return new RectF(this.x1, this.y1, this.x2, this.y2);
    }
}
