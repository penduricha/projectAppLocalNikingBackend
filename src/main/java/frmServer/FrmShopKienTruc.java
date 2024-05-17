package frmServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;

import dao.Clothing_DAO;
import dao.Store_DAO;
import entities.*;
import frmDynamoDB.FrmShopControlDynamo;
import i_dao.I_FrmShopKienTruc;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author LENOVO
 */
public class FrmShopKienTruc extends javax.swing.JFrame implements ActionListener, MouseListener, I_FrmShopKienTruc {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form FrmShopKienTruc
     */
	//Khởi tạo
	Store_DAO store_DAO=new Store_DAO();
	Clothing_DAO clothing_DAO=new Clothing_DAO();
    public FrmShopKienTruc() {
    	setTitle("Quản lí thông tin Shop.");
        initComponents();
        addEvent();
        renderTableStore();
        renderTableClothing();
    }                      
    private void initComponents() {
    	spinnerModel= new SpinnerNumberModel(0, 0, 1000, 1);

        pnlStore1 = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new java.awt.Label();
        pnlStore = new javax.swing.JPanel();
        pnlTable = new javax.swing.JPanel();
        scrollPaneStore = new javax.swing.JScrollPane();
        tableStore = new javax.swing.JTable();
        lblShopName = new java.awt.Label();
        txtStoreName = new javax.swing.JTextField();
        lblIncome = new java.awt.Label();
        txtIncome = new javax.swing.JTextField();
        lblAddress = new java.awt.Label();
        txtAddress = new javax.swing.JTextField();
        btnThemStore = new javax.swing.JButton();
        btnResetAll = new javax.swing.JButton();
        btnSuaStore = new javax.swing.JButton();
        lblLocStore = new java.awt.Label();
        btnFindByNameStore = new javax.swing.JButton();
        txtFindByNameStore = new javax.swing.JTextField();
        btnDemployStore = new javax.swing.JButton();
        pnlCtothing = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scrollPaneClothing = new javax.swing.JScrollPane();
        tableClothing = new javax.swing.JTable();
        lblNameClothing = new java.awt.Label();
        txtClothingName = new javax.swing.JTextField();
        lblQuantity = new java.awt.Label();
        spnQuantity = new javax.swing.JSpinner(spinnerModel);
        lblPrice = new java.awt.Label();
        txtPrice = new javax.swing.JTextField();
        btnThemClothing = new javax.swing.JButton();
        btnXoaClothing = new javax.swing.JButton();
        btnSuaClothing = new javax.swing.JButton();
        lblLocClothing = new java.awt.Label();
        btnFindByNameClothing = new javax.swing.JButton();
        txtFindByNameClothing = new javax.swing.JTextField();
        btnDemployClothing = new javax.swing.JButton();
        
        

        pnlStore1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout pnlStore1Layout = new javax.swing.GroupLayout(pnlStore1);
        pnlStore1.setLayout(pnlStore1Layout);
        pnlStore1Layout.setHorizontalGroup(
            pnlStore1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlStore1Layout.setVerticalGroup(
            pnlStore1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        lblTitle.setAlignment(java.awt.Label.CENTER);
        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTitle.setText("QUẢN LÝ THÔNG TIN SHOP");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        //Tạo bảng
        String[] headersStore= "STT;Mã;Tên;Thu nhập;Địa chỉ".split(";");
        tableModelStore= new DefaultTableModel(headersStore, 0);
        scrollPaneStore=new JScrollPane();     
        scrollPaneStore.setViewportView(tableStore=new JTable(tableModelStore));
        tableStore.setRowHeight(25);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneStore, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneStore, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        lblShopName.setAlignment(java.awt.Label.CENTER);
        lblShopName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblShopName.setText("Tên Shop:\n");

        txtStoreName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        

        lblIncome.setAlignment(java.awt.Label.CENTER);
        lblIncome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIncome.setText("Thu nhập (USD):\n\n");

        txtIncome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        

        lblAddress.setAlignment(java.awt.Label.CENTER);
        lblAddress.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAddress.setText("Địa chỉ:\n\n");

        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        

        btnThemStore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThemStore.setText("Thêm\n");
        btnThemStore.setActionCommand("Thêm");
        btnThemStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        btnResetAll.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnResetAll.setText("Reset");
        btnResetAll.setActionCommand("Thêm");
        btnResetAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
       
        btnSuaStore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSuaStore.setText("Sửa\n");
        btnSuaStore.setActionCommand("Thêm");
        btnSuaStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        

        lblLocStore.setAlignment(java.awt.Label.CENTER);
        lblLocStore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLocStore.setText("Lọc dữ liệu:");

        btnFindByNameStore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnFindByNameStore.setText("Tìm (Tên)");
        btnFindByNameStore.setActionCommand("Thêm");
        btnFindByNameStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        txtFindByNameStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        
        btnDemployStore.setBackground(new java.awt.Color(255, 153, 51));
        btnDemployStore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDemployStore.setText("Đẩy dữ liệu lên dịch vụ AWS");
        btnDemployStore.setActionCommand("Thêm");
        btnDemployStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
      
        javax.swing.GroupLayout pnlStoreLayout = new javax.swing.GroupLayout(pnlStore);
        pnlStore.setLayout(pnlStoreLayout);
        pnlStoreLayout.setHorizontalGroup(
            pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStoreLayout.createSequentialGroup()
                        .addComponent(lblIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStoreLayout.createSequentialGroup()
                        .addComponent(lblShopName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStoreLayout.createSequentialGroup()
                        .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemStore, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStoreLayout.createSequentialGroup()
                                .addComponent(btnResetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnSuaStore, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlStoreLayout.createSequentialGroup()
                        .addComponent(btnFindByNameStore, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFindByNameStore, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStoreLayout.createSequentialGroup()
                        .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLocStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDemployStore, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlStoreLayout.setVerticalGroup(
            pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlStoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShopName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemStore)
                    .addComponent(btnResetAll)
                    .addComponent(btnSuaStore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindByNameStore)
                    .addComponent(txtFindByNameStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDemployStore)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCtothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        //Tạo bảng cho Clothing
        /*
         * String[] headers = "STT;Mã;Tên;Địa chỉ;Điểm".split(";");
        //String[] colName = "STT;Mã;Tên;Địa chỉ;Điểm thi".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		scrollPane= new JScrollPane();
		scrollPane.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
         */
        String[] headersClothing= "STT;Mã;Tên;Số lượng;Giá;Mã Store".split(";");
        tableModelClothing= new DefaultTableModel(headersClothing, 0);
        scrollPaneClothing=new JScrollPane();     
        scrollPaneClothing.setViewportView(tableClothing=new JTable(tableModelClothing));
        tableClothing.setRowHeight(25);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPaneClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneClothing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        lblNameClothing.setAlignment(java.awt.Label.CENTER);
        lblNameClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNameClothing.setText("Tên Clothing:");

        txtClothingName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
      

        lblQuantity.setAlignment(java.awt.Label.CENTER);
        lblQuantity.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblQuantity.setText("Số Lượng:");

        spnQuantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        lblPrice.setAlignment(java.awt.Label.CENTER);
        lblPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPrice.setText("Giá:");

        txtPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        

        btnThemClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThemClothing.setText("Thêm\n");
        btnThemClothing.setActionCommand("Thêm");
        btnThemClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        btnXoaClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoaClothing.setText("Xóa");
        btnXoaClothing.setActionCommand("Thêm");
        btnXoaClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        

        btnSuaClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSuaClothing.setText("Sửa\n");
        btnSuaClothing.setActionCommand("Thêm");
        btnSuaClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
       

        lblLocClothing.setAlignment(java.awt.Label.CENTER);
        lblLocClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLocClothing.setText("Lọc dữ liệu:");

        btnFindByNameClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnFindByNameClothing.setText("Tìm (Tên)");
        btnFindByNameClothing.setActionCommand("Thêm");
        btnFindByNameClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        txtFindByNameClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        

        btnDemployClothing.setBackground(new java.awt.Color(255, 153, 51));
        btnDemployClothing.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDemployClothing.setText("Đẩy dữ liệu lên dịch vụ AWS");
        btnDemployClothing.setActionCommand("Thêm");
        btnDemployClothing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
      

        javax.swing.GroupLayout pnlCtothingLayout = new javax.swing.GroupLayout(pnlCtothing);
        pnlCtothing.setLayout(pnlCtothingLayout);
        pnlCtothingLayout.setHorizontalGroup(
            pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCtothingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCtothingLayout.createSequentialGroup()
                        .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNameClothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClothingName, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(spnQuantity)))
                    .addGroup(pnlCtothingLayout.createSequentialGroup()
                        .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCtothingLayout.createSequentialGroup()
                                .addComponent(btnXoaClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnSuaClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(pnlCtothingLayout.createSequentialGroup()
                        .addComponent(btnFindByNameClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFindByNameClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCtothingLayout.createSequentialGroup()
                        .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLocClothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDemployClothing, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCtothingLayout.setVerticalGroup(
            pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCtothingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClothingName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameClothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemClothing)
                    .addComponent(btnXoaClothing)
                    .addComponent(btnSuaClothing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocClothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCtothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindByNameClothing)
                    .addComponent(txtFindByNameClothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDemployClothing)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlStore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCtothing, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCtothing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                                                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(FrmShopKienTruc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(FrmShopKienTruc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(FrmShopKienTruc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(FrmShopKienTruc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmShopKienTruc frmShopKienTruc=new FrmShopKienTruc();
                frmShopKienTruc.setIconImage(new CustomIcon("src/images/shopicon.png").getImage());
                frmShopKienTruc.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDemployClothing;
    private javax.swing.JButton btnDemployStore;
    private javax.swing.JButton btnFindByNameClothing;
    private javax.swing.JButton btnFindByNameStore;
    private javax.swing.JButton btnSuaClothing;
    private javax.swing.JButton btnSuaStore;
    private javax.swing.JButton btnThemClothing;
    private javax.swing.JButton btnThemStore;
    private javax.swing.JButton btnXoaClothing;
    private javax.swing.JButton btnResetAll;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPaneStore;
    private javax.swing.JScrollPane scrollPaneClothing;
    private java.awt.Label lblAddress;
    private java.awt.Label lblIncome;
    private java.awt.Label lblLocClothing;
    private java.awt.Label lblLocStore;
    private java.awt.Label lblNameClothing;
    private java.awt.Label lblPrice;
    private java.awt.Label lblQuantity;
    private java.awt.Label lblShopName;
    private java.awt.Label lblTitle;
    private javax.swing.JPanel pnlCtothing;
    private javax.swing.JPanel pnlStore;
    private javax.swing.JPanel pnlStore1;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tableClothing;
    private DefaultTableModel tableModelClothing;
    private javax.swing.JTable tableStore;
    private DefaultTableModel tableModelStore;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtClothingName;
    private javax.swing.JTextField txtFindByNameClothing;
    private javax.swing.JTextField txtFindByNameStore;
    private javax.swing.JTextField txtIncome;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStoreName;
    private SpinnerNumberModel spinnerModel;
    // End of variables declaration     
    //Click chuột
    /*
     * // TODO Auto-generated method stub
		int row=table.getSelectedRow();
		txtMa.setText(table.getValueAt(row, 1).toString());
		txtTen.setText(table.getValueAt(row, 2).toString());
		txtDiaChi.setText(table.getValueAt(row, 3).toString());
		spnDiem.setValue((int) Double.parseDouble(table.getValueAt(row, 4).toString()));
     */
	@Override
	public void mouseClicked(MouseEvent e) throws NumberFormatException{
		Object o=e.getSource();
		if(o.equals(tableStore))
		{
			int rowStore=tableStore.getSelectedRow();
			txtStoreName.setText(tableStore.getValueAt(rowStore, 2).toString());
			txtIncome.setText(tableStore.getValueAt(rowStore, 3).toString());
			txtAddress.setText(tableStore.getValueAt(rowStore, 4).toString());
		}
		if(o.equals(tableClothing))
		{
			int rowClothing=tableClothing.getSelectedRow();
			txtClothingName.setText(tableClothing.getValueAt(rowClothing, 2).toString());
			spnQuantity.setValue((int) Integer.parseInt(tableClothing.getValueAt(rowClothing, 3).toString()));
			txtPrice.setText(tableClothing.getValueAt(rowClothing, 4).toString());
		}	
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnThemStore))
		{
			if(validateInputStore())
				addDataStore();
		}
		if(o.equals(btnResetAll))
		{
			resetClothing();
			resetStore();
		}
		if(o.equals(btnSuaStore))
		{
			if(validateInputStore())
				updateDataStore();
		}
		if(o.equals(btnFindByNameStore))
		{
			renderTableStoreFindByName();
		}
		if(o.equals(btnThemClothing))
		{
			if(validateInputClothing())
				addDataClothing();
		}
		if(o.equals(btnSuaClothing))
		{
			if(validateInputClothing())
				updateDataClothing();
		}
		if(o.equals(btnXoaClothing))
		{
			deleteDataClothing();
		}
		if(o.equals(btnFindByNameClothing))
		{
			renderTableClothingFindByName();
		}
		if(o.equals(btnDemployStore))
		{
			demployStore();
		}
		if(o.equals(btnDemployClothing))
		{
			demployClothing();
		}
	}
	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		btnDemployClothing.addActionListener(this);
		btnDemployStore.addActionListener(this);
		btnFindByNameClothing.addActionListener(this);
		btnFindByNameStore.addActionListener(this);
		btnSuaClothing.addActionListener(this);
		btnSuaStore.addActionListener(this);
		btnThemClothing.addActionListener(this);
		btnThemStore.addActionListener(this);
		btnXoaClothing.addActionListener(this);
		btnResetAll.addActionListener(this);	
		tableClothing.addMouseListener(this);
		tableStore.addMouseListener(this);
	}
	/*
	 * // TODO Auto-generated method stub
		tableModel.setRowCount(0);
		comboboxTim.removeAllItems();
		//String arr[]= {"a","b","c","d","e"};
		//tableModel.addRow(arr);
		if(sinhVien_DAO.getListSinhVien().size()==0)
		{
			JOptionPane.showMessageDialog(null, "Không có dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			System.out.println("Đã render danh sách sinh viên.");
			int stt= 1;
			for(SinhVien s: sinhVien_DAO.getListSinhVien())
			{
				String row[]= {Integer.toString(stt++),s.getMaSV(),s.getTenSV(),s.getDiaChi(),Double.toString(s.getDiem())};
				System.out.println(s.toString());
				tableModel.addRow(row);
				comboboxTim.addItem(s.getMaSV());
			}
		}
	 */
	@Override
	public void renderTableStore() {
		// TODO Auto-generated method stub
		tableModelStore.setRowCount(0);
		if(store_DAO.getListStore().size()==0)
		{
			JOptionPane.showMessageDialog(null, "Không có dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else
		{
			//public Store(String storeName, double income, String address)
			List<Store> listStore=store_DAO.getListStore();
			Collections.sort(listStore, new Comparator<Store>() {
				@Override
				public int compare(Store o1, Store o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1.getStoreId(),o2.getStoreId());
					//giảm thì ngược lại
				}
			});
			System.out.println("Đã render danh sách Store.");
			int stt= 1;
			for(Store store: listStore)
			{
				System.out.println(store.toString());
				String row[]= {Integer.toString(stt++),Integer.toString(store.getStoreId()),store.getStoreName(),Double.toString(store.getIncome()),store.getAddress()};
				tableModelStore.addRow(row);
			}
		}	
	}
	@Override
	public void addDataStore() {
		// TODO Auto-generated method stub	
		try
		{
			String storeName=txtStoreName.getText();
			String income=txtIncome.getText();
			String address=txtAddress.getText();		
			Store store=new Store(storeName, Double.parseDouble(income), address);
			if(store_DAO.addStore(store))
			{
				JOptionPane.showMessageDialog(null, "Thêm Store thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
				resetStore();
			}else
			{
				System.out.println("Xảy ra lỗi khi thêm.");
				JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi thêm.", "Lỗi.", JOptionPane.ERROR_MESSAGE);
			}
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "Lỗi.", JOptionPane.ERROR_MESSAGE);
		}
	}
	/*
	 * // TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			String maSV = table.getModel().getValueAt(row, 1).toString();
			int kTra = JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (kTra == JOptionPane.YES_OPTION) {
				if(sinhVien_DAO.deleteSinhVien(maSV))
				{
					renderTable();
					JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	 */
	@Override
	public void deleteDataStore() {
		
		
	}
	/*
	 * // TODO Auto-generated method stub
		//Chọn hàng
		int row = table.getSelectedRow();
		if (row != -1) {
			String ma=txtMa.getText();
			String ten=txtTen.getText();
			String diaChi=txtDiaChi.getText();
			int diem=(int) spnDiem.getValue();
			SinhVien sinhVien=new SinhVien(ma, ten, diaChi,(double)diem );
			if(sinhVien_DAO.updateSinhVien(sinhVien))
			{
				renderTable();
				JOptionPane.showMessageDialog(null, "Cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}else
			{
				JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	 */
	@Override
	public void updateDataStore() {
		// TODO Auto-generated method stub
		int row = tableStore.getSelectedRow();
		if(row!=-1)
		{
			try
			{
				String storeName=txtStoreName.getText();
				String income=txtIncome.getText();
				String address=txtAddress.getText();		
				Store store=new Store(storeName, Double.parseDouble(income), address);
				String storeId= tableStore.getModel().getValueAt(row, 1).toString();
				store.setStoreId(Integer.parseInt(storeId));
				if(store_DAO.updateStore(store))
				{
					JOptionPane.showMessageDialog(null, "Cập nhật Store thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
					resetStore();
				}else
				{
					System.out.println("Xảy ra lỗi khi cập nhật.");
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi cập nhật.", "Lỗi.", JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException e)
			{
				e.printStackTrace();
				System.out.println(e);
				JOptionPane.showMessageDialog(null, e, "Lỗi.", JOptionPane.ERROR_MESSAGE);
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	private boolean checkDouble(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}
	@Override
	public boolean validateInputStore() {
		String storeName=txtStoreName.getText();
		String income=txtIncome.getText();
		String address=txtAddress.getText();
		if(storeName.trim().equals("")||income.trim().equals("")||address.trim().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập hết.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(storeName.length()>=100||income.length()>=100||address.length()>=100)
		{
			JOptionPane.showMessageDialog(null, "Kí tự không được lớn hơn 100.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!checkDouble(income))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giá là số thực.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	@Override
	public void renderTableClothing() {
		//public Clothing(String clothingName, int quantity, double price)
		tableModelClothing.setRowCount(0);
		if(clothing_DAO.getListClothing().size()==0)
		{
			JOptionPane.showMessageDialog(null, "Không có dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else
		{
			System.out.println("Đã render danh sách Clothing.");
			List<Clothing> listClothing=clothing_DAO.getListClothing();
			Collections.sort(listClothing, new Comparator<Clothing>() {
				@Override
				public int compare(Clothing o1,Clothing o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1.getClothingId(),o2.getClothingId());
					//giảm thì ngược lại
				}
			});
			int stt= 1;
			for(Clothing clothing: listClothing)
			{
				System.out.println(clothing.toString());
				String row[]= {Integer.toString(stt++),Integer.toString(clothing.getClothingId()),clothing.getClothingName(),Integer.toString(clothing.getQuantity()),Double.toString(clothing.getPrice()),Integer.toString(clothing.getStore().getStoreId())};
				tableModelClothing.addRow(row);
			}
		}
		
	}
	@Override
	public void addDataClothing() throws NumberFormatException{
		// TODO Auto-generated method stub
		int row = tableStore.getSelectedRow();
		if(row!=-1)
		{
			String clothingName=txtClothingName.getText();
			int quantity=(int) spnQuantity.getValue();
			String priceString=txtPrice.getText();
			double price=Double.parseDouble(priceString);
			String storeIdString= tableStore.getModel().getValueAt(row, 1).toString();
			int storeId=Integer.parseInt(storeIdString);
			Clothing clothing=new Clothing(clothingName, quantity, price);
			if(clothing_DAO.addClothing(clothing, storeId,getClothingIdMax()))
			{
				JOptionPane.showMessageDialog(null, "Thêm dữ liệu Clothing thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				resetClothing();
			}else
			{
				JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi thêm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}	
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng trong bảng Store.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	@Override
	public void updateDataClothing() {
		// TODO Auto-generated method stub	
		int row = tableClothing.getSelectedRow();
		if(row!=-1)
		{
			try
			{
				String clothingName=txtClothingName.getText();
				int quantity=(int) spnQuantity.getValue();
				String priceString=txtPrice.getText();
				double price=Double.parseDouble(priceString);
				Clothing clothing=new Clothing(clothingName, quantity, price);
				String clothingId= tableClothing.getModel().getValueAt(row, 1).toString();
				clothing.setClothingId(Integer.parseInt(clothingId));
				if(clothing_DAO.updateClothing(clothing))
				{
					JOptionPane.showMessageDialog(null, "Cập nhật Clothing thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
					resetClothing();
				}else
				{
					System.out.println("Xảy ra lỗi khi cập nhật.");
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi cập nhật.", "Lỗi.", JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException e)
			{
				e.printStackTrace();
				System.out.println(e);
				JOptionPane.showMessageDialog(null, e, "Lỗi.", JOptionPane.ERROR_MESSAGE);
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/*
	private boolean checkInteger(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}*/
	@Override
	public boolean validateInputClothing() {
		// TODO Auto-generated method stub
		String clothingName=txtClothingName.getText();
		int quantity=(int) spnQuantity.getValue();
		String priceString=txtPrice.getText();
		if(clothingName.trim().equals("")||priceString.trim().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập hết.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(clothingName.length()>=100||priceString.length()>=100)
		{
			JOptionPane.showMessageDialog(null, "Kí tự không được lớn hơn 100.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!checkDouble(priceString))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giá là số thực.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(quantity<=0)
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0.", "Thông báo.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	@Override
	public void demployStore() throws DynamoDbException,DynamoDBMappingException{
		// TODO Auto-generated method stub
		store_DAO.deleteAllDynamoDB();
		for(Store store: store_DAO.getListStore())
		{
			store_DAO.addDataDynamoDB(store);
		}
		System.out.println("Demploy Store thành công.");
		JOptionPane.showMessageDialog(null, "Demploy Store thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
		FrmShopControlDynamo frmShopControlDynamo=new FrmShopControlDynamo();
		frmShopControlDynamo.renderFrm();
	}
	@Override
	public void demployClothing() throws DynamoDbException,DynamoDBMappingException {
		// TODO Auto-generated method stub
		clothing_DAO.deleteAllDynamoDB();
		for(Clothing clothing: clothing_DAO.getListClothing())
		{
			clothing_DAO.addDataDynamoDB(clothing, clothing.getStore().getStoreId());
		}
		System.out.println("Demploy Clothing thành công.");
		JOptionPane.showMessageDialog(null, "Demploy Clothing thành công.", "Thông báo.", JOptionPane.INFORMATION_MESSAGE);
		FrmShopControlDynamo frmShopControlDynamo=new FrmShopControlDynamo();
		frmShopControlDynamo.renderFrm();
	}
	@Override
	public void resetStore() {
		// TODO Auto-generated method stub
		txtStoreName.setText("");
		txtIncome.setText("");
		txtAddress.setText("");
		txtFindByNameStore.setText("");
		renderTableStore();
		
	}
	@Override
	public void resetClothing() {
		// TODO Auto-generated method stub
		txtClothingName.setText("");
		spnQuantity.setValue(0);
		txtPrice.setText("");
		txtFindByNameClothing.setText("");
		renderTableClothing();
	}
	/*
	 * tableModelStore.setRowCount(0);
		if(store_DAO.getListStore().size()==0)
		{
			JOptionPane.showMessageDialog(null, "Không có dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else
		{
			//public Store(String storeName, double income, String address)
			System.out.println("Đã render danh sách Store.");
			int stt= 1;
			for(Store store: store_DAO.getListStore())
			{
				System.out.println(store.toString());
				String row[]= {Integer.toString(stt++),Integer.toString(store.getStoreId()),store.getStoreName(),Double.toString(store.getIncome()),store.getAddress()};
				tableModelStore.addRow(row);
			}
		}
	 */
	@Override
	public void renderTableStoreFindByName() {
		// TODO Auto-generated method stub
		String nameFind=txtFindByNameStore.getText();	
		if(nameFind.trim().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên để tìm.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}else
		{
			tableModelStore.setRowCount(0);
			if(store_DAO.getListStoreByName(nameFind).size()==0)
			{
				JOptionPane.showMessageDialog(null, "Không tìm thấy.", "Thông báo", JOptionPane.WARNING_MESSAGE);
			}else
			{
				//public Store(String storeName, double income, String address)
				System.out.println("Đã render danh sách Store.");
				int stt= 1;
				for(Store store: store_DAO.getListStoreByName(nameFind))
				{
					System.out.println(store.toString());
					String row[]= {Integer.toString(stt++),Integer.toString(store.getStoreId()),store.getStoreName(),Double.toString(store.getIncome()),store.getAddress()};
					tableModelStore.addRow(row);
				}
			}
		}
	}
	@Override
	public void renderTableClothingFindByName() {
		// TODO Auto-generated method stub
		String nameFind=txtFindByNameClothing.getText();	
		if(nameFind.trim().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên Clothing để tìm.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}else
		{
			tableModelClothing.setRowCount(0);
			if(clothing_DAO.getListClothingByName(nameFind).size()==0)
			{
				JOptionPane.showMessageDialog(null, "Không tìm thấy.", "Thông báo", JOptionPane.WARNING_MESSAGE);
			}else
			{
				//public Store(String storeName, double income, String address)
				System.out.println("Đã render danh sách Clothing.");
				int stt= 1;
				for(Clothing clothing: clothing_DAO.getListClothingByName(nameFind))
				{
					System.out.println(clothing.toString());
					String row[]= {Integer.toString(stt++),Integer.toString(clothing.getClothingId()),clothing.getClothingName(),Integer.toString(clothing.getQuantity()),Double.toString(clothing.getPrice()),Integer.toString(clothing.getStore().getStoreId())};
					tableModelClothing.addRow(row);
				}
			}
		}		
	}
	/*
	 * int row = table.getSelectedRow();
		if (row != -1) {
			String maSV = table.getModel().getValueAt(row, 1).toString();
			int kTra = JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (kTra == JOptionPane.YES_OPTION) {
				if(sinhVien_DAO.deleteSinhVien(maSV))
				{
					renderTable();
					JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	 */
	@Override
	public void deleteDataClothing() throws NumberFormatException{
		// TODO Auto-generated method stub
		int row=tableClothing.getSelectedRow();
		if (row != -1) {
			String clothingIdString=tableClothing.getModel().getValueAt(row, 1).toString();
			int kTra = JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (kTra == JOptionPane.YES_OPTION) {
				if(clothing_DAO.deleteClothing(Integer.parseInt(clothingIdString)))
				{
					JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					renderTableClothing();
					renderTableStore();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng bảng Clothing để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	@Override
	public int getClothingIdMax() {
		// TODO Auto-generated method stub
		int max = Integer.MIN_VALUE;
		if(clothing_DAO.getListClothing().size()==0)
		{
			return 0;
		}
		for(Clothing clothing: clothing_DAO.getListClothing())
		{
			if(clothing.getClothingId() > max)
			{
				max= clothing.getClothingId();
			}	
		}
		return max;
	}
	
}
