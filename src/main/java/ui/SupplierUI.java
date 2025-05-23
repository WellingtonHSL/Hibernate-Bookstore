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
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Excluir");
            System.out.println("5. Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> listar();
                case 3 -> atualizar(scanner);
                case 4 -> excluir(scanner);
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrar(Scanner scanner) {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        Supplier s = new Supplier();
        s.setName(nome);
        supplierService.save(s);
        System.out.println("Fornecedor cadastrado.");
    }

    private static void listar() {
        List<Supplier> lista = supplierService.findAll();
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
        } else {
            System.out.println("--- LISTA DE FORNECEDORES ---");
            lista.forEach(System.out::println);
        }
    }

    private static void atualizar(Scanner scanner) {
        System.out.print("ID do fornecedor: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Supplier s = supplierService.findById(id);
        if (s == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }
        System.out.print("Novo nome: ");
        s.setName(scanner.nextLine());
        supplierService.update(s);
        System.out.println("Fornecedor atualizado.");
    }

    private static void excluir(Scanner scanner) {
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