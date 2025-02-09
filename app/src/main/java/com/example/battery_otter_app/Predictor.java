package com.example.battery_otter_app;

import android.content.Context;
import org.tensorflow.lite.Interpreter;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.IOException;

public class Predictor {

    private static final float MEAN = 1525.1585714285713f;  // Mean value from training data
    private static final float STD_DEV = 819.1364144757152f; // Standard deviation from training data

    private Interpreter tflite; // TensorFlow Lite Interpreter

    public Predictor(Context context) {
        try {
            tflite = new Interpreter(loadModelFile(context)); // Load the model
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadModelFile(Context context) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(context.getAssets().openFd("battery_mode_model.tflite").getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = context.getAssets().openFd("battery_mode_model.tflite").getStartOffset();
        long declaredLength = context.getAssets().openFd("battery_mode_model.tflite").getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // Method to normalize battery drain value using mean and standard deviation
    private float normalizeBatteryDrain(float batteryDrain) {
        return (batteryDrain - MEAN) / STD_DEV;
    }

    public String makePrediction(float batteryDrain) {
        // Normalize the battery drain value using the training data mean and standard deviation
        float normalizedBatteryDrain = (batteryDrain - MEAN) / STD_DEV;

        float[][] input = new float[1][1];  // Input array for one sample with one feature
        input[0][0] = normalizedBatteryDrain;  // Assign normalized battery drain to input

        float[][] output = new float[1][3];  // Output array for three classes (modes)

        tflite.run(input, output);  // Run model inference

        int predictedModeIndex = argMax(output[0]);  // Get the class index with the highest probability

        // Convert prediction to a mode string
        String mode;
        switch (predictedModeIndex) {
            case 0:
                mode = "Battery over Performance";
                break;
            case 1:
                mode = "Balanced";
                break;
            case 2:
                mode = "Performance over Battery";
                break;
            default:
                mode = "Unknown";
                break;
        }
        return mode;  // Return the predicted mode
    }


    public void close() {
        if (tflite != null) {
            tflite.close();  // Close the interpreter
            tflite = null;   // Set to null for garbage collection
        }
    }

    private int argMax(float[] array) {
        int maxIndex = 0;  // Initialize max index to 0
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;  // Update max index if a higher value is found
            }
        }
        return maxIndex;  // Return the index of the highest value
    }
}
