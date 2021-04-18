package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Koleksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface KoleksiRepository extends JpaRepository<Koleksi, Long> {
    Set<Koleksi> findByNama(String nama);
}
