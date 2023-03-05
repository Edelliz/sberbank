package sberbank.coreservice.utils.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@Component
public class LocalDateTimeConverter implements Converter {
    /**
     * Форматтер для дат "dd.MM.yyyy HH:mm:ss"
     */
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.forLanguageTag("ru"));

    @Override
    public String convert(Object columnValue) {
        TemporalAccessor date = (TemporalAccessor) columnValue;

        String formatDate = DATE_TIME_FORMAT.format(date);
        return StringUtils.capitalize(formatDate);
    }
}
