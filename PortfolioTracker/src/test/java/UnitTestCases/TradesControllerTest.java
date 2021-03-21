package UnitTestCases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import portfolio.tracker.Application;
import portfolio.tracker.Model.Trade;
import portfolio.tracker.Repositories.TradeRepository;
import portfolio.tracker.Services.PortfolioService;
import portfolio.tracker.Services.TradeService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TradesControllerTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private PortfolioService portfolioService;

    @Test
    public void testTradeControllers() throws Exception {
        List<Trade> allTrades = tradeService.getAllTrades();
        if(allTrades != null ){
            System.out.println("System is working fine.");
        }
    }
}
