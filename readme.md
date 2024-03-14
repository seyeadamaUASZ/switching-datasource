switch datasource est un moyen de jongler sur plusieurs datasources
pour exploiter des données venant de différentes bases de données

pour les configs les plus importants, il faut définir vos properties au niveau de application.properties

stack:  
Spring-Boot 2. vers 3  
Maven ou Gradle  
JPA  
Lombok  
MySql ou base de données de votre choix

-----application.properties-----------------  
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

#Database Name List
spring.database-names.list =national,international

#student database credentials
spring.datasource.national.url=jdbc:mysql://localhost:3306/db_national
spring.datasource.national.username=root
spring.datasource.national.password=
spring.datasource.national.driver=com.mysql.jdbc.Driver

#college database credentials
spring.datasource.international.url=jdbc:mysql://localhost:3306/db_international
spring.datasource.international.username=root
spring.datasource.international.password=
spring.datasource.international.driver=com.mysql.jdbc.Driver
--- -----------------------------------------------------  
Définissez les fichiers config  
D'abord, on définit la classe AbstractRouting pour gérer le lookup des datasources définies  
au niveau de la liste  **"spring.database-names.list =national,international"**  

**AbstractRoutingDatasourceImpl.java**

`private static final ThreadLocal<String> DATABASE_NAME= new ThreadLocal<>();`

    public AbstractRoutingDataSourceImpl(DataSource dataSource, Map<Object,Object> targetDataSource){
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }

    public static void setDatabaseName(String key){
        DATABASE_NAME.set(key);
    }
    public static String getDatabaseName(){
        return DATABASE_NAME.get();
    }
    public static void removeDatabaseName(){
        DATABASE_NAME.remove();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DATABASE_NAME.get();
    }

Cette classe nous permet de récupérer la datasource courante ou même de modifier de datasource  
avec **setDatabaseName()**  

La classe DynamicDatabaseRouter permet de charger les propriétés requises pour la dataSource choisie:  
En premier on charge les propriétés d'une des datasources par défaut:  

private Map<Object,Object> getTargetDataSources() {

        //loading the database names to a list from application.properties file
        List<String> databaseNames = environment.getProperty("spring.database-names.list",List.class);
        Map<Object,Object> targetDataSourceMap = new HashMap<>();

        for (String dbName : databaseNames) {

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getProperty(PROPERTY_PREFIX + dbName + ".driver"));
            dataSource.setUrl(environment.getProperty(PROPERTY_PREFIX + dbName + ".url"));
            dataSource.setUsername(environment.getProperty(PROPERTY_PREFIX + dbName + ".username"));
            dataSource.setPassword(environment.getProperty(PROPERTY_PREFIX + dbName + ".password"));
            targetDataSourceMap.put(dbName,dataSource);

        }
        targetDataSourceMap.put("default",targetDataSourceMap.get(databaseNames.get(0)));
        return targetDataSourceMap;
    }


`@Bean
@Primary
@Scope("prototype")
public AbstractRoutingDataSourceImpl dataSource(){
Map<Object,Object> targetDataSource =getTargetDataSources();
return new AbstractRoutingDataSourceImpl((DataSource) targetDataSource.get("default"),targetDataSource);
}`

Pour customiser le switch sur plusieurs datasources, il faut créer une annotation customiser  
et à l'aide de spring AOP, on gere le changement de datasource avant execution de la méthode,
on récupére le datasource souhaité et les propriétés seront chargées  

`@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchDataSource {
    String value() default "";
}`

Le code de l'aspect sur le changement de datasource :

`@Pointcut("@annotation(com.sid.gl.switchingdatasource.config.SwitchDataSource)")
public void annotationPointCut(){}
@Before("annotationPointCut()")
public void before(JoinPoint joinPoint){
MethodSignature signature = (MethodSignature) joinPoint.getSignature();
Method method = signature.getMethod();
SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);
if(annotation!=null){  
AbstractRoutingDataSourceImpl.setDatabaseName(annotation.value());
logger.info("Switch datasource to [{}] in method [{}]",
annotation.value(),joinPoint.getSignature());
}
}`

Exemple d'utilisation de l'annotation :  

@Service
public class TestService {
@Autowired
private UserNationalRepository userNationalRepository;

    @Autowired
    private UserInternationalRepository userInternationalRepository;

    @SwitchDataSource(value = "national")
    public List<UserNational> getAllUserNational(){
        return userNationalRepository.findAll();
    }
    @SwitchDataSource(value = "international")
    public List<UserInternational> getAllUserInternational(){
        return userInternationalRepository.findAll();
    }
}



