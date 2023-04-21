import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trojuhelnik extends JFrame {
    private JButton lzeSestrojitButton;
    private JButton aButton;
    private JTextField cField;
    private JTextField bField;
    private JTextField aField;
    private JPanel panel;

    public Trojuhelnik() {
        initComponents();
    }

    JFileChooser jFileChooser = new JFileChooser(".");

    List<Strany> stranyList = new ArrayList<>();

    public void initComponents() {
        aButton.addActionListener(e -> DoplnA());
        lzeSestrojitButton.addActionListener(e -> lzeSestrojit());
    }

    public void DoplnA() {
        if (aField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vyplňte pole A");
        } else {
            int a = Integer.parseInt(aField.getText());
            bField.setText(String.valueOf(a));
            cField.setText(String.valueOf(a));
        }
    }

    public void lzeSestrojit() {
        try {
            int a = Integer.parseInt(aField.getText());
            int b = Integer.parseInt(bField.getText());
            int c = Integer.parseInt(cField.getText());
            if ((a * a + b * b) == (c * c)) {
                JOptionPane.showMessageDialog(this, "Z těchto hodnot lze sestrojit trojúhelník.", "Úspěch", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Z těchto hodnot nelze sestrojit trojúhelník.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platná čísla.", "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void nacteni() {
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                aField.setText(parts[0]);
                bField.setText(parts[1]);
                cField.setText(parts[2]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platné čísla. Zadejte prosím platné čísla.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ulozeni() {
        int returnVal = jFileChooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                int a = Integer.parseInt(aField.getText());
                int b = Integer.parseInt(bField.getText());
                int c = Integer.parseInt(cField.getText());
                writer.printf("%d;%d;%d", a, b, c);
                aField.setText("");
                bField.setText("");
                cField.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platné.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        Trojuhelnik t = new Trojuhelnik();
        t.setVisible(true);
        t.setContentPane(t.panel);
        t.setSize(500, 250);
        t.setLocation(450, 250);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuSoubor = new JMenu("Soubor");
        JMenu menuAkce = new JMenu("Akce");
        menuBar.add(menuSoubor);
        menuBar.add(menuAkce);
        JMenuItem uloz = new JMenuItem("Ulož");
        JMenuItem nacti = new JMenuItem("Načti");
        JMenuItem abc = new JMenuItem("A->(B, C)");
        JMenuItem jetrojuhelnik = new JMenuItem("lze sestrojit trojúhelník");
        menuSoubor.add(uloz);
        menuSoubor.add(nacti);
        menuAkce.add(abc);
        menuAkce.add(jetrojuhelnik);
        t.setJMenuBar(menuBar);
        uloz.addActionListener(e -> t.ulozeni());
        nacti.addActionListener(e -> t.nacteni());

    }
}
