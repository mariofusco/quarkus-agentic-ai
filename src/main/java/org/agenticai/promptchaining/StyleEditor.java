package org.agenticai.promptchaining;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface StyleEditor {

    @UserMessage("""
            You are a professional editor.
            Analyze and rewrite the following short novel to better fit and be more coherent with the {style} style.
            Return only the novel and nothing else. 
            The novel is "{novel}".
            """)
    String editNovel(String novel, String style);

}
