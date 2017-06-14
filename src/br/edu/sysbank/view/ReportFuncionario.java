
package br.edu.sysbank.view;

import br.edu.sysbank.controller.CtrlFuncionario;
import br.edu.sysbank.model.Funcionario;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ReportFuncionario extends javax.swing.JInternalFrame {
    List<Funcionario> funcionarios;
    DefaultTableModel modelo = new DefaultTableModel();
    CtrlFuncionario ctrlFuncionario;
    
    public ReportFuncionario() {
        initComponents();
        busca();
    }
    
    public ReportFuncionario(List lista) {
        initComponents();
        funcionarios = lista;
        busca();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPesquisa = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jButtonPesquisa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jButtonAltera = new javax.swing.JButton();

        jLabelPesquisa.setText("Pesquisa");

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
        jTablePesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTablePesquisaFocusGained(evt);
            }
        });
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
                        .addComponent(jLabelPesquisa)
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
                    .addComponent(jLabelPesquisa)
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
            "Id" ,"Nome", "CPF", "E-mail", "CEP","Cargo"});
        modelo.setRowCount(0);
        ctrlFuncionario = new CtrlFuncionario();
        funcionarios = ctrlFuncionario.pesquisar(jTextFieldPesquisa.getText());
        for(Funcionario f: funcionarios){
            modelo.addRow(new Object[]{
                f.getIdFuncionario(),
                f.getNome(),
                f.getCpf(),
                f.getEmail(), 
                f.getEndereco().getCEP(),
                f.getCargo().getNomeCargo()
            });
        }
        jTablePesquisa.setModel(modelo);
    }//GEN-LAST:event_jButtonPesquisaActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    public void busca(){
        jButtonPesquisa.doClick();
    }
    
    private void jButtonAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteraActionPerformed
        // TODO add your handling code here:
        if (!jTablePesquisa.isCursorSet()) {
            long id = Long.parseLong(modelo.getValueAt(jTablePesquisa.getSelectedRow(), 0).toString());
            IFormFuncionario formFuncionario = new IFormFuncionario(id);
            super.getParent().add(formFuncionario);
            formFuncionario.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAlteraActionPerformed

    private void jTablePesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTablePesquisaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablePesquisaFocusGained

    private void jTextFieldPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaFocusGained
        // TODO add your handling code here:
        busca();
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
    private javax.swing.JLabel jLabelPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesquisa;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
