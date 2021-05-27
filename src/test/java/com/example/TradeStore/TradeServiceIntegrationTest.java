package com.example.TradeStore;

import com.example.controller.RestTradeGateway;
import com.example.repository.TradeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(value = "TradeStoreApplication.class")
@SpringJUnitConfig
//@AutoConfigureMockMvc
public class TradeServiceIntegrationTest {

    private static final String url = "http://localhost:8080/instrumentsBy";

    //    public WireMockRule wireMockRule = new WireMockRule(8089);
    private MockMvc mvc;
    @MockBean
    TradeData tradeData;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(new RestTradeGateway()).build();
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
