package com.dmi_sauron.repositories;
import com.dmi_sauron.models.NinjoServerModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Nedarver fra JpaRepository klassen (bibliotek af metoder, som vi gør brug af)
// Integer refererer til ID i Sogn (kunne også være en Long, men så også skal være det samme i modellen)
public interface NinjoServiceRepository extends JpaRepository<NinjoServerModel, Integer>{
}

