package be.ucll.da.dentravak.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No sandwich found")
public class SandwichNotFoundException extends RuntimeException {

}
