package pl.komoor.pcbuilder.services.builds;

import pl.komoor.pcbuilder.payload.request.builds.CompatibilityCheckRequest;

import java.util.List;

public interface CompatibilityCheckService {

    List<String> compatibilityCheck(CompatibilityCheckRequest compatibilityCheckRequest);


}
