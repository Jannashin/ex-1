package src;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class QuickSortTimeComplexity {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        XYSeries series = new XYSeries("Quicksort Time Complexity");

        for (int size : sizes) {
            long duration = measureQuicksortTime(size);
            series.add(size, duration);
            System.out.println("Array size: " + size + " | Time taken: " + duration + " ms");
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Quicksort Time Complexity",
                "Array Size",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        JFrame frame = new JFrame("Quicksort Time Complexity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static long measureQuicksortTime(int size) {
        int[] randomArray = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            randomArray[i] = rand.nextInt(size);
        }

        long startTime = System.nanoTime();
        quicksort(randomArray, 0, randomArray.length - 1);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000; // convert to milliseconds
    }

    public static void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
