package be.ucll.da.dentravak.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Throw a 404 when a sandwich was not found with a specific message
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No sandwich found")
public class SandwichNotFoundException extends RuntimeException {
}