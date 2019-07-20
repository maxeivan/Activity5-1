/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcgill.cccs425.activity5_1;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;

/**
 *
 * @author Maxim
 */
@WebService(serviceName = "HelloWorld")
public class HelloWorld {
    @Resource
    WebServiceContext context;
    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        if(isAuthenticated()){
        return "Hello " + txt + " !";
        }else {
            throw new HTTPException(401);
        }
    }
    private boolean isAuthenticated(){
        MessageContext messageContext = context.getMessageContext();
        Map httpHeaders = (Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) httpHeaders.get("Authorization");
        if (userList.contains("bWFrc3ltOjEyMw==")){
            return true;
        }else{
            return false;
        }
    }
}
