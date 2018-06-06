package MB2.belleperche;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.tree.*;

import static java.awt.Font.TRUETYPE_FONT;
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
    private JButton SearchFile;
    private BufferedReader readertext;
    int click = 0;
    int nbperform = 0;
    int nb = 0;
    int nbsous = 0;
    int okperf = 0;
    int enter=0;
    int oksousper = 0;
    static int nbAtlasmodule = 0;
    int i = 0;
    int r = 0;
    int subnode[] = new int[100];
    int s = 0;
    int maxr = 0;
    int maxnb = 0;int maxsousnb=0;
    int cleartime = 0;
    int presencedefine=0;int presenceAtlasmodule=0;int presencedrawing=0;int presencerequire=0;
    int presencemotrecherche1[] = new int[1000];int presencemotrecherche2[]= new int[1000];int presencemotrecherche3[]= new int[1000];
    int presencemotrecherche4[]= new int[1000];int presencemotrecherche5[]= new int[1000];
    static int nbdefine;
    static int nbdrawing = 0;
    static int nbrequire = 0;
    static int nbFdefinie;
    int number[] = new int[100];
    int number1[] = new int[100];
    static int nbperforms;
    static String Mot = "PERFORM";
    String test[] = new String[1000];
    String undernode[] = new String[1000];
    String Affichagetest[] = new String[1000];
    String Drawingname[] = new String[1000];
    String Drawingcode[] = new String[1000];
    String Alasmoduletab[] = new String[1000];
    String Atlasmodulename[] = new String[1000];
    String Requirename[] = new String[1000];
    String Requirecode[] = new String[1000];
    String Affichagesouscode[] = new String[1000];
    String Performname[] = new String[10000];
    static String searchword;
    static String Definetab[] = new String[1000];
    static String definename[] = new String[1000];
    static String[] decoup;
    static String select;
    DefaultMutableTreeNode entree = new DefaultMutableTreeNode("ENTRY");
    DefaultMutableTreeNode DEFINE = new DefaultMutableTreeNode("DEFINE/PROCEDURE");
    DefaultMutableTreeNode ATLASMODULE = new DefaultMutableTreeNode("ATLAS MODULE");
    DefaultMutableTreeNode REQUIRE = new DefaultMutableTreeNode("REQUIRE");
    DefaultMutableTreeNode Performdoss[] = new DefaultMutableTreeNode[1000];
    DefaultMutableTreeNode Performsousdoss[] = new DefaultMutableTreeNode[1000];
    DefaultMutableTreeNode Drawing = new DefaultMutableTreeNode("DEFINE/DRAWING");
    DefaultMutableTreeNode newinfo[] = new DefaultMutableTreeNode[1000];

    DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();

    private Highlighter.HighlightPainter yellowPainter = new HPainter(Color.YELLOW);
    private Highlighter.HighlightPainter redpainter = new HPainter(Color.RED);

    DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) TREE.getCellRenderer();


    public Textread() {
        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
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

        /*TREE.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                          boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel)super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                 node = (Performdoss[1])value;
                if (node.isFlagged())
                    label.setForeground(Color.RED);

                return label;
            }
        });*/


        SearchFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter textFilter = new FileNameExtensionFilter("ATLAS(.txt,.ATL,.AS)", "txt", "ATL" ,"AS");
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(textFilter);
                int aa = fc.showOpenDialog(null);
                System.out.println(aa);
                if (aa ==JFileChooser.APPROVE_OPTION){
                    entrytext.setText(fc.getSelectedFile().getAbsolutePath());

                    }
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
                if (WTS.getText().equals("")){
                    WTS.setText("VEULLEZ ENTRER UN MOT");

                }

                else{
                    removeHighlights(txt);
                    Mot = WTS.getText().toUpperCase();
                    addHighlight(txt, Mot, redpainter);
                    searchword = "Occurence du mot " + Mot + " dans les dossiers suivants :";

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
                                searchword = searchword + "\n" + colornode;
                            }
                        }
                    }

                    for (int z = 0; z < nbFdefinie; z++) {
                        if (Mot != null) {
                            int numb = Definetab[z + 1].indexOf(Mot);
                            if (numb != -1) {
                                presencemotrecherche3[z] = 1;
                                String colornode = DEFINE.getChildAt(z).toString();
                                searchword = searchword + "\n" + DEFINE.toString() + ": " + colornode;
                            }
                        }
                    }

                    for (int z = 0; z < nbAtlasmodule; z++) {
                        if (Mot != null) {
                            int numb = Alasmoduletab[z + 1].indexOf(Mot);
                            if (numb != -1) {
                                presencemotrecherche4[z] = 1;
                                String colornode = ATLASMODULE.getChildAt(z).toString();
                                searchword = searchword + "\n" + ATLASMODULE.toString() + ": " + colornode;
                            }
                        }
                    }

                    for (int z = 0; z < nbrequire; z++) {
                        if (Mot != null) {
                            int numb = Requirecode[z + 1].indexOf(Mot);
                            if (numb != -1) {
                                presencemotrecherche5[z] = 1;
                                String colornode = REQUIRE.getChildAt(z).toString();
                                searchword = searchword + "\n" + REQUIRE.toString() + ": " + colornode;
                            }
                        }
                    }

                    for (int z = 0; z < nbdrawing; z++) {
                        if (Mot != null) {
                            int numb = Drawingcode[z + 1].indexOf(Mot);
                            if (numb != -1) {
                                presencemotrecherche5[z] = 1;
                                String colornode = Drawing.getChildAt(z).toString();
                                searchword = searchword + "\n" + Drawing.toString() + ": " + colornode;
                            }
                        }
                    }

                    JFrame frame3 = new JFrame("Perform/Define");
                    frame3.setContentPane(new Searchword().Findpanel);
                    frame3.pack();
                    frame3.setVisible(true);
                    frame3.setSize(800, 400);
                    frame3.setLocationRelativeTo(null);

                    Searchword searchword = new Searchword();

                }
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


        Comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Comment.isSelected()) {
                    txt.setText(null);
                    Codetest();
                } else {
                    String Perform = "PERFORM";
                    Enlevercomment(txt);
                    addHighlight(txt, Mot,redpainter);
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
                    for (int yaaaaaaa = 0; yaaaaaaa < nbFdefinie; yaaaaaaa++) {
                        String tou = definename[yaaaaaaa];
                        int youyt = select.indexOf(tou);
                        if (youyt == -1) {

                        } else {
                            txt.setText(null);
                            txt.setText(Definetab[yaaaaaaa + 1]);
                            addHighlight(txt, Mot,redpainter);
                        }


                    }
                }
            }
        });

        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (txt.getSelectedText()!=null) {
                    select = txt.getSelectedText();
                    for (int yaaaaaaa = 0; yaaaaaaa < nbFdefinie; yaaaaaaa++) {
                        String tou = definename[yaaaaaaa];
                        int youyt = select.indexOf(tou);
                        if (youyt == -1) {

                        } else {
                            txt.setText(null);
                            txt.setText(Definetab[yaaaaaaa + 1]);
                            addHighlight(txt, Mot,redpainter);
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

        WTS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                WTS.setText(null);
            }
        });

        WTS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER);
                enter = 1;
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
        r = 0;
        nb = 0;
        nbsous = 0;
        nbAtlasmodule=0;nbdefine=0;nbrequire=0;nbdrawing=0;

        model.reload();
        cleartime = 1;

    }

    public void Affichagewithbuffer() {

        String code = "TITRE\n";                            // Definition d'une chaine de caractère
        String codedef = "FIRST DEFINE\n";
        String codeAtlasmodule = "ATLAS MODULE\n";
        String coderequire = "ATLAS REQUIRE\n";
        String codedrawing = "Drawingcode\n";
        int souscode = 0;                                    // Definition d'un int et initialisation à 0
        int SandEdefine = 0;
        int SandEAtlasmodule = 0;
        int SandEBlock = 0;
        int SandErequire = 0;
        int waitforprocedure = 0;
        int waitfordrawing = 0;
        int waitforperform=0;
        int waitforsousperform = 0;
        int SandEdrawing=0;

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
                    oksousper = 0;
                }

                if (SandEdefine == 1) {
                    codedef = codedef + "\n" + line;
                    Definetab[nbdefine] = codedef;
                }

                if (line.matches(".*END, '.*")) {
                    SandEdefine = 0;
                }

                if (SandEdrawing == 1) {
                    codedrawing = codedrawing + "\n" + line;
                    Drawingcode[nbdrawing] = codedrawing;
                }

                if (line.startsWith("C")){
                    SandEdrawing = 0;
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



                if (line.matches(".*PERFORM,.*") && okperf ==1) {

                    waitforperform = 1;

                }

                    if (line.matches(".*'.*") && (waitforperform==1)){
                    int add=0;
                    decoup = line.split("\\'");
                    Performname[nbperform] = decoup[1];
                    //System.out.println(decoup[1]);
                    if (nbperform ==0){nbperform++;
                        DefaultMutableTreeNode newPerform = new DefaultMutableTreeNode(decoup[1]);
                        Performdoss[nb].add(newPerform);
                        //System.out.println("NOUVEAU PREMIER  NOEUD");
                    }
                    else{
                    for (int child=0; child<nbperform;child++){
                        if (decoup[1].equals(Performname[child])){
                            //System.out.println("NOEUD EGALE PRECEDENT" + child);
                            child = nbperform+1;
                            nbperform--;
                            add=0;
                        }
                    else{
                    //System.out.println("NOUVEAU NOEUD" + child );
                    model.reload();
                        add = 1;}
                    }

                    if (add ==1){
                        DefaultMutableTreeNode newPerform = new DefaultMutableTreeNode(decoup[1]);
                        Performdoss[nb].add(newPerform);
                    }
                    nbperform++;
                    }

                    waitforperform = 0;
                }

                if (line.matches(".*PERFORM,.*") && oksousper ==1) {

                    waitforsousperform = 1;

                }

                if (line.matches(".*'.*") && (waitforsousperform==1)){
                    int add=0;
                    decoup = line.split("\\'");
                    Performname[nbperform] = decoup[1];
                    //System.out.println(decoup[1]);
                    if (nbperform ==0){nbperform++;
                        DefaultMutableTreeNode newPerform = new DefaultMutableTreeNode(decoup[1]);
                        Performsousdoss[nbsous].add(newPerform);
                        //System.out.println("NOUVEAU PREMIER  NOEUD");
                    }
                    else{
                        for (int child=0; child<nbperform;child++){
                            if (decoup[1].equals(Performname[child])){
                                //System.out.println("NOEUD EGALE PRECEDENT" + child);
                                child = nbperform+1;
                                nbperform--;
                                add=0;
                            }
                            else{
                                //System.out.println("NOUVEAU NOEUD" + child );
                                model.reload();
                                add = 1;}
                        }

                        if (add ==1){
                            DefaultMutableTreeNode newPerform = new DefaultMutableTreeNode(decoup[1]);
                            Performsousdoss[nbsous].add(newPerform);


                        }
                        nbperform++;
                    }

                    waitforsousperform = 0;
                }



                if (line.matches(".*DEFINE,.*")) {

                    decoup = line.split("\\'");
                    txt.append(decoup[1] + "\n");
                    definename[nbdefine] = decoup[1];
                    Drawingname[nbdrawing] = decoup[1];
                    waitforprocedure = 1;
                    waitfordrawing = 1;
                }


                if (line.matches(".*PROCEDURE.*") && (waitforprocedure == 1)) {
                    presencedefine=1;
                    DefaultMutableTreeNode newdefine = new DefaultMutableTreeNode(definename[nbdefine]);
                    DEFINE.add(newdefine);
                    model.reload();
                    codedef = "DEFINE" + nbdefine + "  " + decoup[1] + "\n";
                    SandEdefine = 1;
                    waitforprocedure = 0;
                    waitfordrawing = 0;
                    nbdefine++;
                }

                if (line.matches(".*DRAWING,.*")&&(waitfordrawing ==1)){
                    presencedrawing=1;
                    DefaultMutableTreeNode newdrawing = new DefaultMutableTreeNode(Drawingname[nbdrawing]);
                    Drawing.add(newdrawing);
                    model.reload();
                    codedrawing = "DRAWING" + nbdrawing + "  " + decoup[1] + "\n";
                    waitforprocedure = 0;
                    waitfordrawing = 0;
                    SandEdrawing = 1;
                    nbdrawing++;
                }


                if (line.matches(".*REQUIRE, '.*")) {
                    presencerequire=1;
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

                if ((line.matches(".*999999.*"))){System.out.println("END  ");
                txt.append("TERMINAISON PROGRAMME/ATLAS MODULE" + "\n");}

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
                        test[i] = decoup[1];                   // On rentre le nom de test dans un tableau (depend de i)
                        code = "TEST " + i;        // On réinitialise la variable code (depend de i)
                        System.out.println(test[i]);
                        Performdoss[nb] = new DefaultMutableTreeNode("PROCEDURE");
                        for (int nbn=0;nbn<nbperform;nbn++){
                            Performname[nbn]=null;
                        }
                        nbperform=0;
                        SandEBlock = 1;
                        i++;
                    }

                }

                if (line.matches(".*BEGIN,.*")) {
                    txt.append(line + "\n");
                    if (line.matches(".*BEGIN, ATLAS MODULE '.*")) {
                        presenceAtlasmodule=1;
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
                            oksousper = 0;
                            subnode[s] = 0;
                            txt.append(decoup[1] + "\n");         // Affichage du nom du test dans la zone de text
                            txt.append(line + "\n");            // Affichage de la ligne en entier dans la zone de text
                            txt.append(number[i] + "\n");         // Affichage du retour de la fonction getnum2 (depend de i)
                            if (i == 0){test[i] = "TITRE : "+decoup[1];}                  // On rentre le nom de test dans un tableau (depend de i)
                            else {test[i] = decoup[1];   }                        // On rentre le nom de test dans un tableau (depend de i)
                            code = "TEST " + i;        // On réinitialise la variable code (depend de i)
                            System.out.println(test[i]);
                            SandEBlock = 1;
                            i++;                                  // On incrémente i
                            Performdoss[nb] = new DefaultMutableTreeNode("PROCEDURE");
                            for (int nbn=0;nbn<nbperform;nbn++){
                                Performname[nbn]=null;
                            }
                            nbperform=0;
                        } else if (number[i] != 0) {
                            nbsous ++;
                            okperf = 0;
                            oksousper = 1;
                            souscode = 1;                           // la variable souscode vaut 1
                            undernode[r] = decoup[1];             // On rentre le nom du sous test dans un tableau
                            subnode[s] = 1;                         // la variable subnode vaut 1 (depend de s)
                            txt.append(decoup[1] + "\n");
                            txt.append(line + "\n");
                            txt.append(number[i] + "\n");
                            SandEBlock = 1;
                            code = "TEST " + (number1[i]) + "." + (number[i]); // On réinitialise la variable code
                            System.out.println(undernode[r]);
                            Performsousdoss[nbsous] = new DefaultMutableTreeNode("PROCEDURE");
                            for (int nbn=0;nbn<nbperform;nbn++){
                                Performname[nbn]=null;
                            }
                            nbperform=0;
                            r++;                                  // Incrémentation de r
                        }
                        s++;                                     // Incrémentation de s
                        maxr = r;
                        maxnb = nb;
                        maxsousnb = nbsous;

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
        DefaultMutableTreeNode etr = new DefaultMutableTreeNode();


        if (i ==0){etr = new DefaultMutableTreeNode(test[i]);}

        else {etr = new DefaultMutableTreeNode(test[i]);}
        if (!test[i].trim().equals("")) {
            entree.add(etr);
            model.reload();
            if (i!=0) {
                addperf(etr,Performdoss,nb);
            }} else {
                JOptionPane.showMessageDialog(null, "It doesn't Work MOTHERFUCKER");
            }

               nb++;
        return etr;
    }


    private void Codetest() {

        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) TREE.getLastSelectedPathComponent();
        String V = selectNode.toString();
        if (selectNode != null) {
            System.out.println(V);

            addcode(V,i,test,Affichagetest);
            addcode(V,maxr,undernode,Affichagesouscode);
            addcode(V,nbAtlasmodule,Atlasmodulename,Alasmoduletab);
            addcode(V,nbFdefinie,definename,Definetab);
            addcode(V,nbrequire,Requirename,Requirecode);
            addcode(V,nbdrawing,Drawingname,Drawingcode);

            for (int z = 0; z < maxnb; z ++){
                DefaultMutableTreeNode Perform = Performdoss[z+1];
                for (int a=0; a < Perform.getChildCount(); a++){
                if (V == Performdoss[z+1].getChildAt(a).toString()) {
                    for (int per=0; per < nbFdefinie ;per++){
                        String definetitle = definename[per];
                        int you = Performdoss[z+1].getChildAt(a).toString().indexOf(definetitle);
                        if(you==-1){}
                        else{
                            txt.append(Performdoss[z+1].getChildAt(a).toString() + "\n" + Definetab[per+1]);
                            addHighlight(txt, Mot,redpainter);}}
                }}}

            for (int y = 0; y < maxsousnb; y ++){
                DefaultMutableTreeNode Perform = Performsousdoss[y+1];
                for (int a=0; a < Perform.getChildCount(); a++){
                    if (V == Performsousdoss[y+1].getChildAt(a).toString()) {
                        for (int per=0; per < nbFdefinie ;per++){
                            String definetitle = definename[per];
                            int you = Performsousdoss[y+1].getChildAt(a).toString().indexOf(definetitle);
                            if(you==-1){}
                            else{
                                txt.append(Performsousdoss[y+1].getChildAt(a).toString() + "\n" + Definetab[per+1]);
                                addHighlight(txt, Mot,redpainter);}}
                    }}}

            System.out.println("Nbnoeud, nbsousnoeud, AtlasModule, Define, Require, Drawing, Perform");
            System.out.println(i+","+maxr+","+nbAtlasmodule+","+nbFdefinie+","+nbrequire+","+nbdrawing+","+nbperforms);



        }
    }


    public void  addHighlight(final JTextComponent tcomp, final String word,Highlighter.HighlightPainter youyou) {
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
        nbsous = 0;
        DefaultMutableTreeNode V = null;
        int addchild = 0;
        if (cleartime == 1) {
            for (int a = 0; a < i; a++) {
                if ((addchild == 1)&&(maxr!=0)) {
                    while (subnode[s] == 1) {
                        System.out.println("OK");
                        DefaultTreeModel model = (DefaultTreeModel) TREE.getModel();
                        newinfo[r] = new DefaultMutableTreeNode(undernode[r]);
                        V.add(newinfo[r]);
                        //newinfo[r].add(Performsousdoss[nbsous+1]);
                        addperf(newinfo[r],Performsousdoss,nbsous);
                        model.reload();
                        r++;
                        s++;
                        nbsous++;
                    }
                }


                V = Addanode(a);
                if (a == 0) {
                    if (presencedefine==1)V.add(DEFINE);
                    if (presenceAtlasmodule==1)V.add(ATLASMODULE);
                    if (presencerequire==1)V.add(REQUIRE);
                    if (presencedrawing==1)V.add(Drawing);
                }
                s++;

                if (subnode[s] == 1) {
                    addchild = 1;
                }
            }
        }

        cleartime = 0;
    }


    public int indexOfpos(String chaine, char lettre) {
        //on parcoure les caracteres de la chaine
        for (int i = 0; i < chaine.length(); i++) {
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


    public void addcode(String noeud, int variablenb, String nomtalbeau[], String code[]) {

        for (int a = 0; a < variablenb; a++) {
            if (noeud == nomtalbeau[a]) {
                txt.append(code[a + 1]);
                System.out.println(nomtalbeau[a]);
                addHighlight(txt, Mot, redpainter);
            }
        }
    }

    public void addperf(DefaultMutableTreeNode parent, DefaultMutableTreeNode tableau[], int nbdos){

        String performs[] = new String[1000];
        int nbperfo=0;

        DefaultMutableTreeNode Perform = tableau[nbdos + 1];
        for (int nbper = 0; nbper < tableau[nbdos + 1].getChildCount(); nbper++) {
            performs[nbperfo] = Perform.getChildAt(nbper).toString();
            nbperfo++;
        }
        for (int youyou = 0; youyou < nbperfo; youyou++) {
            DefaultMutableTreeNode newperf = new DefaultMutableTreeNode(performs[youyou]);
            parent.add(newperf);
            model.reload();
        }
        //etr.add(Performdoss[nb + 1]);
        model.reload();
    }
}
