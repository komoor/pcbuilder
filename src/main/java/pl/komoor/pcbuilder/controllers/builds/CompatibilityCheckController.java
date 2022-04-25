package pl.komoor.pcbuilder.controllers.builds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.payload.request.builds.CompatibilityCheckRequest;
import pl.komoor.pcbuilder.payload.response.CompatibilityCheckResponse;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.builds.CompatibilityCheckService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/compatibility")
public class CompatibilityCheckController {

    @Autowired
    CompatibilityCheckService compatibilityCheckService;


    @PostMapping("/check")
    public ResponseEntity<AppResponse> compatibilityCheck(@RequestBody CompatibilityCheckRequest compatibilityCheckRequest) {

        List<String> cmpList = compatibilityCheckService.compatibilityCheck(compatibilityCheckRequest);

        if(!cmpList.isEmpty())
            return new ResponseEntity<>(new CompatibilityCheckResponse(cmpList), HttpStatus.OK);

        return new ResponseEntity<>(new ErrorResponse("Brak błędów kompatybilności"), HttpStatus.OK);


    }


}
