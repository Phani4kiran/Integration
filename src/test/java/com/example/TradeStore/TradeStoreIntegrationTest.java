package com.example.TradeStore;

import com.example.controller.RestTradeGateway;
import com.example.controller.TradeRequest;
import com.example.repository.Trade;
import com.example.repository.TradeRepository;
import com.example.service.TradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TradeStoreApplication.class})
//@RunWith(SpringRunner.class)
//@DataJpaTest
@Import({TradeService.class, Trade.class, TradeRequest.class})
@ContextConfiguration(classes = {TradeStoreApplication.class})
public class TradeStoreIntegrationTest {

    private static final String url = "http://localhost:8080/instrumentsBy";

    //    public WireMockRule wireMockRule = new WireMockRule(8089);
    private MockMvc mvc;
    @Autowired
    Trade trade;
    @Autowired
    TradeRequest tradeRequest;
    //    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(new RestTradeGateway(tradeService)).build();
        Trade trade = Trade.builder()
                .id(1)
                .clientId("123")
                .name("Stock1")
                .price(10.05)
                .build();
        tradeRepository.save(trade);
    }

    @AfterEach
    public void delete() {
        this.mvc = MockMvcBuilders.standaloneSetup(new RestTradeGateway(tradeService)).build();
        tradeRepository.deleteAll();
    }

    @Test
    public void testOne() throws Exception {
        callTradeGatewayGetInstrumentsBy("Hare", "name", "value", "2019-12-31");
    }

    private void callTradeGatewayGetInstrumentsBy(String id, String name, String value, String date) throws Exception {
        mvc.perform(get(url).param("id", id).param("name", name).param("value", value).param("date", date))
                .andExpect(status().isCreated());

//        given()
//
//                .and().contentType(ContentType.TEXT)
//                .when()
//                .get(url,id,name,date)
//                .then()
//                .statusCode(HttpStatus.SC_OK);
    }

}
