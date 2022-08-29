package com.dmi_sauron.repositories;

import com.dmi_sauron.models.NinjoServerModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Nedarver fra JpaRepository klassen (bibliotek af metoder, som vi gør brug af)
// Long refererer til ID i NinjoServerModel
public interface NinjoServiceRepository extends JpaRepository<NinjoServerModel, Long>{ }

