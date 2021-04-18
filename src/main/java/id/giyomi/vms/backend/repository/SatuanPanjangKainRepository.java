package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.SatuanPanjangKain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatuanPanjangKainRepository extends JpaRepository<SatuanPanjangKain, Long> {
}