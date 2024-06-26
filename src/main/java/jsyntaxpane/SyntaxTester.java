/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jsyntaxpane;

import jsyntaxpane.actions.ActionUtils;
import jsyntaxpane.actions.CaretMonitor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SyntaxTester extends javax.swing.JFrame {

    /**
     * Creates new form Tester
     */
    public SyntaxTester() {
        // this is a test for adding regex lexer.  It wont work unless the
        // JavaRegex.properties is found in the classpath
        // DefaultSyntaxKit.registerContentType("text/aa_regex", "jsyntaxpane.JavaRegexKit");
        initComponents();
        jCmbLangs.setModel(new DefaultComboBoxModel(DefaultSyntaxKit.getContentTypes()));
        // jEdtTest.setContentType(jCmbLangs.getItemAt(0).toString());
        jCmbLangs.setSelectedItem("text/java");
        new CaretMonitor(jEdtTest, lblCaretPos);
        try {
            // Try to load a relatively big Java file
            loadFile("./target/generated-sources/jflex/jsyntaxpane/lexers/ClojureLexer.java");
        } catch (IOException ex) {
            // This happens when not building from source - ignore
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButtonLoadFile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblCaretPos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEdtTest = new javax.swing.JEditorPane();
        lblToken = new javax.swing.JLabel();
        jCmbLangs = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jsyntaxpane/Bundle"); // NOI18N
        setTitle(bundle.getString("SyntaxTester.title")); // NOI18N

        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);

        jButtonLoadFile.setMnemonic('L');
        jButtonLoadFile.setText(bundle.getString("SyntaxTester.jButtonLoadFile.text")); // NOI18N
        jButtonLoadFile.setFocusable(false);
        jButtonLoadFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLoadFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadFileActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonLoadFile);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        lblCaretPos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblCaretPos.setText(bundle.getString("SyntaxTester.lblCaretPos.text")); // NOI18N

        jEdtTest.setContentType(bundle.getString("SyntaxTester.jEdtTest.contentType")); // NOI18N
        jEdtTest.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jEdtTest.setCaretColor(new java.awt.Color(153, 204, 255));
        jEdtTest.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jEdtTestCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEdtTest);

        lblToken.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lblToken.setText(bundle.getString("SyntaxTester.lblToken.text")); // NOI18N

        jCmbLangs.setMaximumRowCount(20);
        jCmbLangs.setFocusable(false);
        jCmbLangs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbLangsItemStateChanged(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"8pts", "12pts", "16pts"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(486, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jCmbLangs, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 473, Short.MAX_VALUE)
                                                        .addComponent(lblCaretPos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(lblToken, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                                        .addGap(484, 484, 484)))
                                        .addGap(0, 0, 0)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(426, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)
                                        .addComponent(lblToken, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblCaretPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jCmbLangs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jEdtTestCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jEdtTestCaretUpdate
        SyntaxDocument sDoc = ActionUtils.getSyntaxDocument(jEdtTest);
        if (sDoc != null) {
            Token t = sDoc.getTokenAt(evt.getDot());
            if (t != null) {
                CharSequence tData = t.getText(sDoc);
                if (t.length > 40) {
                    tData = tData.subSequence(0, 40);
                }
                lblToken.setText(t.toString() + ": " + tData);
            } else {
                // null token, remove the status
                lblToken.setText(java.util.ResourceBundle.getBundle("jsyntaxpane/Bundle").getString("NO_TOKEN_AT_CURSOR"));
            }
        }

    }//GEN-LAST:event_jEdtTestCaretUpdate

    private void jCmbLangsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbLangsItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String lang = jCmbLangs.getSelectedItem().toString();

            // save the state of the current JEditorPane, as it's Document is about
            // to be replaced.
            String oldText = jEdtTest.getText();

            // install a new DefaultSyntaxKit on the JEditorPane for the requested language.
            jEdtTest.setContentType(lang);
            // Recreate the Toolbar
            jToolBar1.removeAll();
            EditorKit kit = jEdtTest.getEditorKit();
            if (kit instanceof DefaultSyntaxKit) {
                DefaultSyntaxKit defaultSyntaxKit = (DefaultSyntaxKit) kit;
                defaultSyntaxKit.addToolBarActions(jEdtTest, jToolBar1);
            }
            jToolBar1.add(jButtonLoadFile);
            jToolBar1.validate();
            try {
                // setText should not be called (read the JavaDocs).  Better use the read
                // method and create a new document:
                // jEdtTest.read(new StringReader(oldText), lang);

                // ... however, the default read() will trigger a call to insertString() for each line
                // of the document which again will a call to parse(), making the UI freeze for large documents.
                // Therefore, for large texts, its best to create a new document and insert the data in
                // a single operation:
                Document doc = kit.createDefaultDocument();
                doc.insertString(0, oldText, null);
                jEdtTest.setDocument(doc);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        jEdtTest.requestFocusInWindow();
    }//GEN-LAST:event_jCmbLangsItemStateChanged

    private void jButtonLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadFileActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) try {
            loadFile(fc.getSelectedFile().getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonLoadFileActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() == 0) {
            jEdtTest.setFont(Font.decode("sans-8"));
        } else if (jComboBox1.getSelectedIndex() == 1) {
            jEdtTest.setFont(Font.decode("sans-12"));
        } else if (jComboBox1.getSelectedIndex() == 2) {
            jEdtTest.setFont(Font.decode("sans-16"));
        } else {
            throw new IllegalArgumentException("not implemented");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Loads a file and shows in the editor
     */
    private void loadFile(String filename) throws IOException {
        // This will load a file:
        // jEdtTest.read(new FileInputStream(filename), null);

        // ... however, the default read() will trigger a call to insertString() for each line
        // of the document which again will a call to parse(), making the UI freeze for large files.
        // Therefore, for large texts, its best to create a new document and insert the data in
        // a single operation:
        Document doc = jEdtTest.getEditorKit().createDefaultDocument();
        String str = new String(Files.readAllBytes(Paths.get(filename)));
        try {
            doc.insertString(0, str, null);
        } catch (BadLocationException ex) {
            throw new IOException(ex); // Should never happen
        }
        jEdtTest.setDocument(doc);
        ((SyntaxDocument) jEdtTest.getDocument()).resetUndo();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    DefaultSyntaxKit.initKit();
                    new SyntaxTester().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(2);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoadFile;
    private javax.swing.JComboBox jCmbLangs;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEdtTest;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCaretPos;
    private javax.swing.JLabel lblToken;
    // End of variables declaration//GEN-END:variables
}
