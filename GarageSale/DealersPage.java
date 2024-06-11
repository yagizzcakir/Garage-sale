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

public class DealersPage extends JFrame implements ActionListener {

    JPanel dataPanel;
    JButton importButton, deleteButton, updateButton;
    JTable myTable;
    JScrollPane myScrollPane;
    GarageSaleDatabase GSDB = new GarageSaleDatabase();

    DealersPage() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Garage Sale");
        this.setLayout(null);

        DefaultTableModel tableModel = GSDB.getDataTableModel();

        myTable = new JTable(tableModel);

        myScrollPane = new JScrollPane(myTable);

        importButton = new JButton();
        importButton.setBounds(35, 25, 380, 70);
        importButton.addActionListener(this);
        importButton.setText("IMPORT");
        importButton.setFocusable(false);
        importButton.setHorizontalTextPosition(JButton.CENTER);
        importButton.setVerticalTextPosition(JButton.CENTER);
        importButton.setFont(new Font("MV Boli", Font.BOLD, 19));
        importButton.setForeground(Color.white);
        importButton.setBackground(new Color(25, 40, 40));
        importButton.setBorder(BorderFactory.createEtchedBorder());

        updateButton = new JButton();
        updateButton.setBounds(445, 25, 380, 70);
        updateButton.addActionListener(this);
        updateButton.setText("UPDATE");
        updateButton.setFocusable(false);
        updateButton.setHorizontalTextPosition(JButton.CENTER);
        updateButton.setVerticalTextPosition(JButton.CENTER);
        updateButton.setFont(new Font("MV Boli", Font.BOLD, 19));
        updateButton.setForeground(Color.white);
        updateButton.setBackground(new Color(25, 40, 40));
        updateButton.setBorder(BorderFactory.createEtchedBorder());

        deleteButton = new JButton();
        deleteButton.setBounds(855, 25, 380, 70);
        deleteButton.addActionListener(this);
        deleteButton.setText("DELETE");
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(JButton.CENTER);
        deleteButton.setVerticalTextPosition(JButton.CENTER);
        deleteButton.setFont(new Font("MV Boli", Font.BOLD, 19));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(25, 40, 40));
        deleteButton.setBorder(BorderFactory.createEtchedBorder());

        dataPanel = new JPanel();
        dataPanel.setSize(1200, 500);
        dataPanel.setLocation(35, 120);
        dataPanel.setOpaque(true);
        dataPanel.setLayout(new GridLayout());
        dataPanel.add(myScrollPane);

        this.add(importButton);
        this.add(updateButton);
        this.add(deleteButton);
        this.add(dataPanel);
        this.setSize(1280, 670);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == importButton) {

            String dealerIDString = JOptionPane.showInputDialog("Enter your ID number: ");
            String showroomIDString = JOptionPane.showInputDialog("Enter your showroom ID number: ");
            String importValueTable = JOptionPane.showInputDialog("VEHICLES/SALES");


            if(importValueTable.equalsIgnoreCase("VEHICLES")){

                String importBrandName = JOptionPane.showInputDialog("Enter the brand name: ");
                String importModel = JOptionPane.showInputDialog("Enter model: ");
                String importVehicleYearString = JOptionPane.showInputDialog("Enter vehicle year: ");
                String importColor = JOptionPane.showInputDialog("Enter color: ");
                String importUsedStatus = JOptionPane.showInputDialog("Enter used status: ");
                String importDamageStatus = JOptionPane.showInputDialog("Enter damage status(DAMAGED/NON)");
                String importKMString = JOptionPane.showInputDialog("Enter km: ");

                int brandID = GSDB.fetchBrandID(importBrandName);

                int dealerID = Integer.parseInt(dealerIDString);
                int importKM = Integer.parseInt(importKMString);
                int importVehicleYear = Integer.parseInt(importVehicleYearString);

                GSDB.insertIntoVehicles(importDamageStatus, importKM, importVehicleYear, importUsedStatus, importModel,
                    importColor, brandID, dealerID);

            }

            else if(importValueTable.equalsIgnoreCase("SALES")) {

                String importBrandName = JOptionPane.showInputDialog("Enter the brand name: ");
                String importvehicleIDString = JOptionPane.showInputDialog("Enter Vehicle ID: ");
                String importCostString = JOptionPane.showInputDialog("Enter cost: ");
                String importDescription = JOptionPane.showInputDialog("Description: ");

                int brandID = GSDB.fetchBrandID(importBrandName);

                int importCost = Integer.parseInt(importCostString);
                int vehicleID = Integer.parseInt(importvehicleIDString);
                int showroomID = Integer.parseInt(showroomIDString);

                GSDB.insertIntoSales(importCost, importDescription, null, "NOT SOLD", vehicleID, showroomID, brandID);

            }
            
            this.dispose();
            new DealersPage();

        }

        else if (e.getSource() == updateButton) {

            String updatedValueTable = JOptionPane.showInputDialog("VEHICLES/SALES");

            if (updatedValueTable.equalsIgnoreCase("VEHICLES")) {

                String updateID = JOptionPane.showInputDialog("Enter the ID of the vehicle to update: ");
                String updateDamage = JOptionPane.showInputDialog("Enter damage status: ");
                String updateKM = JOptionPane.showInputDialog("Enter km: ");
                String updateYear = JOptionPane.showInputDialog("Enter year: ");
                String updateUsedStatus = JOptionPane.showInputDialog("Enter used status: ");
                String updateModel = JOptionPane.showInputDialog("Enter model: ");
                String updateColor = JOptionPane.showInputDialog("Enter color: ");

                int updateIDInt = Integer.parseInt(updateID);
                int updateKMInt = Integer.parseInt(updateKM);
                int updateYearInt = Integer.parseInt(updateYear);

                GSDB.updateVehicleData(updateIDInt, updateDamage, updateKMInt, updateYearInt, updateUsedStatus,
                        updateModel, updateColor);

                this.dispose();
                new DealersPage();

            }

            else if (updatedValueTable.equalsIgnoreCase("SALES")) {

                String updateID = JOptionPane.showInputDialog("Enter the ID of the sales to update: ");
                String updateCost = JOptionPane.showInputDialog("Enter cost: ");
                String updateDescription = JOptionPane.showInputDialog("Enter description: ");
                String updateDate = JOptionPane.showInputDialog("Enter date: ");
                String updateStatus = JOptionPane.showInputDialog("Enter sold status: ");

                int updateIDInt = Integer.parseInt(updateID);
                int updateCostInt = Integer.parseInt(updateCost);

                GSDB.updateSalesData(updateIDInt, updateCostInt, updateDescription, updateDate, updateStatus);

                this.dispose();
                new DealersPage();
            }

        }

        else if (e.getSource() == deleteButton) {

            String deletedValueTable = JOptionPane.showInputDialog("VEHICLES/SALES");
            String deletedData = JOptionPane
                    .showInputDialog("Enter the " + deletedValueTable.toLowerCase() + " ID to delete: ");

            int deletedDataInt = Integer.parseInt(deletedData);

            GSDB.deleteData(deletedDataInt, deletedValueTable);

            this.dispose();
            new DealersPage();

        }

    }
}
