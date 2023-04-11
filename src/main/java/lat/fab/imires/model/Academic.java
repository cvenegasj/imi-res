package lat.fab.imires.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Academic {

    @Id
    private String id;
    private String email;
    private String companyName;
    private String type;
    private String description;
    private String phone;
    private String website;
    private List<String> extraUrls;
    private List<String> countries;
    private List<Imi> imis;
}
