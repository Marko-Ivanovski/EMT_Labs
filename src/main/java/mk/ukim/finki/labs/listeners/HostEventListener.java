package mk.ukim.finki.labs.listeners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mk.ukim.finki.labs.events.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HostEventListener {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener
    @Transactional
    public void handleHostCreated(HostCreatedEvent event) {
        refreshMaterializedView();
    }

    @EventListener
    @Transactional
    public void handleHostUpdated(HostUpdatedEvent event) {
        refreshMaterializedView();
    }

    @EventListener
    @Transactional
    public void handleHostDeleted(HostDeletedEvent event) {
        refreshMaterializedView();
    }

    private void refreshMaterializedView() {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW hosts_per_country").executeUpdate();
        System.out.println("[EVENT] Refreshed materialized view: hosts_per_country");
    }
}
