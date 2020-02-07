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
		if (!(quantity <= 0)) {
			products.put(product, quantity);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public BigDecimal getNetPrice() {
		BigDecimal sum = BigDecimal.ZERO;
		for(Product product : this.products.keySet()){
			Integer amount = this.products.get(product);
			BigDecimal quantity = new BigDecimal(amount);
			BigDecimal price = product.getPrice();
			sum = sum.add(price.multiply(quantity));
		}
		return sum;
	}

	public BigDecimal getTax() {
		return this.getGrossPrice().subtract(this.getNetPrice());
	}

	public BigDecimal getGrossPrice() {
		BigDecimal gross = BigDecimal.ZERO;
		for(Product product : this.products.keySet()){
			BigDecimal quantity = new BigDecimal(this.products.get(product));
			BigDecimal price = product.getPriceWithTax();
			gross = gross.add(price.multiply(quantity));
		}
		return gross;
	}
}
