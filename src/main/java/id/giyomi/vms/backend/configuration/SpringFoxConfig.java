package id.giyomi.vms.backend.configuration;

import id.giyomi.vms.backend.component.authentication.JwtTokenUtil;
import id.giyomi.vms.backend.service.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    private static final String TITLE = "Giyomi Vendor Management Service";
    private static final String DESC = "This is api documentation of giyomi vendor management";
    private static final String VERSION = "1.0.0";
    private static final String TERM_URL = "http://giyomi.id";
    private static final String CONTACT_NAME = "Etika Pahang Krisdyan";
    private static final String CONTACT_URL = "https://linkedin.com/in/pahangkrisdyan";
    private static final String CONTACT_EMAIL = "etika.pahang.k.1998@gmail.com";
    private static final String LICENCE = "Apache 2.0";
    private static final String LICENCE_URL = "http://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public Docket apiDocket() {
        Contact contact = new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(TITLE, DESC, VERSION, TERM_URL, contact,
                        LICENCE, LICENCE_URL, Collections.emptyList()))
                .globalOperationParameters(Collections.singletonList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .hidden(true)
                                .defaultValue("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjZW8iLCJpYXQiOjE1OTE0MjM5Mjd9.k_9WbUA4sTNzKWUMGNW0ziKUvc62DLSe8Ry8jKZRcWOtz3kFki7_ILKSZCZCzcD3oE4Hup9btko1DQ5Nc3q1Dg")
                .build()
        ));
    }


}