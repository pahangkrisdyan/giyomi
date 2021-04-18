package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.JenisKoleksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisKoleksiRepository extends JpaRepository<JenisKoleksi, Long> {
}
