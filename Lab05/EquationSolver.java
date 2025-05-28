import javax.swing.JOptionPane;

public class EquationSolver {
    public static void main(String[] args) {
        while (true) {
            String[] options = {
                "Solve linear equation (ax + b = 0)", 
                "Solve system of linear equations", 
                "Solve quadratic equation (ax^2 + bx + c = 0)", 
                "Exit"
            };
            int choice = JOptionPane.showOptionDialog(null, "Choose an equation to solve:", 
                    "Equation Solver", JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                solveLinearEquation();
            } else if (choice == 1) {
                solveLinearSystem();
            } else if (choice == 2) {
                solveQuadraticEquation();
            } else {
                break;
            }
        }
    }

    public static void solveLinearEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient b:"));

        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, "Infinite solutions.");
            } else {
                JOptionPane.showMessageDialog(null, "No solution.");
            }
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "Solution: x = " + x);
        }
    }

    public static void solveLinearSystem() {
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Enter a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Enter a12:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Enter b1:"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Enter a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Enter a22:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Enter b2:"));

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                JOptionPane.showMessageDialog(null, "Infinite solutions.");
            } else {
                JOptionPane.showMessageDialog(null, "No solution.");
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            JOptionPane.showMessageDialog(null, "Solution: x1 = " + x1 + ", x2 = " + x2);
        }
    }

    public static void solveQuadraticEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient c:"));

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "This is not a quadratic equation.");
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            JOptionPane.showMessageDialog(null, "Two distinct solutions: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "One double root: x = " + x);
        } else {
            JOptionPane.showMessageDialog(null, "No real solution.");
        }
    }
}
