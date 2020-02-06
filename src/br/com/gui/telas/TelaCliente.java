/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gui.telas;

import java.sql.*;
import br.com.gui.dal.ModuloConexao;
import javax.swing.JOptionPane;
// a linha abaixo importa recurços da biblioteca rs2xml.jar 
import net.proteanit.sql.DbUtils;

/**
 *
 * @author angel
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //metodo para adicionar clientes
    private void adicionar() {
        String sql = "insert into tbClientes(nomecli,endcli,fonecli,emailcli)values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());

            // validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                // a linha abaixo atualiza a tabela com os dados do usúario
                // a estrtrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                // a linha abaixo serve de apoio para o entendimento da lógica
                // System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
                    // as linhas abaixo limpam os campos
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // método para pesquisar clientes pelo nome com filtro
    private void pesquisar_clientes() {
        String sql = "select * from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o comteudo da caixa de pesquisa para o '?'
            // atenção ao '%' continuação da String sql
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // método pera preencher os campos do formulário com os dados da tabela
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());

        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        // a linha abaixo desabilita o botão adicionar
        btnAdicionar.setEnabled(false);

    }

    // criando o metódo alterar dados do cliente
    private void alterar_clientes() {
        String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());

            // validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                // a linha abaixo atualiza a tabela com os dados do cliente
                // a estrtrutura abaixo é usada para alterar os dados do cliente
                int adicionado = pst.executeUpdate();
                // a linha abaixo serve de apoio para o entendimento da lógica
                // System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso!");
                    // as linhas abaixo limpam os campos

                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                      btnAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
// criando o método remover clientes
    private void remover(){
        // a estrutura abaixo confirma a remoção do usúario
        int comfirma = JOptionPane.showConfirmDialog(null,"Tem serteza que deseja remover esse clientes","Atenção",JOptionPane.YES_NO_OPTION);
         if (comfirma==JOptionPane.YES_NO_OPTION) {
             String sql = "delete from tbclientes where idcli=?";
             try {
                 pst=conexao.prepareStatement(sql);
                 pst.setString(1,txtCliId.getText());
                  int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Clientes removido com sucesso!");
                    // as linhas abaixo limpam os campos
                    txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                     btnAdicionar.setEnabled(true);
                }
                  
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
             }
            
        } 
        
       
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(499, 454));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gui/icones/read3.png"))); // NOI18N

        jLabel1.setText("*Campos obrigatórios");

        jLabel3.setText("*Nome");

        jLabel4.setText("Endereço");

        jLabel5.setText("*Telefone");

        jLabel6.setText("Email");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        jToolBar1.setRollover(true);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gui/icones/create.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gui/icones/update.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gui/icones/delete.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel7.setText("Id Cliente");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(183, 183, 183))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(52, 52, 52))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionar)
                        .addGap(32, 32, 32)
                        .addComponent(btnAlterar)
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRemover)
                        .addGap(25, 25, 25))))
        );

        setBounds(0, 0, 499, 456);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // método para alterar clientes
        alterar_clientes();
    }//GEN-LAST:event_btnAlterarActionPerformed
// o evento abaixo é do tipo 'enquanto for digitdo'
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // método pesquisar clientes
        pesquisar_clientes();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
// o evento abaixo é usado para setar os campos da tabela 'clicando com o mouse
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // método setar campos
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // método de remover clientes
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
