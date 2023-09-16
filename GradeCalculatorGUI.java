import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GradeCalculatorGUI extends JFrame implements ActionListener {
    private JLabel[] subjectLabels;
    private JTextField[] subjectTextFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        int numSubjects = 5; // Change this to the number of subjects you want to calculate for

        subjectLabels = new JLabel[numSubjects];
        subjectTextFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectTextFields[i] = new JTextField();
            add(subjectLabels[i]);
            add(subjectTextFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        add(calculateButton);
        add(totalMarksLabel);
        add(averagePercentageLabel);
        add(gradeLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = subjectTextFields.length;

            for (int i = 0; i < numSubjects; i++) {
                try {
                    int marks = Integer.parseInt(subjectTextFields[i].getText());
                    totalMarks += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for Subject " + (i + 1));
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / (numSubjects * 100);
            String grade;

            if (averagePercentage >= 0.9) {
                grade = "A";
            } else if (averagePercentage >= 0.8) {
                grade = "B";
            } else if (averagePercentage >= 0.7) {
                grade = "C";
            } else if (averagePercentage >= 0.6) {
                grade = "D";
            } else {
                grade = "F";
            }

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + (averagePercentage * 100) + "%");
            gradeLabel.setText("Grade: " + grade);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeCalculatorGUI().setVisible(true);
            }
        });
    }
}
