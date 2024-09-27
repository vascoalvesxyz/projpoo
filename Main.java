// Considere um centro de saúde onde trabalham medicos de diferentes especialidades. A componente fixa do ordenado dos medicos é calculada a partir do salário base da especialidade acrescido de 4% por cada 5 de anos de serviço. A componente variável do ordenado depende do número de horas de trabalho extra. Pretende desenvolver-se uma aplicação para gerir o centro de saúde considerando um número indeterminado de medicos e de especialidades. A aplicação deverá calcular os ordenados a pagar por médico e por especialidade.
//
// String[] especialidades = { 
     //nome/salário base/custo da hora extra 
     //"Radiologia/2030/50",  
     //"Oftalmologia/2500/70", 
     //"Pediatria/2700/75" 
// }; 
//
//String[] medicos = { 
//nome/especialidade/anos de serviço/horas extra 
//"Vasco Santana/radiologia/15/10", 
//"Laura Alves/oftalmologia/5/7", 
//"António Silva/oftalmologia/12/5" 
//}; 
//
//Resultado: 
//Vasco Santana: 2783.5€ 
//Laura Alves: 3090.0€ 
//António Silva: 3054.0€ 
//
//Radiologia: 2783.5€ 
//Oftalmologia: 6144.0€
import java.math.*;

public class Main {
    //private static print_especialidadedes() {}; 
    //private static print_medicos() {}; 
    
    // Devolve um array com o salario base (i=0) e o custo da hora extra (i=1)
    // Se não for encontrado a especialidade os valores são iguais a -1
    private static int[] especialidadeGetBases(String[] especilidade_table, String especialidade ) {
        int[] res = {-1 , -1};

        int i;
        String[] linha = {"-1", "-1"};
        for (i = 0; i < especilidade_table.length; i++) {
             linha = especilidade_table[i].split("/");
            if ( (linha[0].toLowerCase()).equals(especialidade.toLowerCase()) ) {
                break;
            }
        }
        res[0] = Integer.parseInt(linha[1]);
        res[1] = Integer.parseInt(linha[2]);
        return res;
    }
   
    // Calcula o ordenado
    private static double medicoOrdenado(int salario_base, int valor_horas_extra, int anos_servico, int horas_extra) {
        double new_salario = salario_base; // necessário
        for (int i = 0; i < Math.floor(anos_servico/5); i++) {
            new_salario *= 1.04; 
        }
        return new_salario + valor_horas_extra*horas_extra; 
    };

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

        int i;
        String[] linha = {"-1"};
        for (i = 0; i < medicos.length; i++) {
            linha = medicos[i].split("/");
            int[] bases = especialidadeGetBases(especialidades, linha[1]);
            double ordenado = medicoOrdenado(bases[0], bases[1], Integer.parseInt(linha[2]), Integer.parseInt(linha[3]));
            System.out.printf("%s: %.2f\n", linha[0], ordenado );
        }

    }
}
