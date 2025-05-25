package ui;

import db.CreateDB;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CreateDB.createDatabase();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Fornecedores");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    SupplierUI.menu(scanner);
                    break;
                case 2:
                    ProductUI.menu(scanner);
                    break;
                case 3:
                    System.out.println("Sistema encerrado.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}