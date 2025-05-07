package org.agenticai.routing;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService(modelName = "tool-use")
@ApplicationScoped
public interface ExpertsSelectorAgent {

    @UserMessage("""
            Analyze the following user request and categorize it as 'legal', 'medical' or 'technical',
            then forward the request as it is to the corresponding expert provided as a tool.
            Finally return the answer that you received from the expert without any modification.

            The user request is: '{request}'.
            """)
    @ToolBox({MedicalExpert.class, LegalExpert.class, TechnicalExpert.class})
    String askToExpert(String request);

}
