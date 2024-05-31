package com.cadastroPessoas.erp.repository;

import java.io.Serializable;
import java.util.List;

import com.cadastroPessoas.erp.model.PessoaModel;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PessoaRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	public PessoaRepository() {

	}

	public PessoaRepository(EntityManager em) {
		this.em = em;
	}

	// procura uma pessoa pelo seu Id e retorna a pessoa
	public PessoaModel findById(Long id) {
		return em.find(PessoaModel.class, id);
	}

	// pesquisa as pessoas pelo nome // Uma pesquisa por nome incompleto tambem retorna as pessoas com o nome que de match
	@SuppressWarnings("unchecked")
	public List<PessoaModel> searchByName(String nameIn) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<PessoaModel> cq = cb.createQuery(PessoaModel.class);
		
		Root<PessoaModel> root = cq.from(PessoaModel.class); //query from Pessoa where nome like :nome (forma alternativa de fazer a query usando CRITERIA)
		cq.select(root);
		cq.where(cb.like(root.get("name"), nameIn + "%"));
		
		Query query = em.createQuery(cq);
		return query.getResultList();
	}
	
	public PessoaModel savePessoa(PessoaModel pessoa) {
		return em.merge(pessoa);
	}
	
	public void removePessoa(PessoaModel pessoa) {
		pessoa = findById(pessoa.getId());
		em.remove(pessoa);
	}

}
