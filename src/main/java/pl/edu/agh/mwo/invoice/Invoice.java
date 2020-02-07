package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
//	private Collection<Product> products
	private Map<Product, Integer> products =  new HashMap<>();

	public void addProduct(Product product) {
		this.addProduct(product, 1);
//		if (products.containsKey(product)) {
//			int a = products.get(product);
//			products.put(product,a+1);
//		}
//		products.put(product,1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (products.containsKey(product)) {
			int a = products.get(product);
			products.put(product,quantity+1);
		}
		products.put(product,quantity);
	}

	public BigDecimal getNetPrice() {
		BigDecimal sum = new BigDecimal ("0");
		for(Product s : this.products.keySet()){
			Integer amount = this.products.get(s);
			BigDecimal a = new BigDecimal(amount);
			BigDecimal price = s.getPrice();
			sum = sum.add(price.multiply(a));
		}
		return sum;
	}

	public BigDecimal getTax() {
		return this.getGrossPrice().subtract(this.getNetPrice());
	}

	public BigDecimal getGrossPrice() {
		BigDecimal gross = new BigDecimal("0");
		for(Product s : this.products.keySet()){
			BigDecimal a = new BigDecimal(this.products.get(s));
			BigDecimal tax = s.getPriceWithTax().subtract(s.getPrice());
			gross = gross.add(tax.multiply(a));
		}
		return gross;
	}
}
