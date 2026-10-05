package br.com.rang.saude.bean;

import br.com.rang.saude.dao.UnidadeSaudeDAO;
import br.com.rang.saude.model.UnidadeSaude;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.sql.SQLIntegrityConstraintViolationException;

@Named
@RequestScoped
public class CadastroBean {

    private String cnes;
    private String nomeEstabelecimento;
    private Integer cepInicio;
    private Integer cepFim;

    private String mensagem;

    public void cadastrar() {

        mensagem = null;

        if (cepInicio == null || cepFim == null) {
            mensagem = "Informe o CEP inicial e o CEP final.";
            return;
        }

        if (cepInicio > cepFim) {
            mensagem = "O CEP inicial não pode ser maior que o CEP final.";
            return;
        }

        UnidadeSaude unidade = new UnidadeSaude();

        unidade.setCnes(cnes);
        unidade.setNomeEstabelecimento(nomeEstabelecimento);
        unidade.setCepInicio(cepInicio);
        unidade.setCepFim(cepFim);

        UnidadeSaudeDAO dao = new UnidadeSaudeDAO();

        try {

            dao.salvar(unidade);

            mensagem = "Unidade cadastrada com sucesso.";

        } catch (Exception e) {

            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                mensagem = "CNES já cadastrado. Informe outro CNES.";
            } else {
                mensagem = "Não foi possível cadastrar a unidade.";
            }
        }
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public Integer getCepInicio() {
        return cepInicio;
    }

    public void setCepInicio(Integer cepInicio) {
        this.cepInicio = cepInicio;
    }

    public Integer getCepFim() {
        return cepFim;
    }

    public void setCepFim(Integer cepFim) {
        this.cepFim = cepFim;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}