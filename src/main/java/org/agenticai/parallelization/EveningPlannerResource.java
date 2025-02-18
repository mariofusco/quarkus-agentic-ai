package org.agenticai.parallelization;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Path("/evening")
public class EveningPlannerResource {

    private final MovieExpert movieExpert;

    private final FoodExpert foodExpert;

    private final Vertx vertx;

    public EveningPlannerResource(MovieExpert movieExpert, FoodExpert foodExpert, Vertx vertx) {
        this.movieExpert = movieExpert;
        this.foodExpert = foodExpert;
        this.vertx = vertx;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("mood/{mood}")
    public List<EveningPlan> plan(String mood) {
        Executor scheduler = r -> vertx.getDelegate().executeBlocking(() -> {
            r.run();
            return null;
        }, false);

        return Uni.combine().all()
                .unis(Uni.createFrom().item(() -> movieExpert.findMovie(mood)).runSubscriptionOn(scheduler),
                      Uni.createFrom().item(() -> foodExpert.findMeal(mood)).runSubscriptionOn(scheduler))
                .with((movies, meals) -> {
                    List<EveningPlan> moviesAndMeals = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        Log.infof("Movie #%d: %s", i+1, movies.get(i));
                        Log.infof("Meal #%d: %s", i+1, meals.get(i));
                        moviesAndMeals.add(new EveningPlan(movies.get(i), meals.get(i)));
                    }
                    return moviesAndMeals;
                })
                .await()
                .indefinitely();
    }

    public record EveningPlan(String movie, String meal) { }
}
