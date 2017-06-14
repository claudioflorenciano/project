
package br.edu.sysbank.view;

import br.edu.sysbank.controller.CtrlCliente;
import br.edu.sysbank.model.Cliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ReportCliente extends javax.swing.JInternalFrame {
    List<Cliente> clientes;
    DefaultTableModel modelo = new DefaultTableModel();
    CtrlCliente ctrlCliente;
    
    public ReportCliente() {
        initComponents();
        
    }
    
    public ReportCliente(List lista) {
        initComponents();
        clientes = lista;
        jButtonPesquisa.doClick();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jButtonPesquisa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jButtonAltera = new javax.swing.JButton();

        jLabel1.setText("Pesquisa");

        jTextFieldPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPesquisaFocusGained(evt);
            }
        });

        jButtonPesquisa.setText("OK");
        jButtonPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisaActionPerformed(evt);
            }
        });

        jTablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTablePesquisa);

        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonAltera.setText("Alterar");
        jButtonAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisa)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAltera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFechar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisa)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonAltera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaActionPerformed
        modelo.setColumnIdentifiers(new String[] {
            "id","Nome", "CPF", "E-mail", "CEP"});
        modelo.setRowCount(0);
        ctrlCliente = new CtrlCliente();
        clientes = ctrlCliente.pesquisar(jTextFieldPesquisa.getText());
        for(Cliente c: clientes){
            modelo.addRow(new Object[]{
                c.getIdCliente(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(), 
                c.getEndereco().getCEP()
            });
        }
        jTablePesquisa.setModel(modelo);
    }//GEN-LAST:event_jButtonPesquisaActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteraActionPerformed
        // TODO add your handling code here:
        if (!jTablePesquisa.isCursorSet()) {
            long id = Long.parseLong(modelo.getValueAt(jTablePesquisa.getSelectedRow(), 0).toString());
            IFormCliente formCliente = new IFormCliente(id);
            super.getParent().add(formCliente);
            formCliente.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAlteraActionPerformed

    private void jTextFieldPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaFocusGained
        // TODO add your handling code here:
        jButtonPesquisa.doClick();
    }//GEN-LAST:event_jTextFieldPesquisaFocusGained
    
    /*private void listarTodos(){
        for(Cliente c : clientes){
            System.out.println(""
                    + "Nome: " + c.getNome() + " - "
                    + "CPF: " + c.getCpf());
        }
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAltera;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesquisa;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
