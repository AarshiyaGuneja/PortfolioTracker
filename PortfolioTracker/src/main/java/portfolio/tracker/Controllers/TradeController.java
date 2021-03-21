package portfolio.tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.tracker.Model.Trade;
import portfolio.tracker.Services.TradeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    TradeService tradeService;

    /** Fetching trades **/
    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> list = tradeService.getAllTrades();

        return new ResponseEntity<List<Trade>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /** Adding trade **/
    @PostMapping
    public void addTrades(@RequestBody Trade trade) throws Exception {
        tradeService.addTrade(trade);
    }

    /** Updating the trade **/
    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@RequestBody Trade trade, @PathVariable int id) throws Exception {
        try {
            Trade existingTrade = tradeService.getTradeWithId(id);
            trade.setId(id);
            tradeService.updateTrade(trade, existingTrade);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /** Removing the trade **/
    @DeleteMapping("/{id}")
    public void removeTrade(@PathVariable int id) throws Exception {
        tradeService.removeTrade(id);
    }
}
