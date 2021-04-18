package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByNama(String nama);
}
