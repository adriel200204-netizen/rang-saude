package br.com.rang.saude;

import br.com.rang.saude.dao.UnidadeSaudeDAO;
import br.com.rang.saude.model.UnidadeSaude;

public class TesteBanco {

    public static void main(String[] args) {

        UnidadeSaudeDAO dao = new UnidadeSaudeDAO();

        Integer cep = 1234567;

        UnidadeSaude unidade = dao.buscarPorCep(cep);

        if (unidade != null) {

            System.out.println("=================================");
            System.out.println("CEP: " + cep);
            System.out.println("UNIDADE ENCONTRADA:");
            System.out.println(unidade.getNomeEstabelecimento());
            System.out.println("CNES: " + unidade.getCnes());
            System.out.println("=================================");

        } else {

            System.out.println("=================================");
            System.out.println("NENHUMA UNIDADE ENCONTRADA");
            System.out.println("=================================");
        }
    }
}