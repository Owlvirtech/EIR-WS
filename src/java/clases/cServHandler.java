/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alumno SS
 */
public class cServHandler {
    String clientURL = "http://localhost:8084/EIR";
    HttpServletRequest request ;
    HttpServletResponse response;
    
    public cServHandler(){
        
    }

    public cServHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public cServHandler(String clientURL) {
        this.clientURL = clientURL;
    }

    public String getClientURL() {
        return clientURL;
    }

    public void setClientURL(String clientURL) {
        this.clientURL = clientURL;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    
    
}
