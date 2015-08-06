package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    /** Main method */
    public static void main(final String[] args) {
        logger.info("Hello World!");
    }
}
