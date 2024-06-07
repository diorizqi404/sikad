/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sikad;

import java.util.Date;
import com.toedter.calendar.JDateChooser;
import config.koneksi;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 *
 * @author rizqi
 */
public class DashboardGuru extends javax.swing.JFrame {

    private Connection conn;
    private String id_mapel;
    private String namaGuru;
    private String id_guru;
    private String tbl_id;
    private String tbl_nama;
    private String keyword;

    public DashboardGuru() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/assets/rpl.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(this);
        conn = koneksi.getConnection();
        getData("");

        fillComboBox();
        getMapel();
    }

    public DashboardGuru(String id_mapel, String namaGuru, String id_guru) {
        initComponents();
        setLocationRelativeTo(this);
        ImageIcon icon = new ImageIcon("src/assets/rpl.png");
        setIconImage(icon.getImage());
        this.id_mapel = id_mapel;
        this.id_guru = id_guru;
        conn = koneksi.getConnection();
        getData("");
        fillComboBox();
        getMapel();
        nama_guru.setText("Selamat Datang, " + namaGuru + "!");
        btn_hapus.setVisible(false);
//        jLabel1.setText(namaGuru);
    }

    private void getMapel() {
        String namaMapel;
        try {
            String sql = "SELECT * FROM mapel WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id_mapel);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                namaMapel = rs.getString("nama_mapel");
                nama_mapel.setText("Mapel: " + namaMapel);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // menampilkan data ke table
    private void getData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        keyword = tf_search.getText();

        try {
            String sql = "SELECT tgs.id, tgs.nama_tugas, tgs.deskripsi, tgs.deadline, kls.nama_kelas FROM tugas AS tgs JOIN mapel AS mp ON tgs.id_mapel = mp.id JOIN guru AS gr ON tgs.id_guru = gr.id JOIN kelas AS kls ON tgs.id_kelas = kls.id WHERE gr.id = ? AND tgs.nama_tugas LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id_guru);
            st.setString(2, "%" + keyword + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("tgs.id");
                String nama_tugas = rs.getString("tgs.nama_tugas");
                String deskripsi = rs.getString("tgs.deskripsi");
                String deadline = rs.getString("tgs.deadline");
                String nama_kelas = rs.getString("kls.nama_kelas");

                Object[] rowData = {id, nama_tugas, deskripsi, deadline, nama_kelas};
                model.addRow(rowData);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Logger.getLogger(DashboardGuru.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public class Item {

        private String id;
        private String name;

        public Item(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        // Override `toString` method to determine what to display in the JComboBox
        @Override
        public String toString() {
            return name;
        }
    }

    private void fillComboBox() {
        try {
            String sql = "SELECT id, nama_kelas FROM kelas";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            mapel.removeAllItems();

            while (rs.next()) {
                mapel.addItem(new Item(rs.getString("id"), rs.getString("nama_kelas")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nama_guru = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nm_tugas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        desc_tugas = new javax.swing.JTextArea();
        nama_mapel = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_clear = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        deadline = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mapel = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tf_search = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKAD - Dashboard Guru");
        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

        nama_guru.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nama_guru.setText("Selamat Datang, Agus !");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tambahkan Tugas Baru");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nama Tugas");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Deskripsi Tugas");

        desc_tugas.setColumns(20);
        desc_tugas.setRows(5);
        jScrollPane1.setViewportView(desc_tugas);

        nama_mapel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        nama_mapel.setText("Mapel: Matematika");

        btn_tambah.setBackground(new java.awt.Color(0, 255, 0));
        btn_tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save-3-line (1).png"))); // NOI18N
        btn_tambah.setText("TAMBAHKAN TUGAS");
        btn_tambah.setBorder(null);
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Tugas", "Deskripsi", "Deadline", "Kelas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(48);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        btn_clear.setBackground(new java.awt.Color(255, 255, 0));
        btn_clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete-back-2-line.png"))); // NOI18N
        btn_clear.setText("CLEAR FORM");
        btn_clear.setBorder(null);
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 51));
        btn_hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete-bin-6-line.png"))); // NOI18N
        btn_hapus.setText("HAPUS TUGAS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Deadline Tugas");

        mapel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapelActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Kelas");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout-box-line.png"))); // NOI18N
        jButton1.setText("Logout");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/search-line.png"))); // NOI18N
        jButton2.setText("Cari Tugas");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cetak.setBackground(new java.awt.Color(102, 255, 102));
        cetak.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cetak.setText("Cetak Tugas Dokumen PDF");
        cetak.setBorder(null);
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(nm_tugas)
                                                .addComponent(jScrollPane1)
                                                .addComponent(deadline, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 2, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_hapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_tambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
                                .addGap(45, 45, 45)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_mapel)
                            .addComponent(nama_guru))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(nama_guru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(tf_search))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(nama_mapel)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nm_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deadline, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearForm() {
        nm_tugas.setText("");
        desc_tugas.setText("");
        deadline.setText("");
        btn_tambah.setText("TAMBAHKAN TUGAS");
        btn_hapus.setVisible(false);
    }

//    public void printDateFromJDateChooser(JDateChooser dateChooser) {
//    Date date = dateChooser.getDate();
//    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//    String strDate = formatter.format(date);
//    System.out.println("Tanggal yang dipilih adalah " + strDate);
//}
    // simpan data
    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String nama = nm_tugas.getText();
        String desc = desc_tugas.getText();
        String deadline_tgs = deadline.getText();
        Item selectedItem = (Item) mapel.getSelectedItem();
        String kelas = selectedItem.getId();

        if (nama.isEmpty() || desc.isEmpty() || deadline_tgs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String namaTombol = btn_tambah.getText();

        if (namaTombol == "SIMPAN PERUBAHAN") {
//            String nama = nm_tugas.getText();
//            String desc = desc_tugas.getText();
//            String deadline_tgs = deadline.getText();
//            Item selectedItem = (Item) mapel.getSelectedItem();
//            String kelas = selectedItem.getId();

//lakukan edit data
            try {
                String sql = "UPDATE tugas SET id_mapel = ?, nama_tugas = ?, deskripsi = ?, deadline = ?, id_guru = ?, id_kelas = ? WHERE id = ?";
                System.out.println("SQL statement: " + sql);
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, Integer.parseInt(id_mapel));
                st.setString(2, nama);
                st.setString(3, desc);
                st.setString(4, deadline_tgs);
                st.setInt(5, Integer.parseInt(id_guru));
                st.setInt(6, Integer.parseInt(kelas));
                st.setInt(7, Integer.parseInt(tbl_id));
                System.out.println("Parameters: " + id_mapel + ", " + nama + ", " + desc + ", " + deadline_tgs + ", " + id_guru + ", " + kelas + ", " + tbl_id);
                int affectedRows = st.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Tugas berhasil diubah!");
                    getData("");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Tugas gagal diubah!");
                    clearForm();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            btn_tambah.setText("TAMBAHKAN TUGAS");
        } else {
            //lakukan create data
            try {
                String sql = "INSERT INTO tugas (id_mapel,  nama_tugas, deskripsi, deadline, id_guru, id_kelas) VALUE (?, ?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, id_mapel);
                st.setString(2, nama);
                st.setString(3, desc);
                st.setString(4, deadline_tgs);
                st.setString(5, id_guru);
                st.setString(6, kelas);
                int affectedRows = st.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Tugas berhasil ditambahkan!");
                    getData("");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Tugas gagal ditambahkan!");
                    clearForm();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clearForm();
    }//GEN-LAST:event_btn_clearActionPerformed

    //hapus data
    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        try {
            String sql = "DELETE FROM tugas WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(tbl_id));

            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus tugas " + tbl_nama + "?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                int affectedRows = st.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Tugas berhasil dihapus!");
                    getData("");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Tugas gagal dihapus!");
                    clearForm();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tugas batal dihapus!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void mapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapelActionPerformed
        // TODO add your handling code here:
        //A
    }//GEN-LAST:event_mapelActionPerformed

    public void setSelectedItemByValue(JComboBox<Item> comboBox, String value) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).toString().equals(value)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    // ketika baris di tabel diklik
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        tbl_id = jTable1.getValueAt(row, 0).toString();
        tbl_nama = jTable1.getValueAt(row, 1).toString();
        String tbl_desc = jTable1.getValueAt(row, 2).toString();
        String tbl_deadline = jTable1.getValueAt(row, 3).toString();
        String tbl_kelas = jTable1.getValueAt(row, 4).toString();

        nm_tugas.setText(tbl_nama);
        desc_tugas.setText(tbl_desc);
        deadline.setText(tbl_deadline);
        btn_hapus.setVisible(true);
        setSelectedItemByValue(mapel, tbl_kelas);
        btn_tambah.setText("SIMPAN PERUBAHAN");
    }//GEN-LAST:event_jTable1MouseClicked

    // tombol logout
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin logout?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            MainMenu form = new MainMenu();
            this.dispose();
            form.setVisible(true);
            JOptionPane.showMessageDialog(null, "Anda telah logout!");
        } else {
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //btn search data
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getData(keyword);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        String guru_name = namaGuru;
        CreatePDF createPDF = new CreatePDF();
        try {
            URL logoUrl = getClass().getResource("/assets/rpl.png");
            createPDF.generatePdfFromTable(jTable1, "SIKAD", logoUrl.toString(), "Laporan Tugas Siswa", guru_name);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cetakActionPerformed

    public class CreatePDF {

        public void generatePdfFromTable(JTable table, String appName, String logoUrl, String textTitle, String nama_guru) throws DocumentException, FileNotFoundException, MalformedURLException, IOException {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
            fileChooser.setFileFilter(filter);

            fileChooser.setSelectedFile(new java.io.File("Laporan_tugas.pdf"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                    fileToSave = new java.io.File(fileToSave + ".pdf");
                }

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                document.open();

                // Create header table
                PdfPTable headerTable = new PdfPTable(2);
                headerTable.setWidthPercentage(85);
                headerTable.setSpacingAfter(30f);

                // Add logo
                Image logo = Image.getInstance(new URL(logoUrl));
                PdfPCell logoCell = new PdfPCell(logo, true);
                logoCell.setBorder(Rectangle.NO_BORDER);
                logoCell.setFixedHeight(70);
                headerTable.addCell(logoCell);

                // Add application name and date
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedDate = sdf.format(new Date());
                Paragraph title = new Paragraph(appName + "\n" + formattedDate, titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                PdfPCell titleCell = new PdfPCell();
                titleCell.addElement(title);
                titleCell.setBorder(Rectangle.NO_BORDER);
                titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                headerTable.addCell(titleCell);

                document.add(headerTable);
                
                PdfPTable headerTable2 = new PdfPTable(1);
                headerTable2.setWidthPercentage(85);
                headerTable2.setSpacingAfter(20f);
                
                Paragraph titleTable = new Paragraph(textTitle, titleFont);
                PdfPCell titleTableCell = new PdfPCell();
                titleTableCell.addElement(titleTable);
                titleTableCell.setBorder(Rectangle.NO_BORDER);
                titleTableCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable2.addCell(titleTableCell);
                
                document.add(headerTable2);
                

                // Create PdfPTable with the same number of columns as the JTable
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

                // Get column widths from JTable
                int totalWidth = 0;
                for (int i = 0; i < table.getColumnCount(); i++) {
                    totalWidth += table.getColumnModel().getColumn(i).getWidth();
                }

                // Convert JTable column widths to relative widths for PdfPTable
                float[] columnWidths = new float[table.getColumnCount()];
                for (int i = 0; i < table.getColumnCount(); i++) {
                    columnWidths[i] = (float) table.getColumnModel().getColumn(i).getWidth() / totalWidth;
                }

                // Set column widths in PdfPTable
                pdfTable.setWidths(columnWidths);

                //adding table headers
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(table.getColumnName(i));
                }

                //extracting data from the JTable to the PDF table
                TableModel model = table.getModel();
                for (int rows = 0; rows < model.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        pdfTable.addCell(model.getValueAt(rows, cols).toString());
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "Saved as: " + fileToSave.getAbsolutePath());
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardGuru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField deadline;
    private javax.swing.JTextArea desc_tugas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<Item> mapel;
    private javax.swing.JLabel nama_guru;
    private javax.swing.JLabel nama_mapel;
    private javax.swing.JTextField nm_tugas;
    private javax.swing.JTextField tf_search;
    // End of variables declaration//GEN-END:variables
}
