package org.agenticai.routing;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService(chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
@ApplicationScoped
public interface CategoryRouter {

    @UserMessage("""
            Analyze the following user request and categorize it as 'legal', 'medical' or 'technical'.
            In case the request doesn't belong to any of those categories categorize it as 'unknown'.
            Reply with only one of those words and nothing else.
            The user request is: '{request}'.
            """)
    RequestCategory classify(String request);

}
