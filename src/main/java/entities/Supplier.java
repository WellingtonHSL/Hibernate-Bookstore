package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

    public Supplier() {

    }

    public Supplier(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("AÇÃO OBRIGATÓRIA: Digite o nome do fornecedor!");
        }
        this.name = name.trim().toUpperCase();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("AÇÃO OBRIGATÓRIA: Digite o valor do produto!");
        }
        if (p.getBookName() == null || p.getBookName().trim().isEmpty()) {
            throw new IllegalArgumentException("Produto inválido: nome está vazio.");
        }
        if (p.getPrice() < 0) {
            throw new IllegalArgumentException("Produto inválido: preço negativo.");
        }
        products.add(p);
    }

    @Override
    public String toString() {
        return String.format("ID: %d \nNome: %s\n------------------", id, name);
    }
}