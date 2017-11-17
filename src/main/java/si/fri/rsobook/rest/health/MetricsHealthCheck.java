package si.fri.rsobook.rest.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rsobook.rest.metrics.WallMetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class MetricsHealthCheck  implements HealthCheck {

    @Inject
    private WallMetrics wallMetrics;

    @Override
    public HealthCheckResponse call() {

        if(!wallMetrics.isHealthy()){
            return HealthCheckResponse.named(MetricsHealthCheck.class.getSimpleName()).down().build();
        }

        return HealthCheckResponse.named(MetricsHealthCheck.class.getSimpleName()).up().build();
    }

}
