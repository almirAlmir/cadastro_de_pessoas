package com.cadastroPessoas.erp.service;

import java.io.Serializable;

import com.cadastroPessoas.erp.model.PessoaModel;
import com.cadastroPessoas.erp.repository.PessoaRepository;
import com.cadastroPessoas.erp.util.Transacional;

import jakarta.inject.Inject;

public class CadastroPessoaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaRepository pr;
	
	@Transacional
	public void salvar(PessoaModel pessoa) {
		pr.savePessoa(pessoa);
	}
	
	@Transacional
	public void excluir(PessoaModel pessoa) {
		pr.removePessoa(pessoa);
	}

}
