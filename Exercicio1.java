// Considere um centro de saúde onde trabalham medicos de diferentes especialidades.
// A componente fixa do ordenado dos medicos é calculada a partir do salário base da especialidade acrescido de 4% por cada 5 de anos de serviço.
// A componente variável do ordenado depende do número de horas de trabalho extra.
// Pretende desenvolver-se uma aplicação para gerir o centro de saúde considerando um número indeterminado de medicos e de especialidades.
// A aplicação deverá calcular os ordenados a pagar por médico e por especialidade.

// IMPORTANTE: SO PODE HAVE 1 FICHEIRO CHAMADO 'Exercicio1' !!!
// IMPORTANTE 2: NAO PODES USAR ARRAYS TIPO HASHMAPS, Lists, Dicts, etc. YKWIM

import java.math.*;

public class Exercicio1 {
    //private static print_especialidadedes() {};
    //private static print_medicos() {};
    
    // Devolve um array com o salario base (i=0) e o custo da hora extra (i=1)
    // Se não for encontrado a especialidade os valores são iguais a 0
    private static int[] obterRendimentoEspecialidade(String[] especialidades, String especialidade ) {
        int[] resultado = new int[2];

        for (String s : especialidades) {
            String[] linha = s.split("/");
            String nome = linha[0];
            int salario_base = Integer.parseInt(linha[1]);
            int custo_hora_extra = Integer.parseInt(linha[2]);

            if (nome.equalsIgnoreCase(especialidade)) {
                resultado[0] = salario_base;
                resultado[1] = custo_hora_extra;
                break;
            }
        }

        return resultado;
    }
   
    // Calcula o ordenado
    private static double calcularOrdenado(int rendimento_base, int anos_servico, int valor_horas_extra, int horas_extra) {
        double rendimento_atual = rendimento_base; // necessário
        for (int i = 0; i < anos_servico / 5; i++) {
            rendimento_atual *= 1.04;
        }
        return rendimento_atual + valor_horas_extra*horas_extra;
    };


    // Atualiza o rendimento total de todos os medico de uma dada especialidade à sua respectiva coluna 
    // ao somar o valor rendimento
    private static void rendimentoEspecialidadeUpdate(String[] especialidades, String especialidade, double rendimento ) {
        int i;
        String[] valores;
        for (i = 0; i < especialidades.length; i++) {
             valores = especialidades[i].split("/");
             if (valores.length < 4) {
                especialidades[i] = especialidades[i] + "/" + rendimento ;
                break;
            }

            if (valores[0].equalsIgnoreCase(especialidade))  {
                double rendimento_novo = Double.parseDouble(valores[3]) + rendimento;
                especialidades[i] = valores[0] + "/" + valores[1] + "/" + valores[2] + "/" + rendimento_novo;
                break;
            }
        } 
    }

    public static void main(String[] args) {
        String[] especialidades = { 
            //nome/salário base/custo da hora extra 
            "Radiologia/2030/50",  
            "Oftalmologia/2500/70", 
            "Pediatria/2700/75" 
        }; 

        String[] medicos = { 
            //nome/especialidade/anos de serviço/horas extra 
            "Vasco Santana/radiologia/15/10", 
            "Laura Alves/oftalmologia/5/7", 
            "António Silva/oftalmologia/12/5" 
        };

        for (String medico : medicos) {
            String[] linha = medico.split("/");
            String nome = linha[0];
            String especialidade = linha[1];
            int anos_servico = Integer.parseInt(linha[2]);
            int horas_extra = Integer.parseInt(linha[3]);

            int[] rendimento_especialidade = obterRendimentoEspecialidade(especialidades, especialidade);
            int rendimento_base = rendimento_especialidade[0];
            int valor_horas_extra = rendimento_especialidade[1];

            double ordenado = calcularOrdenado(rendimento_base, anos_servico, valor_horas_extra, horas_extra);
            rendimentoEspecialidadeUpdate(especialidades, especialidade, ordenado );

            System.out.printf("%s: %.1f€\n", nome, ordenado);
        }

        System.out.println();
        for (int i = 0; i < especialidades.length; i++) {
            String[] valores = especialidades[i].split("/");
            if (valores.length > 3)
                System.out.printf("%s: %.1f€\n", valores[0],  Double.parseDouble(valores[3]) );
        }

        //Verifica o resultado: 
        //Vasco Santana: 2783.5€ 
        //Laura Alves: 3090.0€ 
        //António Silva: 3054.0€ 

        //Radiologia: 2783.5€ 
        //Oftalmologia: 6144.0€

    }
}
