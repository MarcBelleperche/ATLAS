package MB2.belleperche;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.*;

import static java.awt.Font.BOLD;
import static java.awt.Font.getFont;

//import static jdk.vm.ci.meta.JavaKind.Char;


public class Textread {

    private JPanel mainPanel;
    private JTextField entrytext;
    private JButton Textread;
    private JButton Analisys;
    private JButton Search;
    JTree TREE;
    private JTextArea txt;
    private JButton ADD;
    private JProgressBar progressBar1;
    private JButton Help;
    private JTextField WTS;
    private JButton LightDark;
    private JCheckBox Comment;
    private JButton DefineFrame;
    private JButton back;
    private BufferedReader readertext;
    int click = 0;
    int nbperform = 0;
    int nb = 0;
    int okperf = 0;
    int nbAtlasmodule = 0;
    int i = 0;
    int r = 0;
    int subnode[] = new int[100];
    int s = 0;
    int maxr = 0;
    int maxnb = 0;
    int cleartime = 0;
    int presencemotrecherche1[] = new int[1000];int presencemotrecherche2[]= new int[1000];int presencemotrecherche3[]= new int[1000];
    int presencemotrecherche4[]= new int[1000];int presencemotrecherche5[]= new int[1000];
    static int nbdefine;
    static int nbrequire = 0;
    static int nbFdefinie;
    int number[] = new int[100];
    int number1[] = new int[100];
    static String Mot = null;
    String test[] = new String[1000];
    String undernode[] = new String[1000];
    String Affichagetest[] = new String[1000];
    String Alasmoduletab[] = new String[1000];
    String Atlasmodulename[] = new String[1000];
    String Requirename[] = new String[1000];
    String Requirecode[] = new String[1000];
    String Affichagesouscode[] = new String[1000];
    String Performname[] = new String[1000];
    static String Definetab[] = new String[1000];
    static String definename[] = new String[1000];
    static String[] decoup;
    static String select;
    DefaultMutableTreeNode entree = new DefaultMutableTreeNode("ENTRY");
    DefaultMutableTreeNode DEFINE = new DefaultMutableTreeNode("DEFINE");
    DefaultMutableTreeNode ATLASMODULE = new DefaultMutableTreeNode("ATLAS MODULE");
    DefaultMutableTreeNode REQUIRE = new DefaultMutableTreeNode("REQUIRE");
    DefaultMutableTreeNode Performdoss[] = new DefaultMutableTreeNode[1000];
    DefaultMutableTreeNode Drawing = new DefaultMutableTreeNode("DRAWING");
    DefaultMutableTreeNode newinfo[] = new DefaultMutableTreeNode[1000];

    DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();

    private Highlighter.HighlightPainter hPainter = new HPainter(Color.YELLOW);
    private Highlighter.HighlightPainter redpainter = new HPainter(Color.RED);


    public Textread() {
        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        Color LBtxt = txt.getBackground();
        Color LFtxt = txt.getForeground();
        Color TBtree = TREE.getBackground();
        Color TFtree = TREE.getForeground();
        Color MFpanel = TREE.getForeground();
        progressBar1.setMaximum(1000);
        progressBar1.setValue(0);

        String Introtext = " \nCette application a pour but de simplifier la lecture des programmes ATLAS \n " +
                "Abbreviated Test Language for All Systems. Voici les différentes étapes à suivre : \n\n " +
                "1 : Entrée la path votre fichier \n 2 : Appuyer sur Analysis \n 3 : Appuyer en suite sur" +
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
                    //scan = new Scanner(new File(filename)); // Recherche du fichier texte
                    readertext = new BufferedReader(new FileReader(filename));

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Votre fichier est introuvable");
                }


                Affichagewithbuffer();


            }

        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String perform = "PERFORM";
                Mot = WTS.getText().toUpperCase();
                addHighlight(txt, perform,Mot, hPainter,redpainter);
                String searchword = "Occurence du mot " + Mot + " dans les dossiers suivants :";

                    for (int z = 0; z < i; z++) {
                        if (Mot != null) {
                            int numb = Affichagetest[z + 1].indexOf(Mot);
                            if (numb != -1) {
                                presencemotrecherche1[z] = 1;
                                String colornode = entree.getChildAt(z).toString();
                                searchword = searchword + "\n" + colornode;
                            }
                        }
                    }
                for (int z = 0; z < maxr; z++) {
                    if (Mot != null) {
                        int numb = Affichagesouscode[z + 1].indexOf(Mot);
                        if (numb != -1) {
                            presencemotrecherche2[z] = 1;
                            String colornode = newinfo[z].toString();
                            searchword = searchword + "\n"+ colornode ;
                        }
                    }
                }

                for (int z = 0; z < nbdefine; z++) {
                    if (Mot != null) {
                        int numb = Definetab[z + 1].indexOf(Mot);
                        if (numb != -1) {
                            presencemotrecherche3[z] = 1;
                            String colornode = DEFINE.getChildAt(z).toString();
                            searchword = searchword +"\n" +DEFINE.toString() +": "+colornode ;
                        }
                    }
                }

                for (int z = 0; z < nbAtlasmodule; z++) {
                    if (Mot != null) {
                        int numb = Alasmoduletab[z + 1].indexOf(Mot);
                        if (numb != -1) {
                            presencemotrecherche4[z] = 1;
                            String colornode = ATLASMODULE.getChildAt(z).toString();
                            searchword = searchword +"\n" +ATLASMODULE.toString() +": "+colornode ;
                        }
                    }
                }

                for (int z = 0; z < nbrequire; z++) {
                    if (Mot != null) {
                        int numb = Requirecode[z + 1].indexOf(Mot);
                        if (numb != -1) {
                            presencemotrecherche5[z] = 1;
                            String colornode = REQUIRE.getChildAt(z).toString();
                            searchword = searchword +"\n" +REQUIRE.toString() +": "+colornode ;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null , searchword);

            }
        });


        ADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int nbfilltree = 0; nbfilltree < 2; nbfilltree++) {
                    Filltree();
                }
            }

        });


        TREE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                txt.setText(null);
                Comment.setSelected(true);
                int youyou = Performdoss[2].getChildCount();
                System.out.println(youyou);
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
                    String Perform = "PERFORM";
                    Enlevercomment(txt);
                    addHighlight(txt, Perform,Mot, hPainter,redpainter);
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


            }
        });

        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String perform = "PERFORM";
                if (e.getClickCount() == 2) {
                    select = txt.getSelectedText();
                    for (int yaaaaaaa = 0; yaaaaaaa < nbdefine; yaaaaaaa++) {
                        String tou = definename[yaaaaaaa];
                        int youyt = select.indexOf(tou);
                        if (youyt == -1) {

                        } else {
                            txt.setText(null);
                            txt.setText(Definetab[yaaaaaaa + 1]);
                            addHighlight(txt, perform,Mot, hPainter,redpainter);

                        }


                    }
                }
            }
        });




        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        root.add(entree);
        entree.removeAllChildren();
        DEFINE.removeAllChildren();
        ATLASMODULE.removeAllChildren();
        REQUIRE.removeAllChildren();
        i = 0;
        nb = 0;
        nbperform=0;nbAtlasmodule=0;nbdefine=0;nbrequire=0;

        model.reload();
        cleartime = cleartime + 1;

    }

    public void Affichagewithbuffer() {

        String code = "TITRE\n";                            // Definition d'une chaine de caractère
        String codedef = "FIRST DEFINE\n";
        String codeAtlasmodule = "ATLAS MODULE\n";
        String coderequire = "ATLAS REQUIRE\n";
        int souscode = 0;                                    // Definition d'un int et initialisation à 0
        int SandEdefine = 0;
        int SandEAtlasmodule = 0;
        int SandEBlock = 0;
        int SandErequire = 0;
        int waitforprocedure = 0;

        try {
            String line;
            while ((line = readertext.readLine()) != null) {
                progressBar1.setValue(progressBar1.getValue() + 1);

                if ((souscode == 0) && (SandEBlock == 1)) {
                    code = code + "\n" + line;
                    Affichagetest[i] = code;                        // On rentre dans un tableau le code correspondant

                } else if ((souscode == 1) && (SandEBlock == 1)) {
                    code = code + "\n" + line;
                    Affichagesouscode[r] = code;                // On rentre dans un tableau le code correspondant
                }


                if (line.matches(".*END, BLOCK, '.*")) {
                    SandEBlock = 0;
                    okperf = 0;
                }

                if (SandEdefine == 1) {
                    codedef = codedef + "\n" + line;
                    Definetab[nbdefine] = codedef;
                }

                if (line.matches(".*END, '.*")) {
                    SandEdefine = 0;
                }

                if (SandErequire == 1) {
                    coderequire = coderequire + "\n" + line;
                    Requirecode[nbrequire] = coderequire;
                }

                if (line.matches(".*CNX .*")) {
                    SandErequire = 0;
                }


                if (SandEAtlasmodule == 1) {
                    codeAtlasmodule = codeAtlasmodule + "\n" + line;
                    Alasmoduletab[nbAtlasmodule] = codeAtlasmodule;
                }

                if (line.matches(".*TERMINATE, ATLAS MODULE '.*")) {
                    SandEAtlasmodule = 0;
                }

                if (line.matches(".*PERFORM, '.*") && okperf ==1) {

                    decoup = line.split("\\'");
                    Performname[nbperform] = decoup[1];

                            DefaultMutableTreeNode newPerform = new DefaultMutableTreeNode(decoup[1]);
                            Performdoss[nb].add(newPerform);
                            nbperform++;
                            model.reload();
                    }


                if (line.matches(".*DEFINE,.*")) {
                    decoup = line.split("\\'");
                    txt.append(decoup[1] + "\n");
                    definename[nbdefine] = decoup[1];
                    waitforprocedure = 1;
                }


                if (line.matches(".*PROCEDURE.*") && (waitforprocedure == 1)) {
                    DefaultMutableTreeNode newdefine = new DefaultMutableTreeNode(definename[nbdefine]);
                    DEFINE.add(newdefine);
                    model.reload();
                    codedef = "DEFINE" + nbdefine + "  " + decoup[1] + "\n";
                    SandEdefine = 1;
                    waitforprocedure = 0;
                    nbdefine++;
                }


                if (line.matches(".*REQUIRE, '.*")) {
                    decoup = line.split("\\'");
                    System.out.println(decoup[1]);
                    txt.append(decoup[1] + "\n");
                    Requirename[nbrequire] = decoup[1];
                    DefaultMutableTreeNode newrequire = new DefaultMutableTreeNode(Requirename[nbrequire]);
                    REQUIRE.add(newrequire);
                    model.reload();
                    coderequire = "REQUIRE" + nbrequire + "  " + decoup[1] + "\n";
                    SandErequire = 1;
                    nbrequire++;
                }

                if ((line.startsWith("E"))){System.out.println("ENTREE  "+ line);}

                if (line.startsWith("E")&& line.matches(".*EQ 0.*")) {
                    souscode = 0;                               // souscode remis à 0
                    nb++;
                    okperf = 1;
                    decoup = line.split("\\'");       // On découpe la ligne tout les '
                    number[i] = getnum2(decoup);              // Utilisation de getnum2(dépend de i)
                    number1[i] = getnum1(decoup);             // Utilisation de getnum1(dépend de i)
                    if (number[i] == 0) {                     // En fonction du résultat de getnum2(depend de de i)
                        subnode[s] = 0;
                        txt.append(decoup[1] + "\n");         // Affichage du nom du test dans la zone de text
                        txt.append(line + "\n");            // Affichage de la ligne en entier dans la zone de text
                        txt.append(number[i] + "\n");         // Affichage du retour de la fonction getnum2 (depend de i)
                        test[i] = decoup[1];                  // On rentre le nom de test dans un tableau (depend de i)
                        code = "TEST " + i;        // On réinitialise la variable code (depend de i)
                        System.out.println(test[i]);
                        Performdoss[nb] = new DefaultMutableTreeNode("PERFORM");
                        SandEBlock = 1;
                        i++;
                    }

                }

                if (line.matches(".*BEGIN,.*")) {
                    txt.append(line + "\n");
                    if (line.matches(".*BEGIN, ATLAS MODULE '.*")) {
                        decoup = line.split("\\'");
                        txt.append(decoup[1] + "\n");
                        Atlasmodulename[nbAtlasmodule] = decoup[1];
                        DefaultMutableTreeNode newAtlasModule = new DefaultMutableTreeNode(Atlasmodulename[nbAtlasmodule]);
                        ATLASMODULE.add(newAtlasModule);
                        model.reload();
                        codeAtlasmodule = "ATLAS MODULE" + nbAtlasmodule + "  " + decoup[1] + "\n";
                        SandEAtlasmodule = 1;
                        nbAtlasmodule++;


                    } else if ((line.matches(".*BEGIN, BLOCK, '.*") || line.matches(".*BEGIN, ATLAS PROGRAM '.*"))) {
                        souscode = 0;                               // souscode remis à 0
                        decoup = line.split("\\'");       // On découpe la ligne tout les '
                        number[i] = getnum2(decoup);              // Utilisation de getnum2(dépend de i)
                        number1[i] = getnum1(decoup);             // Utilisation de getnum1(dépend de i)
                        if (number[i] == 0) {                     // En fonction du résultat de getnum2(depend de de i)
                            nb++;
                            okperf = 1;
                            subnode[s] = 0;
                            txt.append(decoup[1] + "\n");         // Affichage du nom du test dans la zone de text
                            txt.append(line + "\n");            // Affichage de la ligne en entier dans la zone de text
                            txt.append(number[i] + "\n");         // Affichage du retour de la fonction getnum2 (depend de i)
                            test[i] = decoup[1];                  // On rentre le nom de test dans un tableau (depend de i)
                            code = "TEST " + i;        // On réinitialise la variable code (depend de i)
                            System.out.println(test[i]);
                            SandEBlock = 1;
                            i++;                                  // On incrémente i
                            Performdoss[nb] = new DefaultMutableTreeNode("PERFORM");
                        } else if (number[i] != 0) {
                            souscode = 1;                           // la variable souscode vaut 1
                            undernode[r] = decoup[1];             // On rentre le nom du sous test dans un tableau
                            subnode[s] = 1;                         // la variable subnode vaut 1 (depend de s)
                            txt.append(decoup[1] + "\n");
                            txt.append(line + "\n");
                            txt.append(number[i] + "\n");
                            SandEBlock = 1;
                            code = "TEST " + (number1[i]) + "." + (number[i]); // On réinitialise la variable code
                            r++;                                  // Incrémentation de r
                        }
                        s++;                                     // Incrémentation de s
                        maxr = r;
                        maxnb = nb;

                    }

                }
            }

            nbFdefinie = nbdefine;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readertext.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        String performs[] = new String[1000]; String children[] = new String[1000];
        int nbperfo=0;
        int child ;
        if (!test[i].trim().equals("")) {
            entree.add(etr);
            model.reload();
            //Removedouble(Performdoss[nb+1]);
            DefaultMutableTreeNode Perform = Performdoss[nb+1];

            /*for (int nbper = 0; nbper < Performdoss[nb+1].getChildCount() ; nbper++){
                performs[nbperfo] = Perform.getChildAt(nbper).toString();
                nbperfo++;
            }

            for (int youyou = 0; youyou < nbperfo; youyou++) {
                DefaultMutableTreeNode newperf = new DefaultMutableTreeNode(performs[youyou]);
                 etr.add(newperf);
                 model.reload();
            }*/

           etr.add(Performdoss[nb+1]);
           model.reload();
        } else {
            JOptionPane.showMessageDialog(null, "It doesn't Work MOTHERFUCKER");
        }

               nb++;
        return etr;
    }


    private void Codetest() {

        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) TREE.getLastSelectedPathComponent();
        String V = selectNode.toString();
        String Perform = "PERFORM";
        if (selectNode != null) {
            System.out.println(V);
            for (int z = 0; z < 999999; z++) {
                if (V == test[z]) {
                    txt.append(Affichagetest[z + 1]);
                    addHighlight(txt, Mot, Perform, redpainter,hPainter);
                } else if (V == undernode[z]) {
                    txt.append(Affichagesouscode[z + 1]);
                    addHighlight(txt, Mot,Perform, redpainter,hPainter);
                } else if (V == definename[z]) {
                    txt.append(definename[z] + "\n" + Definetab[z + 1]);
                    addHighlight(txt, Mot,Perform, redpainter,hPainter);
            } else if (V == Atlasmodulename[z]) {
                    txt.append(Atlasmodulename[z] + "\n" + Alasmoduletab[z + 1]);
                    addHighlight(txt, Mot,Perform, redpainter,hPainter);
                } else if (V == Requirename[z]) {
                    txt.append(Requirename[z] + "\n" + Requirecode[z + 1]);
                    addHighlight(txt, Mot,Perform, redpainter,hPainter);
                }
                else if (V == Performname[z]) {
                    for (int per=0; per < nbdefine ;per++){
                        String definetitle = definename[per];
                        int you = Performname[z].indexOf(definetitle);
                        if(you==-1){}
                        else{
                    txt.append(Performname[z] + "\n" + Definetab[per+1]);
                    addHighlight(txt, Mot,Perform, redpainter,hPainter);}}
                }

            }
        }
    }


    public void  addHighlight(final JTextComponent tcomp, final String word,final String word2, Highlighter.HighlightPainter youyou,Highlighter.HighlightPainter youyou2) {
        removeHighlights(tcomp);// Supprime les anciens
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

            while ((pos = fullText.indexOf(word2, pos)) >= 0) {
                // Ajout du nouveau painter
                h.addHighlight(pos, pos + word2.length(), youyou2);
                // On avance pour la suite
                pos += word2.length();
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

    public void Filltree() {
        r = 0;
        s = 0;
        nb = 0;
        DefaultMutableTreeNode V = null;
        int addchild = 0;
        if (cleartime == 1) {
            for (int a = 0; a < 100; a++) {
                if (addchild == 1) {
                    while (subnode[s] == 1) {
                        System.out.println("OK");
                        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
                        newinfo[r] = new DefaultMutableTreeNode(undernode[r]);
                        V.add(newinfo[r]);
                        model.reload();
                        r++;
                        s++;
                    }
                }


                V = Addanode(a);
                if (a == 0) {
                    V.add(DEFINE);
                    V.add(ATLASMODULE);
                    V.add(REQUIRE);
                    DEFINE.add(Drawing);
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

    public void Enlevercomment(JTextArea txt) {
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

    public String getDefinename(int nbr) {
        return definename[nbr];
    }


    public String getDefinetab(int b) {
        return Definetab[b];
    }

    public void enlevagedejumeaux (MutableTreeNode parent) {
        String children[] = new String[1000];
        int Childcount = parent.getChildCount();
        int child;
        if (Childcount != 0) {
            for (child = 0; child < Childcount; child++) {
                children[child] = parent.getChildAt(child).toString();}

            for (int remove = 0; remove < child; remove++) {
                if (children[child]== children[remove]) {
                        parent.remove(child);
                        model.reload();
                    }
                }
        }
    }

    public void Removedouble(DefaultMutableTreeNode perfor){

        int nbchil = perfor.getChildCount();
        System.out.println("MAX "+maxnb + "  " + nbchil);
        DefaultMutableTreeNode Performance = perfor;
        String enfant[] = new String[1000];
        String suitedenfant = null;
        for (int chil=0; chil < nbchil ;chil ++){
            enfant[chil] =Performance.getChildAt(chil).toString();
            suitedenfant = suitedenfant + " " + Performance.getChildAt(chil).toString();

        for (int chilou=0; chilou < nbchil ;chilou ++) {
            int regexo = regexOccur(suitedenfant, enfant[chil]);
            if (regexo > 2){
                    System.out.println("Remove child " + chil + " " + regexo);
                    Performance.remove(chil);
                    model.reload();
                    nbchil--;
            }
                }

            }
    }

    public int regexOccur(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int occur = 0;
        while(matcher.find()) {
            occur ++;
        }
        return occur;
    }


}



