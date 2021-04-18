package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.PanjangKain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanjangKainRepository extends JpaRepository<PanjangKain, Long> {
}
