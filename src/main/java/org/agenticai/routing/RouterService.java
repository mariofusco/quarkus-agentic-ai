package org.agenticai.routing;

import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

@ApplicationScoped
public class RouterService {

    @Inject
    CategoryRouter categoryRouter;

    @Inject
    LegalExpert legalExpert;

    @Inject
    MedicalExpert medicalExpert;

    @Inject
    TechnicalExpert technicalExpert;

    private final Map<RequestType, UnaryOperator<String>> experts = new HashMap<>();

    @PostConstruct
    public void init() {
        experts.put(RequestType.LEGAL, legalExpert::chat);
        experts.put(RequestType.MEDICAL, medicalExpert::chat);
        experts.put(RequestType.TECHNICAL, technicalExpert::chat);
        experts.put(RequestType.UNKNOWN, ignore -> "I cannot find an appropriate category for this request.");
    }

    public UnaryOperator<String> findExpert(String request) {
        String category = categoryRouter.classify(request);
        Log.infof("Detected request category: %s", category);
        return experts.get(RequestType.decode(category));
    }

    public enum RequestType {
        LEGAL, MEDICAL, TECHNICAL, UNKNOWN;

        public static RequestType decode(String category) {
            try {
                return RequestType.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                return RequestType.UNKNOWN;
            }
        }
    }
}
