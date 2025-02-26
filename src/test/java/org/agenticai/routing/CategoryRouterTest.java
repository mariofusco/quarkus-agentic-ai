package org.agenticai.routing;

import io.quarkiverse.langchain4j.scorer.junit5.AiScorer;
import io.quarkiverse.langchain4j.testing.scorer.EvaluationReport;
import io.quarkiverse.langchain4j.testing.scorer.EvaluationSample;
import io.quarkiverse.langchain4j.testing.scorer.Samples;
import io.quarkiverse.langchain4j.testing.scorer.Scorer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@AiScorer
public class CategoryRouterTest {

    @Inject
    CategoryRouter categoryRouter;

    @Test
    @ActivateRequestContext
    void testCategoryRouter(Scorer scorer) {
        Samples<RequestCategory> samples = new Samples<>(
                EvaluationSample.<RequestCategory>builder()
                        .withName("Medical")
                        .withParameter("I broke my leg what should I do")
                        .withExpectedOutput(RequestCategory.MEDICAL)
                        .build(),
                EvaluationSample.<RequestCategory>builder()
                        .withName("Legal")
                        .withParameter("I have been sued for tax evasion")
                        .withExpectedOutput(RequestCategory.LEGAL)
                        .build()
        );

        EvaluationReport report = scorer.evaluate(samples,
                i -> categoryRouter.classify(i.get(0)),
                (EvaluationSample<RequestCategory> sample, RequestCategory output) -> sample.expectedOutput() == output);
        assertThat(report.score()).isEqualTo(100); // Expect full success
    }
}
