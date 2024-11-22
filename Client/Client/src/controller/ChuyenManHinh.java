/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.Bean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.DangNhap2;
import view.NoiQuy;
import view.GioiThieu;

/**
 *
 * @author Admin
 */
public class ChuyenManHinh {

    private JPanel jpnRoot;
    private String kindSelected = "";
private List<Bean> listItem = null;
    public ChuyenManHinh(JPanel jpnRoot) {
        this.jpnRoot = jpnRoot;
    }

    public void setView(JPanel jp, JLabel jl) {
        kindSelected = "GioiThieu";
        jpnRoot.removeAll();
        jpnRoot.setLayout(new BorderLayout());
        jpnRoot.add(new GioiThieu());
        jpnRoot.validate();
        jpnRoot.repaint();

    }

    public void setEvent(List<Bean> listItem) {
        this.listItem = listItem;
        for (Bean a : listItem) {
a.getJlb().addMouseListener(new LabelEvent(a.getKind(),a.getJlb(),a.getJpn()));
        }

    }

    public class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JLabel Jlabel;
        private JPanel Jpanel;

        public LabelEvent(String kind, JLabel Jlabel, JPanel Jpanel) {
            this.kind = kind;
            this.Jlabel = Jlabel;
            this.Jpanel = Jpanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "GioiThieu":
                    node = new GioiThieu();
                    break;
                case "NoiQuy":
                    node = new NoiQuy();
                    break;
                case "DangNhap":
                    node = new DangNhap2();
                    break;              
                default:
                    break;
            }
            jpnRoot.removeAll();
            jpnRoot.setLayout(new BorderLayout());
            jpnRoot.add(node);
            jpnRoot.validate();
            jpnRoot.repaint();
            thaydoinen(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            Jpanel.setBackground(Color.red);
            Jlabel.setBackground(Color.red);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
             Jpanel.setBackground(Color.red);
            Jlabel.setBackground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
            Jpanel.setBackground(Color.YELLOW);
            Jlabel.setBackground(Color.YELLOW);
            }
        }

    }
    public void thaydoinen(String kind){
    for(Bean item: listItem){
    if(item.getKind().equalsIgnoreCase(kind)){
    item.getJlb().setBackground(Color.red);
    item.getJpn().setBackground(Color.red);
    }
    else{
    item.getJlb().setBackground(Color.yellow);
    item.getJpn().setBackground(Color.yellow);
    }
        
    }
    }

}
