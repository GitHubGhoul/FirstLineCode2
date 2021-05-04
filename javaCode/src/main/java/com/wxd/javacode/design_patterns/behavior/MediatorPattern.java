package com.wxd.javacode.design_patterns.behavior;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class MediatorPattern {
    public static void main(String[] args) {
        new OrderFrame("Hanburger", "Nugget", "Chip", "Coffee");
    }

    static class OrderFrame extends JFrame {
        public OrderFrame(String... names) {
            setTitle("Order");
            setSize(460, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container c = getContentPane();
            c.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
            c.add(new JLabel("Use Mediator Pattern"));
            List<JCheckBox> checkboxList = addCheckBox(names);
            JButton selectAll = addButton("Select All");
            JButton selectNone = addButton("Select None");
            selectNone.setEnabled(false);
            JButton selectInverse = addButton("Inverse Select");
            new Mediator(checkboxList, selectAll, selectNone, selectInverse);
            setVisible(true);
        }

        private List<JCheckBox> addCheckBox(String... names) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Menu:"));
            List<JCheckBox> list = new ArrayList<>();
            for (String name : names) {
                JCheckBox checkbox = new JCheckBox(name);
                list.add(checkbox);
                panel.add(checkbox);
            }
            getContentPane().add(panel);
            return list;
        }

        private JButton addButton(String label) {
            JButton button = new JButton(label);
            getContentPane().add(button);
            return button;
        }
    }

    public static class Mediator {
        // 引用UI组件:
        private List<JCheckBox> checkBoxList;
        private JButton selectAll;
        private JButton selectNone;
        private JButton selectInverse;

        public Mediator(List<JCheckBox> checkBoxList, JButton selectAll, JButton selectNone, JButton selectInverse) {
            this.checkBoxList = checkBoxList;
            this.selectAll = selectAll;
            this.selectNone = selectNone;
            this.selectInverse = selectInverse;
            // 绑定事件:
            this.checkBoxList.forEach(checkBox -> {
                checkBox.addChangeListener(this::onCheckBoxChanged);
            });
            this.selectAll.addActionListener(this::onSelectAllClicked);
            this.selectNone.addActionListener(this::onSelectNoneClicked);
            this.selectInverse.addActionListener(this::onSelectInverseClicked);
        }

        // 当checkbox有变化时:
        public void onCheckBoxChanged(ChangeEvent event) {
            boolean allChecked = true;
            boolean allUnchecked = true;
            for (var checkBox : checkBoxList) {
                if (checkBox.isSelected()) {
                    allUnchecked = false;
                } else {
                    allChecked = false;
                }
            }
            selectAll.setEnabled(!allChecked);
            selectNone.setEnabled(!allUnchecked);
        }

        // 当点击select all:
        public void onSelectAllClicked(ActionEvent event) {
            checkBoxList.forEach(checkBox -> checkBox.setSelected(true));
            selectAll.setEnabled(false);
            selectNone.setEnabled(true);
        }

        // 当点击select none:
        public void onSelectNoneClicked(ActionEvent event) {
            checkBoxList.forEach(checkBox -> checkBox.setSelected(false));
            selectAll.setEnabled(true);
            selectNone.setEnabled(false);
        }

        // 当点击select inverse:
        public void onSelectInverseClicked(ActionEvent event) {
            checkBoxList.forEach(checkBox -> checkBox.setSelected(!checkBox.isSelected()));
            onCheckBoxChanged(null);
        }
    }
}
