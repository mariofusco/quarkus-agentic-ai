package org.agenticai.parallelization;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

import java.util.List;

@RegisterAiService
public interface MovieExpert {

    @UserMessage("""
            You are a great evening planner.
            Propose a list of 3 movies matching the given mood.
            The mood is {mood}.
            Provide a list with the 3 items and nothing else.
            """)
    List<String> findMovie(String mood);
}
