package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.UkuranJumlah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UkuranJumlahRepository extends JpaRepository<UkuranJumlah, Long> {
}
