package ui;

import entities.Product;
import entities.Supplier;
import service.ProductService;
import service.SupplierService;

import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private static final ProductService productService = new ProductService();
    private static final SupplierService supplierService = new SupplierService();

    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Produtos ---");
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
        List<Supplier> fornecedores = supplierService.findAll();
        if (fornecedores.isEmpty()) {
            System.out.println("Cadastre um fornecedor antes.");
            return;
        }

        System.out.println("Escolha o fornecedor:");
        for (int i = 0; i < fornecedores.size(); i++) {
            System.out.println(i + " - " + fornecedores.get(i).getName());
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();
        if (escolha < 0 || escolha >= fornecedores.size()) {
            System.out.println("Fornecedor inválido.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Product p = new Product();
        p.setBookName(nome);
        p.setPrice(preco);
        p.setSupplier(fornecedores.get(escolha));

        productService.save(p);
        System.out.println("Produto cadastrado.");
    }

    private static void listar() {
        List<Product> lista = productService.findAll();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("--- LISTA DE LIVROS ---");
            lista.forEach(System.out::println);
        }
    }

    private static void atualizar(Scanner scanner) {
        System.out.print("ID do produto: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Product p = productService.findById(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        p.setBookName(scanner.nextLine());
        System.out.print("Novo preço: ");
        p.setPrice(scanner.nextDouble());
        scanner.nextLine();

        productService.update(p);
        System.out.println("Produto atualizado.");
    }

    private static void excluir(Scanner scanner) {
        System.out.print("ID do produto: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Product p = productService.findById(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        productService.delete(p);
        System.out.println("Produto excluído.");
    }
}