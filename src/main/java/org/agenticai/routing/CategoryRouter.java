package org.agenticai.routing;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface CategoryRouter {

    @UserMessage("""
            Analyze the following user request and categorize it as 'legal', 'medical' or 'technical'.
            Reply with only one of those words and nothing else.
            The user request is {request}.
            """)
    String classify(String request);

}
