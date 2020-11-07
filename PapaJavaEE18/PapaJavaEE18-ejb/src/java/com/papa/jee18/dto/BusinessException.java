/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto;


public class BusinessException extends UnsupportedOperationException {
    
    public BusinessException(){}
    
    public BusinessException(String errorMessage){
        setErrorMessage(errorMessage);
    }
    
    private String ErrorMessage;

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }
    
    
    
}
