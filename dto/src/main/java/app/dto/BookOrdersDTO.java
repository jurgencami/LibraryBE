package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrdersDTO {
    private Long id;

    private LocalDate order_date;
    private int approved;
    private String name;
    private String username;
}
