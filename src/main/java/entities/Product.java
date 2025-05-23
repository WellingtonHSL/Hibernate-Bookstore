package entities;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product() {

    }

    public Product(Supplier supplier, Double price, String bookName) {
        this.supplier = supplier;
        this.price = price;
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Fornecedor é obrigatório.");
        }
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price < 1.0) {
            throw new IllegalArgumentException("Preço do livro deve ser maior que R$ 1.0");
        }
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        if (bookName.isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome do livro' não pode ser vazio");
        }
        this.bookName = bookName.toUpperCase().trim();
    }

    @Override
    public String toString() {
        return String.format("ID: %d \nTítulo do Livro: %s \nPreço: R$%.2f \nFornecedor: %s\n---------------------------", id, bookName, price, supplier.getName());
    }
}
