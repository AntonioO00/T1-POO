import java.util.ArrayList;


public class Clientela {

    private ArrayList<Cliente> clientela;

    public Clientela() {
        clientela = new ArrayList<Cliente>();
    }

    public boolean cadastraCliente(Cliente cliente) {
        String email = cliente.getEmail();
        if (pesquisaCliente(email) == null) {
            return clientela.add(cliente);
        }
        return false ;
}

    public Cliente pesquisaCliente(String email) {
        for (Cliente c : clientela) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
            return null;
    }


    public ArrayList<Cliente> devolveClientes(){
        return clientela;

    }


}
