package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

// Classe de fábrica para criar instâncias de PedidosDAO
public class PedidosDAOFactory {

    // Enum que define os tipos de implementação de PedidosDAO
    public enum PedidosDAOType{
        DATABASE; // Tipo de implementação no banco de dados
    }

    // Atributo que define qual tipo de implementação será criada pela fábrica
    private PedidosDAOType type;

    // Construtor da fábrica
    public PedidosDAOFactory() {
        // Inicializa o tipo com DATABASE, indicando que a implementação será do tipo 'DatabasePedidosDAO'
        type = PedidosDAOType.DATABASE;
    }

    // Método responsável por criar a instância de PedidosDAO com base no tipo definido
    public PedidosDAO factory() {
        // Verifica se o tipo é DATABASE
        if(type == PedidosDAOType.DATABASE) {
            // Retorna uma nova instância de DatabasePedidosDAO
            return new DatabasePedidosDAO();
        } else {
            // Caso o tipo não seja reconhecido, lança uma exceção
            throw new IllegalArgumentException("Tipo de dado desconhecido: " + type);
        }
    }

}
