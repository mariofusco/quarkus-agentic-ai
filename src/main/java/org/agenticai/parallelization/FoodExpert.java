package org.agenticai.parallelization;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

import java.util.List;

@RegisterAiService
public interface FoodExpert {

    @UserMessage("""
            You are a great evening planner.
            Propose a list of 3 meals matching the given mood.
            The mood is {mood}.
            For each meal, just give the name of the meal.
            Provide a list with the 3 items and nothing else.
            """)
    List<String> findMeal(String mood);

}
