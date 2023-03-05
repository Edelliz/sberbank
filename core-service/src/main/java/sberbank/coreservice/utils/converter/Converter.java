package sberbank.coreservice.utils.converter;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface Converter {
    /**
     * Конвертирует значение
     */
    String convert(@NotNull Object columnValue);
}
