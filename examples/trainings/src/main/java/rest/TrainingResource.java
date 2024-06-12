package rest;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.TrainingEntity;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import services.TrainingService;

@Path("trainings")
public class TrainingResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    TrainingService trainingService;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public TrainingEntity find(@PathParam("id") Long id) {
        logger.info("Getting training by id " + id);
        return trainingService.findById(id)
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }


    @GET
    @Produces("application/json")
    public List<TrainingEntity> findAll() {
        logger.info("Getting all trainings");
        return trainingService.findAll();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public TrainingEntity create(TrainingEntity TrainingEntity) {
        logger.info("Creating training " + TrainingEntity.getTitle());
        try{
            return trainingService.create(TrainingEntity);
        }catch (PersistenceException ex){
            logger.info("Error creating training " + TrainingEntity.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }


    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        logger.info("Deleting training by id " + id);
        try{
            trainingService.delete(id);
        }catch (IllegalArgumentException e){
            logger.info("Error deleting TrainingEntity by id " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public TrainingEntity update(TrainingEntity TrainingEntity) {
        logger.info("Updating TrainingEntity " + TrainingEntity.getTitle());
        try{
            return trainingService.update(TrainingEntity);
        }catch (PersistenceException ex){
            logger.info("Error updating TrainingEntity " + TrainingEntity.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}