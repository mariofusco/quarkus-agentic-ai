package org.agenticai.routing;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.function.UnaryOperator;

@ApplicationScoped
public class RouterService {

    private final CategoryRouter categoryRouter;

    private final LegalExpert legalExpert;

    private final MedicalExpert medicalExpert;

    private final TechnicalExpert technicalExpert;

    public RouterService(CategoryRouter categoryRouter, LegalExpert legalExpert, MedicalExpert medicalExpert,
                         TechnicalExpert technicalExpert) {
        this.categoryRouter = categoryRouter;
        this.legalExpert = legalExpert;
        this.medicalExpert = medicalExpert;
        this.technicalExpert = technicalExpert;
    }

    public UnaryOperator<String> findExpert(String request) {
        RequestCategory requestCategory = categoryRouter.classify(request);
        Log.infof("Detected request category: %s", requestCategory);
        return switch (requestCategory) {
            case LEGAL -> legalExpert::legalRequest;
            case MEDICAL -> medicalExpert::medicalRequest;
            case TECHNICAL -> technicalExpert::technicalRequest;
            default -> ignore -> "I cannot find an appropriate category for this request.";
        };
    }
}
