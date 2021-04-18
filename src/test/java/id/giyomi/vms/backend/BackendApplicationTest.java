package id.giyomi.vms.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.giyomi.vms.backend.controller.rest.model.AutoFillPenilaianVendor;
import id.giyomi.vms.backend.controller.rest.model.PenilaianSpk;
import id.giyomi.vms.backend.controller.rest.model.SpkStatusEnum;
import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailProsesReq;
import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailUkuranJumlahReq;
import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailVarianReq;
import id.giyomi.vms.backend.controller.rest.model.spk.request.SpkRequestModel;
import id.giyomi.vms.backend.controller.rest.model.spk.response.SpkResponseModel;
import id.giyomi.vms.backend.controller.restful.RestfulSpkProsesController;
import id.giyomi.vms.backend.controller.restful.RestfulVendorController;
import id.giyomi.vms.backend.controller.restful.model.SpkProsesRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.UkuranJumlahPenerimaanRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.VendorRestfulModel;
import id.giyomi.vms.backend.entity.*;
import id.giyomi.vms.backend.repository.*;
import id.giyomi.vms.backend.service.SpkService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.naming.OperationNotSupportedException;
import java.util.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = {
        "spring.jpa.hibernate.ddl-auto = create",
        "spring.datasource.url = jdbc:mysql://localhost:3306/giyomi_test?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false",
        "spring.datasource.username = root",
        "spring.datasource.password =",
        "spring.datasource.hikari.maximum-pool-size=10",
})
public class BackendApplicationTest {
    @Autowired
    private SpkService spkService;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private JenisKoleksiRepository jenisKoleksiRepository;
    @Autowired
    private KoleksiRepository koleksiRepository;
    @Autowired
    private VarianRepository varianRepository;
    @Autowired
    private UkuranRepository ukuranRepository;
    @Autowired
    private SatuanPanjangKainRepository satuanPanjangKainRepository;
    @Autowired
    private ProsesRepository prosesRepository;
    @Autowired
    private SpkRepository spkRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private SpkProsesRepository spkProsesRepository;
    @Autowired
    private SpkStatusRepository spkStatusRepository;
    @Autowired
    private CatatanRepository catatanRepository;
    @Autowired
    private RestfulVendorController restfulVendorController;
    @Autowired
    private UkuranJumlahPenerimaanRepository ukuranJumlahPenerimaanRepository;
    @Autowired
    private NilaiSpkRepository nilaiSpkRepository;
    @Autowired
    private RestfulSpkProsesController restfulSpkProsesController;

    private static final String NAMA_VENDOR = "Pak Nopal";
    private static final String ALAMAT_VENDOR = "Jalan Pak Nopal Nomor 5";
    private static final String EMAIL_VENDOR = "paknopal@gmail.com";
    private static final String NO_TELP_VENDOR = "081231684642";
    private static final String CATATAN_1 = "Proses ini sudah benar";
    private static final String CATATAN_2 = "Proses ini sudah rapi";
    private static final String PROSES_CUTTING = "cutting";
    private static final String PROSES_JAHIT = "jahit";
    private static final String PROSES_FINISHING = "finishing";
    private static final String STATUS_DRAFT = "DRAFT";
    private static final String STATUS_REQ_VAL = "REQ_VAL";
    private static final String STATUS_ON_GOING = "ON_GOING";
    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final String STATUS_PENDING = "PENDING";
    private static final String UKURAN_S = "s";
    private static final String UKURAN_M = "m";
    private static final String UKURAN_L = "l";
    private static final String UKURAN_XL = "xl";
    private static final String UKURAN_XXL = "xxl";
    private static final String JENIS_KOLEKSI_ATASAN = "atasan";
    private static final String KOLEKSI_AMERAA_SWEATER = "ameraa sweater";
    private static final String VARIAN_AMERAA_MAROON = "amera maroon";
    private static final String VARIAN_AMERAA_BIRU = "amera biru";
    private static final String VARIAN_AMERAA_HITAM = "amera hitam";
    private static final Double VARIAN_AMERAA_MAROON_PANJANG_KAIN = 200D;
    private static final Double VARIAN_AMERAA_BIRU_PANJANG_KAIN = 200D;
    private static final Double VARIAN_AMERAA_HITAM_PANJANG_KAIN = 200D;
    private static final String SATUAN_PANJANG_M = "m";
    private static final Integer DURASI_RENCANA_CUTTING = 3;
    private static final Integer DURASI_RENCANA_JAHIT = 5;
    private static final Integer DURASI_RENCANA_FINISHING = 4;
    private static final Integer DURASI_NYATA_CUTTING = 3;
    private static final Integer DURASI_NYATA_JAHIT = 5;
    private static final Integer DURASI_NYATA_FINISHING = 4;
    private static final Integer VARIAN_AMERAA_MAROON_S = 15;
    private static final Integer VARIAN_AMERAA_MAROON_M = 15;
    private static final Integer VARIAN_AMERAA_MAROON_L = 10;
    private static final Integer VARIAN_AMERAA_MAROON_XL = 5;
    private static final Integer VARIAN_AMERAA_MAROON_XXL = 5;
    private static final Integer VARIAN_AMERAA_BIRU_S = 15;
    private static final Integer VARIAN_AMERAA_BIRU_M = 15;
    private static final Integer VARIAN_AMERAA_BIRU_L = 10;
    private static final Integer VARIAN_AMERAA_BIRU_XL = 5;
    private static final Integer VARIAN_AMERAA_BIRU_XXL = 5;
    private static final Integer VARIAN_AMERAA_HITAM_M = 15;
    private static final Integer VARIAN_AMERAA_HITAM_L = 20;
    private static final Integer VARIAN_AMERAA_HITAM_XL = 10;
    private static final Integer VARIAN_AMERAA_HITAM_XXL = 5;
    @Test
    @Order(1)
    void createVendor(){
        restfulVendorController.create(new VendorRestfulModel(ALAMAT_VENDOR, EMAIL_VENDOR, NAMA_VENDOR, NO_TELP_VENDOR));
        Vendor vendor = getLastVendor();
        assertEquals(NAMA_VENDOR, vendor.getNama());
        assertEquals(ALAMAT_VENDOR, vendor.getAlamat());
        assertEquals(EMAIL_VENDOR, vendor.getEmail());
        assertEquals(NO_TELP_VENDOR, vendor.getTelepon());
    }

    @Test
    @Order(2)
    void createSpk() throws JsonProcessingException {
        JenisKoleksi jenisKoleksi = jenisKoleksiRepository.save(new JenisKoleksi(JENIS_KOLEKSI_ATASAN));
        Koleksi koleksi = koleksiRepository.save(new Koleksi(KOLEKSI_AMERAA_SWEATER, null, jenisKoleksi, "Detail aksesoris lorem ipsum dolor sit amet."));
        Ukuran s = ukuranRepository.save(new Ukuran(UKURAN_S));
        Ukuran m = ukuranRepository.save(new Ukuran(UKURAN_M));
        Ukuran l = ukuranRepository.save(new Ukuran(UKURAN_L));
        Ukuran xl = ukuranRepository.save(new Ukuran(UKURAN_XL));
        Ukuran xxl = ukuranRepository.save(new Ukuran(UKURAN_XXL));
        SatuanPanjangKain yard = satuanPanjangKainRepository.save(new SatuanPanjangKain(SATUAN_PANJANG_M));
        Proses cutting =  prosesRepository.save(new Proses(PROSES_CUTTING));
        Proses jahit =  prosesRepository.save(new Proses(PROSES_JAHIT));
        Proses finishing =  prosesRepository.save(new Proses(PROSES_FINISHING));
        Status draft = statusRepository.save(new Status(STATUS_DRAFT));
        Status validated = statusRepository.save(new Status(STATUS_REQ_VAL));
        Status onGoing = statusRepository.save(new Status(STATUS_ON_GOING));
        Status completed = statusRepository.save(new Status(STATUS_COMPLETED));
        Status pending = statusRepository.save(new Status(STATUS_PENDING));

        ArrayList<DetailVarianReq> detailVarianReqs = new ArrayList<>();

        Varian v1 = varianRepository.save(new Varian(koleksi, VARIAN_AMERAA_MAROON));
        ArrayList<DetailUkuranJumlahReq> v1Details = new ArrayList<>();
        v1Details.add(new DetailUkuranJumlahReq(s.getId(), VARIAN_AMERAA_MAROON_S));
        v1Details.add(new DetailUkuranJumlahReq(m.getId(), VARIAN_AMERAA_MAROON_M));
        v1Details.add(new DetailUkuranJumlahReq(l.getId(), VARIAN_AMERAA_MAROON_L));
        v1Details.add(new DetailUkuranJumlahReq(xl.getId(), VARIAN_AMERAA_MAROON_XL));
        v1Details.add(new DetailUkuranJumlahReq(xxl.getId(), VARIAN_AMERAA_MAROON_XXL));
        detailVarianReqs.add(new DetailVarianReq(v1.getId(), v1Details, VARIAN_AMERAA_MAROON_PANJANG_KAIN, yard.getId()));

        Varian v2 = varianRepository.save(new Varian(koleksi, VARIAN_AMERAA_BIRU));
        ArrayList<DetailUkuranJumlahReq> v2Details = new ArrayList<>();
        v2Details.add(new DetailUkuranJumlahReq(s.getId(), VARIAN_AMERAA_BIRU_S));
        v2Details.add(new DetailUkuranJumlahReq(m.getId(), VARIAN_AMERAA_BIRU_M));
        v2Details.add(new DetailUkuranJumlahReq(l.getId(), VARIAN_AMERAA_BIRU_L));
        v2Details.add(new DetailUkuranJumlahReq(xl.getId(), VARIAN_AMERAA_BIRU_XL));
        v2Details.add(new DetailUkuranJumlahReq(xxl.getId(), VARIAN_AMERAA_BIRU_XXL));
        detailVarianReqs.add(new DetailVarianReq(v2.getId(), v2Details, VARIAN_AMERAA_BIRU_PANJANG_KAIN, yard.getId()));

        Varian v3 = varianRepository.save(new Varian(koleksi, VARIAN_AMERAA_HITAM));
        ArrayList<DetailUkuranJumlahReq> v3Details = new ArrayList<>();
        v3Details.add(new DetailUkuranJumlahReq(m.getId(), VARIAN_AMERAA_HITAM_M));
        v3Details.add(new DetailUkuranJumlahReq(l.getId(), VARIAN_AMERAA_HITAM_L));
        v3Details.add(new DetailUkuranJumlahReq(xl.getId(), VARIAN_AMERAA_HITAM_XL));
        v3Details.add(new DetailUkuranJumlahReq(xxl.getId(), VARIAN_AMERAA_HITAM_XXL));
        detailVarianReqs.add(new DetailVarianReq(v3.getId(), v3Details, VARIAN_AMERAA_HITAM_PANJANG_KAIN, yard.getId()));


        ArrayList<DetailProsesReq> detailProsesReqs = new ArrayList<>();
        detailProsesReqs.add(new DetailProsesReq(cutting.getId(), DURASI_RENCANA_CUTTING));
        detailProsesReqs.add(new DetailProsesReq(jahit.getId(), DURASI_RENCANA_JAHIT));
        detailProsesReqs.add(new DetailProsesReq(finishing.getId(), DURASI_RENCANA_FINISHING));


        SpkRequestModel spkRequestModel = new SpkRequestModel(getLastVendor().getId(), detailVarianReqs, detailProsesReqs, "https://d1icd6shlvmxi6.cloudfront.net/gsc/PWA7YD/ec/c1/e5/ecc1e51d12a74754bd7f922c7903be7a/images/draft_sahara_-_sahara_2/u182.png?token=a6a53c39c62ed8bb158dff0d9e4d004f815a9a09e7e1ebfeefb46ff884d14646");

        SpkResponseModel spkResponseModel = spkService.createSpk(spkRequestModel, SpkStatusEnum.DRAFT);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(spkResponseModel);
        System.out.println(jsonString);
        ////CHECK

        Spk spk = getLastSpk();

        spk.getSpkVarians().forEach(spkVarian -> {
            assertEquals(yard.getId(), spkVarian.getPanjangKain().getSatuanPanjangKain().getId());
            switch (spkVarian.getVarian().getVarian()){
                case VARIAN_AMERAA_MAROON : {
                    assertEquals(VARIAN_AMERAA_MAROON_PANJANG_KAIN, spkVarian.getPanjangKain().getPanjang());
                    spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
                        switch (ukuranJumlah.getUkuran().getNama()){
                            case UKURAN_S: assertEquals(VARIAN_AMERAA_MAROON_S, ukuranJumlah.getJumlah()); break;
                            case UKURAN_M: assertEquals(VARIAN_AMERAA_MAROON_M, ukuranJumlah.getJumlah()); break;
                            case UKURAN_L: assertEquals(VARIAN_AMERAA_MAROON_L, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XL: assertEquals(VARIAN_AMERAA_MAROON_XL, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XXL: assertEquals(VARIAN_AMERAA_MAROON_XXL, ukuranJumlah.getJumlah()); break;
                            default: throw new Error("Ukuran with name \"" + ukuranJumlah.getUkuran().getNama() + "\" invalid!");
                        }
                    });
                } break;
                case VARIAN_AMERAA_BIRU : {
                    assertEquals(VARIAN_AMERAA_BIRU_PANJANG_KAIN, spkVarian.getPanjangKain().getPanjang());
                    spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
                        switch (ukuranJumlah.getUkuran().getNama()){
                            case UKURAN_S: assertEquals(VARIAN_AMERAA_BIRU_S, ukuranJumlah.getJumlah()); break;
                            case UKURAN_M: assertEquals(VARIAN_AMERAA_BIRU_M, ukuranJumlah.getJumlah()); break;
                            case UKURAN_L: assertEquals(VARIAN_AMERAA_BIRU_L, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XL: assertEquals(VARIAN_AMERAA_BIRU_XL, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XXL: assertEquals(VARIAN_AMERAA_BIRU_XXL, ukuranJumlah.getJumlah()); break;
                            default: throw new Error("Ukuran with name \"" + ukuranJumlah.getUkuran().getNama() + "\" invalid!");
                        }
                    });
                } break;
                case VARIAN_AMERAA_HITAM : {
                    assertEquals(VARIAN_AMERAA_HITAM_PANJANG_KAIN, spkVarian.getPanjangKain().getPanjang());
                    spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
                        switch (ukuranJumlah.getUkuran().getNama()){
                            case UKURAN_M: assertEquals(VARIAN_AMERAA_HITAM_M, ukuranJumlah.getJumlah()); break;
                            case UKURAN_L: assertEquals(VARIAN_AMERAA_HITAM_L, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XL: assertEquals(VARIAN_AMERAA_HITAM_XL, ukuranJumlah.getJumlah()); break;
                            case UKURAN_XXL: assertEquals(VARIAN_AMERAA_HITAM_XXL, ukuranJumlah.getJumlah()); break;
                            default: throw new Error("Ukuran with name \"" + ukuranJumlah.getUkuran().getNama() + "\" invalid!");
                        }
                    });
                } break;
                default: throw new Error("Varian with name \"" + spkVarian.getVarian().getVarian() + "\" invalid");
            }
        });

        spkResponseModel = spkService.getSingleSpk(spk.getId());
        assertEquals(spkResponseModel.getVendor(), NAMA_VENDOR);
        assertEquals(spkResponseModel.getStatus(), STATUS_DRAFT);
        //TODO: Change status to complete before SPK activated
    }

    @Test
    @Order(3)
    void changeStatusToReqVal() throws OperationNotSupportedException {
        Spk spk = getLastSpk();
        Set<SpkStatus> spkSpkStatuses = spk.getSpkStatuses();
        Status status = statusRepository
                .findByNama(SpkStatusEnum.REQ_VAL.toString());
        assertEquals(SpkStatusEnum.REQ_VAL.toString(), status.getNama());
        if(status==null)
            throw new OperationNotSupportedException("Not found status with name " + SpkStatusEnum.REQ_VAL.toString());
        SpkStatus spkStatus = spkStatusRepository.save(new SpkStatus(status, spk));
        spkStatus.setCreatedAt(new Date(System.currentTimeMillis() - 3600 * 1000));
        spkSpkStatuses.add(spkStatus);
        spk.setSpkStatuses(spkSpkStatuses);
        spkRepository.save(spk);

        spk = getLastSpk();

        spkSpkStatuses = spk.getSpkStatuses();

        ArrayList<SpkStatus> spkStatusArrayList = new ArrayList<>(spkSpkStatuses);

        spkStatusArrayList.sort(Comparator.comparing(AuditModel::getCreatedAt));

        System.out.println("assertEquals");
        System.out.println("Expected: " + SpkStatusEnum.REQ_VAL.toString());
        System.out.println("Actual: " + spkStatusArrayList.get(spkStatusArrayList.size()-1).getStatus().getNama());
        assertEquals(SpkStatusEnum.REQ_VAL.toString(), spkStatusArrayList.get(spkStatusArrayList.size()-1).getStatus().getNama());
    }

    @Test
    @Order(4)
    void changeStatusToOnGoing() throws OperationNotSupportedException {
        Spk spk = getLastSpk();
        Set<SpkStatus> spkSpkStatuses = spk.getSpkStatuses();
        Status status = statusRepository
                .findByNama(SpkStatusEnum.ON_GOING.toString());
        if(status==null)
            throw new OperationNotSupportedException("Not found status with name " + SpkStatusEnum.ON_GOING.toString());
        spkSpkStatuses.add(spkStatusRepository.save(new SpkStatus(status, spk)));
        spk.setSpkStatuses(spkSpkStatuses);
        spkRepository.save(spk);

        spk = getLastSpk();

        spkSpkStatuses = spk.getSpkStatuses();

        ArrayList<SpkStatus> spkStatusArrayList = new ArrayList<>(spkSpkStatuses);

        spkStatusArrayList.sort(Comparator.comparing(AuditModel::getCreatedAt));

        SpkStatus spkStatus = spkStatusArrayList.get(spkStatusArrayList.size()-1);
        System.out.println("assertEquals");
        System.out.println("Expected: " + SpkStatusEnum.ON_GOING.toString());
        System.out.println("Actual: " + spkStatus.getStatus().getNama());
        String statusName = spkStatus.getStatus().getNama();
        assertEquals(SpkStatusEnum.ON_GOING.toString(), statusName);
    }

    @Test
    @Order(5)
    void submitCuttingProcessSpk() throws Exception {
        Spk spk = getLastSpk();
        List<SpkProses> spkProsesSet = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator = spkProsesSet.iterator();
        SpkProses spkProsesCutting = null;
        while (spkProsesIterator.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_CUTTING)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesCutting = currentSpkProses;
            }
        }
        if(spkProsesCutting==null){
            throw new Exception("Spk Proses Cutting not found");
        }
        assertEquals(PROSES_CUTTING, spkProsesCutting.getProses().getNama());

        SpkProsesRestfulModel spkProsesRestfulModel = new SpkProsesRestfulModel();
        spkProsesRestfulModel.setDurasiNyata(DURASI_NYATA_CUTTING);
        spkProsesRestfulModel.setSpkId(spk.getId());
        spkProsesRestfulModel.setProsesId(spkProsesCutting.getId());
        restfulSpkProsesController.update(spkProsesCutting.getId(), spkProsesRestfulModel);

        catatanRepository.save(new Catatan(spkProsesCutting, CATATAN_1));
        catatanRepository.save(new Catatan(spkProsesCutting, CATATAN_2));

        List<SpkProses> spkProsesSet2 = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator2 = spkProsesSet2.iterator();
        SpkProses spkProsesCutting2 = null;
        while (spkProsesIterator2.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator2.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_CUTTING)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesCutting2 = currentSpkProses;
            }
        }
        if(spkProsesCutting2==null){
            throw new Exception("Spk Proses Cutting not found");
        }

        List<Catatan> catatans = catatanRepository.findAll();
        List<Catatan> catatansOfSpkProses = new ArrayList<>();
        for (Catatan catatan: catatans) {
            if(catatan
                    .getSpkProses()
                    .getId()==spkProsesCutting2
                    .getId()){
                catatansOfSpkProses.add(catatan);
            }
        }

        assertEquals(2, catatansOfSpkProses.size());
        assertEquals(CATATAN_1, catatansOfSpkProses.get(0).getIsi());
        assertEquals(CATATAN_2, catatansOfSpkProses.get(1).getIsi());
    }

    @Test
    @Order(6)
    void submitJahitProcessSpk() throws Exception {
        Spk spk = getLastSpk();
        List<SpkProses> spkProsesSet = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator = spkProsesSet.iterator();
        SpkProses spkProsesJahit = null;
        while (spkProsesIterator.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_JAHIT)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesJahit = currentSpkProses;
            }
        }
        if(spkProsesJahit==null){
            throw new Exception("Spk Proses Cutting not found");
        }
        assertEquals(PROSES_JAHIT, spkProsesJahit.getProses().getNama());





        spkProsesJahit.setDurasiNyata(DURASI_NYATA_JAHIT);
        spkProsesRepository.save(spkProsesJahit);

        catatanRepository.save(new Catatan(spkProsesJahit, CATATAN_1));
        catatanRepository.save(new Catatan(spkProsesJahit, CATATAN_2));

        List<SpkProses> spkProsesSet2 = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator2 = spkProsesSet2.iterator();
        SpkProses spkProsesJahit2 = null;
        while (spkProsesIterator2.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator2.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_JAHIT)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesJahit2 = currentSpkProses;
            }
        }
        if(spkProsesJahit2==null){
            throw new Exception("Spk Proses Jahit not found");
        }
        List<Catatan> catatans = catatanRepository.findAll();
        List<Catatan> catatansOfSpkProses = new ArrayList<>();
        for (Catatan catatan: catatans) {
            if(catatan
                    .getSpkProses()
                    .getId()==spkProsesJahit2
                    .getId()){
                catatansOfSpkProses.add(catatan);
            }
        }

        assertEquals(2, catatansOfSpkProses.size());
        assertEquals(CATATAN_1, catatansOfSpkProses.get(0).getIsi());
        assertEquals(CATATAN_2, catatansOfSpkProses.get(1).getIsi());
    }

    @Test
    @Order(7)
    void submitFinishingProcessSpk() throws Exception {
        Spk spk = getLastSpk();
        List<SpkProses> spkProsesSet = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator = spkProsesSet.iterator();
        SpkProses spkProsesFinishing = null;
        while (spkProsesIterator.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_FINISHING)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesFinishing = currentSpkProses;
            }
        }
        if(spkProsesFinishing==null){
            throw new Exception("Spk Proses Finishing not found");
        }
        assertEquals(PROSES_FINISHING, spkProsesFinishing.getProses().getNama());
        spkProsesFinishing.setDurasiNyata(DURASI_NYATA_FINISHING);
        spkProsesRepository.save(spkProsesFinishing);

        catatanRepository.save(new Catatan(spkProsesFinishing, CATATAN_1));
        catatanRepository.save(new Catatan(spkProsesFinishing, CATATAN_2));

        List<SpkProses> spkProsesSet2 = spkProsesRepository.findAll();
        Iterator<SpkProses> spkProsesIterator2 = spkProsesSet2.iterator();
        SpkProses spkProsesFinishing2 = null;
        while (spkProsesIterator2.hasNext()){
            SpkProses currentSpkProses = spkProsesIterator2.next();
            if(currentSpkProses.getProses().getNama().equals(PROSES_FINISHING)&&currentSpkProses.getSpk().getId()==spk.getId()){
                spkProsesFinishing2 = currentSpkProses;
            }
        }
        if(spkProsesFinishing2==null){
            throw new Exception("Spk Proses Finishing not found");
        }

        List<Catatan> catatans = catatanRepository.findAll();
        List<Catatan> catatansOfSpkProses = new ArrayList<>();
        for (Catatan catatan: catatans) {
            if(catatan
                    .getSpkProses()
                    .getId()==spkProsesFinishing2
                    .getId()){
                catatansOfSpkProses.add(catatan);
            }
        }

        assertEquals(2, catatansOfSpkProses.size());
        assertEquals(CATATAN_1, catatansOfSpkProses.get(0).getIsi());
        assertEquals(CATATAN_2, catatansOfSpkProses.get(1).getIsi());
    }

    @Test
    @Order(8)
    void getAutiFillPenilaianVendor() {
        Spk spk = getLastSpk();
        spk.getSpkVarians().forEach(spkVarian -> {
            spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
                ukuranJumlahPenerimaanRepository.save(new UkuranJumlahPenerimaan(ukuranJumlah, ukuranJumlah.getJumlah(), true));
            });
        });
        AutoFillPenilaianVendor autoFillPenilaianVendor = spkService.autoFillPenilaianVendor(spk.getId());
        assertEquals(1D, autoFillPenilaianVendor.getWaktu());
        assertEquals(1D, autoFillPenilaianVendor.getJumlah());
    }

    @Test
    @Order(9)
    void submitPenilaianVendor() {
        Spk spk = getLastSpk();
        spk.getSpkVarians().forEach(spkVarian -> {
            spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
                ukuranJumlahPenerimaanRepository.save(new UkuranJumlahPenerimaan(ukuranJumlah, ukuranJumlah.getJumlah(), true));
            });
        });
        AutoFillPenilaianVendor autoFillPenilaianVendor = spkService.autoFillPenilaianVendor(spk.getId());
        NilaiSpk nilaiSpk = nilaiSpkRepository.save(new NilaiSpk(spk, 0.9D, 0.8D, autoFillPenilaianVendor.getWaktu(), autoFillPenilaianVendor.getJumlah(), "Bagus"));
        assertEquals(0.925D, nilaiSpk.getRata());
    }

    @Test
    @Order(10)
    void getNilaiVendor(){

    }

    private Spk getLastSpk(){
        List<Spk> spks = spkRepository.findAll();
        return spks.get(spks.size()-1);

    }

    private Vendor getLastVendor(){
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.get(vendors.size()-1);
    }
}
