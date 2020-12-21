package com.model2.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	public String getResultView();
	public boolean isForward();

}
