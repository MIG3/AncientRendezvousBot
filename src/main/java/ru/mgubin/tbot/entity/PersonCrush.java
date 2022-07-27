package ru.mgubin.tbot.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mgubin.tbot.exception.ParseToJsonException;

/**
 * Сущность связей между клиентами
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonCrush {
    Long id;
    Long userId;
    Long crushId;

    public PersonCrush(Long userId, Long crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new ParseToJsonException();
        }
    }
}
