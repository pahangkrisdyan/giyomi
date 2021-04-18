package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Varian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianRepository extends JpaRepository<Varian, Long> {
}
