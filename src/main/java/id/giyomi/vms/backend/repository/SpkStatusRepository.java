package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.SpkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpkStatusRepository extends JpaRepository<SpkStatus, Long> {
    List<SpkStatus> findBySpkId(Long spkId);
}
