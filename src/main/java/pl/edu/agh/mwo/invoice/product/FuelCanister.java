package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FuelCanister extends ExciseDutyProduct {

    public FuelCanister(String name, BigDecimal price) {
        super(name, price);
    }
    
    public BigDecimal getPriceWithTax() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        
        BigDecimal excise;
        
        if (month == 4 && day == 26) {
            excise = this.getExciseDuty();
        } else {
            excise = BigDecimal.ZERO;
        }     
        return this.getPrice().multiply(this.getTaxPercent()).add(this.getPrice()).add(excise);
    }

}
