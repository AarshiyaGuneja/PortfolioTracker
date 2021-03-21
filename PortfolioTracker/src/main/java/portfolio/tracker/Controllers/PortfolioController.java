package portfolio.tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.tracker.Model.Portfolio;
import portfolio.tracker.Services.PortfolioService;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    /** Fetching portfolio **/
    @GetMapping
    public ResponseEntity<List<Portfolio>> getPortfolio() throws Exception {
        List<Portfolio> list = portfolioService.getPortfolio();

        return new ResponseEntity<List<Portfolio>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /** Fetching returns **/
    @GetMapping("/returns")
    public double getReturns() throws Exception {
        double returns = portfolioService.calculateReturns();
        return returns;
    }
}
