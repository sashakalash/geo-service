import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {
    private final Location RUS_LOCATION = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    private final String MOSCOW_IP = "172.0.32.11";
    private final GeoService geoService;

    public GeoServiceTest() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void geoservice_by_ip_test() {
        Location actual = geoService.byIp(MOSCOW_IP);
        Location expected = RUS_LOCATION;
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}