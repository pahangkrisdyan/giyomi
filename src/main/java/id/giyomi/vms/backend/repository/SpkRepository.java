package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Spk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpkRepository extends JpaRepository<Spk, Long> {

}
