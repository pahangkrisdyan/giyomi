package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkVarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpkVarianRepository extends JpaRepository<SpkVarian, Long> {
    List<SpkVarian> findBySpkId(Long spkId);
}
