package org.graalphp.util;

/**
 * @author abertschi
 * <p>
 * Simple logging interface to use in graalphp.
 * As for now, we dont use java.logging classes to avoid issues with graal native.
 */
public class PhpLogger implements Logger {

    private final String name;

    public static boolean DISABLE = false;
    public static boolean DISABLE_FINEST = true;
    public static boolean DISABLE_FINE = true;

    public static synchronized void disable() {
        DISABLE = false;
    }

    public static synchronized void enable() {
        DISABLE = true;
    }

    private PhpLogger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        // XXX: to reduce object creation make logger static
        // fine for now, Logger is debug utility
        return new PhpLogger(name);
    }

    @Override
    public void fine(String msg) {
        if (DISABLE || DISABLE_FINE) return;
        System.err.println(format(msg, "fine"));
    }

    @Override
    public void info(String msg) {
        if (DISABLE) return;
        System.err.println(format(msg, "info"));
    }

    @Override
    public void finest(String msg) {
        if (DISABLE || DISABLE_FINEST) return;
        System.err.println(format(msg, "finest"));
    }

    @Override
    public void parserEnumerationError(String msg) {
        System.err.println(format(msg, "ERROR"));
    }

    private String format(String msg, String lvl) {
        // [%1$tF %1$tT] [%2$-7s] %3$s",
        return String.format("[%1$-15s] [%2$-7s] %3$s",
                name,
                lvl,
                msg
        );
    }
}
