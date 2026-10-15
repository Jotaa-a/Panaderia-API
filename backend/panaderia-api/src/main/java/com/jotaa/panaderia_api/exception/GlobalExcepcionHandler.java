package com.jotaa.panaderia_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalExcepcionHandler {
    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ProblemDetail noEncontrado(RecursoNoEncontradoExcepcion ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ReglaNegocioExcepcion.class)
    public ProblemDetail reglaNegocio(ReglaNegocioExcepcion ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validacion(MethodArgumentNotValidException ex){
        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Datos inválidos");
                pd.setProperty("errores", errores);

        return pd;
    }
}
