package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.SpkProses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpkProsesRepository extends JpaRepository<SpkProses, Long> {
}
