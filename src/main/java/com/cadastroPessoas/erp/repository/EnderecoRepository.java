package com.cadastroPessoas.erp.repository;

import java.io.Serializable;
import java.util.List;

import com.cadastroPessoas.erp.model.EnderecoModel;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class EnderecoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em; // em = entity manager

	public EnderecoRepository() {

	}

	public EnderecoRepository(EntityManager em) {
		this.em = em;

	}
	//procura um endereco pelo seu Id e retorna o objeto
	public EnderecoModel findById(Long id) {
		return em.find(EnderecoModel.class, id);
	}
	//pesquisa os enderecos de determinado estado
	@SuppressWarnings("unchecked")
	public List<EnderecoModel> searchByEstado(String estadoIn){ //utilizei enderecoIn aqui para não entrar em conflito com a query SQL
		Query query = em.createQuery("from EnderecoModel where estado like :estado");
		query.setParameter("estado", estadoIn + "%"); //Pesquisa pelo nome do estado completo ou parcialmente escrito
		
		return query.getResultList();
	}
	
	public EnderecoModel saveEndereco(EnderecoModel endereco) {
		return em.merge(endereco);
	}
	
	public void removeEndereco(EnderecoModel endereco) {
		endereco = findById(endereco.getId());
		em.remove(endereco);
	}
}
