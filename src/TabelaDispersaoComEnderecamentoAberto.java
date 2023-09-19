import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaDispersaoComEnderecamentoAberto {
    private Veiculo[] tabela;
    private int tamanho;

    public TabelaDispersaoComEnderecamentoAberto(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Veiculo[tamanho];
    }

    public void inserir(Veiculo veiculo) {
        int indice = calcularIndice(veiculo.placa);

        while (tabela[indice] != null) {
            indice = (indice + 1) % tamanho;
        }

        tabela[indice] = veiculo;
    }

    public Veiculo buscarPorPlaca(String placa) {
        int indice = calcularIndice(placa);

        while (tabela[indice] != null) {
            if (tabela[indice].placa.equals(placa)) {
                return tabela[indice];
            }
            indice = (indice + 1) % tamanho;
        }
        System.out.println("Veículo não encontrado.");
        return null;
    }

    public boolean removerPorPlaca(String placa) {
        int indice = calcularIndice(placa);

        while (tabela[indice] != null) {
            if (tabela[indice].placa.equals(placa)) {
                tabela[indice] = null; // Remove o elemento
                reorganizarTabela();
                return true;
            }
            indice = (indice + 1) % tamanho;
        }

        return false;
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> todosVeiculos = new ArrayList<>();
        for (Veiculo veiculo : tabela) {
            if (veiculo != null) {
                todosVeiculos.add(veiculo);
            }
        }
        return todosVeiculos;
    }

    public int tamanho() {
        int tamanhoTotal = 0;
        for (Veiculo veiculo : tabela) {
            if (veiculo != null) {
                tamanhoTotal++;
            }
        }
        return tamanhoTotal;
    }

    private int calcularIndice(String placa) {
        int hashCode = placa.hashCode();
        int indice = hashCode % tamanho;

        if (indice < 0) {
            indice += tamanho;
        }

        return indice;
    }

    private void reorganizarTabela() {
        Veiculo[] novaTabela = new Veiculo[tamanho];
    
    for (int i = 0; i < tamanho; i++) {
        if (tabela[i] != null) {
            int indice = calcularIndice(tabela[i].placa);
            
            while (novaTabela[indice] != null) {
                indice = (indice + 1) % tamanho;
            }
            
            novaTabela[indice] = tabela[i];
        }
    }
    
    tabela = novaTabela;

    }

    public LinkedList<Veiculo>[] getTabela() {
        LinkedList<Veiculo>[] tabelaListas = new LinkedList[tamanho];

         for (int i = 0; i < tamanho; i++) {
            tabelaListas[i] = new LinkedList<>();
        }

        // Adiciona os elementos da tabela atual nas listas apropriadas na matriz
        for (Veiculo veiculo : tabela) {
            if (veiculo != null) {
                int indice = calcularIndice(veiculo.placa);
                tabelaListas[indice].add(veiculo);
            }
        }

        return tabelaListas;
    }
}
