import java.util.LinkedList;

public class Servidor {
    private boolean tipoTabela;

    private Object tabelaVeiculos;

    public Servidor(boolean tipoTabelas) {
        this.tipoTabela = tipoTabelas;
        if (tipoTabela) {
            tabelaVeiculos = new TabelaDispersaoComEnderecamentoAberto(100);
        } else {
            tabelaVeiculos = new TabelaDispersao(100);
        }
        inicializarVeiculos();
    }

    private void inicializarVeiculos() {
        // Adicionar 50 veículos à tabela de dispersão
        for (int i = 10; i < 60; i++) {
            Veiculo veiculo = new Veiculo("ABC" + i, "0042" + i, "Condutor" + i, "017" + i, "2023", 2023);
            if (tipoTabela) {
                ((TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos).inserir(veiculo);
            } else {
                ((TabelaDispersao) tabelaVeiculos).inserir(veiculo);
            }
        }
        System.out.println("Veículos inicializados.");
        System.out.println(listarVeiculos());
    }

    public String cadastrarVeiculo(Veiculo veiculo) {
        if (tipoTabela) {
            ((TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos).inserir(veiculo);
        } else {
            ((TabelaDispersao) tabelaVeiculos).inserir(veiculo);
        }
        return "Veículo cadastrado com sucesso.";
    }

    public String listarVeiculos() {
        StringBuilder result = new StringBuilder();

        if (tipoTabela) {
            TabelaDispersaoComEnderecamentoAberto tabelaAberta = (TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos;
            for (Veiculo compartimento : tabelaAberta.listarTodos()) {
                result.append(compartimento.toString());
                result.append("\n");
            }
        } else {
            TabelaDispersao tabelaNormal = (TabelaDispersao) tabelaVeiculos;
            for (Veiculo compartimento : tabelaNormal.listarTodos()) {
                result.append(compartimento.toString());
                result.append("\n");
            }
        }

        if (result.length() == 0) {
            return "Nenhum veículo cadastrado.";
        }

        return result.toString();
    }

    public String alterarVeiculo(String placa, Veiculo novoVeiculo) {
        if (tipoTabela) {
            TabelaDispersaoComEnderecamentoAberto tabelaAberta = (TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos;
            tabelaAberta.removerPorPlaca(placa);
            tabelaAberta.inserir(novoVeiculo);
        } else {
            TabelaDispersao tabelaNormal = (TabelaDispersao) tabelaVeiculos;
            tabelaNormal.removerPorPlaca(placa);
            tabelaNormal.inserir(novoVeiculo);
        }
        return "Veículo alterado com sucesso.";
    }

    public String removerVeiculo(String placa) {
        if (tipoTabela) {
            TabelaDispersaoComEnderecamentoAberto tabelaAberta = (TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos;
            if (tabelaAberta.removerPorPlaca(placa)) {
                return "Veículo removido com sucesso.";
            } else {
                return "Veículo não encontrado.";
            }
        } else {
            TabelaDispersao tabelaNormal = (TabelaDispersao) tabelaVeiculos;
            if (tabelaNormal.removerPorPlaca(placa)) {
                return "Veículo removido com sucesso.";
            } else {
                return "Veículo não encontrado.";
            }
        }
    }

    public int quantidadeVeiculos() {
        int totalVeiculos = 0;

        if (tipoTabela) {
            TabelaDispersaoComEnderecamentoAberto tabelaAberta = (TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos;
            for (LinkedList<Veiculo> compartimento : tabelaAberta.getTabela()) {
                totalVeiculos += compartimento.size();
            }
        } else {
            TabelaDispersao tabelaNormal = (TabelaDispersao) tabelaVeiculos;
            for (LinkedList<Veiculo> compartimento : tabelaNormal.getTabela()) {
                totalVeiculos += compartimento.size();
            }
        }

        return totalVeiculos;
    }

    public Veiculo BuscarVeiculoPorPlaca (String placa) {
        if (tipoTabela) {
            TabelaDispersaoComEnderecamentoAberto tabelaAberta = (TabelaDispersaoComEnderecamentoAberto) tabelaVeiculos;
            return tabelaAberta.buscarPorPlaca(placa);
        } else {
            TabelaDispersao tabelaNormal = (TabelaDispersao) tabelaVeiculos;
            return tabelaNormal.buscarPorPlaca(placa);
        }
    }

    public static void main(String[] args) {
        boolean tipoTabela = true;

        Servidor servidor = new Servidor(tipoTabela);
    }
}
