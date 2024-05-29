package com.cadastroPessoas.erp.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class EnderecoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String estado;
	
	private String cidade;
	
	private String logradouro;
	
	private String numero_casa;
	
	private String cep;
	
	//Aqui colocarei o atributo para a Foreign key
}
