package kal.apka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class kalkurator {

    private JPanel panelMain;
    private JTextField textField1;
    private JTextArea textArea1;

    public kalkurator() {

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String dz = JOptionPane.showInputDialog("Wpisz dzialanie");

                String[] dzialanie = new String[20];
                dzialanie = dz.split(" ");
                int dl = dzialanie.length;
                String[] onp = new String[20];
                String[] stos = new String[20];
                int ds = 0, dn = 0;
                int z = 0, x = 0, m = 0, n = 0, t = 0, p = 0;

                for (int i = 0; i < dl; i++) {
                    x = 0;
                    if (dzialanie[i].equals("(")) {
                        stos[n] = dzialanie[i];
                        n++;
                        p++;
                    } else if (dzialanie[i].equals(")")) {
                        p++;

                        if (stos[n - 1].equals("(")) {
                            stos[n - 1] = "Null";
                            n--;

                        } else {
                            onp[m] = stos[n - 1];
                            m++;
                            stos[n - 1] = "Null";
                            n--;
                            stos[n - 1] = "Null";
                            n--;
                        }


                    } else {
                        if (dzialanie[i].equals("+") || dzialanie[i].equals("-")) {
                            x = 1;
                        } else if (dzialanie[i].equals("*") || dzialanie[i].equals("/") || dzialanie[i].equals("%")) {
                            x = 2;
                        } else if (dzialanie[i].equals("^") || dzialanie[i].equals("@")) {
                            x = 3;
                        } else {
                            onp[m] = dzialanie[i];
                            t = 1;
                            m++;

                        }

                        if (n > 0) {
                            if (stos[n - 1].equals("(")) {
                                z = 0;
                            } else if (stos[n - 1].equals("+") || stos[n - 1].equals("-")) {
                                z = 1;
                            } else if (stos[n - 1].equals("*") || stos[n - 1].equals("/") || stos[n - 1].equals("%")) {
                                z = 2;
                            } else if (stos[n - 1].equals("^") || stos[n - 1].equals("@")) {
                                z = 3;
                            }
                            if (x == 3) {
                                if (z == 3) {
                                    onp[m] = stos[n - 1];
                                    m++;
                                    stos[n - 1] = dzialanie[i];
                                } else {
                                    stos[n] = dzialanie[i];
                                    n++;
                                }
                            } else if (x == 2) {
                                if (z == 3) {
                                    onp[m] = stos[n - 1];
                                    m++;
                                    stos[n - 1] = dzialanie[i];
                                } else if (z == 2) {
                                    onp[m] = stos[n - 1];
                                    m++;
                                    stos[n - 1] = dzialanie[i];
                                } else {
                                    stos[n] = dzialanie[i];
                                    n++;
                                }
                            } else if (x == 1) {
                                if (z == 3 || z == 2) {
                                    onp[m] = stos[n - 1];
                                    stos[n - 1] = dzialanie[i];
                                    m++;
                                } else if (z == 1) {
                                    onp[m] = stos[n - 1];
                                    m++;
                                    stos[n - 1] = dzialanie[i];
                                } else {
                                    stos[n] = dzialanie[i];
                                    n++;
                                }
                            }


                        } else if (n == 0 && t != 1) {
                            stos[n] = dzialanie[i];
                            n++;
                        }

                    }
                    t = 0;


                }

                int v = dl - p;
                for (int i = dl - n - p; i < v; i++) {
                    onp[i] = stos[n - 1];
                    n--;
                }

                double w = 0;

                double[] tab = new double[20];
                int d = 0;
                for (int i = 0; i < v; i++) {
                    if (onp[i].equals("+")) {
                        w = tab[d - 2] + tab[d - 1];
                        tab[d - 2] = w;
                        d--;

                    } else if (onp[i].equals("-")) {
                        w = tab[d - 2] - tab[d - 1];
                        tab[d - 2] = w;
                        d--;

                    } else if (onp[i].equals("*")) {
                        w = tab[d - 2] * tab[d - 1];
                        tab[d - 2] = w;
                        d--;

                    } else if (onp[i].equals("/")) {
                        w = tab[d - 2] / tab[d - 1];
                        tab[d - 2] = w;
                        d--;

                    } else if (onp[i].equals("%")) {
                        w = tab[d - 2] % tab[d - 1];
                        tab[d - 2] = w;
                        d--;

                    } else if (onp[i].equals("^")) {
                        w = Math.pow(tab[d - 2], tab[d - 1]);
                        tab[d - 2] = w;
                        d--;
                    } else if (onp[i].equals("@")) {
                        w = sqrt(tab[d - 1]);
                        tab[d - 1] = w;
                    } else {
                        double kk = Double.parseDouble(onp[i]);
                        tab[d] = kk;
                        d++;
                    }

                }

                JOptionPane.showConfirmDialog(null, "wynik = " + w, "okienko", JOptionPane.CLOSED_OPTION);


            }
        });

        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                String txt = ("Opis znaków\n+ dodawanie\n- odejmowanie\n* mnozenie\n/ dzielenie\n@ pierwiaskowanie\n^ potegowanie(zapisz w nawiasie)\n" +
                        "% dzielenie z reszta\nMozesz uzywac nawiasow\n\nWpisz dzialanie na bialym tle\nkolejne znaki lub liczby oddzielaj spacją(np. 3 * ( 2 + 1 ))");
                textArea1.setText(txt);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("kalkulator");
        frame.setContentPane(new kalkurator().panelMain);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocation(600,300);
        frame.setVisible(true);
    }


}
