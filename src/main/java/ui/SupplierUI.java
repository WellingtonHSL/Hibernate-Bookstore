package ui;

import entities.Supplier;
import service.SupplierService;

import java.util.List;
import java.util.Scanner;

public class SupplierUI {
    private static final SupplierService supplierService = new SupplierService();

    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Fornecedores ---");
            System.out.println("1. Cadastrar Fornecedores");
            System.out.println("2. Listar Fornecedores");
            System.out.println("3. Atualizar Fornecedores");
            System.out.println("4. Excluir Fornecedores");
            System.out.println("5. Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> register(scanner);
                case 2 -> listAll();
                case 3 -> update(scanner);
                case 4 -> delete(scanner);
                case 5 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void register(Scanner scanner) {
        Supplier s = new Supplier();

        while (true) {
            System.out.print("Nome do fornecedor: ");
            String nome = scanner.nextLine();
            if (supplierService.existsByName(nome)) {
                System.out.println("Erro: Fornecedor já cadastrado.");
            } else {
                s.setName(nome);
                break;
            }
        }

        supplierService.save(s);
        System.out.println("Fornecedor cadastrado.");
    }

    private static void listAll() {
        List<Supplier> list = supplierService.findAll();
        if (list.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
        } else {
            System.out.println("--- LISTA DE FORNECEDORES ---");
            list.forEach(System.out::println);
        }
    }

    private static void update(Scanner scanner) {
        System.out.print("ID do fornecedor: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Supplier s = supplierService.findById(id);
        if (s == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        while (true) {
            System.out.print("Atualizar nome do Fornecedor: ");
            String novoNome = scanner.nextLine();
            if (supplierService.existsByName(novoNome)) {
                System.out.println("Erro: Nome já existe.");
            } else {
                s.setName(novoNome);
                break;
            }
        }

        supplierService.update(s);
        System.out.println("Fornecedor atualizado.");
    }

    private static void delete(Scanner scanner) {
        System.out.print("ID do fornecedor: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Supplier s = supplierService.findById(id);
        if (s == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }
        supplierService.delete(s);
        System.out.println("Fornecedor excluído.");
    }
}