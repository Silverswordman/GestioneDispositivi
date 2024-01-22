package giuliasilvestrini.GestioneDispositivi.payloads;

import java.util.Date;
import java.util.List;

public record ErroreResponseListDTO(String message,
                                    Date timestamp,
                                    List<String> errorsList) {
}
