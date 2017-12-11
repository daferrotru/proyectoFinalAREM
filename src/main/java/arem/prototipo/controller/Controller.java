/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.controller;

import arem.prototipo.services.PrototipoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rami
 */
@RestController
public class Controller {

    @Autowired
    PrototipoServices pros = null;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getMessage() {
        if (pros != null) {
            return new ResponseEntity<>(pros.getMessages(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<?> getItems() {
        if (pros != null) {
            return new ResponseEntity<>(pros.getItems(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
