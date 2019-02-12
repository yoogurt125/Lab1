import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by java on 19.11.18.
 */
public class GuiForm {
    private JPanel Window1;
    private JTextArea textAreaSequence;
    private JTextField indexTextField;
    private JTextField indexTextField2;
    private JTextField indexTextField3;
    private JTextField indexTextField1;
    private JTextArea textAreaSecondIndex;
    private JTextArea textAreaFirstIndex;
    private JTextArea textAreaIndex;
    private JTextArea textAreaThirdIndex;
    private JButton button1;
    private JTextArea textAreaSequenceChanged;
    private JTextArea textAreaResults;
    private JTextArea textAreaTempSequence;
    private JButton dodajZPrawejButton;
    private JButton dodajZLewejButton;
    private JButton odejmijZPrawejButton;
    private JButton odejmijZLewejButton;

    static int idx = 0;
    static int prawyIdx = 0;
    static int lewyIdx = 0;
    String sequence = "";
    String sequenceAfterChange = "";
    String tempSequence = "";
    String replacement = "";
    int mismatch = 0;
    double gc_procent; // = procent_gc(testowyString);
    int n_ilosc_znakow = 0; // = testowyString.length();
    double mismatch_procent = 0; // (double) mismatch/(double) n_ilosc_znakow * 100;
    double temperatura = 0; // 81.5 + 0.41*(gc_procent) - (675/n_ilosc_znakow) - mismatch_procent;

    public GuiForm() {
        textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx = Integer.parseInt(textAreaIndex.getText());
                prawyIdx = idx + 2;
                lewyIdx = idx - 1;
                replacement = textAreaFirstIndex.getText() + textAreaSecondIndex.getText() + textAreaThirdIndex.getText();
                sequence = textAreaSequence.getText();
                sequenceAfterChange = sequence.substring(0, idx - 1) + replacement + sequence.substring(idx + 2, sequence.length());
                textAreaSequenceChanged.setText(sequenceAfterChange);
                mismatch = licznik_roznych_znakow(sequence, sequenceAfterChange);
                tempSequence = sequenceAfterChange.substring(idx - 1, idx + 2);
                n_ilosc_znakow = tempSequence.length();
                mismatch_procent = (double) mismatch / (double) n_ilosc_znakow * 100;
                gc_procent = procent_gc(tempSequence);
                temperatura = 81.5 + 0.41 * (gc_procent) - (double) (675 / n_ilosc_znakow) - mismatch_procent;


                textAreaTempSequence.setText(tempSequence);
                textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                        "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                        "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);
            }
        });

        dodajZLewejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lewyIdx >= 1) {

                    tempSequence = sequenceAfterChange.charAt(lewyIdx - 1) + tempSequence;
                    textAreaTempSequence.setText(tempSequence);
                    n_ilosc_znakow = tempSequence.length();
                    mismatch_procent = (double) mismatch / (double) n_ilosc_znakow * 100;
                    gc_procent = procent_gc(tempSequence);
                    temperatura = 81.5 + 0.41 * (gc_procent) - (double) (675 / n_ilosc_znakow) - mismatch_procent;
                    lewyIdx--;
                }
                textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                        "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                        "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);
            }
        });
        dodajZPrawejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prawyIdx < sequenceAfterChange.length()) {

                    tempSequence = tempSequence + sequenceAfterChange.charAt(prawyIdx);
                    textAreaTempSequence.setText(tempSequence);
                    n_ilosc_znakow = tempSequence.length();
                    mismatch_procent = (double) mismatch / (double) n_ilosc_znakow * 100;
                    gc_procent = procent_gc(tempSequence);
                    temperatura = 81.5 + 0.41 * (gc_procent) - (double) (675 / n_ilosc_znakow) - mismatch_procent;
                    prawyIdx++;
                }
                textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                        "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                        "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);
            }
        });
        odejmijZLewejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lewyIdx < idx - 1) {
                    tempSequence = tempSequence.substring(1, tempSequence.length());
                    mismatch_procent = (double) mismatch / (double) n_ilosc_znakow * 100;
                    n_ilosc_znakow = tempSequence.length();
                    gc_procent = procent_gc(tempSequence);
                    temperatura = 81.5 + 0.41 * (gc_procent) - (double) (675 / n_ilosc_znakow) - mismatch_procent;
                    lewyIdx++;
                }
                textAreaTempSequence.setText(tempSequence);
                textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                        "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                        "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);
            }
        });
        odejmijZPrawejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prawyIdx > idx + 2) {
                    tempSequence = tempSequence.substring(0, tempSequence.length() - 1);
                    mismatch_procent = (double) mismatch / (double) n_ilosc_znakow * 100;
                    n_ilosc_znakow = tempSequence.length();
                    gc_procent = procent_gc(tempSequence);
                    temperatura = 81.5 + 0.41 * (gc_procent) - (double) (675 / n_ilosc_znakow) - mismatch_procent;
                    prawyIdx--;
                }
                textAreaTempSequence.setText(tempSequence);
                textAreaResults.setText(" Index: " + idx + "\n Number of Characters(25<=n<=45): " + n_ilosc_znakow +
                        "\n Replaced Sequence: " + replacement + "\n Mismatch: " + mismatch +
                        "\n Mismatch %: " + mismatch_procent + "\n GC %: " + gc_procent + "\n TEMPERATURE (>=78°C): " + temperatura);
            }
        });
    }

    public static int licznik_roznych_znakow(String str1, String str2) {
        int licznik = 0;
        if (str1 == null) {
            return -1;
        }
        if (str2 == null) {
            return -1;
        }
        for (int i = 0; i < str1.length(); i++) {

            char chA = str1.charAt(i);
            char chB = str2.charAt(i);
            if (chA != chB) {
                licznik++;
            }
        }
        return licznik;
    }

    public static double procent_gc(String str1) {
        if (str1 == null) {
            return -1;
        }
        double licznik = 0;

        for (int i = 0; i < str1.length(); i++) {
            char znak = str1.charAt(i);
            if (znak == 'G' || znak == 'C') {
                licznik++;
            }
        }
        double wynik_procent = licznik / (double) (str1.length()) * 100;
        return wynik_procent;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Primer Design Tool");
        frame.setContentPane(new GuiForm().Window1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);


    }


}
