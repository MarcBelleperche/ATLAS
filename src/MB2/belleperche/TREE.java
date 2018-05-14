package MB2.belleperche;

import jdk.nashorn.api.tree.Tree;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import static javafx.scene.input.KeyCode.T;

public class TREE {
    private JPanel EntryList;
    private JTree Entrydetails;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JButton valueButton;
    private JButton Remove;

    public TREE() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Addanode();
            }
        });

        valueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Addinfo();
            }
        });
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("Entry list");
        frame.setContentPane(new TREE().EntryList);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
    }

    private void Addanode() {
        Textread lecture = new Textread();
        DefaultTreeModel model = (DefaultTreeModel) Entrydetails.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        if (!textField1.getText().trim().equals("")) {
            root.add(new DefaultMutableTreeNode(textField1.getText()));
            model.reload();
        } else {
            JOptionPane.showMessageDialog(null, "enter an entry");
        }
    }

    private void Addinfo() {

        DefaultTreeModel model = (DefaultTreeModel) Entrydetails.getModel();
        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) Entrydetails.getLastSelectedPathComponent();
        DefaultMutableTreeNode newinfo = new DefaultMutableTreeNode(textField2.getText());

        if (selectNode != null) {
            if (!textField2.getText().trim().equals("")) {
                model.insertNodeInto(newinfo, selectNode, selectNode.getChildCount());
            } else {
                JOptionPane.showMessageDialog(null, "enter an entry ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "select an entry");
        }


    }

    private void Clear() {

        Entrydetails.setModel(null);
        DefaultTreeModel model = (DefaultTreeModel) Entrydetails.getModel();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("TESTS LIST");
        model.reload();
        DefaultMutableTreeNode parent;
        parent = new DefaultMutableTreeNode("youyou");
        root.add(parent);
    }
}
