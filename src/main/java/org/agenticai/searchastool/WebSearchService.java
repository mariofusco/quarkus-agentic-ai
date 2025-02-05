package org.agenticai.searchastool;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;

import java.io.IOException;

@ApplicationScoped
public class WebSearchService {

    @Tool("Perform a web search to retrieve information online")
    String webSearch(String q) throws IOException {
        String webUrl = "https://html.duckduckgo.com/html/?q=" + q;
        String text = Jsoup.connect(webUrl).get().text();
        Log.info("Parsed html: " + text);
        return text;
    }
}
