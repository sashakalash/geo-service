import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;

public class MessageSenderTest {
    private final String IP_ADDRESS_HEADER = "x-real-ip";
    private final String MOSCOW_IP = "172.0.32.11";
    private final String NEW_YORK_IP = "96.44.183.149";
    private final Location RUS_LOCATION = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    private final Location ENG_LOCATION = new Location("New York", Country.USA, " 10th Avenue", 32);
    private final GeoService geoService;
    private final LocalizationService localizationService;
    private final MessageSenderImpl messageSenderImpl;
    private final String RUS_LOCAL_RESPONSE = "Добро пожаловать";
    private final String ENG_LOCAL_RESPONSE = "Welcome";


    public MessageSenderTest() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);

        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(RUS_LOCATION);
        Mockito.when(geoService.byIp(NEW_YORK_IP))
                .thenReturn(ENG_LOCATION);

        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn(RUS_LOCAL_RESPONSE);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(ENG_LOCAL_RESPONSE);
    }

    @Test
    public void test_send_message() {

        Map<String, String> rusHeaders = new HashMap<String, String>();
        Map<String, String> engHeaders = new HashMap<String, String>();

        rusHeaders.put(IP_ADDRESS_HEADER, MOSCOW_IP);
        engHeaders.put(IP_ADDRESS_HEADER, NEW_YORK_IP);

        String rusLocale = messageSenderImpl.send(rusHeaders);
        String engLocale = messageSenderImpl.send(engHeaders);

        String russianExpected = RUS_LOCAL_RESPONSE;
        String engExpected = ENG_LOCAL_RESPONSE;

        Assertions.assertEquals(russianExpected, rusLocale);
        Assertions.assertEquals(engExpected, engLocale);
    }
}