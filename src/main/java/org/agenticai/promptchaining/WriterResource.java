package org.agenticai.promptchaining;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/write")
public class WriterResource {

    @Inject
    CreativeWriter creativeWriter;

    @Inject
    StyleEditor styleEditor;

    @Inject
    AudienceEditor audienceEditor;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("topic/{topic}/style/{style}/audience/{audience}")
    public String hello(String topic, String style, String audience) {
        Log.infof("Generate a novel about %s in style %s and for %s audience", topic, style, audience);
        String novel = creativeWriter.generateNovel(topic);
        Log.infof("First draft novel: %s", novel);
        novel = styleEditor.editNovel(novel, style);
        Log.infof("Novel in style %s: %s", style, novel);
        novel = audienceEditor.editNovel(novel, audience);
        Log.infof("Final novel: %s", novel);
        return novel;
    }
}
