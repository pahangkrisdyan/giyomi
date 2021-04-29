package id.giyomi.vms.backend.command;

import id.giyomi.vms.backend.component.authentication.JwtTokenUtil;
import id.giyomi.vms.backend.entity.*;
import id.giyomi.vms.backend.pricipal.UserPrincipal;
import id.giyomi.vms.backend.repository.*;
import id.giyomi.vms.backend.service.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.net.InetAddress;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private CatatanRepository catatanRepository;
    private HakRepository hakRepository;
    private JenisKoleksiRepository jenisKoleksiRepository;
    private KoleksiRepository koleksiRepository;
    private PanjangKainRepository panjangKainRepository;
    private VarianRepository varianRepository;
    private ProsesRepository prosesRepository;
    private RoleRepository roleRepository;
    private SatuanPanjangKainRepository satuanPanjangKainRepository;
    private SpkVarianRepository spkVarianRepository;
    private SpkProsesRepository spkProsesRepository;
    private SpkRepository spkRepository;
    private SpkStatusRepository spkStatusRepository;
    private StatusRepository statusRepository;
    private UkuranJumlahRepository ukuranJumlahRepository;
    private UkuranRepository ukuranRepository;
    private UserRepository userRepository;
    private VendorRepository vendorRepository;
    private UserPrincipalDetailService userPrincipalDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public MyCommandLineRunner(CatatanRepository catatanRepository, HakRepository hakRepository, JenisKoleksiRepository jenisKoleksiRepository, KoleksiRepository koleksiRepository, PanjangKainRepository panjangKainRepository, VarianRepository varianRepository, ProsesRepository prosesRepository, RoleRepository roleRepository, SatuanPanjangKainRepository satuanPanjangKainRepository, SpkVarianRepository spkVarianRepository, SpkProsesRepository spkProsesRepository, SpkRepository spkRepository, SpkStatusRepository spkStatusRepository, StatusRepository statusRepository, UkuranJumlahRepository ukuranJumlahRepository, UkuranRepository ukuranRepository, UserRepository userRepository, VendorRepository vendorRepository, UserPrincipalDetailService userPrincipalDetailService) {
        this.catatanRepository = catatanRepository;
        this.hakRepository = hakRepository;
        this.jenisKoleksiRepository = jenisKoleksiRepository;
        this.koleksiRepository = koleksiRepository;
        this.panjangKainRepository = panjangKainRepository;
        this.varianRepository = varianRepository;
        this.prosesRepository = prosesRepository;
        this.roleRepository = roleRepository;
        this.satuanPanjangKainRepository = satuanPanjangKainRepository;
        this.spkVarianRepository = spkVarianRepository;
        this.spkProsesRepository = spkProsesRepository;
        this.spkRepository = spkRepository;
        this.spkStatusRepository = spkStatusRepository;
        this.statusRepository = statusRepository;
        this.ukuranJumlahRepository = ukuranJumlahRepository;
        this.ukuranRepository = ukuranRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.userPrincipalDetailService = userPrincipalDetailService;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Running at => " + ip);
//        initUser();
//        initData();
//        modeShowJwtCeo();

    }

    private void modeShowJwtCeo() throws Exception {
        authenticate("ceo", "pass_ceo");
        final UserDetails userDetails = userPrincipalDetailService
                .loadUserByUsername("ceo");
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("============");
        System.out.println("============");
        System.out.println("============");
        System.out.println("============");
        System.out.println(token);
        System.out.println("============");
        System.out.println("============");
        System.out.println("============");
    }

    private void initData() {
        //Master Data

        //Vendor
        Vendor vendorBuSulastri = vendorRepository.save(new Vendor("Jalan vendorBuSulastri", "vendorBuSulastri@gmail.com", "Bu Sulastri", "081231684642"));
        Vendor vendorBuYanik = vendorRepository.save(new Vendor("Jalan vendorBuYanik", "vendorBuYanik@gmail.com", "Bu Yanik", "081231684642"));
        Vendor vendorPakRuben = vendorRepository.save(new Vendor("Jalan vendorPakRuben", "vendorPakRuben@gmail.com", "Pak Ruben", "081231684642"));
        Vendor vendorPakNoval = vendorRepository.save(new Vendor("Jalan vendorPakNoval", "vendorPakNoval@gmail.com", "Pak Noval", "081231684642"));
        Vendor vendorTacikVina = vendorRepository.save(new Vendor("Jalan vendorTacikVina", "vendorTacikVina@gmail.com", "Tacik Vina", "081231684642"));

        //Jenis Koleksi
        JenisKoleksi jenisKoleksiAtasan = jenisKoleksiRepository.save(new JenisKoleksi("Atasan"));
        JenisKoleksi jenisKoleksiCelana = jenisKoleksiRepository.save(new JenisKoleksi("Celana"));
        JenisKoleksi jenisKoleksiTas = jenisKoleksiRepository.save(new JenisKoleksi("Tas"));

        //Proses
        Proses prosesCutting = prosesRepository.save(new Proses("cutting"));
        Proses prosesJahit = prosesRepository.save(new Proses("jahit"));
        Proses prosesFinishing = prosesRepository.save(new Proses("finishing"));

        //Satuan Panjang Kain
        SatuanPanjangKain satuanPanjangKainM = satuanPanjangKainRepository.save(new SatuanPanjangKain("meter"));
        SatuanPanjangKain satuanPanjangKainKg = satuanPanjangKainRepository.save(new SatuanPanjangKain("kg"));

        //Status
        Status statusDraft = statusRepository.save(new Status("draft"));
        Status statusReqVal = statusRepository.save(new Status("req_val"));
        Status statusValid = statusRepository.save(new Status("valid"));
        Status statusOnGoing = statusRepository.save(new Status("on_going"));

        //UKURAN
        Ukuran ukuranS = ukuranRepository.save(new Ukuran("s"));
        Ukuran ukuranM = ukuranRepository.save(new Ukuran("m"));
        Ukuran ukuranL = ukuranRepository.save(new Ukuran("l"));
        Ukuran ukuranXL = ukuranRepository.save(new Ukuran("Xl"));

        //Koleksi
        Koleksi koleksiAmeeraSweater = koleksiRepository.save(new Koleksi("Ameera Sweater", "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B", jenisKoleksiAtasan, "Ini detail aksesosris"));
        Koleksi koleksiPallasoPants = koleksiRepository.save(new Koleksi("Pallaso Pants", "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",jenisKoleksiAtasan, "Ini detail aksesosris"));

        //Produk
        Varian varianAmeeraMaroon = varianRepository.save(new Varian(koleksiAmeeraSweater, "Maroon"));
        Varian varianAmeeraBiru = varianRepository.save(new Varian(koleksiAmeeraSweater, "Biru"));
        Varian varianAmeeraHitam = varianRepository.save(new Varian(koleksiAmeeraSweater, "Hitam"));


        Spk spk = spkRepository.save(new Spk(vendorPakNoval));
        SpkProses spkProsesCutting = spkProsesRepository.save(new SpkProses(5, null, prosesCutting, spk, 1));
        SpkProses spkProsesJahit = spkProsesRepository.save(new SpkProses(10, null, prosesJahit, spk, 2));
        SpkProses spkProsesFinishing = spkProsesRepository.save(new SpkProses(4, null, prosesFinishing, spk, 3));

        PanjangKain panjangKain10M = panjangKainRepository.save(new PanjangKain(10.0, satuanPanjangKainM));
        SpkVarian spkVarianAmeeraMaroon = spkVarianRepository.save(new SpkVarian(spk, varianAmeeraMaroon, panjangKain10M));
        SpkVarian spkVarianAmeeraBiru = spkVarianRepository.save(new SpkVarian(spk, varianAmeeraBiru, panjangKain10M));
        SpkVarian spkVarianAmeeraHitam = spkVarianRepository.save(new SpkVarian(spk, varianAmeeraHitam, panjangKain10M));

        UkuranJumlah ukuranJumlahAmeeraMaroonS100 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraMaroon, ukuranS, 100));
        UkuranJumlah ukuranJumlahAmeeraMaroonM200 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraMaroon, ukuranM, 200));
        UkuranJumlah ukuranJumlahAmeeraMaroonL300 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraMaroon, ukuranL, 300));

        UkuranJumlah ukuranJumlahAmeeraBiruS100 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraBiru, ukuranS, 100));
        UkuranJumlah ukuranJumlahAmeeraBiruM200 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraBiru, ukuranM, 200));
        UkuranJumlah ukuranJumlahAmeeraBiruL300 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraBiru, ukuranL, 300));

        UkuranJumlah ukuranJumlahAmeeraHitamS100 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraHitam, ukuranS, 100));
        UkuranJumlah ukuranJumlahAmeeraHitamM200 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraHitam, ukuranM, 200));
        UkuranJumlah ukuranJumlahAmeeraHitamL300 = ukuranJumlahRepository.save(new UkuranJumlah(spkVarianAmeeraHitam, ukuranL, 300));

        SpkStatus spkStatusDraft = spkStatusRepository.save(new SpkStatus(statusDraft, spk));

        Catatan catatan1 = catatanRepository.save(new Catatan(spkProsesCutting, "Catatan 1"));
        Catatan catatan2 = catatanRepository.save(new Catatan(spkProsesCutting, "Catatan 2"));
        Catatan catatan3 = catatanRepository.save(new Catatan(spkProsesCutting, "Catatan 3"));

    }

    private void initUser() {
        //#0
        Hak hakUser = hakRepository.save(new Hak("USER"));
        hakRepository.save(hakUser);

        //#1 ROLE
        Role roleCeo = roleRepository.save(new Role("admin"));
        roleCeo.getHaks().add(hakUser);
        roleRepository.save(roleCeo);

        //#2 USER
        User userCeo = userRepository.save(
                new User(
                        "ceo",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_ceo"),
                        "ceo@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleCeo
                ));

        //Login
        UserPrincipal userPrincipal = (UserPrincipal) userPrincipalDetailService.loadUserByUsername("ceo");
        Authentication authentication =  new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Role roleStaff = roleRepository.save(new Role("staff"));
        roleStaff.getHaks().add(hakUser);
        roleRepository.save(roleStaff);

        User userStaff1 = userRepository.save(
                new User(
                        "staff1",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff1"),
                        "staff1@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
        User userStaff2 = userRepository.save(
                new User(
                        "staff2",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff2"),
                        "staff12@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
        User userStaff3 = userRepository.save(
                new User(
                        "staff3",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff3"),
                        "staff3@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
        User userStaff4 = userRepository.save(
                new User(
                        "staff4",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff4"),
                        "staff14@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
        User userStaff5 = userRepository.save(
                new User(
                        "staff5",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff5"),
                        "staff14@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
        User userStaff6 = userRepository.save(
                new User(
                        "staff6",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_staff6"),
                        "staff14@gmail.com",
                        "081231684642",
                        "http://scontent-sin6-2.cdninstagram.com/v/t51.2885-15/e35/35000952_259532818125171_5138521924023877632_n.jpg?_nc_ht=scontent-sin6-2.cdninstagram.com&_nc_cat=103&_nc_ohc=DYWbkkRqEYoAX-rXf88&oh=867b44d6abcbb99c7da605782685a01e&oe=5E9B706B",
                        roleStaff
                )
        );
    }
}
