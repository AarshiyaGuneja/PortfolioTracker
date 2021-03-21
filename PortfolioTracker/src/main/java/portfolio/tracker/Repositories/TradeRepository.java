package portfolio.tracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import portfolio.tracker.Model.Trade;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer>{

    /** Customized query to get trades w.r.t security ticker**/

    @Query(value = "select * from trades where security_ticker = :security_ticker", nativeQuery = true)
    List<Trade> findBySecurityTicker(@Param("security_ticker") String security_ticker);

    /** Customized query to get distinct securities from all trades **/

    @Query(value = "select distinct security_ticker from trades", nativeQuery = true)
    List<String> findDistinctSecurityTicker();

}
