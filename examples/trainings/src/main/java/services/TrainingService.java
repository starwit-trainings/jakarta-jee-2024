package services;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import entities.TrainingEntity;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class TrainingService implements Serializable {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    EntityManager em;

    public TrainingEntity create(TrainingEntity entity) {
        logger.info("Creating training " + entity.getTitle());
        em.persist(entity);

        return entity;
    }

    public TrainingEntity updateByTitle(TrainingEntity entity) {
        logger.info("Updating training with same title" + entity.getTitle());
        TypedQuery<TrainingEntity> query = em.createQuery("SELECT c FROM TrainingEntity c WHERE c.title like :title", TrainingEntity.class);
        query.setParameter("title", entity.getTitle());
        List<TrainingEntity> trainings = query.getResultList();
        if (trainings != null && !trainings.isEmpty()){
            TrainingEntity fromDB = trainings.get(0);
            fromDB.setCategory(entity.getCategory());
            fromDB.setDurationInDays(entity.getDurationInDays());
            fromDB.setMaxParticipants(entity.getMaxParticipants());
            fromDB.setMinParticipants(entity.getMinParticipants());
            fromDB.setTrainer(entity.getTrainer());
            em.persist(fromDB);
            return fromDB;
        } 
        em.persist(entity);
        return entity;
    }

    public List<TrainingEntity> findAll() {
        logger.info("Getting all training");
        return em.createQuery("SELECT c FROM TrainingEntity c", TrainingEntity.class).getResultList();
    }

    public Optional<TrainingEntity> findById(Long id) {
        logger.info("Getting training by id " + id);
        return Optional.ofNullable(em.find(TrainingEntity.class, id));
    }

    public void delete(Long id) {
        logger.info("Deleting training by id " + id);
        var entity = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid training Id:" + id));
        em.remove(entity);
    }

    public TrainingEntity update(TrainingEntity entity) {
        logger.info("Updating training " + entity.getTitle());
        return em.merge(entity);
    }
}
