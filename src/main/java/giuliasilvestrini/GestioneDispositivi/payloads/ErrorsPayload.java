package giuliasilvestrini.GestioneDispositivi.payloads;

import java.time.LocalDateTime;

public record ErrorsPayload(String message, LocalDateTime timestamp) {
}