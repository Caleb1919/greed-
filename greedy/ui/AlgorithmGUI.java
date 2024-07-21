package ui;

import algorithms.Algorithm;
import algorithms.DivideAndConquer.*;
import algorithms.Greedy.*;
import tester.PerformanceTester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmGUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> algorithmComboBox;
    private JButton runButton;
    private JTextArea outputTextArea;
    private JPanel inputPanel;
    private JLabel algorithmPathLabel;

    public AlgorithmGUI() {
        setTitle("Algorithm Comparison");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel categoryLabel = new JLabel("Select Algorithm Category:");
        categoryComboBox = new JComboBox<>(new String[]{"Divide and Conquer", "Greedy"});
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);

        JLabel algorithmLabel = new JLabel("Select Algorithm:");
        algorithmComboBox = new JComboBox<>();
        topPanel.add(algorithmLabel);
        topPanel.add(algorithmComboBox);

        runButton = new JButton("Run Algorithm");
        topPanel.add(runButton);

        algorithmPathLabel = new JLabel("Algorithm Path: ");
        topPanel.add(algorithmPathLabel);

        add(topPanel, BorderLayout.NORTH);

        inputPanel = new JPanel();
        add(inputPanel, BorderLayout.CENTER);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.SOUTH);

        initializeComboBoxes();

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runAlgorithm();
            }
        });
    }

    private void initializeComboBoxes() {
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAlgorithmComboBox();
                updateInputFields();
            }
        });

        algorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInputFields();
                updateAlgorithmPath();
            }
        });

        updateAlgorithmComboBox();
        updateInputFields();
        updateAlgorithmPath();
    }

    private void updateAlgorithmComboBox() {
        algorithmComboBox.removeAllItems();
        if (categoryComboBox.getSelectedItem().equals("Divide and Conquer")) {
            algorithmComboBox.addItem("Strassen's Matrix Multiplication");
            algorithmComboBox.addItem("QuickHull");
            algorithmComboBox.addItem("QuickSort");
            algorithmComboBox.addItem("MergeSort");
        } else if (categoryComboBox.getSelectedItem().equals("Greedy")) {
            algorithmComboBox.addItem("Prim's MST");
            algorithmComboBox.addItem("Travelling Salesman Problem (Approximate)");
            algorithmComboBox.addItem("Kruskal's MST");
            algorithmComboBox.addItem("Dijkstra's Shortest Path");
            algorithmComboBox.addItem("Huffman Codes");
        }
    }

    private void updateInputFields() {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(0, 1));

        if (categoryComboBox.getSelectedItem().equals("Divide and Conquer")) {
            if (algorithmComboBox.getSelectedItem().equals("Strassen's Matrix Multiplication")) {
                inputPanel.add(new JLabel("Enter size of Matrix A and B:"));
                inputPanel.add(new JTextField(5));
                inputPanel.add(new JLabel("Enter elements of Matrix A:"));
                inputPanel.add(new JTextArea(5, 20));
                inputPanel.add(new JLabel("Enter elements of Matrix B:"));
                inputPanel.add(new JTextArea(5, 20));
            } else if (algorithmComboBox.getSelectedItem().equals("QuickHull")) {
                inputPanel.add(new JLabel("Enter points (x1,y1 x2,y2 ...):"));
                inputPanel.add(new JTextField(20));
            } else if (algorithmComboBox.getSelectedItem().equals("QuickSort") || algorithmComboBox.getSelectedItem().equals("MergeSort")) {
                inputPanel.add(new JLabel("Enter array elements (comma-separated):"));
                inputPanel.add(new JTextField(20));
            }
        } else if (categoryComboBox.getSelectedItem().equals("Greedy")) {
            if (algorithmComboBox.getSelectedItem().equals("Prim's MST") || algorithmComboBox.getSelectedItem().equals("Travelling Salesman Problem (Approximate)") || algorithmComboBox.getSelectedItem().equals("Kruskal's MST")) {
                inputPanel.add(new JLabel("Enter number of nodes in the graph:"));
                inputPanel.add(new JTextField(5));
                inputPanel.add(new JLabel("Enter adjacency matrix of the graph:"));
                inputPanel.add(new JTextArea(5, 20));
            } else if (algorithmComboBox.getSelectedItem().equals("Dijkstra's Shortest Path")) {
                inputPanel.add(new JLabel("Enter number of nodes in the graph:"));
                inputPanel.add(new JTextField(5));
                inputPanel.add(new JLabel("Enter adjacency matrix of the graph:"));
                inputPanel.add(new JTextArea(5, 20));
                inputPanel.add(new JLabel("Enter source node:"));
                inputPanel.add(new JTextField(5));
            } else if (algorithmComboBox.getSelectedItem().equals("Huffman Codes")) {
                inputPanel.add(new JLabel("Enter characters (comma-separated):"));
                inputPanel.add(new JTextField(20));
                inputPanel.add(new JLabel("Enter frequencies (comma-separated):"));
                inputPanel.add(new JTextField(20));
            }
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void updateAlgorithmPath() {
        String category = (String) categoryComboBox.getSelectedItem();
        String algorithm = (String) algorithmComboBox.getSelectedItem();
        if (category != null && algorithm != null) {
            algorithmPathLabel.setText("Algorithm Path: " + category + " -> " + algorithm);
        } else {
            algorithmPathLabel.setText("Algorithm Path: ");
        }
    }

    private void runAlgorithm() {
        String category = (String) categoryComboBox.getSelectedItem();
        String algorithm = (String) algorithmComboBox.getSelectedItem();

        Algorithm selectedAlgorithm = null;

        if (category.equals("Divide and Conquer")) {
            if (algorithm.equals("Strassen's Matrix Multiplication")) {
                JTextField sizeField = (JTextField) inputPanel.getComponent(1);
                JTextArea matrixATextArea = (JTextArea) inputPanel.getComponent(3);
                JTextArea matrixBTextArea = (JTextArea) inputPanel.getComponent(5);
                int[][] matrixA = parseMatrix(matrixATextArea.getText(), Integer.parseInt(sizeField.getText()));
                int[][] matrixB = parseMatrix(matrixBTextArea.getText(), Integer.parseInt(sizeField.getText()));
                selectedAlgorithm = new StrassenMatrixMultiplication(matrixA, matrixB);
            } else if (algorithm.equals("QuickHull")) {
                JTextField pointsField = (JTextField) inputPanel.getComponent(1);
                Point[] points = parsePoints(pointsField.getText());
                selectedAlgorithm = new QuickHull(points);
            } else if (algorithm.equals("QuickSort")) {
                JTextField arrayField = (JTextField) inputPanel.getComponent(1);
                int[] array = parseArray(arrayField.getText());
                selectedAlgorithm = new QuickSort(array);
            } else if (algorithm.equals("MergeSort")) {
                JTextField arrayField = (JTextField) inputPanel.getComponent(1);
                int[] array = parseArray(arrayField.getText());
                selectedAlgorithm = new MergeSort(array);
            }
        } else if (category.equals("Greedy")) {
            if (algorithm.equals("Prim's MST")) {
                JTextField sizeField = (JTextField) inputPanel.getComponent(1);
                JTextArea graphTextArea = (JTextArea) inputPanel.getComponent(3);
                int[][] graph = parseMatrix(graphTextArea.getText(), Integer.parseInt(sizeField.getText()));
                selectedAlgorithm = new PrimMST(graph);
            } else if (algorithm.equals("Travelling Salesman Problem (Approximate)")) {
                JTextField sizeField = (JTextField) inputPanel.getComponent(1);
                JTextArea graphTextArea = (JTextArea) inputPanel.getComponent(3);
                int[][] graph = parseMatrix(graphTextArea.getText(), Integer.parseInt(sizeField.getText()));
                selectedAlgorithm = new TSP(graph);
            } else if (algorithm.equals("Kruskal's MST")) {
                JTextField sizeField = (JTextField) inputPanel.getComponent(1);
                JTextArea graphTextArea = (JTextArea) inputPanel.getComponent(3);
                int[][] graph = parseMatrix(graphTextArea.getText(), Integer.parseInt(sizeField.getText()));
                selectedAlgorithm = new KruskalMST(graph);
            } else if (algorithm.equals("Dijkstra's Shortest Path")) {
                JTextField sizeField = (JTextField) inputPanel.getComponent(1);
                JTextArea graphTextArea = (JTextArea) inputPanel.getComponent(3);
                int[][] graph = parseMatrix(graphTextArea.getText(), Integer.parseInt(sizeField.getText()));
                JTextField sourceField = (JTextField) inputPanel.getComponent(5);
                int source = Integer.parseInt(sourceField.getText());
                selectedAlgorithm = new Dijkstra(graph, source);
            } else if (algorithm.equals("Huffman Codes")) {
                JTextField charsField = (JTextField) inputPanel.getComponent(1);
                char[] chars = parseChars(charsField.getText());
                JTextField freqField = (JTextField) inputPanel.getComponent(3);
                int[] freq = parseArray(freqField.getText());
                selectedAlgorithm = new HuffmanCodes(chars, freq);
            }
        }

        if (selectedAlgorithm != null) {
            PerformanceTester tester = new PerformanceTester(selectedAlgorithm);
            long startTime = System.nanoTime();
            tester.test();
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            outputTextArea.append("\nExecution time: " + duration + " nanoseconds\n");
        }
    }

    private int[] parseArray(String text) {
        String[] parts = text.trim().split(",");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i].trim());
        }
        return array;
    }

    private int[][] parseMatrix(String text, int size) {
        String[] rows = text.trim().split("\n");
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] cols = rows[i].trim().split(" ");
            for (int j = 0; j < cols.length; j++) {
                matrix[i][j] = Integer.parseInt(cols[j].trim());
            }
        }
        return matrix;
    }

    private Point[] parsePoints(String text) {
        String[] pairs = text.trim().split(" ");
        Point[] points = new Point[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            String[] coords = pairs[i].split(",");
            int x = Integer.parseInt(coords[0].trim());
            int y = Integer.parseInt(coords[1].trim());
            points[i] = new Point(x, y);
        }
        return points;
    }

    private char[] parseChars(String text) {
        String[] parts = text.trim().split(",");
        char[] chars = new char[parts.length];
        for (int i = 0; i < parts.length; i++) {
            chars[i] = parts[i].trim().charAt(0);
        }
        return chars;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlgorithmGUI gui = new AlgorithmGUI();
                gui.setVisible(true);
            }
        });
    }
}
