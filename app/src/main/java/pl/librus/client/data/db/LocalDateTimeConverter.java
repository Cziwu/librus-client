package pl.librus.client.data.db;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import io.requery.Converter;

/**
 * Created by robwys on 04/02/2017.
 */

public class LocalDateTimeConverter implements Converter<LocalDateTime, Long> {

    @Override
    public Class<LocalDateTime> getMappedType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<Long> getPersistedType() {
        return Long.class;
    }

    @Override
    public Integer getPersistedSize() {
        return null;
    }

    @Override
    public Long convertToPersisted(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toDateTime(DateTimeZone.UTC).getMillis();

    }

    @Override
    public LocalDateTime convertToMapped(Class<? extends LocalDateTime> type, Long value) {
        if (value == null) {
            return null;
        }
        return new LocalDateTime(value, DateTimeZone.UTC);
    }
}