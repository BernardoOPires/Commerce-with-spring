package com.devsuperior.DsCommerce.services.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String msg){
         super(msg);
    }
}
