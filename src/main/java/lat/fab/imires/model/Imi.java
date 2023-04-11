package lat.fab.imires.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imi {

    private Map<Integer, Integer> vars;
    private Date dateTime;
}
