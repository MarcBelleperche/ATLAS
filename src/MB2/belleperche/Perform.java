package MB2.belleperche;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Perform extends Textread {

    JPanel Performpanel;
    JTextArea Performcode;
    JList Performlist;
    private JCheckBox comment;
    DefaultListModel model1 = new DefaultListModel();

    Textread textread = new Textread();

    public Perform(){
        Performcode.setText("Selectionner une procédure");
        addnode();

        Performlist.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        comment.setSelected(true);
                        Performcode.setText(null);
                        addcode();
                    }
                });


        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comment.isSelected()){
                    Performcode.setText(null);
                    addcode();
                }

                else{textread.Enlevercomment(Performcode);}
            }
        });
    }

    public static void main(String[] args) {


    }

public void addnode(){

    for (int nbp=0; nbp < nbdefine ; nbp ++) {
        model1.addElement(textread.getDefinename(nbp));
    }
    Performlist.setModel(model1);
}

public void addcode(){

    Object selectname = Performlist.getSelectedValue();
    String V = selectname.toString();
    if (selectname != null){
        System.out.println(V);
        for (int codeperform=0; codeperform < nbdefine; codeperform++ ){

            if (V == definename[codeperform]){
                Performcode.append(textread.getDefinetab(codeperform + 1));
            } else nbdefine++;
        }
    }
    }
}