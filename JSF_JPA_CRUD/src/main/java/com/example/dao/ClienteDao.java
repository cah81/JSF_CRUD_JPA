package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.model.Cliente;
import com.example.model.JPAUtil;

public class ClienteDao {
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager(); //permite crear los crud para bd
	
	//guardar cliente
	//persiste un objeto cliente con la base de datos usando hibernate
	public void guardar(Cliente cliente) {
		entity.getTransaction().begin();//vamos a empezar una transaccion
		entity.persist(cliente);//persistimos el objeto por debajo hibernate mapea nuestros atributos del objeto en la bd
		entity.getTransaction().commit(); //se cierrra transaccion y se guarda en bd
		// se quito el metodo de close en vista de que se cierra la transaccion prematuramente
		
	}
	//metodo para editar un cliente
	public void editar(Cliente cliente) {
		entity.getTransaction().begin();
		entity.merge(cliente); //hibernate busca en la bd que coincida con el id de este objeto cliente y lo actualiza con
		//las propiedades que se le envian por el navegador
		entity.getTransaction().commit();
	
		
	}
	
	//permite obtener un cliente de la bd 
	public Cliente buscar(Long id) {
		Cliente cliente = new Cliente();
		cliente = entity.find(Cliente.class, id);//pasamos el id del registro que quiero obtener con find busca dentro de la bd
	
		return cliente;
		
	}
	
	//obtener todos los clientes de la bd
	public List<Cliente> obtenerClientes(){
		List<Cliente> listaClientes= new ArrayList<>();
		//esta es usada por hibernate
		Query q = entity.createQuery("SELECT c FROM Cliente c");//en jpql no toma el *
		listaClientes = q.getResultList();// se obtiene todos los registros de la tabla	
		
		return listaClientes;
	}
	//para eliminar un registro
	public void eliminar(Long id) {
		Cliente c = new Cliente();
		c = entity.find(Cliente.class, id);
		entity.getTransaction().begin();
		entity.remove(c);
		entity.getTransaction().commit();
		
	}
	
	
	

}
