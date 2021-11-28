import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceTest {
    private final Location RUS_LOCATION = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    private final String MOSCOW_IP = "172.0.32.11";
    private final GeoService geoService;

    public GeoServiceTest() {
        geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(RUS_LOCATION);
    }

    @Test
    public void geoservice_by_ip_test() {
        Location location = geoService.byIp(MOSCOW_IP);
        Location expected = RUS_LOCATION;
        Assertions.assertEquals(expected, location);
    }
}