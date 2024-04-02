package rest;

import java.lang.invoke.MethodHandles;
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

public class TrainingResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Path("{id}")
    @Produces("application/json")
    public TrainingEntity find(@PathParam("id") Long id) {
        logger.info("Getting training by id " + id);
        return null;
        // TODO trainingService.findById(id)
            //.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }


    public List<TrainingEntity> findAll() {
        logger.info("Getting all trainings");
        return null;
    }

    public TrainingEntity create(TrainingEntity TrainingEntity) {
        logger.info("Creating training " + TrainingEntity.getTitle());
        try{
            //TODO
            return null;
        }catch (PersistenceException ex){
            logger.info("Error creating training " + TrainingEntity.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }


    public void delete() {
        //logger.info("Deleting training by id " + id);
        try{
            //TODO
        }catch (IllegalArgumentException e){
            //logger.info("Error deleting TrainingEntity by id " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public TrainingEntity update(TrainingEntity TrainingEntity) {
        logger.info("Updating TrainingEntity " + TrainingEntity.getTitle());
        try{
            //TODO
            return null;
        }catch (PersistenceException ex){
            logger.info("Error updating TrainingEntity " + TrainingEntity.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}