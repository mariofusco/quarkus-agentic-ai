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

    private final CategoryRouter categoryRouter;

    private final LegalExpert legalExpert;

    private final MedicalExpert medicalExpert;

    private final TechnicalExpert technicalExpert;

    private final Map<RequestType, UnaryOperator<String>> experts = new HashMap<>();

    public RouterService(CategoryRouter categoryRouter, LegalExpert legalExpert, MedicalExpert medicalExpert,
                         TechnicalExpert technicalExpert) {
        this.categoryRouter = categoryRouter;
        this.legalExpert = legalExpert;
        this.medicalExpert = medicalExpert;
        this.technicalExpert = technicalExpert;

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
