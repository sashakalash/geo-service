import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;

import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceTest {
    private final LocalizationService localizationService;
    private final String RUS_LOCAL_RESPONSE = "Добро пожаловать";

    public LocalizationServiceTest() {
        localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn(RUS_LOCAL_RESPONSE);
    }

    @Test
    public void locale_test() {
        String greeting = localizationService.locale(RUSSIA);
        String expected = RUS_LOCAL_RESPONSE;
        Assertions.assertEquals(expected, greeting);
    }
}