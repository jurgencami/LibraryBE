package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderDTO {
    private Long id;

    private String orderdate;
    private int approved;
    private Long book_id;
    private Long user_id;

}
