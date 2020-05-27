package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class ExciseDutyProduct extends Product {
    
    private BigDecimal exciseDuty;

    public void setExciseDuty(BigDecimal exciseDuty) {
        this.exciseDuty = exciseDuty;
    }
    
    public BigDecimal getExciseDuty() {
        return exciseDuty;
    }

    public ExciseDutyProduct(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
        this.exciseDuty = new BigDecimal("5.56");
    }

    public BigDecimal getPriceWithTax() {
        return this.getPrice().multiply(this.getTaxPercent()).add(this.getPrice()).add(this.getExciseDuty());
    }

}
