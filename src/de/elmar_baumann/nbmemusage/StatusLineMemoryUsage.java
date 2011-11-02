package de.elmar_baumann.nbmemusage;

import java.awt.Component;

import org.openide.awt.StatusLineElementProvider;
import org.openide.util.lookup.ServiceProvider;

/**
 * Displays the memory usage in the status line.
 * @author Elmar Baumann
 */
@ServiceProvider(service = StatusLineElementProvider.class)
public final class StatusLineMemoryUsage implements StatusLineElementProvider {

    private final MemoryUsageStatusLinePanel memoryUsageStatusLinePanel = new MemoryUsageStatusLinePanel();

    @Override
    public Component getStatusLineElement() {
        return memoryUsageStatusLinePanel;
    }
}
