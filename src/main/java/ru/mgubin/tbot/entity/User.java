package ru.mgubin.tbot.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.InputStream;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User
{
    int id;
    String name;
    String gender;
    String info;
    String crash;
    LocalDate birthday;
    InputStream image;

}
