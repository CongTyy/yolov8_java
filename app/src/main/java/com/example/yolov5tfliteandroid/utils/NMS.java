package com.example.yolov5tfliteandroid.utils;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NMS {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<BoundingBox> applyNMS(ArrayList<BoundingBox> boxes, double overlapThreshold) {
        ArrayList<BoundingBox> selectedBoxes = new ArrayList<>();

        // Sort the boxes based on confidence in descending order
        Collections.sort(boxes, (box1, box2) -> Double.compare(box2.conf, box1.conf));

        while (!boxes.isEmpty()) {
            // Select the box with the highest confidence (top box)
            BoundingBox topBox = boxes.remove(0);
            selectedBoxes.add(topBox);

            // Remove boxes with high overlap with the selected box
            boxes.removeIf(box -> calculateIoU(topBox, box) > overlapThreshold);
        }

        return selectedBoxes;
    }

    private static double calculateIoU(BoundingBox box1, BoundingBox box2) {
        // Implement the Intersection over Union (IoU) calculation
        // Calculate the intersection area
        int xI = Math.max(box1.x1, box2.x1);
        int yI = Math.max(box1.y1, box2.y1);
        int xU = Math.min(box1.x2, box2.x2);
        int yU = Math.min(box1.y2, box2.y2);

        int intersectionArea = Math.max(0, xU - xI + 1) * Math.max(0, yU - yI + 1);

        // Calculate the area of each box
        int areaBox1 = (box1.x2 - box1.x1 + 1) * (box1.y2 - box1.y1 + 1);
        int areaBox2 = (box2.x2 - box2.x1 + 1) * (box2.y2 - box2.y1 + 1);

        // Calculate the union area
        int unionArea = areaBox1 + areaBox2 - intersectionArea;

        // Calculate and return IoU
        return (double) intersectionArea / unionArea;
    }
}
