package oving6.observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockIndex implements StockListener{

    ArrayList<Stock> aksjemarked = new ArrayList<Stock>();
    Stock stock;

    @Override
    public void stockPriceChanged(Stock stock, double oldValue, double newValue) {
        
        
    }

    public StockIndex(Stock... stock) {
        if (this.stock == null) {
            
        } else {
            //aksjemarked.add(stock);
        }
    }

    public void addStock(Stock stock) {
        aksjemarked.add(stock);
    }
    public void removeStock(Stock stock) {
        aksjemarked.remove(stock);
    }
    public double getIndex() {
        return aksjemarked.indexOf(stock);
    }
    
}
