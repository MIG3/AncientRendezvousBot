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
public class PersToPers
{
    Integer userId;
    Integer crushId;

    public String toJson()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e)
        {
            throw new ParseToJsonException();
        }
    }
}
