/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.api;

import javax.jms.JMSException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author rami
 */
@SpringBootApplication
@ComponentScan(basePackages = {"arem.prototipo"})
public class PrototipoApi {

    public static void main(String[] args) throws JMSException {
        SpringApplication.run(PrototipoApi.class, args);
    }
}
