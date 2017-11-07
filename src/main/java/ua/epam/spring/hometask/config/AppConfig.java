package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ua.epam.spring.hometask.aspects.CounterAspect;
import ua.epam.spring.hometask.aspects.DiscountAspect;
import ua.epam.spring.hometask.aspects.LuckyWinnerAspect;
import ua.epam.spring.hometask.repository.AuditoriumRepository;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.repository.TicketRepository;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.repository.jdbc.JdbcAuditoriumRepositoryImpl;
import ua.epam.spring.hometask.repository.jdbc.JdbcEventRepositoryImpl;
import ua.epam.spring.hometask.repository.jdbc.JdbcTicketRepository;
import ua.epam.spring.hometask.repository.jdbc.JdbcUserRepositoryIml;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.service.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.service.strategy.DiscountStrategy;
import ua.epam.spring.hometask.service.strategy.TicketCountStrategy;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by Vladimir on 19.10.2017.
 */
@Configuration
@EnableAspectJAutoProxy
//@PropertySource({"classpath:auditorium.properties", "classpath:db.properties"})
//@PropertySource("classpath:auditorium.properties")
public class AppConfig {


//    @Value("${names}")
//    private String[] names;
//
//    @Value("#{T(java.util.Arrays).asList('${numberOfSeats}')}")
//    private List<String> numberOfSeats;
//
//    @Value("#{${vipSeats}}")
//    private Map<String, String> vipSeats;


//    @Value("${jdbc.driverClassName}")
//    private String driverClass;
//
//    @Value("${jdbc.url}")
//    private String jdbcUrl;
//
//    @Value("${jdbc.user}")
//    private String jdbcUser;

    //2nd approach to get properties variable
//    @Autowired
//    private Environment env;

//    @Bean
//    private DriverManagerDataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcUser);
//        dataSource.setPassword(env.getProperty("jdbc.password"));
//        return dataSource;
//    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.DERBY).addScript("init_db.sql").addScript("populate_db.sql");
        return builder.build();
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public UserRepository userRepository() {

        return new JdbcUserRepositoryIml(jdbcTemplate());

        /*InMemory impl*/
        //return new InMemoryUserRepositoryImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public EventRepository eventRepository() {
        return new JdbcEventRepositoryImpl(jdbcTemplate());

        /*InMemory impl*/
        //return new InMemoryEventRepositoryImpl();
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImpl();
    }

    @Bean
    public TicketRepository ticketRepository() {
        //return new InMemoryTicketRepositoryImpl();
        return new JdbcTicketRepository(jdbcTemplate());
    }

    @Bean
    public DiscountStrategy birthdayStrategy() {
        return new BirthdayStrategy();
    }

    @Bean
    public DiscountStrategy ticketCountStrategy() {
        return new TicketCountStrategy();
    }

    @Bean
    public DiscountService discountService() {
        DiscountServiceImpl discountService = new DiscountServiceImpl();
        discountService.setStrategies(Arrays.asList(birthdayStrategy(), ticketCountStrategy()));
        return discountService;
    }

    @Bean
    public BookingService bookingService() {
        return new BookingServiceImpl(ticketRepository(), discountService(), auditoriumService());
    }

    @Bean
    public AuditoriumRepository auditoriumRepository() {
        return new JdbcAuditoriumRepositoryImpl(jdbcTemplate());

        /*InMemory impl*/
//        return new InMemoryAuditoriumRepositoryImpl(
//                Arrays.asList(names),
//                numberOfSeats,
//                vipSeats.values().stream().collect(Collectors.toList()));
    }

    @Bean
    public AuditoriumService auditoriumService() {
        return new AuditoriumServiceImpl();
    }

    @Bean
    public CounterAspect counterAspect() {
        return new CounterAspect(jdbcTemplate());
    }

    @Bean
    public DiscountAspect discountAspect() {
        return new DiscountAspect(jdbcTemplate());
    }

    @Bean
    public LuckyWinnerAspect luckyWinnerAspect() {
        return new LuckyWinnerAspect();
    }
}
