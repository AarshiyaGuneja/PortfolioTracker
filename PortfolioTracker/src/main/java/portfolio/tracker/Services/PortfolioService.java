package portfolio.tracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.tracker.Model.Portfolio;
import portfolio.tracker.Model.Trade;
import portfolio.tracker.Repositories.TradeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    TradeRepository tradeRepository;

    /** Function to get Portfolio **/
    public List<Portfolio> getPortfolio() throws Exception {
        List<Portfolio> portfolio = new ArrayList<>();
        List<String> securities = tradeRepository.findDistinctSecurityTicker();
        for (String security : securities)
        {
            List<Trade> tradesAfterSelling;
            List<Trade> trades = tradeRepository.findBySecurityTicker(security);
            tradesAfterSelling = tradesArrangementBeforeAveragePricing(trades);
            if(tradesAfterSelling.size() > 0) {
                Portfolio portfolioOfParticularSecurity = calculateAveragePrice(tradesAfterSelling);
                portfolio.add(portfolioOfParticularSecurity);
            }
        }
        return portfolio;
    }

    /** Method to arrange trades in a manner to adjust selling trades with buying trades
     * @param trades
     * @return list of trades
     */
    public List<Trade> tradesArrangementBeforeAveragePricing(List<Trade> trades) throws Exception {
        try {
            for (int i = 0; i < trades.size(); i++) {
                if (trades.get(i).getType().equals("S")) {
                    int sellQuantity = trades.get(i).getQuantity();
                    int index = 0;
                    while (sellQuantity > 0) {
                        if (trades.get(index).getQuantity() > sellQuantity && trades.get(index).getType().equals("B")) {
                            int actualQuantity = trades.get(index).getQuantity() - sellQuantity;
                            Trade obj = new Trade(trades.get(index).getId(), trades.get(index).getSecurity_ticker(),
                                    trades.get(index).getType(), actualQuantity, trades.get(index).getPrice());
                            trades.add(0, obj);
                            trades.remove(1);
                            trades.remove(i);
                            sellQuantity = 0;
                            i--;
                        } else if (trades.get(index).getQuantity() == sellQuantity && trades.get(index).getType().equals("B")) {
                            trades.remove(i);
                            trades.remove(0);
                            sellQuantity = 0;
                            i -= 2;
                        } else if (trades.get(index).getQuantity() < sellQuantity && trades.get(index).getType().equals("B")) {
                            sellQuantity = sellQuantity - trades.get(index).getQuantity();
                            trades.remove(0);
                            index = 0;
                            i--;
                        } else {
                            index++;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
        return trades;
    }

    /** Method to calculate average buying price **/
    public Portfolio calculateAveragePrice(List<Trade> trades) {
        double avgPrice = 0;
        int finalQuantity = 0;
        for (Trade obj : trades) {
            avgPrice += obj.getPrice()* obj.getQuantity();
            finalQuantity += obj.getQuantity();
        }
        avgPrice = avgPrice/finalQuantity;
        return new Portfolio(trades.get(0).getSecurity_ticker(), avgPrice, finalQuantity);
    }

    /** Method to calculate returns of the portfolio **/
    public double calculateReturns() throws Exception {
        List<Portfolio> portfolio = getPortfolio();
        int currentPrice = 100;
        double returns = 0;
        for(Portfolio obj : portfolio) {
            returns += (currentPrice - obj.getAvg_price())* obj.getQuantity();
        }
        return returns;
    }
}
