package id.giyomi.vms.backend.controller.rest.model.spk.response;

import id.giyomi.vms.backend.entity.*;
import id.giyomi.vms.backend.util.SpkServiceUtil;

import java.lang.reflect.Array;
import java.util.*;

public class SpkResponseModel {
    private Long id;
    private String vendor;
    private String status;
    private String koleksi;
    private String jenisKoleksi;
    private Date tanggalPesan;
    private Date estTanggalKirim;
    private ArrayList<DetailVarianRes> varians;
    private String fotoDownloadUrl;
    private ArrayList<DetailSpkProses> progress;
    private String detailAksesoris;

    public SpkResponseModel() {}

    public SpkResponseModel(Spk spk) {
        Koleksi koleksi = getKoleksi(spk.getSpkVarians());
        this.tanggalPesan = SpkServiceUtil.getTanggalPesan(spk);
        this.vendor = spk.getVendor().getNama();
        this.status = getLastStatus(spk.getSpkStatuses());
        this.koleksi = koleksi.getNama();
        this.estTanggalKirim = getEstTanggalKirim(spk);
        this.varians = getVarians(spk.getSpkVarians());
        this.progress = getProgress(spk.getSpkProseses());
        this.id = spk.getId();
        this.fotoDownloadUrl = koleksi.getFotoDownloadUrl();
        this.jenisKoleksi = koleksi.getJenisKoleksi().getNama();
        this.detailAksesoris = koleksi.getDetailAksesoris();
    }

    public String getDetailAksesoris() {
        return detailAksesoris;
    }

    public void setDetailAksesoris(String detailAksesoris) {
        this.detailAksesoris = detailAksesoris;
    }

    public String getJenisKoleksi() {
        return jenisKoleksi;
    }

    public void setJenisKoleksi(String jenisKoleksi) {
        this.jenisKoleksi = jenisKoleksi;
    }

    private ArrayList<DetailSpkProses> getProgress(Set<SpkProses> spkProseses) {
        ArrayList<DetailSpkProses> result = new ArrayList<>();

        ArrayList<SpkProses> temp = new ArrayList<>();
        temp.addAll(spkProseses);
        temp.sort(Comparator.comparingInt(SpkProses::getOrder));

        temp.forEach(spkProses -> {
            String namaProses = spkProses.getProses().getNama();
            //TODO: implement date
            DetailSpkProses detailSpkProses = new DetailSpkProses(getCatatans(spkProses.getCatatans()), new Date(), namaProses, spkProses.getId(), spkProses.getTanggalSelesai(), spkProses.getDurasiNyata(), spkProses.getDurasiRencana());
            result.add(detailSpkProses);
        });
        return result;
    }

    private ArrayList<DetailCatatan> getCatatans(Set<Catatan> catatans) {
        ArrayList<DetailCatatan> result = new ArrayList<>();
        catatans.forEach(catatan -> {
            String namaPencatat = catatan.getCreatedBy().getUsername();
            Date tanggalCatatan = catatan.getCreatedAt();
            String isi = catatan.getIsi();
            DetailCatatan detailCatatan = new DetailCatatan(namaPencatat, isi, tanggalCatatan);
            result.add(detailCatatan);
        });
        return result;
    }

    private ArrayList<DetailVarianRes> getVarians(Set<SpkVarian> spkVarians) {
        ArrayList<DetailVarianRes> results = new ArrayList<>();
        spkVarians.forEach(spkVarian -> {
            String namaVarian = spkVarian.getVarian().getVarian();
            DetailVarianRes varian = new DetailVarianRes(spkVarian.getPanjangKain().getPanjang(), spkVarian.getPanjangKain().getSatuanPanjangKain().getNama(), getUkuranJumlahs(spkVarian), namaVarian);
            results.add(varian);
        });
        return results;
    }

    private HashSet<DetailUkuranJumlahRes> getUkuranJumlahs(SpkVarian spkVarian){
        HashSet<DetailUkuranJumlahRes> detailUkuranJumlahs = new HashSet<>();
        spkVarian.getUkuranJumlahs().forEach(ukuranJumlah -> {
            DetailUkuranJumlahRes detailUkuranJumlah1 = new DetailUkuranJumlahRes(ukuranJumlah.getUkuran().getNama(), ukuranJumlah.getJumlah(), ukuranJumlah.getId());
            detailUkuranJumlahs.add(detailUkuranJumlah1);
        });
        return detailUkuranJumlahs;
    }

    private Date getEstTanggalKirim(Spk spk) {
        if(tanggalPesan==null){
            return new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(tanggalPesan);
        Iterator<SpkProses> spkProsesIterator = spk.getSpkProseses().iterator();
        while(spkProsesIterator.hasNext()){
            SpkProses spkProses = spkProsesIterator.next();
            c.add(Calendar.DATE, spkProses.getDurasiRencana());
        }
        return c.getTime();
    }

    private Koleksi getKoleksi(Set<SpkVarian> spkVarians) {
        if(spkVarians.size()<1){
            throw new Error("spkVarians length equals to 0");
        }
        Iterator<SpkVarian> spkProdukIterator = spkVarians.iterator();
        return spkProdukIterator.next().getVarian().getKoleksi();
    }

    private String getLastStatus(Set<SpkStatus> spkStatuses){
        if(spkStatuses.size()<1){
            throw new Error("spkStatuses length equals to 0");
        }

        SpkStatus result = null;
        Date last = new Date(0);
        Iterator<SpkStatus> spkStatusesIterator = spkStatuses.iterator();
        while (spkStatusesIterator.hasNext()){
            SpkStatus spkStatus = spkStatusesIterator.next();
            if(spkStatus.getCreatedAt().compareTo(last)>0){
                result = spkStatus;
                last = spkStatus.getCreatedAt();
            }
        }
        return result.getStatus().getNama();
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKoleksi() {
        return koleksi;
    }

    public void setKoleksi(String koleksi) {
        this.koleksi = koleksi;
    }

    public Date getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public Date getEstTanggalKirim() {
        return estTanggalKirim;
    }

    public void setEstTanggalKirim(Date estTanggalKirim) {
        this.estTanggalKirim = estTanggalKirim;
    }

    public ArrayList<DetailVarianRes> getVarians() {
        return varians;
    }

    public void setVarians(ArrayList<DetailVarianRes> varians) {
        this.varians = varians;
    }

    public ArrayList<DetailSpkProses> getProgress() {
        return progress;
    }

    public void setProgress(ArrayList<DetailSpkProses> progress) {
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoDownloadUrl() {
        return fotoDownloadUrl;
    }

    public void setFotoDownloadUrl(String fotoDownloadUrl) {
        this.fotoDownloadUrl = fotoDownloadUrl;
    }

    class Pair<L,R> {
        private L left;
        private R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public void setLeft(L left) {
            this.left = left;
        }

        public R getRight() {
            return right;
        }

        public void setRight(R right) {
            this.right = right;
        }
    }
}
