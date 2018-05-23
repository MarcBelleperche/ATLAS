package MB2.belleperche;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.*;

//import static jdk.vm.ci.meta.JavaKind.Char;


public class Textread {

    JPanel mainPanel;
    private JTextField entrytext;
    private JButton Textread;
    private JButton Analisys;
    private JButton Search;
    private JTree TREE;
    private JTextArea txt;
    private JButton ADD;
    private JProgressBar progressBar1;
    private JButton Help;
    private JTextField WTS;
    private JButton LightDark;
    private JCheckBox Comment;
    private JButton DefineFrame;
    private Scanner scan;
    private JList Perfromlist;
    int click = 0;
    int mbdefine = 0;
    int i = 0;
    int r = 0;
    int adddefine = 0;
    int subnode[] = new int[100];
    int s = 0;
    int maxr = 0;
    int cleartime = 0;
    int o;
    int number[] = new int[100];
    int number1[] = new int[100];
    String test[] = new String[1000];
    String undernode[] = new String[1000];
    String Affichagetest[] = new String[1000];
    String Presenceperform[] = new String[1000];
    String Affichagesouscode[] = new String[1000];
    String AffichagesouscodeWC[] = new String[1000];
    String Definetab[] = new String[1000];
    String definename[] = new String[1000];
    String[] decoup;
    DefaultMutableTreeNode entree = new DefaultMutableTreeNode("ENTRY");
    DefaultMutableTreeNode DEFINE = new DefaultMutableTreeNode("DEFINE");
    DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();

    DefaultListModel modelist = new DefaultListModel();


    private Highlighter.HighlightPainter hPainter = new HPainter(Color.YELLOW);

    public Textread() {
        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        Color LBtxt = txt.getBackground();
        Color LFtxt = txt.getForeground();
        Color TBtree = TREE.getBackground();
        Color TFtree = TREE.getForeground();
        Color MFpanel = TREE.getForeground();


        String Introtext = " \nCette application a pour but de simplifier la lecture des programmes ATLAS \n " +
                "Abbreviated Test Language for All Systems. Voici les différentes étapes à suivre : \n\n " +
                "1 : Entrée la path votre fichier \n 2 : Appuyer sur Analysis \n 3 : Appuyer en suite 2 fois sur" +
                "fill the Tree \n 4 : Pour afficher les codes correspondants à chaque entrée cliquer sur celles-ci \n\n" +
                "Les entrées ayant des sous entrées sont représentées par des dossiers\n Vous pouvez appuyer sur le bouton HELP a tout moment\n" +
                "Les Perfom seront suligné en Jaune\n\n" +
                "Si vous chercher un mot rentré le dans le champs en bas\n\n" +
                "Puis appuyé sur Search pour surligner toutes les occurences de ce mot";
        txt.setText(Introtext);

        Cleartree();

        Textread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Still Working, All clear");
                Cleartree();
                txt.setText(null);
                cleartime = 0;
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

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Mot = WTS.getText().toUpperCase();
                addHighlight(txt, Mot, hPainter);
            }


        });

        ADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filltree();
            }

        });


        TREE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                txt.setText(null);
                Comment.setSelected(true);
                Codetest();

            }

        });

        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText(Introtext);
            }
        });


        LightDark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (click == 0) {
                    txt.setBackground(Color.GRAY);
                    txt.setForeground(Color.lightGray);
                    TREE.setBackground(Color.GRAY);
                    TREE.setForeground(Color.lightGray);
                    mainPanel.setBackground(Color.DARK_GRAY);
                    click = 1;
                } else if (click == 1) {
                    txt.setBackground(LBtxt);
                    txt.setForeground(LFtxt);
                    TREE.setBackground(TBtree);
                    TREE.setForeground(TFtree);
                    mainPanel.setBackground(MFpanel);
                    click = 0;
                }


            }
        });
        Comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Comment.isSelected()) {
                    txt.setText(null);
                    Codetest();
                } else {
                    Enlevercomment();
                }
            }
        });


        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String select = txt.getSelectedText();
                if (txt.getSelectedText() != null) {
                    JOptionPane.showMessageDialog(null, select);

                }


            }
        });


        DefineFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("Perform/Define");
                frame2.setContentPane(new Perform().Performpanel);
                frame2.pack();
                frame2.setVisible(true);
                frame2.setSize(800, 400);
                frame2.setLocationRelativeTo(null);

                Perform perform = new Perform();
                JOptionPane.showMessageDialog(null, definename[1]);
                String defin = definename[1];
                perform.addnode();


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
        entree.removeAllChildren();
        cleartime = cleartime + 1;

    }

    public void Affichage() {
        String code = "TITRE\n";                            // Definition d'une chaine de caractère
        String codedef = "FIRST DEFINE\n";
        String titre;                                       // Definition d'une chaine de caractère
        String action = "Code contenu dans chaque entrée";  // Definition d'une chaine de caractère
        int nightynine = 99;
        int souscode = 0;                                    // Definition d'un int et initialisation à 0
        int youyou = 0;
        int SandEdefine = 0;
        titre = scan.nextLine();                            // La première ligne est entrée dans titre
        while (scan.hasNextLine()) {                        // On scanne toutes les lignes jusqu'à la dernière

            //action = scan.nextLine();
            //com = action.split("\\s");
            int id[] = new int[0];                          // Définition d'un tableau int
            String EorC = scan.next();                      // le permier caractère de la ligne est entré dans une variable

            System.out.printf("%s", EorC);
            for (int j = 0; j >= 6; j++) {
                id[j] = scan.nextInt();                     // les 6 int suivants sont rentrés dans le tableau id

                System.out.printf("%s", id[j]);
            }
            if (souscode == 0) {
                action = scan.nextLine();                       // le reste de la ligne rentre dans action
                code = code + "\n" + EorC + "  " + action;
                Affichagetest[i] = code;                        // On rentre dans un tableau le code correspondant

            } else if (souscode == 1) {
                action = scan.nextLine();                   // le reste de la ligne rentre dans action
                code = code + "\n" + EorC + "  " + action;
                Affichagesouscode[r] = code;                // On rentre dans un tableau le code correspondant
            }
            System.out.printf("%s\n", action);

            //String findefine = " END, '"+definename[o]+"' $";
            //int nbfindefine = action.indexOf(findefine);

            try {
                youyou = Integer.parseInt(EorC);
            } catch (NumberFormatException nfe) {
                System.out.println("Ce n'est pas un entier");
            }

            if (SandEdefine == 1) {
                codedef = codedef + "\n" + EorC + " " + action;
                Definetab[o] = codedef;
            }

            if (youyou == nightynine) {
                SandEdefine = 0;
            }


            String Define = "DEFINE";
            String Define2 = " DEFINE";
            String Define3 = "  DEFINE";
            int nbdefine = action.indexOf(Define);
            int nbdefine2 = action.indexOf(Define2);
            int nbdefine3 = action.indexOf(Define3);

            if (nbdefine == 1 || nbdefine2 == 1 || nbdefine3 == 1) {
                decoup = action.split("\\'");
                txt.append(decoup[1] + "\n");
                definename[o] = decoup[1];
                DefaultMutableTreeNode newdefine = new DefaultMutableTreeNode(definename[o]);
                DEFINE.add(newdefine);
                model.reload();
                codedef = "DEFINE" + o + "\n";
                SandEdefine = 1;
                o++;
            }


            String begin = "BEGIN";
            String begin1 = " BEGIN";
            String begin2 = "  BEGIN";
            int tree = action.indexOf(begin2);
            int two = action.indexOf(begin1);
            int one = action.indexOf(begin);
            if (one == 1 || two == 1 || tree == 1) {      // Si la ligne contient le mot Begin
                souscode = 0;                               // souscode remis à 0
                decoup = action.split("\\'");       // On découpe la ligne tout les '
                number[i] = getnum2(decoup);              // Utilisation de getnum2(dépend de i)
                number1[i] = getnum1(decoup);             // Utilisation de getnum1(dépend de i)
                if (number[i] == 0) {                     // En fonction du résultat de getnum2(depend de de i)
                    subnode[s] = 0;
                    txt.append(decoup[1] + "\n");         // Affichage du nom du test dans la zone de text
                    txt.append(action + "\n");            // Affichage de la ligne en entier dans la zone de text
                    txt.append(number[i] + "\n");         // Affichage du retour de la fonction getnum2 (depend de i)
                    test[i] = decoup[1];                  // On rentre le nom de test dans un tableau (depend de i)
                    code = "TEST " + (number1[i]);        // On réinitialise la variable code (depend de i)
                    System.out.println(test[i]);
                    i++;                                  // On incrémente i
                } else if (number[i] != 0) {
                    souscode = 1;                           // la variable souscode vaut 1
                    undernode[r] = decoup[1];             // On rentre le nom du sous test dans un tableau
                    subnode[s] = 1;                         // la variable subnode vaut 1 (depend de s)
                    txt.append(decoup[1] + "\n");
                    txt.append(action + "\n");
                    txt.append(number[i] + "\n");
                    code = "TEST " + (number1[i]) + "." + (number[i]); // On réinitialise la variable code

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
        } else {
            JOptionPane.showMessageDialog(null, "It doesn't Work MOTHERFUCKER");
        }
        return etr;
    }


    private void Codetest() {

        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) TREE.getLastSelectedPathComponent();
        String V = selectNode.toString();
        String Perform = "PERFORM";
        if (selectNode != null) {
            System.out.println(V);
            for (int z = 0; z < i; z++) {
                if (V == test[z]) {
                    txt.append(Affichagetest[z + 1]);
                    addHighlight(txt, Perform, hPainter);
                } else if (V == undernode[z]) {
                    txt.append(Affichagesouscode[z + 1]);
                    addHighlight(txt, Perform, hPainter);
                } else if (V == definename[z]) {
                    txt.append(definename[z] + "\n" + Definetab[z + 1]);
                    addHighlight(txt, Perform, hPainter);
                } else i++;

            }
        }
    }


    public void addHighlight(final JTextComponent tcomp, final String word, Highlighter.HighlightPainter youyou) {
        // Supprime les anciens
        removeHighlights(tcomp);

        try {
            final Highlighter h = tcomp.getHighlighter();
            final Document doc = tcomp.getDocument();
            final String fullText = doc.getText(0, doc.getLength());
            int pos = 0;

            // Recherche du "word"
            while ((pos = fullText.indexOf(word, pos)) >= 0) {
                // Ajout du nouveau painter
                h.addHighlight(pos, pos + word.length(), youyou);
                // On avance pour la suite
                pos += word.length();
            }
        } catch (final BadLocationException e) {
            e.printStackTrace();
        }

    }

    // Supprime les HighlightPainter de type HPainter
    public void removeHighlights(final JTextComponent textComp) {
        final Highlighter her = textComp.getHighlighter();
        final Highlighter.Highlight[] h = her.getHighlights();
        for (int i = 0; i < h.length; ++i) {
            // Si c'est le notre on delete
            if (HPainter.class.isInstance(h[i].getPainter()))
                her.removeHighlight(h[i]);
        }
    }

    // Le passage par une classe privée permet d'isoler nos HighlightPainter au
    // moment du remove
    class HPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public HPainter(final Color color) {
            super(color);
        }
    }


    public void Progressbar(int a) {
        if (progressBar1.getValue() < a) {
            progressBar1.setValue(progressBar1.getValue() + 1);
        }
    }


    public void Filltree() {
        r = 0;
        s = 0;
        DefaultMutableTreeNode V = null;
        int addchild = 0;
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
                if (a == 0) {
                    V.add(DEFINE);
                }
                s++;
                if (subnode[s] == 1) {
                    addchild = 1;
                }
            }
        }

        cleartime = cleartime - 1;
    }

    public String Scanfunction(Scanner nomscanner, String tableau[], int a, String searchword) {
        String nextligne = "";
        String textascanner = tableau[a];
        nomscanner = new Scanner(new String((textascanner)));

        while (nomscanner.hasNextLine()) {

            nextligne = nomscanner.nextLine();
            int nombredesearchword = nextligne.indexOf(searchword);
            if (nombredesearchword == 1) {
                // executer une Action propre a votre code
                textascanner = textascanner + "\n";
            } else {
                textascanner = textascanner + "\n" + nextligne;
            }

        }
        return textascanner;

    }

    public int indexOfpos(String chaine, char lettre) {
        //on parcoure les caracteres de la chaine
        for (i = 0; i < chaine.length(); i++) {
            if (chaine.charAt(i) == lettre)   //caractere trouvé
                return i;   //on retourne sa position
        }

        //si on est là c'est que la lettre n'y est pas
        return -1;
    }

    public void Enlevercomment() {
        StringBuilder sb = new StringBuilder();
        String[] lignes = txt.getText().split("\n");
        for (int nbl = 0; nbl < lignes.length; nbl++) {
            int pc = indexOfpos(lignes[nbl], 'C');
            if (pc == 0) {
                lignes[nbl] = "";
            } else {
                lignes[nbl] = lignes[nbl] + "\n";
            }
            sb.append(lignes[nbl]);
        }
        txt.setText(sb.toString());
    }

    public String getDefinename(int nb) {
        return definename[nb];
    }


    /*static class Performframe extends Textread{

        JPanel Performpanel;
        JTextArea Performcode;
        JList PerformList;
        Textread textread = new Textread();


        public Performframe() {
            addlist(definename[1]);

        }



        public void addlist (String nomajouter){

            System.out.println(nomajouter);
            modellist.addElement(nomajouter);
            PerformList.setModel(modellist);
        }


        public static void main(String[] args) {

            JFrame frame2 = new JFrame("Perform/Define");
            frame2.setContentPane(new Performframe().Performpanel);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.pack();
            frame2.setVisible(true);
            frame2.setSize(800, 400);
            frame2.setLocationRelativeTo(null);

        }

        public String[] Define(){
        String [] define = textread.definename;
            return define ;
        }


    }*/
}

