package id.giyomi.vms.backend.service;

import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailUkuranJumlahReq;
import id.giyomi.vms.backend.entity.*;
import id.giyomi.vms.backend.controller.rest.model.*;
import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailProsesReq;
import id.giyomi.vms.backend.controller.rest.model.spk.request.DetailVarianReq;
import id.giyomi.vms.backend.controller.rest.model.spk.request.SpkRequestModel;
import id.giyomi.vms.backend.controller.rest.model.spk.response.SpkResponseModel;
import id.giyomi.vms.backend.repository.*;
import id.giyomi.vms.backend.util.SpkServiceUtil;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class SpkService {

    private SpkRepository spkRepository;
    private SpkStatusRepository spkStatusRepository;
    private SpkProsesRepository spkProsesRepository;
    private StatusRepository statusRepository;
    private VendorRepository vendorRepository;
    private ProsesRepository prosesRepository;
    private VarianRepository varianRepository;
    private SatuanPanjangKainRepository satuanPanjangKainRepository;
    private PanjangKainRepository panjangKainRepository;
    private SpkVarianRepository spkVarianRepository;
    private UkuranRepository ukuranRepository;
    private UkuranJumlahRepository ukuranJumlahRepository;

    public SpkService(SpkRepository spkRepository, SpkStatusRepository spkStatusRepository, SpkProsesRepository spkProsesRepository, StatusRepository statusRepository, VendorRepository vendorRepository, ProsesRepository prosesRepository, VarianRepository varianRepository, SatuanPanjangKainRepository satuanPanjangKainRepository, PanjangKainRepository panjangKainRepository, SpkVarianRepository spkVarianRepository, UkuranRepository ukuranRepository, UkuranJumlahRepository ukuranJumlahRepository) {
        this.spkRepository = spkRepository;
        this.spkStatusRepository = spkStatusRepository;
        this.spkProsesRepository = spkProsesRepository;
        this.statusRepository = statusRepository;
        this.vendorRepository = vendorRepository;
        this.prosesRepository = prosesRepository;
        this.varianRepository = varianRepository;
        this.satuanPanjangKainRepository = satuanPanjangKainRepository;
        this.panjangKainRepository = panjangKainRepository;
        this.spkVarianRepository = spkVarianRepository;
        this.ukuranRepository = ukuranRepository;
        this.ukuranJumlahRepository = ukuranJumlahRepository;
    }

    public SpkProses spkProsesDone(Long spkId, Long spkProsesId){
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
        SpkProses spkProses = spkProsesRepository
                .findById(spkProsesId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkProses with id " + spkProsesId));
        spkProses.setDurasiNyata(getDurasiNyata(spk, spkProses));
        spkProses.setTanggalSelesai(new Date());
        return spkProsesRepository.save(spkProses);
    }

    private Integer getDurasiNyata(Spk spk, SpkProses spkProses) {
        SpkProses spkProsesBefore = getSpkProsesBefore(spk, spkProses);
        DateTime now = new DateTime(new Date());
        DateTime startDate;
        if(spkProsesBefore==null){
            startDate = new DateTime(SpkServiceUtil.getTanggalPesan(spk));
        }else{
            startDate = new DateTime(spkProsesBefore.getTanggalSelesai());
        }
        Integer durasiNyata = Days.daysBetween(startDate, now).getDays();
        return durasiNyata;
    }

    private SpkProses getSpkProsesBefore(Spk spk, SpkProses spkProses) {
        SpkProses spkProsesBefore = null;
        Date winner = new Date(0l);
        Iterator<SpkProses> spkProsesIterator = spk.getSpkProseses().iterator();
        while (spkProsesIterator.hasNext()){
            SpkProses spkProsesPointer = spkProsesIterator.next();
            if(spkProsesPointer.getTanggalSelesai()==null){
                continue;
            }
            if(winner
                    .compareTo(spkProsesPointer
                            .getTanggalSelesai())==-1 && spkProses
                    .getTanggalSelesai()
                    .compareTo(spkProsesPointer
                            .getTanggalSelesai())==1){
                winner = spkProsesPointer.getTanggalSelesai();
                spkProsesBefore = spkProsesPointer;
            }
        }
        return spkProsesBefore;
    }

    public SpkResponseModel getSingleSpk(Long spkId){
        System.out.println("Try to fins spk with id = " + spkId);
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found resource with id " + spkId));
        return new SpkResponseModel(spk);
    }

    public List<SpkResponseModel> getSpksByStatusName(SpkStatusEnum spkStatusEnum){
        List<SpkResponseModel> spks = new ArrayList<>();
        Status status;
        if(spkStatusEnum==null){
            status = null;
        }else{
            status = statusRepository.findByNama(spkStatusEnum.toString());
        }
        spkRepository.findAll().forEach(spk -> {
            if(spkStatusEnum!=null){
                if (spk.getLastStatus().getId() == status.getId()) {
                    spks.add(new SpkResponseModel(spk));
                }
            }else{
                spks.add(new SpkResponseModel(spk));
            }

        });
        return spks;
    }

//    private Status getLastStatusOfSpk(Spk spk){
//        Status status = null;
//        Date date = new Date(0L);
//        for(SpkStatus spkStatus : spk.getSpkStatuses()){
//            Date statusDate = spkStatus.getCreatedAt();
//            if(statusDate.compareTo(date) > 0){
//                status = spkStatus.getStatus();
//                date = statusDate;
//            }
//        }
//        if(status==null)
//            throw new ResourceNotFoundException("Not found status for SPK with id " + spk.getId());
//        return status;
//    }

    public SpkResponseModel createSpk(SpkRequestModel spkRequestModel, SpkStatusEnum spkStatusEnum) {
        //Vendor
        Vendor vendor = vendorRepository
                .findById(spkRequestModel.getVendorId())
                .orElseThrow(()-> new ResourceNotFoundException("Not found vendor with id " + spkRequestModel.getVendorId()));
        Spk spk = spkRepository.save(new Spk(vendor));
        //Proseses
        Set<SpkProses> spkProseses = new HashSet<>();
        for (int i = 0; i<spkRequestModel.getProseses().size(); i++){
            DetailProsesReq proses = spkRequestModel.getProseses().get(i);
            Proses proses1 = prosesRepository
                    .findById(proses.getProsesId())
                    .orElseThrow(()-> new ResourceNotFoundException("Not found proses with id " + proses.getProsesId()));
            SpkProses spkProses = spkProsesRepository.save(new SpkProses(proses.getDurasiRencana(), null, proses1, spk, i));
            spkProseses.add(spkProses);
        }
        spk.setSpkProseses(spkProseses);

        //Varian
        HashSet<SpkVarian> spkVarians = new HashSet<>();
        for (int i = 0; i<spkRequestModel.getVarians().size(); i++){
            DetailVarianReq varianDetail = spkRequestModel.getVarians().get(i);
            Varian varian = varianRepository
                    .findById(varianDetail.getVarianId())
                    .orElseThrow(()-> new ResourceNotFoundException("Not found varian with id " + varianDetail.getVarianId()));
            if(spkRequestModel.getFotoDownloadUrl()!=null&&spkRequestModel.getFotoDownloadUrl()!=""){
                varian.getKoleksi().setFotoDownloadUrl(spkRequestModel.getFotoDownloadUrl());
                varian = varianRepository.save(varian);
            }
            SatuanPanjangKain satuanPanjangKain = satuanPanjangKainRepository
                    .findById(varianDetail.getSatuanPanjangkainId())
                    .orElseThrow(()-> new ResourceNotFoundException("Not found satuan panjang kain with id " + varianDetail.getSatuanPanjangkainId()));
            PanjangKain panjangKain = panjangKainRepository.save(new PanjangKain(varianDetail.getPanjangKain(), satuanPanjangKain));
            SpkVarian spkVarian = spkVarianRepository.save(new SpkVarian(spk, varian, panjangKain));
            Set<UkuranJumlah> ukuranJumlahs = new HashSet<>();
            for (int j = 0; j<varianDetail.getUkuranJumlahs().size(); j++){
                DetailUkuranJumlahReq detailUkuranJumlahReq = varianDetail.getUkuranJumlahs().get(i);
                Ukuran ukuran = ukuranRepository
                        .findById(detailUkuranJumlahReq.getUkuranId())
                        .orElseThrow(()-> new ResourceNotFoundException("Not found ukuran kain with id " + detailUkuranJumlahReq.getUkuranId()));
                UkuranJumlah ukuranJumlah = ukuranJumlahRepository.save(new UkuranJumlah(spkVarian, ukuran, detailUkuranJumlahReq.getJumlah()));
                ukuranJumlahs.add(ukuranJumlah);
            }
            spkVarian.setUkuranJumlahs(ukuranJumlahs);
            spkVarianRepository.save(spkVarian);
            spkVarians.add(spkVarian);
        }
        spk.setSpkVarians(spkVarians);

        //Status
        HashSet<SpkStatus> spkStatuses = new HashSet<>();
        Status status = statusRepository.findByNama(spkStatusEnum.toString());
        spkStatuses.add(spkStatusRepository.save(new SpkStatus(status, spk)));
        spk.setSpkStatuses(spkStatuses);
        spk = spkRepository.save(spk);
        return new SpkResponseModel(spk);
    }

    public AutoFillPenilaianVendor autoFillPenilaianVendor(Long spkId) {
        Spk spk = spkRepository.findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));

        Set<SpkProses> spkProseses = spk.getSpkProseses();
        Set<SpkVarian> spkVarians = spk.getSpkVarians();

        Double waktu = getNilaiWaktuVendor(spkProseses);

        Double jumlah = getNilaiJumlahVendor(spkVarians);

        return new AutoFillPenilaianVendor(waktu, jumlah);
    }

    private Double getNilaiJumlahVendor(Set<SpkVarian> spkVarians) {
        Iterator<SpkVarian> spkVariansIterator = spkVarians.iterator();
        Integer totalDiterima = 0;
        Integer totalSKU = 0;
        while (spkVariansIterator.hasNext()){
            SpkVarian spkVarian = spkVariansIterator.next();
            Set<UkuranJumlah> ukuranJumlahs = spkVarian.getUkuranJumlahs();
            Iterator<UkuranJumlah> ukuranJumlahsIterator = ukuranJumlahs.iterator();
            while (ukuranJumlahsIterator.hasNext()){
                UkuranJumlah ukuranJumlah = ukuranJumlahsIterator.next();
                Set<UkuranJumlahPenerimaan> ukuranJumlahPenerimaans = ukuranJumlah.getUkuranJumlahPenerimaans();
                Iterator<UkuranJumlahPenerimaan> ukuranJumlahPenerimaansIterator = ukuranJumlahPenerimaans.iterator();
                while (ukuranJumlahPenerimaansIterator.hasNext()){
                    UkuranJumlahPenerimaan ukuranJumlahPenerimaan = ukuranJumlahPenerimaansIterator.next();
                    if(ukuranJumlahPenerimaan.getDiterima()){
                        totalDiterima += ukuranJumlahPenerimaan.getJumlahPenerimaan();
                    }
                    totalSKU += ukuranJumlahPenerimaan.getJumlahPenerimaan();
                }
            }
        }

        if(totalSKU==0) return 0.0D;

        return Double.valueOf(totalDiterima) / Double.valueOf(totalSKU);
    }

    private Double getNilaiWaktuVendor(Set<SpkProses> spkProseses) {
        Iterator<SpkProses> spkProsesesIterator = spkProseses.iterator();
        Integer totalDurasi = 0;
        Integer totalDurasiTelat = 0;
        while (spkProsesesIterator.hasNext()){
            SpkProses spkProses = spkProsesesIterator.next();
            totalDurasi += spkProses.getDurasiRencana();
            if(spkProses.getDurasiNyata()==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SPK belum selesai");
            }
            totalDurasiTelat += spkProses.getDurasiNyata() - spkProses.getDurasiRencana();
        }
        Integer durasiTidakTelat = totalDurasi - totalDurasiTelat;

        Double result = Double.valueOf(durasiTidakTelat)/Double.valueOf(totalDurasi);

        if(result>1.0) result = 1.0;

        return result;
    }

    public void submitNilai(PenilaianSpk penilaianSpk, Long spkId) {

    }
}