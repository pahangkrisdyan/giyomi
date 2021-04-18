package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.Hak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatatanRepository extends JpaRepository<Catatan, Long> {

}
