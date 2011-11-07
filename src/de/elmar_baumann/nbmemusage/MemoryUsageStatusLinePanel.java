package de.elmar_baumann.nbmemusage;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 * Similar {@code org.openide.actions.HeapView} for the status line (smaller).
 * @author Elmar Baumann
 */
public class MemoryUsageStatusLinePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private static final int REFRESH_INTERVAL_IN_SECONDS = 1;
    private static final Icon ICON_UPARROW = new ImageIcon(MemoryUsageStatusLinePanel.class.getResource("/de/elmar_baumann/nbmemusage/uparrow.png"));
    private static final Icon ICON_DOWNARROW = new ImageIcon(MemoryUsageStatusLinePanel.class.getResource("/de/elmar_baumann/nbmemusage/downarrow.png"));
    private final MessageFormat format = new MessageFormat("{0,choice,0#{0,number,0.0}|999<{0,number,0}} MB");
    private final MemoryUsageDetailsPanel detailsPanel = new MemoryUsageDetailsPanel();
    private final ScheduledExecutorService executorService;
    private Popup detailsPopup;
    private boolean isDisplayDetails;

    public MemoryUsageStatusLinePanel() {
        initComponents();
        executorService = Executors.newSingleThreadScheduledExecutor(threadFactory);
        executorService.scheduleAtFixedRate(updater, 0, REFRESH_INTERVAL_IN_SECONDS, TimeUnit.SECONDS);
        listen();
    }

    private void listen() {
        addMouseListener(toggleDetailsPanelDisplayListener);
        labelUsedMemory.addMouseListener(toggleDetailsPanelDisplayListener);
        detailsPanel.addMouseListener(toggleDetailsPanelDisplayListener);
    }
    private final Runnable updater = new Runnable() {

        @Override
        public void run() {
            updateMemoryUsageInfo();
        }

        private void updateMemoryUsageInfo() {
            Runtime runtime = Runtime.getRuntime();
            long totalMemoryInBytes = runtime.totalMemory();
            long freeMemoryInBytes = runtime.freeMemory();
            long maxMemoryInBytes = runtime.maxMemory();
            long usedMemoryInBytes = totalMemoryInBytes - freeMemoryInBytes;
            labelUsedMemory.setText(formatAsMegabytes(usedMemoryInBytes));
            detailsPanel.setTotalMemory(formatAsMegabytes(totalMemoryInBytes));
            detailsPanel.setMaxMemory(formatAsMegabytes(maxMemoryInBytes));
            detailsPanel.setFreeMemory(formatAsMegabytes(freeMemoryInBytes));
            detailsPanel.setUsedMemory(formatAsMegabytes(usedMemoryInBytes));
        }
    };

    private String formatAsMegabytes(long bytes) {
        double megabytes = (double) bytes / 1024 / 1024;
        return format.format(new Object[]{megabytes});
    }

    private final ThreadFactory threadFactory = new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Memory Usage Status Line Panel");
            return thread;
        }
    };

    private void toggleDisplayDetails() {
        if (isDisplayDetails) {
            detailsPopup.hide();
        } else {
            createDetailsPopup();
            detailsPopup.show();
        }
        isDisplayDetails = !isDisplayDetails;
        buttonShowDetails.setIcon(isDisplayDetails ? ICON_DOWNARROW : ICON_UPARROW);
    }

    private void createDetailsPopup() {
        PopupFactory popupFactory = PopupFactory.getSharedInstance();
        Frame mainWindow = WindowManager.getDefault().getMainWindow();
        Point detailsPanelTopLeft = getDetailsPanelTopLeftPointForPopup();
        detailsPopup = popupFactory.getPopup(mainWindow, detailsPanel, detailsPanelTopLeft.x, detailsPanelTopLeft.y);
    }

    private Point getDetailsPanelTopLeftPointForPopup() {
        Point thisTopLeft = getLocationOnScreen();
        int thisX = thisTopLeft.x;
        int thisY = thisTopLeft.y;
        Dimension popupSize = detailsPanel.getPreferredSize();
        int popupHeight = popupSize.height;
        int popupWidth = popupSize.width;
        int thisWidth = getWidth();
        int popupX = popupWidth > thisWidth ? thisX + thisWidth - popupWidth : thisX;
        int popupY = thisY - popupHeight;
        return new Point(popupX, popupY);
    }
    private final MouseListener toggleDetailsPanelDisplayListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                toggleDisplayDetails();
            }
        }
    };

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents
        GridBagConstraints gridBagConstraints;

        separator = new JSeparator();
        labelUsedMemory = new JLabel();
        buttonShowDetails = new JButton();

        setName("Form"); // NOI18N
        setLayout(new GridBagLayout());

        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setName("separator"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(separator, gridBagConstraints);

        labelUsedMemory.setToolTipText(NbBundle.getMessage(MemoryUsageStatusLinePanel.class, "MemoryUsageStatusLinePanel.labelUsedMemory.toolTipText")); // NOI18N
        labelUsedMemory.setName("labelUsedMemory"); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 3, 0, 0);
        add(labelUsedMemory, gridBagConstraints);

        buttonShowDetails.setIcon(ICON_UPARROW);
        buttonShowDetails.setToolTipText(NbBundle.getMessage(MemoryUsageStatusLinePanel.class, "MemoryUsageStatusLinePanel.buttonShowDetails.toolTipText")); // NOI18N
        buttonShowDetails.setBorder(null);
        buttonShowDetails.setContentAreaFilled(false);
        buttonShowDetails.setName("buttonShowDetails"); // NOI18N
        buttonShowDetails.setPreferredSize(new Dimension(16, 16));
        buttonShowDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonShowDetailsActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 3, 0, 3);
        add(buttonShowDetails, gridBagConstraints);
    }//GEN-END:initComponents

    private void buttonShowDetailsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_buttonShowDetailsActionPerformed
        toggleDisplayDetails();
    }//GEN-LAST:event_buttonShowDetailsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonShowDetails;
    private JLabel labelUsedMemory;
    private JSeparator separator;
    // End of variables declaration//GEN-END:variables
}
