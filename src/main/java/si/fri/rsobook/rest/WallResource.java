package si.fri.rsobook.rest;

import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.UserWall;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.rest.config.WallApiConfigProperties;
import si.fri.rsobook.rest.service.DatabaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.UUID;

@RequestScoped
@Path("Wall")
public class WallResource extends CrudResource<UUID, UserWall> {

    @Inject
    private WallApiConfigProperties wallApiConfigProperties;

    @Inject
    private DatabaseService databaseService;

    public WallResource() {
        super(UserWall.class);
    }

    @PostConstruct
    private void postInit() {
        if(wallApiConfigProperties.getMaxWallPosts() != null){
            defaultMaxLimit = wallApiConfigProperties.getMaxWallPosts();
        }
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
