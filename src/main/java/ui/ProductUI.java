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
                case 5 -> {
                    return;
                }
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

        String name = null;
        double price = 0.0;

        while (true) {
            try {
                System.out.print("Nome do Livro: ");
                name = scanner.nextLine();

                Product teste = new Product();
                teste.setBookName(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Preço: ");
                price = scanner.nextDouble();
                scanner.nextLine();
                Product teste = new Product();
                teste.setPrice(price);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        Product p = new Product();
        p.setBookName(name);
        p.setPrice(price);
        p.setSupplier(suppliers.get(escolha));

        productService.save(p);
        System.out.println("Produto cadastrado.");
    }

    private static void listAllProduct() {
        List<Product> lista = productService.findAll();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("--- LISTA DE LIVROS ---");
            lista.forEach(System.out::println);
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("ID do produto: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Product p = productService.findById(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        while (true) {
            try {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                p.setBookName(novoNome);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Novo preço: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                p.setPrice(newPrice);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro: Insira um número válido para o preço.");
                scanner.nextLine();
            }
        }

        productService.update(p);
        System.out.println("Produto atualizado.");
    }

    private static void deleteProduct(Scanner scanner) {
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