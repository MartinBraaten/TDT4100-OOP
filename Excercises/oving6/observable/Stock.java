package oving6.observable;

public class Stock {
    String ticker;
    double price;
    StockListener stockListener;

    public Stock(String ticker, double price) {
        this.ticker = ticker;
        this.price = price;
    }

    public void setPrice(double price) {
        if (price == 0 || price < 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        } else {
            double oldPrice = price;
            this.price = price;
            if (stockListener == null) {
                throw new IllegalArgumentException("Ingen stocklistener");
            } else {
                stockListener.stockPriceChanged(this, oldPrice, price);
            }
        }
    }
    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }
    public void addStockListener(StockListener stockListener) {
        this.stockListener = stockListener;
        
    }
    public void removeStockListener(StockListener stockListener) {
        this.stockListener = null;
    }
}
