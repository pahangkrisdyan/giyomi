package id.giyomi.vms.backend.repository;

import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.NilaiSpk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NilaiSpkRepository extends JpaRepository<NilaiSpk, Long> {
    NilaiSpk findNilaiSpkBySpkId(Long spkId);
}