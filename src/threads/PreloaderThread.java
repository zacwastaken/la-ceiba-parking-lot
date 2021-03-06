package threads;

import javafx.application.Platform;
import model.PreloaderBar;
import ui.PreloaderGUI;

/**
 * The thread in charge of the preloader bar. <br>
 */
public class PreloaderThread extends Thread {
    PreloaderBar progressBar;
    PreloaderGUI preloader;

    /**
     * The main constructor of the class. <br>
     */
    public PreloaderThread(PreloaderBar bar, PreloaderGUI controller) {
        progressBar = bar;
        preloader = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        int LOADING_TIME_INTERVAL = 6;
        while (progressBar.isActive()) {
            if (!currentThread().isInterrupted()) {
                progressBar.doProgress();
                Platform.runLater(new Thread(() -> {
                    try {
                        preloader.loadBar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
                wait(LOADING_TIME_INTERVAL);
            }

        }
        Platform.runLater(new Thread(() -> preloader.postLoaded()));
    }

    /**
     * Sleeps the thread for a specified amount of milliseconds. <br>
     *
     * @param millis The amount of milliseconds. @NotNeg. <br>
     */
    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
