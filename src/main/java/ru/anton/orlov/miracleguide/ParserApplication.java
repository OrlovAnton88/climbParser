package ru.anton.orlov.miracleguide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 19:06 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */

@Configuration
@EnableAutoConfiguration(exclude = MustacheAutoConfiguration.class)
@EnableTransactionManagement
@ComponentScan
public class ParserApplication {

    private static final Logger logger = LoggerFactory.getLogger(ParserApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);


        logger.error("Message logged at ERROR level");
        logger.warn("Message logged at WARN level");
        logger.info("Message logged at INFO level");
        logger.debug("Message logged at DEBUG level");

    }


}
