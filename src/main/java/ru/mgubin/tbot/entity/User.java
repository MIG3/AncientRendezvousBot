package ru.mgubin.tbot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.mgubin.tbot.enums.GenderButtonsEnum;
import ru.mgubin.tbot.enums.SearchButtonsEnum;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User
{
    Long id;
    String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate birthday;
    SearchButtonsEnum crush;
    GenderButtonsEnum gender;
    String description;

    /**
     * Метод преобразования сущности User в json формат
     * @return json пользователя
     */
    public String toJson()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public User jsonToUser(String user)
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(user, this.getClass());
    }
}
