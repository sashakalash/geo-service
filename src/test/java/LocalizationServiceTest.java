import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceTest {
    private final LocalizationService localizationService;
    private final String RUS_LOCAL_RESPONSE = "Добро пожаловать";

    public LocalizationServiceTest() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void locale_test() {
        String actual = localizationService.locale(RUSSIA);
        String expected = RUS_LOCAL_RESPONSE;
        assertThat(actual).isEqualTo(expected);
    }
}