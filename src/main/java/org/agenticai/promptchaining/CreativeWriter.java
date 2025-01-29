package org.agenticai.promptchaining;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface CreativeWriter {

    @UserMessage("""
            You are a creative writer.
            Generate a draft of a short novel around the given topic. 
            Return only the novel and nothing else. 
            The topic is {topic}.
            """)
    String generateNovel(String topic);

}
