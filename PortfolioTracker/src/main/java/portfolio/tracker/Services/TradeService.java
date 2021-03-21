package portfolio.tracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.tracker.Common.CommonMethods;
import portfolio.tracker.Model.Trade;
import portfolio.tracker.Repositories.TradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    CommonMethods commonMethods;

    /** Method to get all the trades of all securities **/
    public List<Trade> getAllTrades() {
        List<Trade> allTrades = tradeRepository.findAll();

        if (allTrades.size() > 0) {
            return allTrades;
        } else {
            return new ArrayList<Trade>();
        }
    }

    public Trade getTradeWithId(int id) {
        return tradeRepository.findById(id).get();
    }

    /** Function to add the trade **/
    public void addTrade(Trade trade) throws Exception {
        int value = checkQuantity(trade.getSecurity_ticker(), trade.getType(), trade.getQuantity());
        if (value >= 0)
            tradeRepository.save(trade);
        else
            throw new Exception(commonMethods.getExceptionMessageForAddUpdate());
    }

    /** Function to update the trade **/
    public void updateTrade(Trade trade, Trade existingTrade) throws Exception {
        int flag = checkPositiveQuantitiesOfSecurities(existingTrade);
        if (trade.getType().equals("S")) {
            int id = trade.getId();
            List<Trade> tradesOfTicker = tradeRepository.findBySecurityTicker(trade.getSecurity_ticker());
            int totalQuantityExcludingUpdateTrade = 0;
            for (Trade obj : tradesOfTicker) {
                if (obj.getId() != id) {
                    if (obj.getType().equals("B")) {
                        totalQuantityExcludingUpdateTrade += obj.getQuantity();
                    } else if (obj.getType().equals("S")) {
                        totalQuantityExcludingUpdateTrade -= obj.getQuantity();
                    }
                }
            }
            if (totalQuantityExcludingUpdateTrade - trade.getQuantity() >= 0 && flag == 1) {
                tradeRepository.save(trade);
            }
            else if (totalQuantityExcludingUpdateTrade - trade.getQuantity() >= 0 && flag == 0) {
                throw new Exception(commonMethods.getExceptionMessageWhenSecurityTickerIsUpdated(existingTrade.getSecurity_ticker()));
            }
            else {
                throw new Exception(commonMethods.getExceptionMessageForAddUpdate());
            }
        }
        else {
            if(flag == 1) {
                tradeRepository.save(trade);
            }
            else {
                throw new Exception(commonMethods.getExceptionMessageWhenSecurityTickerIsUpdated(existingTrade.getSecurity_ticker()));
            }
        }
    }

    /** Function to delete the trade **/
    public void removeTrade(int id) throws Exception {
        Optional<Trade> tradeToBeDeleted = tradeRepository.findById(id);
        List<Trade> tradesOfTicker = tradeRepository.findBySecurityTicker(tradeToBeDeleted.get().getSecurity_ticker());
        int totalQuantityExcludingDeletionTrade = 0;
        for (Trade trade : tradesOfTicker) {
            if(trade.getId() != id) {
            if (trade.getType().equals("B")) {
                totalQuantityExcludingDeletionTrade += trade.getQuantity(); }
            else if (trade.getType().equals("S")) {
                totalQuantityExcludingDeletionTrade -= trade.getQuantity(); }
        } }
        if (totalQuantityExcludingDeletionTrade >= 0) {
            tradeRepository.deleteById(id); }
        else {
            throw new Exception(commonMethods.getExceptionMessageForDelete());
        }
    }

    /**
     * Method to check if quantity will be greater than or equal
     * to zero after adding the trade
     */
    public int checkQuantity(String securityTicker, String type, int quantityOfNewTrade) {
        List<Trade> tradesOfGivenTicker = tradeRepository.findBySecurityTicker(securityTicker);
        int totalQuantity = 0;
        for (Trade trade : tradesOfGivenTicker) {
            if (trade.getType().equals("B")) {
                totalQuantity += trade.getQuantity();}
            else if (trade.getType().equals("S")) {
                totalQuantity -= trade.getQuantity();}
        }
        if (type.equals("S")) {
            if (totalQuantity - quantityOfNewTrade >= 0) {
                return 1;}
            else {
                return -1;}
        } else
            return 1; }

    /** Method to check if existing security have positive quantities after updating
     * the trade.
     */
    public int checkPositiveQuantitiesOfSecurities(Trade existingTrade) {
        int quantityOfExistingTradeTicker = 0;
        List<Trade> allTradesOfExistingTradeTicker = tradeRepository.findBySecurityTicker(existingTrade.getSecurity_ticker());
        for (Trade obj : allTradesOfExistingTradeTicker) {
            if (obj.getType().equals("B") && obj.getId() != existingTrade.getId()) {
                quantityOfExistingTradeTicker += obj.getQuantity();
            } else if (obj.getType().equals("S") && obj.getId() != existingTrade.getId()) {
                quantityOfExistingTradeTicker -= obj.getQuantity();
            }
        }
        if (quantityOfExistingTradeTicker > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
