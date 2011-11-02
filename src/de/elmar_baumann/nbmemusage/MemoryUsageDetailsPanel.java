package de.elmar_baumann.nbmemusage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 * @author Elmar Baumann
 */
final class MemoryUsageDetailsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    MemoryUsageDetailsPanel() {
        initComponents();
    }

    void setTotalMemoryLongInfo(String info) {
        labelTotalMemorySize.setText(info);
    }

    void setMaxMemoryLongInfo(String info) {
        labelMaxMemorySize.setText(info);
    }

    void setFreeMemoryLongInfo(String info) {
        labelFreeMemorySize.setText(info);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents
        GridBagConstraints gridBagConstraints;

        labelTotalMemoryPrompt = new JLabel();
        labelTotalMemorySize = new JLabel();
        labelMaxMemoryPrompt = new JLabel();
        labelMaxMemorySize = new JLabel();
        labelFreeMemoryPrompt = new JLabel();
        labelFreeMemorySize = new JLabel();

        setBackground(new Color(255, 223, 181));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setName("Form"); // NOI18N
        setLayout(new GridBagLayout());

        Mnemonics.setLocalizedText(labelTotalMemoryPrompt, NbBundle.getMessage(MemoryUsageDetailsPanel.class, "MemoryUsageDetailsPanel.labelTotalMemoryPrompt.text")); // NOI18N
        labelTotalMemoryPrompt.setName("labelTotalMemoryPrompt"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new Insets(3, 3, 0, 0);
        add(labelTotalMemoryPrompt, gridBagConstraints);

        labelTotalMemorySize.setName("labelTotalMemorySize"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(3, 5, 0, 3);
        add(labelTotalMemorySize, gridBagConstraints);

        Mnemonics.setLocalizedText(labelMaxMemoryPrompt, NbBundle.getMessage(MemoryUsageDetailsPanel.class, "MemoryUsageDetailsPanel.labelMaxMemoryPrompt.text")); // NOI18N
        labelMaxMemoryPrompt.setName("labelMaxMemoryPrompt"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new Insets(3, 3, 0, 0);
        add(labelMaxMemoryPrompt, gridBagConstraints);

        labelMaxMemorySize.setName("labelMaxMemorySize"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(3, 5, 0, 3);
        add(labelMaxMemorySize, gridBagConstraints);

        Mnemonics.setLocalizedText(labelFreeMemoryPrompt, NbBundle.getMessage(MemoryUsageDetailsPanel.class, "MemoryUsageDetailsPanel.labelFreeMemoryPrompt.text")); // NOI18N
        labelFreeMemoryPrompt.setName("labelFreeMemoryPrompt"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 0);
        add(labelFreeMemoryPrompt, gridBagConstraints);

        labelFreeMemorySize.setName("labelFreeMemorySize"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 5, 3, 3);
        add(labelFreeMemorySize, gridBagConstraints);
    }//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel labelFreeMemoryPrompt;
    private JLabel labelFreeMemorySize;
    private JLabel labelMaxMemoryPrompt;
    private JLabel labelMaxMemorySize;
    private JLabel labelTotalMemoryPrompt;
    private JLabel labelTotalMemorySize;
    // End of variables declaration//GEN-END:variables
}
