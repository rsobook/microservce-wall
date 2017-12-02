package si.fri.rsobook.rest;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import si.fri.rsobook.config.WallApiConfigProperties;
import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.UserWall;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.service.DatabaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Path("Wall")
public class WallResource extends CrudResource<UUID, UserWall> {

    @Inject
    private WallApiConfigProperties wallApiConfigProperties;

    @Inject
    private DatabaseService databaseService;

    @Inject
    @Metric(name = "walls_returned")
    private Counter wallsReturnedCounter;


    public WallResource() {
        super(UserWall.class);
    }

    @PostConstruct
    private void postInit() {
        if(wallApiConfigProperties.getMaxWallPosts() != null){
            defaultMaxLimit = wallApiConfigProperties.getMaxWallPosts();
        }
    }

    @Log
    @Override
    public Response getList() throws BusinessLogicTransactionException {
        Response res = super.getList();

        List<UserWall> userWallList = (List<UserWall>) res.getEntity();
        wallsReturnedCounter.inc(userWallList.size());

        return res;
    }

    @Override
    protected UUID parseId(String s) {
        return UUID.fromString(s);
    }

    @Override
    protected AuthEntity getAuthorizedEntity() {
        return null;
    }

    @Override
    protected DatabaseImpl getDatabaseService() {
        return databaseService;
    }
}
