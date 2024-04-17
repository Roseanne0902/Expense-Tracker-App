package net.javaguides.expense.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(
        description = "ErrorDetails DTO (Data Transfer Object) to" +
                "transfer the data between the client and server"
)
public class ErrorDetails {
    @Schema(
            description = "Error occurred datetime"
    )
    private LocalDateTime timestamp;
    @Schema(
            description = "Error Message"
    )
    private String message;
    @Schema(
            description = "Error Details"
    )
    private String details;
    @Schema(
            description = "Error Code"
    )
    private String errorCode;
}
