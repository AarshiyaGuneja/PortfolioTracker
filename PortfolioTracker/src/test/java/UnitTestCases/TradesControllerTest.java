package UnitTestCases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import portfolio.tracker.Repositories.TradeRepository;
import portfolio.tracker.Services.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradesControllerTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @Test
    public void testTradeControllers() throws Exception {
        int id = 11;
        tradeService.removeTrade(id);
    }
}
