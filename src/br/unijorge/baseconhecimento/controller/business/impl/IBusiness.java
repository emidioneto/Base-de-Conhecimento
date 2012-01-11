package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;

public interface IBusiness {
	public void save(Object objeto) throws BusinessExceptions;
    public void excluir(Object objeto) throws BusinessExceptions;
    public Object obter(Integer id) throws BusinessExceptions;
    public ArrayList<Object> listar() throws BusinessExceptions;
}
