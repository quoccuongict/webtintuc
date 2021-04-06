package com.javaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.NewModel;
import com.javaweb.paging.PageRequest;
import com.javaweb.paging.Pageble;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;
import com.javaweb.sort.Sorter;
import com.javaweb.utils.FormUtil;
import com.javaweb.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

	private static final long serialVersionUID = -2662059961810446402L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstant.LIST)){
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "views/admin/new/list.jsp";
		}else if(model.getType().equals(SystemConstant.EDIT)){
			if(model.getId() != null){
				model = newService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
