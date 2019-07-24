package cn.com.git.cbs.batch.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

public class BatchLog extends ExtendedLoggerWrapper {

	private static final long serialVersionUID = 93950563451682414L;
	private final ExtendedLoggerWrapper logger;

	private BatchLog(final Logger logger) {
		super((AbstractLogger) logger, logger.getName(), new FormattedMessageFactory());
		this.logger = this;
	}

	public static BatchLog create() {
		final Logger wrapped = LogManager.getLogger("batch");
		return new BatchLog(wrapped);
	}
}
