package pl.komoor.pcbuilder.payload.response.tools;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;

public class FileToDatabaseResponse extends SuccessResponse{

        private FileToDatabaseDTO imageDetails;


    public FileToDatabaseResponse(String message, FileToDatabaseDTO imageDetails) {
        super(message);
        this.imageDetails = imageDetails;
    }

    public FileToDatabaseResponse(FileToDatabaseDTO imageDetails) {
        this.imageDetails = imageDetails;
        addFullMessage("Pomyślnie pobrano obraz.");
    }


    public FileToDatabaseDTO getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(FileToDatabaseDTO imageDetails) {
        this.imageDetails = imageDetails;
    }
}

