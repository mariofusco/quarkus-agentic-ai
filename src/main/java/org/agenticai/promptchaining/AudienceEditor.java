package org.agenticai.promptchaining;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface AudienceEditor {

    @UserMessage("""
            You are a professional editor.
            Analyze and rewrite the following short novel to better align with the target audience of {audience}.
            Return only the novel and nothing else. 
            The novel is "{novel}".
            """)
    String editNovel(String novel, String audience);

}
