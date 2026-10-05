package br.com.rang.saude.bean;

import br.com.rang.saude.dao.UnidadeSaudeDAO;
import br.com.rang.saude.model.UnidadeSaude;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ConsultaBean {

    private Integer cep;
    private UnidadeSaude unidade;

    public void consultar() {

        UnidadeSaudeDAO dao = new UnidadeSaudeDAO();

        unidade = dao.buscarPorCep(cep);
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public UnidadeSaude getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeSaude unidade) {
        this.unidade = unidade;
    }
}