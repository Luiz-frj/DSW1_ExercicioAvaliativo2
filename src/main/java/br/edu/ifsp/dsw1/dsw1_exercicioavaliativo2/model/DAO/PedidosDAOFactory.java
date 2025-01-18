package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

public class PedidosDAOFactory {

    public enum PedidosDAOType{
        DATABASE;
    }

    private PedidosDAOType type;

    public PedidosDAOFactory() {
        type = PedidosDAOType.DATABASE;
    }

    public PedidosDAO factory() {
        if(type == PedidosDAOType.DATABASE) {
            return new DatabasePedidosDAO();
        } else {
            throw new IllegalArgumentException("Tipo de dado desconhecido: " + type);
        }
    }

}

