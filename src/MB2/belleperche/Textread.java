package MB2.belleperche;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.tree.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.JTextComponent;

//import static jdk.vm.ci.meta.JavaKind.Char;


public class Textread {

    private JPanel mainPanel;
    private JTextField entrytext;
    private JButton Textread;
    private JButton Analisys;
    private JButton Clear;
    private JTree TREE;
    private JTextArea txt;
    private JButton ADD;
    private JProgressBar progressBar1;
    private JTextArea perform;
    private Scanner scan;
    int i = 0;int r =0;int subnode[] = new int[100];int s=0;int maxr=0;
    int cleartime = 0;int number[]=new int[100];int number1[]=new int[100];
    String test[] = new String[1000];String undernode[]= new String[1000];
    String Affichagetest[] = new String[1000];
    String Affichagesouscode[] = new String[1000];



    String[] decoup;
    DefaultMutableTreeNode entree = new DefaultMutableTreeNode("ENTRY");

    public Textread() {

        Cleartree();

        Textread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Still Working");
            }
        });

        Analisys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cleartree();                        // Arbre précédent effacé
                txt.setText(null);                  // Zone d'affichage de text effacée aussi
                String get = entrytext.getText();   // Récupération du text entré par l'utilisateur
                String rorw = "";                   // Définition d'une chaine de caractères

                for (int i = 0; i < get.length(); i++) {
                    rorw = "Start analyse";

                }

                JOptionPane.showMessageDialog(null, rorw); // Affichage du debut de l'analyse

                String filename = entrytext.getText(); // Stockage du texte entré dans une chaine de caractères

                try {
                    scan = new Scanner(new File(filename)); // Recherche du fichier texte
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Votre fichier est introuvable");
                }
                Affichage(); // Utilisation de la fonction Affichage
            }

        });

        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText(null);
                Codetest();
            }
        });

        ADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            r=0;
            s=0;
            DefaultMutableTreeNode V = null;
            int addchild=0;
                if (cleartime == 1) {
                    for (int a = 0; a < i; a++) {
                            if (addchild == 1) {
                                while (subnode[s] == 1) {
                                    System.out.println("OK");
                                    DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
                                    DefaultMutableTreeNode newinfo = new DefaultMutableTreeNode(undernode[r]);
                                    V.add(newinfo);
                                    model.reload();
                                    r++;
                                    s++;
                                }
                            }

                        V = Addanode(a);
                        s++;
                        if (subnode[s]==1){addchild =1;}
                    }
                        }

                        cleartime = cleartime - 1;
                    }

        });


        TREE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txt.setText(null);
                Codetest();
            }
        });
    }


    public static void main(String[] args) {

        // DEFINITION FENETRE PRINCIPALE
        JFrame frame = new JFrame("TextRead");
        frame.setContentPane(new Textread().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

    }

    public void Cleartree() {
        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        model.reload();
        root.add(entree);
        cleartime = cleartime + 1;

    }

    public void Affichage() {
        String code = "TITRE\n";                            // Definition d'une chaine de caractère
        String titre;                                       // Definition d'une chaine de caractère
        String action = "Code contenu dans chaque entrée";  // Definition d'une chaine de caractère
        int souscode =0;                                    // Definition d'un int et initialisation à 0
        titre = scan.nextLine();                            // La première ligne est entrée dans titre
        while (scan.hasNextLine()) {                        // On scanne toutes les lignes jusqu'à la dernière
            int id[] = new int[0];                          // Définition d'un tableau int
            String EorC = scan.next();                      // le permier caractère de la ligne est entré dans une variable
            //System.out.printf("%s", EorC);
            for (int j = 0; j >= 6; j++) {
                id[j] = scan.nextInt();                     // les 6 int suivants sont rentrés dans le tableau id

              //  System.out.printf("%s", id[j]);
            }
            if (souscode ==0){
            action = scan.nextLine();                       // le reste de la ligne rentre dans action
            code = code + "\n" + EorC + action;
            Affichagetest[i] = code;                        // On rentre dans un tableau le code correspondant
             }

            else if (souscode==1){
                action = scan.nextLine();                   // le reste de la ligne rentre dans action
                code = code + "\n"+ action;
                Affichagesouscode[r] = code;                // On rentre dans un tableau le code correspondant
            }

            System.out.printf("%s\n", action);
            String begin = "BEGIN";
            String begin1 = " BEGIN";
            String begin2 = "  BEGIN";
            int tree = action.indexOf(begin2);
            int two = action.indexOf(begin1);
            int one = action.indexOf(begin);
            if ( one == 1 || two ==1 || tree == 1 ) {      // Si la ligne contient le mot Begin
                souscode =0;                               // souscode remis à 0
                decoup = action.split("\\'");       // On découpe la ligne tout les '
                number[i] = getnum2(decoup);              // Utilisation de getnum2(dépend de i)
                number1[i] = getnum1(decoup);             // Utilisation de getnum1(dépend de i)
                if (number[i] == 0) {                     // En fonction du résultat de getnum2(depend de de i)
                    subnode[s]=0;
                    txt.append(decoup[1] + "\n");         // Affichage du nom du test dans la zone de text
                    txt.append(action + "\n");            // Affichage de la ligne en entier dans la zone de text
                    txt.append(number[i] + "\n");         // Affichage du retour de la fonction getnum2 (depend de i)
                    test[i] = decoup[1];                  // On rentre le nom de test dans un tableau (depend de i)
                    code = "TEST " + (number1[i]);        // On réinitialise la variable code (depend de i)
                    i++;                                  // On incrémente i
                }
                 else if (number[i] != 0) {
                    souscode=1;                           // la variable souscode vaut 1
                    undernode[r] = decoup[1];             // On rentre le nom du sous test dans un tableau
                    subnode[s]=1;                         // la variable subnode vaut 1 (depend de s)
                    txt.append(decoup[1] + "\n");
                    txt.append(action + "\n");
                    txt.append(number[i] + "\n");
                    code = "TEST " + (number1[i]) + "."+ (number[i]); // On réinitialise la variable code
                    r++;                                  // Incrémentation de r
                }
                s++;                                     // Incrémentation de s
            }
        }
        maxr = r;

    }

    public int getnum1(String tab[]) {
        int n = 0;

        String prenum = tab[1];
        String[] num = prenum.split("\\s");
        String ya = num[0];
        String[] youyou = ya.split("\\.");
        try {
            n = Integer.parseInt(youyou[0]);
        } catch (Exception e) {

        }

        return n;
    }


    public int getnum2(String tab0[]) {
        int m = 0;

        String prenum = tab0[1];
        String[] num = prenum.split("\\s");
        String ya = num[0];
        String[] youyou = ya.split("\\.");
        try {
            m = Integer.parseInt(youyou[1]);
        } catch (Exception e) {

        }

        return m;
    }


    private DefaultMutableTreeNode Addanode(int i) {

        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode etr = new DefaultMutableTreeNode(test[i]);

        if (!test[i].trim().equals("")) {
            entree.add(etr);
            model.reload();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "It doesn't Work MOTHERFUCKER");
        }
return etr;
    }


    private void Codetest(){

        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) TREE.getLastSelectedPathComponent();
        String V = selectNode.toString();
        String Perform ="PERFORM";
        if (selectNode != null) {
            System.out.println(V);
            for (int z = 0; z < i; z++) {
                if (V == test[z]) {
                    txt.append(Affichagetest[z + 1]);

                    Highlighter highlighter = txt.getHighlighter();
                    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
                    int p0 = Affichagetest[z + 1].indexOf(Perform);
                    int p1 = p0 + Perform.length();
                    try {
                        highlighter.addHighlight(p0, p1, painter );
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
                else if (V == undernode[z]) {
                    txt.append(Affichagesouscode[z + 1]);
                    Highlighter highlighter = txt.getHighlighter();
                    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
                    int p0 = Affichagetest[z + 1].indexOf(Perform);
                    int p1 = p0 + Perform.length();
                    try {
                        highlighter.addHighlight(p0, p1, painter );
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
                else i++;maxr++;
            }
        }
    }

    public void Progressbar(int a) {
        progressBar1.setValue('0');
            while (progressBar1.getValue()!=a) {
                    progressBar1.setValue(progressBar1.getValue() + 1);
            }
        }
    }