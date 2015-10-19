package ru.anton.orlov.miracleguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 19:06 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }



}
