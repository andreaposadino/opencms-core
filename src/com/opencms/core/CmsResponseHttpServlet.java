package com.opencms.core;

import java.io.*;
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Implementation of the CmsResponse interface.
 * 
 * This implementation uses a HttpServletResponse as original response to create a
 * CmsResponseHttpServlet.
 * 
 * @author Michael Emmerich
 * @version $Revision: 1.3 $ $Date: 2000/01/24 12:01:46 $  
 */
public class CmsResponseHttpServlet implements I_CmsConstants,  
                                               I_CmsResponse{ 
    
     /**
     * The original response.
     */
    private HttpServletResponse m_res;
    
     /**
     * The original request.
     */
    private HttpServletRequest m_req;
    
    /**
     * The type of this CmsResponset.
     */
    private int m_type=C_RESPONSE_HTTP;
    
    
     /** 
     * Constructor, creates a new CmsResponseHttpServlet object.
     * It is nescessary to give the HttpServletRequest as well, because it is needed
     * to transform the CmsRedirect to a real Http redirect.
     * 
     * @param req The original HttpServletRequest used to create this CmsRequest.
     * @param res The original HttpServletResponse used to create this CmsResponse.
     */
     CmsResponseHttpServlet (HttpServletRequest req, HttpServletResponse res) {
        m_res=res;
        m_req=req;
    }

    /**
     * Returns an OutputStream for writing the response data. 
     * 
     * @return OutputStream for writing data.
     * @exception Throws IOException if an error occurs.
     */
    public OutputStream getOutputStream()
        throws IOException {
        return m_res.getOutputStream();
    }
    
    /**
     * Sets the length of the content being returned by the server.
     * 
     * @param len Number of bytes to be returned by the response.
     */
    public void setContentLength(int len) {
        m_res.setContentLength(len);        
    }
    
    /**
     * Sets the content type of the response to the specified type.
     * 
     * @param type The contnent type of the response.
     */
    public void setContentType(String type) {
        m_res.setContentType(type);
    }
    
    /**
     * Sets the error code that is returnd by the response. The error code is specified
     * by a numeric value.
     * 
     * @param code The error code to be set.
     * @exception Throws IOException if an error occurs.
     */
    public void sendError(int code) 
        throws IOException {
        m_res.sendError(code);
    }
    
    /**
     * Sets the error code and a additional message that is returnd by the response. 
     * The error code is specified by a numeric value.
     * 
     * @param code The error code to be set.
     * @param msg Additional error message.
     * @exception Throws IOException if an error occurs.
     */
    public void sendError(int code, String msg)
        throws IOException{
        m_res.sendError(code,msg);
    }
    
     /**
     * Sets a redirect to send the responst to. 
     * The original HttpServletResponse redirect is used here. Additional information
     * about the servlets location is taken from the original HttpServletRequest.
     * 
     * @param location The location the response is send to.
     * @param msg Additional error message.
     * @exception Throws IOException if an error occurs.
     */
    public void sendCmsRedirect(String location)
        throws IOException {
        String servlet = m_req.getServletPath();
        m_res.sendRedirect(servlet+location);
    }
    
    /**
     * Returns the type of the response that was used to create the CmsResponse.
     * The returned int must be one of the constants defined above in this interface.
     * 
     * @return The type of the CmsResponse.
     */
    public int getOriginalResponseType() {
        return m_type;
    }

    /**
     * Returns the original response that was used to create the CmsResponse.
     * 
     * @return The original response of the CmsResponse.
     */
    public Object getOriginalResponse() {
        return m_res;
    }
    
}
