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
            System.out.println("\n--- Livros ---");
            System.out.println("1. Cadastrar novos Livros");
            System.out.println("2. Listar Livros Cadastrados");
            System.out.println("3. Atualizar Informações dos Livros");
            System.out.println("4. Excluir Livros");
            System.out.println("5. Voltar para o Menu Principal");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registerProduct(scanner);
                case 2 -> listAllProduct();
                case 3 -> updateProduct(scanner);
                case 4 -> deleteProduct(scanner);
                case 5 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void registerProduct(Scanner scanner) {
        List<Supplier> suppliers = supplierService.findAll();
        if (suppliers.isEmpty()) {
            System.out.println("Cadastre um fornecedor antes.");
            return;
        }

        System.out.println("Escolha o fornecedor:");
        for (int i = 0; i < suppliers.size(); i++) {
            System.out.println(i + " - " + suppliers.get(i).getName());
        }

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 0 || escolha >= suppliers.size()) {
            System.out.println("Fornecedor inválido.");
            return;
        }

        String name;
        double price;

        while (true) {
            System.out.print("Nome do Livro: ");
            name = scanner.nextLine();

            if (productService.existsByName(name)) {
                System.out.println("Já existe um livro com esse nome.");
            } else {
                break;
            }
        }

        while (true) {
            try {
                System.out.print("Preço do Livro: ");
                price = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Insira um número válido para o preço.\nDeve ser maior ou igual a R$1,00.");
                scanner.nextLine();
            }
        }

        Product p = new Product();
        p.setBookName(name);
        p.setPrice(price);
        p.setSupplier(suppliers.get(escolha));

        productService.save(p);
        System.out.println("Livro cadastrado.");
    }

    private static void listAllProduct() {
        List<Product> list = productService.findAll();
        if (list.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("--- LISTA DE LIVROS ---");
            list.forEach(System.out::println);
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("ID do Livro: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Product p = productService.findById(id);
        if (p == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        while (true) {
            System.out.print("Atualizar nome do Livro: ");
            String newNome = scanner.nextLine();
            if (productService.existsByName(newNome)) {
                System.out.println("Já existe um livro com esse nome.");
            } else {
                p.setBookName(newNome);
                break;
            }
        }

        while (true) {
            try {
                System.out.print("Atualizar preço do Livro: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                p.setPrice(newPrice);
                break;
            } catch (Exception e) {
                System.out.println("Insira um número válido.");
                scanner.nextLine();
            }
        }

        productService.update(p);
        System.out.println("Livro atualizado.");
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("ID do livro: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Product p = productService.findById(id);
        if (p == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        productService.delete(p);
        System.out.println("Livro excluído.");
    }
}