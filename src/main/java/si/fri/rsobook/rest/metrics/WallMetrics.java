package si.fri.rsobook.rest.metrics;

import com.codahale.metrics.Counter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WallMetrics {

    /*@Inject
    @Metric(name = "users_returned")*/
    private Counter usersReturned;


    public void addUsersReturned(int count){
        //usersReturned.inc(count);
    }

    public boolean isHealthy(){
        return true;
    }

}
