package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private static int currentNumber = 1;
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private int number;

    public Invoice() {
        this.number = currentNumber + 1;
        currentNumber++;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
    
    public String printInvoice() {
        StringBuffer printout = new StringBuffer(Integer.toString(this.number));
        printout.append("\n");
        
        for (Map.Entry<Product, Integer> pair :products.entrySet()) {
            String nextLine = pair.getKey().getName() + ", " 
                             + Integer.toString(pair.getValue()) + ", " 
                             + pair.getKey().getPriceWithTax().setScale(2, RoundingMode.HALF_UP)
                             + "\n";
            printout.append(nextLine);
        }
        
        String summary = "Liczba pozycji: " + Integer.toString(products.size()); 
        printout.append(summary.toString());
        return printout.toString();
    }

    public int getNumber() {
        return number;
    }
}
