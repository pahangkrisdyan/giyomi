package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.UkuranJumlahPenerimaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UkuranJumlahPenerimaanRepository extends JpaRepository<UkuranJumlahPenerimaan, Long> {
}
