package com.example.TradeStore;

import com.example.controller.RestTradeGateway;
import com.example.repository.Trade;
import com.example.repository.TradeRepository;
import com.example.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({TradeService.class, TradeRepository.class})
@ContextConfiguration(classes = { TradeStoreApplication.class })
public class TradeServiceIntegrationTest {

    private static final String url = "http://localhost:8080/instrumentsBy";

    //    public WireMockRule wireMockRule = new WireMockRule(8089);
    private MockMvc mvc;
    @Autowired
    Trade trade;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;
    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(new RestTradeGateway(tradeService)).build();
        tradeRepository.save()
    }

    @Test
    public void testOne() throws Exception {
//        TradeData tradeData = new TradeData("Hare Krishna Hare", "", "", "2019-12-31");
        callTradeGatewayGetInstrumentsBy("Hare", "name", "value", "2019-12-31");
    }

    private void callTradeGatewayGetInstrumentsBy(String id, String name, String value, String date) throws Exception {
        mvc.perform(get(url).param("id",id).param("name",name).param("value",value).param("date",date))
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
