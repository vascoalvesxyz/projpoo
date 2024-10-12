public class Exercicio1 {
    /**
     * Obtém o salário base e o custo da hora extra para uma dada especialidade.
     * @param especialidades Array de especialidades com os respetivos salários e custos de horas extra.
     * @param especialidade A especialidade a procurar.
     * @return Um array onde o índice 0 é o salário base e o índice 1 é o custo da hora extra.
     */
    private static int[] obterRendimentoEspecialidade(String[] especialidades, String especialidade ) {
        int[] resultado = new int[2];

        // Itera sobre todas as especialidades para encontrar a correspondência
        for (String s : especialidades) {
            String[] linha = s.split("/");
            String nome = linha[0];
            int salarioBase = Integer.parseInt(linha[1]);
            int custoHoraExtra = Integer.parseInt(linha[2]);

            // Verifica se a especialidade atual corresponde à procurada (ignorando maiúsculas/minúsculas)
            if (nome.equalsIgnoreCase(especialidade)) {
                resultado[0] = salarioBase;
                resultado[1] = custoHoraExtra;
                break; // Sai do loop assim que encontra a especialidade
            }
        }

        return resultado; // Retorna [0, 0] se a especialidade não for encontrada
    }

    /**
     * Calcula o ordenado total com base no salário base, anos de serviço e horas extra.
     * @param rendimentoBase Salário base.
     * @param anosServico Anos de serviço.
     * @param valorHorasExtra Custo por hora extra.
     * @param horasExtra Número de horas extra.
     * @return O ordenado total calculado.
     */
    private static double calcularOrdenado(int rendimentoBase, int anosServico, int valorHorasExtra, int horasExtra) {
        double rendimentoAtual = rendimentoBase;

        // Aplica um aumento de 4% por cada 5 anos de serviço
        for (int i = 0; i < anosServico / 5; i++) {
            rendimentoAtual *= 1.04;
        }

        // Adiciona o valor das horas extra ao rendimento base atualizado
        return rendimentoAtual + valorHorasExtra * horasExtra;
    }

    /**
     * Encontra o índice de uma especialidade no array.
     * @param array Array de especialidades.
     * @param especialidade A especialidade a procurar.
     * @return O índice da especialidade ou -1 se não for encontrada.
     */
    private static int obterIndexEspecialidade(String[] array, String especialidade) {
        for (int i = 0; i < array.length; i++) {
            // Compara o nome da especialidade (primeiro elemento após split) com a especialidade procurada
            if (array[i].split("/")[0].equalsIgnoreCase(especialidade)) {
                return i;
            }
        }
        return -1; // Retorna -1 se a especialidade não for encontrada
    }

    /**
     * Adiciona o custo calculado à respetiva especialidade.
     * @param custosEspecialidades Array de custos por especialidade.
     * @param especialidade A especialidade a atualizar.
     * @param custo O custo a adicionar.
     */
    private static void adicionarCustoEspecialidade(String[] custosEspecialidades, String especialidade, double custo) {
        int indexEspecialidade = obterIndexEspecialidade(custosEspecialidades, especialidade);
        if (indexEspecialidade != -1) {
            // Extrai o custo atual, adiciona o novo custo e atualiza o array
            double rendimentoNovo = Double.parseDouble(custosEspecialidades[indexEspecialidade].split("/")[1]) + custo;
            // Capitaliza a primeira letra da especialidade
            String especialidadeCapitalizado = especialidade.substring(0, 1).toUpperCase() + especialidade.substring(1);
            custosEspecialidades[indexEspecialidade] = especialidadeCapitalizado + "/" + rendimentoNovo;
        }
    }

    /**
     * Inicializa o array de custos para as especialidades.
     * @param especialidades Array de especialidades.
     * @param custoEspecialidades Array a inicializar com os custos das especialidades.
     */
    private static void inicializarCustoEspecialidades(String[] especialidades, String[] custoEspecialidades) {
        for (int i = 0; i < especialidades.length; i++) {
            // Inicializa cada especialidade com custo zero
            custoEspecialidades[i] = especialidades[i].split("/")[0] + "/0";
        }
    }

    // imprime o ordenado
    private static void printOrdenado(String nome, double ordenado) {
        System.out.printf("%s: %.1f€\n", nome, ordenado );
    };

    // imprime a especialidade
    private static void printEspecialidade(String nomeEspecialidade, double custo) {
        if (custo > 0) {
            System.out.printf("%s: %.1f€\n", nomeEspecialidade, custo);
        }
    };

    public static void main(String[] args) {
        // Define as especialidades com os seus salários base e custos de horas extra
        String[] especialidades = {
            "Radiologia/2030/50",
            "Oftalmologia/2500/70",
            "Pediatria/2700/75"
        };

        // Inicializa o array para armazenar os custos por especialidade
        String[] custosEspecialidades = new String[especialidades.length];
        inicializarCustoEspecialidades(especialidades, custosEspecialidades);

        // Define os médicos com as suas especialidades, anos de serviço e horas extra
        String[] medicos = {
            "Vasco Santana/radiologia/15/10",
            "Laura Alves/oftalmologia/5/7",
            "António Silva/oftalmologia/12/5"
        };

        // Calcula e mostra os ordenados para cada médico
        for (String medico : medicos) {

            String[] linha = medico.split("/");
            String nome = linha[0];
            String especialidade = linha[1];
            int anosServico = Integer.parseInt(linha[2]);
            int horasExtra = Integer.parseInt(linha[3]);

            // Obtém o rendimento base e o valor das horas extra para a especialidade
            int[] rendimentoEspecialidade = obterRendimentoEspecialidade(especialidades, especialidade);
            int rendimentoBase = rendimentoEspecialidade[0];
            int valorHorasExtra = rendimentoEspecialidade[1];

            // Calcula o ordenado total do médico
            double ordenado = calcularOrdenado(rendimentoBase, anosServico, valorHorasExtra, horasExtra);

            // Imprime o ordenado do médico
            printOrdenado(nome, ordenado);

            // Adiciona o ordenado ao custo total da especialidade
            adicionarCustoEspecialidade(custosEspecialidades, especialidade, ordenado);
        }

        System.out.println();

        // Mostra os custos totais por especialidade
        for (String custoEspecialidade : custosEspecialidades) {
            String[] valores = custoEspecialidade.split("/");
            String nomeEspecialidade = valores[0];
            double custo = Double.parseDouble(valores[1]);

            // Só imprime as especialidades com custo maior que zero
            printEspecialidade(nomeEspecialidade, custo);
        }
    }
}
