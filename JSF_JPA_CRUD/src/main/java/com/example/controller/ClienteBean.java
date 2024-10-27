package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.example.dao.ClienteDao;
import com.example.model.Cliente;

@ManagedBean(name="clienteBean")
@RequestScoped //mientras se haga la peticion se mantiene este bean
public class ClienteBean {
	
	public String nuevo() {
		Cliente c = new Cliente();
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", c);
		return "/faces/nuevo.xhtml";
		
	}
	
	public String guardar(Cliente cliente) {
		Date fechaActual = new Date();
		cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.guardar(cliente);
		return "/faces/index.xhtml";
		
		
	}
	
	public List<Cliente> obtenerClientes(){
		ClienteDao clienteDao = new ClienteDao();
		return clienteDao.obtenerClientes();
	}
	
	public String editar(Long id) {
		ClienteDao clienteDao = new ClienteDao();
		Cliente c = new Cliente();
		c = clienteDao.buscar(id);
		System.out.println("***************");
		System.out.println(c);
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", c);
		return "/faces/editar.xhtml";
	}
	
	public String actualizar(Cliente cliente) {
		Date fechaActual = new Date();
		cliente.setFactualizar(new java.sql.Date(fechaActual.getTime()));
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.editar(cliente);
		return "/faces/index.xhtml";
	}
	
	public String eliminar (Long id) {
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.eliminar(id);
		System.out.println("Cliente eliminado..");
		return "faces/index.xhtml";
		
	}

	
}
