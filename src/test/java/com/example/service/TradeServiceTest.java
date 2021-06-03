package com.example.service;

import com.example.controller.ClientPricesByInstrument;
import com.example.controller.InstrumentPriceDetails;
import com.example.controller.InstrumentPricesByClient;
import com.example.endpoint.Trade;
import com.example.repository.Clients;
import com.example.repository.ClientsRepository;
import com.example.repository.Instruments;
import com.example.repository.InstrumentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TradeServiceTest {
    @InjectMocks
    private TradeService tradeService;

    @Mock
    private ClientsRepository clientsRepository;

    @Mock
    private InstrumentsRepository instrumentsRepository;

    private static Stream<Arguments> createPostTradeParameters() throws IOException {
        return Stream.of(
                Arguments.of("ABCD", buildTrades(), buildClients()),
                Arguments.of("1002", buildTrades(), null)
        );
    }

    @ParameterizedTest
    @MethodSource("createPostTradeParameters")
    void postTradesSuccess(String clientCode, List<Trade> trades, Clients clientsInDb) throws Exception {

        //Given
        when(clientsRepository.findByClientCode(clientCode)).thenReturn(clientsInDb);

        //When
        tradeService.postTrades(clientCode, trades);

        //Then
        if (clientsInDb == null) {
            verify(clientsRepository).saveAndFlush(any(Clients.class));
        }
        verify(instrumentsRepository).saveAllAndFlush(any(List.class));
    }


    private static Stream<Arguments> clientCodeAndInstrument() throws IOException {
        return Stream.of(
                Arguments.of("ABCD", buildInstruments("INS", buildClients()), buildClients())
        );
    }

    private static Clients buildClients() {
        return Clients.builder().clientCode("ABCD").build();
    }

    @ParameterizedTest
    @MethodSource("clientCodeAndInstrument")
    void getInstrumentPriceByClientCodeSuccessful_verifyOutcome(String clientCode, List<Instruments> instrumentsInDb, Clients clientsInDb) {
        //Given
        when(clientsRepository.findByClientCode(clientCode)).thenReturn(clientsInDb);
        when(instrumentsRepository.findByClients(clientsInDb)).thenReturn(instrumentsInDb);

        //When
        InstrumentPricesByClient instrumentPricesByClient = tradeService.getInstrumentPriceByClientCode(clientCode);

        //Then
        assertThat(instrumentPricesByClient.getClientCode(), is(clientCode));
        final InstrumentPriceDetails actual = instrumentPricesByClient.getInstrumentPriceDetails().get(0);
        assertThat(actual, hasProperty("instrumentCode", equalTo("INS")));
        assertThat(actual, hasProperty("price", is(11.12)));
        assertThat(actual, hasProperty("localDate", equalTo(LocalDate.now())));
    }

    @Test
    void getClientPricesByInstrumentCode() {
        //Given
        String instrumentCode = "INS";
        when(instrumentsRepository.findByInstrumentCode(anyString())).thenReturn(buildInstruments(instrumentCode, buildClients()));

        //When
        ClientPricesByInstrument actual = tradeService.getClientPricesByInstrumentCode(instrumentCode);

        //Then
        assertThat(actual.getInstrumentCode(), is(instrumentCode));
        assertThat(actual.getClientPriceDetails().size(), is(1));
        assertThat(actual.getClientPriceDetails().get(0), hasProperty("clientCode", equalTo("ABCD")));
        assertThat(actual.getClientPriceDetails().get(0), hasProperty("price", is(11.12)));
        assertThat(actual.getClientPriceDetails().get(0), hasProperty("localDate", equalTo(LocalDate.now())));

    }

    private static List<Trade> buildTrades() {
        return Arrays.asList(Trade.builder()
                .instrumentCode("ABB")
                .instrumentPrice(11.11)
                .build());

    }

    private static List<Instruments> buildInstruments(String code, Clients clients) {
        return Arrays.asList(
                Instruments.builder()
                        .instrumentCode(code)
                        .clients(clients)
                        .instrumentPrice(11.12)
                        .instrumentDate(LocalDate.now())
                        .build());

    }

}