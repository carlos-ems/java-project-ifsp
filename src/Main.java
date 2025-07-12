/*
* Instituto Federal de São Paulo
* Campus São Paulo
* Docente: Prof. Daniel Morais
* Discentes: Carlos Silva e Mariana Keiko
*/

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TarefaService service = new TarefaService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> adicionarTarefa();
                case 2 -> alterarStatus();
                case 3 -> listar("TODAS");
                case 4 -> listar("PENDENTES");
                case 5 -> listar("CONCLUIDAS");
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== LISTA DE TAREFAS ===");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Alterar status de uma tarefa");
        System.out.println("3. Listar todas as tarefas");
        System.out.println("4. Listar tarefas pendentes");
        System.out.println("5. Listar tarefas concluídas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarTarefa() {
        System.out.print("Digite o nome da tarefa (identificador): ");
        String nome = scanner.nextLine();

        System.out.print("Digite a descrição da tarefa: ");
        String texto = scanner.nextLine();

        service.adicionarTarefa(nome, texto);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    private static void alterarStatus() {
        System.out.print("Digite o nome da tarefa a ser atualizada: ");
        String nome = scanner.nextLine();

        System.out.print("Marcar como concluída? (s/n): ");
        String resposta = scanner.nextLine().toLowerCase();
        boolean concluido = resposta.equals("s");

        service.alterarStatus(nome, concluido);
        System.out.println("Status atualizado.");
    }

    private static void listar(String tipo) {
        List<Tarefa> tarefas = switch (tipo) {
            case "PENDENTES" -> service.listarPendentes();
            case "CONCLUIDAS" -> service.listarConcluidas();
            default -> service.listarTodas();
        };

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("\n--- TAREFAS (" + tipo + ") ---");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }
}