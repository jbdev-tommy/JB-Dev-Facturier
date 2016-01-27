package fr.jbdev.facturier.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tommy Servlet d'affichage PDF
 */
@WebServlet("/report.pdf")
public class PdfReportServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request,
	    final HttpServletResponse response) throws ServletException,
	    IOException {
	final byte[] content = (byte[]) request.getSession().getAttribute(
		"reportBytes"); //$NON-NLS-1$

	if (content != null) {
	    response.setContentType("application/pdf"); //$NON-NLS-1$
	    response.setContentLength(content.length);
	    response.getOutputStream().write(content);
	}

    }

}
