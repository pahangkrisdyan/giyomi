package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Proses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProsesRepository extends JpaRepository<Proses, Long> {
//    Boolean existsByNamaAndDurasiShortAndDurasiLong(String nama);
//    Set<Proses> findByNamaAndDurasiShortAndDurasiLong(String nama);
}
