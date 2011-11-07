package de.elmar_baumann.nbmemusage;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

/**
 * @author Elmar Baumann
 */
public final class GarbageCollectAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private static RequestProcessor RP;

    public GarbageCollectAction() {
        super(NbBundle.getMessage(GarbageCollectAction.class, "GarbageCollectAction.Name"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (RP == null) {
            RP = new RequestProcessor("GarbageCollectAction");
        }
        RP.post(
                new Runnable() {

                    @Override
                    public void run() {
                        System.gc();
                        System.runFinalization();
                        System.gc();
                    }
                });
    }
}
