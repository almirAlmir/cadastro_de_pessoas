package com.cadastroPessoas.erp.model;

public enum SexoPessoa {

	H("índividuo do sexo masculino"),
	M("índividuo do sexo feminino"),
	PNI("prefiro não indicar");
	
	private String descricaoSexo;
	
	SexoPessoa(String descricaoSexo){
		this.descricaoSexo = descricaoSexo;
	}
	
	public String getDescricaoSexo() {
		
		return descricaoSexo;
	}
	
}
