import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomersPage extends JFrame implements ActionListener {

    JPanel dataPanel;
    JButton filterButton, allVehiclesButton, brandButton, showroomButton, generalInfoButton;
    JTable myTable;
    JScrollPane myScrollPane;
    GarageSaleDatabase GSDB = new GarageSaleDatabase();

    CustomersPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Garage Sale");
        this.setLayout(null);

        DefaultTableModel tableModel = GSDB.getDataTableModel();

        myTable = new JTable(tableModel);
        myTable.setBounds(340, 130, 903, 480);

        myScrollPane = new JScrollPane(myTable);

        filterButton = new JButton();
        filterButton.setBounds(30, 110, 100, 50);
        filterButton.addActionListener(this);
        filterButton.setText("FILTER");
        filterButton.setFocusable(false);
        filterButton.setHorizontalTextPosition(JButton.CENTER);
        filterButton.setVerticalTextPosition(JButton.CENTER);
        filterButton.setFont(new java.awt.Font("MV Boli", Font.BOLD, 19));
        filterButton.setForeground(Color.white);
        filterButton.setBackground(new Color(25, 40, 40));
        filterButton.setBorder(BorderFactory.createEtchedBorder());

        generalInfoButton = new JButton();
        generalInfoButton.setBounds(150, 110, 100, 50);
        generalInfoButton.addActionListener(this);
        generalInfoButton.setText("INFO");
        generalInfoButton.setFocusable(false);
        generalInfoButton.setHorizontalTextPosition(JButton.CENTER);
        generalInfoButton.setVerticalTextPosition(JButton.CENTER);
        generalInfoButton.setFont(new Font("MV Boli", Font.BOLD, 19));
        generalInfoButton.setForeground(Color.WHITE);
        generalInfoButton.setBackground(new Color(25, 40, 40));
        generalInfoButton.setBorder(BorderFactory.createEtchedBorder());

        allVehiclesButton = new JButton();
        allVehiclesButton.setBounds(30, 210, 300, 120);
        allVehiclesButton.addActionListener(this);
        allVehiclesButton.setText("ALL VEHICLES");
        allVehiclesButton.setFocusable(false);
        allVehiclesButton.setHorizontalTextPosition(JButton.CENTER);
        allVehiclesButton.setVerticalTextPosition(JButton.CENTER);
        allVehiclesButton.setFont(new java.awt.Font("MV Boli", Font.BOLD, 25));
        allVehiclesButton.setForeground(Color.white);
        allVehiclesButton.setBackground(new Color(25, 40, 40)); // 30, 60, 45
        allVehiclesButton.setBorder(BorderFactory.createEtchedBorder());

        brandButton = new JButton();
        brandButton.setBounds(30, 340, 300, 120);
        brandButton.addActionListener(this);
        brandButton.setText("FROM BRANDS");
        brandButton.setFocusable(false);
        brandButton.setHorizontalTextPosition(JButton.CENTER);
        brandButton.setVerticalTextPosition(JButton.CENTER);
        brandButton.setFont(new java.awt.Font("MV Boli", Font.BOLD, 25));
        brandButton.setForeground(Color.white);
        brandButton.setBackground(new Color(25, 40, 40));
        brandButton.setBorder(BorderFactory.createEtchedBorder());

        showroomButton = new JButton();
        showroomButton.setBounds(30, 470, 300, 120);
        showroomButton.addActionListener(this);
        showroomButton.setText("FROM SHOWROOMS");
        showroomButton.setFocusable(false);
        showroomButton.setHorizontalTextPosition(JButton.CENTER);
        showroomButton.setVerticalTextPosition(JButton.CENTER);
        showroomButton.setFont(new java.awt.Font("MV Boli", Font.BOLD, 25));
        showroomButton.setForeground(Color.white);
        showroomButton.setBackground(new Color(25, 40, 40));
        showroomButton.setBorder(BorderFactory.createEtchedBorder());

        dataPanel = new JPanel();
        dataPanel.setSize(903, 580);
        dataPanel.setLocation(340, 30);
        dataPanel.setBackground(new Color(78, 183, 121));
        dataPanel.setOpaque(true);
        dataPanel.setLayout(new GridLayout());
        dataPanel.add(myScrollPane);

        this.add(filterButton);
        this.add(generalInfoButton);
        this.add(allVehiclesButton);
        this.add(brandButton);
        this.add(showroomButton);
        this.add(dataPanel);
        this.setSize(1280, 670);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == filterButton) {

            String filterBox = JOptionPane.showInputDialog("COST/BRAND");

            if (filterBox.equalsIgnoreCase("COST")) {

                String minPoint = JOptionPane.showInputDialog("Enter the minimum cost: ");
                String maxPoint = JOptionPane.showInputDialog("Enter the maximum cost: ");

                int minValue = Integer.parseInt(minPoint);
                int maxValue = Integer.parseInt(maxPoint);

                myTable.setModel(GSDB.getDataTableModelCostFilter(minValue, maxValue));

            }

            else if (filterBox.equalsIgnoreCase("BRAND")) {

                String brandString = JOptionPane.showInputDialog("Enter Brand: ");

                myTable.setModel(GSDB.getDataTableModelBrandFilter(brandString));

            }

        }

        else if(e.getSource() == generalInfoButton) {

            myTable.setModel(GSDB.getDataTableModelGeneralInfo());

        }

        else if(e.getSource() == allVehiclesButton) {

            this.dispose();
            new CustomersPage();

        }

        else if(e.getSource() == brandButton) {

            myTable.setModel(GSDB.getDataTableModelFromBrands());

        }

        else if(e.getSource() == showroomButton){

            myTable.setModel(GSDB.getDataTableModelFromShowrooms());
        }

    }

}