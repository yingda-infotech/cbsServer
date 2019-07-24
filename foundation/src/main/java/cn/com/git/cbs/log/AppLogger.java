/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.log;

import java.io.Serializable;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.Message;
//import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;
//import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

/**
 * Custom Logger interface with convenience methods for
 * the LOG custom log level.
 */
public final class AppLogger implements Serializable {
    private static final long serialVersionUID = 1265309283493501L;
    private final ExtendedLoggerWrapper logger;

    private static final String FQCN = AppLogger.class.getName();
    private static final Level LOG = Level.INFO;

    private AppLogger(final Logger logger) {
        this.logger = new ExtendedLoggerWrapper((AbstractLogger) logger, logger.getName(), new FormattedMessageFactory());
    }

    /**
     * Returns a custom Logger with the name of the calling class.
     * 
     * @return The custom Logger for the calling class.
     */
    public static AppLogger create() {
        final Logger wrapped = LogManager.getLogger("application");
        return new AppLogger(wrapped);
    }

    /**
     * Logs a message with the specific Marker at the {@code LOG} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     */
    public void log(final Marker marker, final Message msg) {
        logger.logIfEnabled(FQCN, LOG, marker, msg, (Throwable) null);
    }

    /**
     * Logs a message with the specific Marker at the {@code LOG} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void log(final Marker marker, final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, marker, msg, t);
    }

    /**
     * Logs a message object with the {@code LOG} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void log(final Marker marker, final Object message) {
        logger.logIfEnabled(FQCN, LOG, marker, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code LOG} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void log(final Marker marker, final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, marker, message, t);
    }

    /**
     * Logs a message object with the {@code LOG} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void log(final Marker marker, final String message) {
        logger.logIfEnabled(FQCN, LOG, marker, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code LOG} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     */
    public void log(final Marker marker, final String message, final Object... params) {
        logger.logIfEnabled(FQCN, LOG, marker, message, params);
    }

    /**
     * Logs a message at the {@code LOG} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void log(final Marker marker, final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, marker, message, t);
    }

    /**
     * Logs the specified Message at the {@code LOG} level.
     * 
     * @param msg the message string to be logged
     */
    public void log(final Message msg) {
        logger.logIfEnabled(FQCN, LOG, null, msg, (Throwable) null);
    }

    /**
     * Logs the specified Message at the {@code LOG} level.
     * 
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void log(final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, null, msg, t);
    }

    /**
     * Logs a message object with the {@code LOG} level.
     * 
     * @param message the message object to log.
     */
    public void log(final Object message) {
        logger.logIfEnabled(FQCN, LOG, null, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code LOG} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void log(final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, null, message, t);
    }

    /**
     * Logs a message object with the {@code LOG} level.
     * 
     * @param message the message object to log.
     */
    public void log(final String message) {
        logger.logIfEnabled(FQCN, LOG, null, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code LOG} level.
     * 
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     */
    public void log(final String message, final Object... params) {
        logger.logIfEnabled(FQCN, LOG, null, message, params);
    }

    /**
     * Logs a message at the {@code LOG} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void log(final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, null, message, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the {@code LOG}level.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void log(final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, LOG, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code LOG}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void log(final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, null, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code LOG} level with the specified Marker.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void log(final Marker marker, final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, LOG, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is the
     * {@code LOG} level.
     *
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void log(final Marker marker, final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, LOG, marker, message, paramSuppliers);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code LOG}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void log(final Marker marker, final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, LOG, marker, msgSupplier, t);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is
     * the {@code LOG} level.
     *
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void log(final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, LOG, null, message, paramSuppliers);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code LOG} level with the specified Marker. The {@code MessageSupplier} may or may
     * not use the {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
//    public void log(final Marker marker, final MessageSupplier msgSupplier) {
//        logger.logIfEnabled(FQCN, LOG, marker, msgSupplier, (Throwable) null);
//    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code LOG}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t A Throwable or null.
     * @since 2.4
     */
//    public void log(final Marker marker, final MessageSupplier msgSupplier, final Throwable t) {
//        logger.logIfEnabled(FQCN, LOG, marker, msgSupplier, t);
//    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code LOG} level. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
//    public void log(final MessageSupplier msgSupplier) {
//        logger.logIfEnabled(FQCN, LOG, null, msgSupplier, (Throwable) null);
//    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code LOG}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     * The {@code MessageSupplier} may or may not use the {@link MessageFactory} to construct the
     * {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
//    public void log(final MessageSupplier msgSupplier, final Throwable t) {
//        logger.logIfEnabled(FQCN, LOG, null, msgSupplier, t);
}

