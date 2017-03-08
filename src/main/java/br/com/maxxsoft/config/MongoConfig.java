package br.com.maxxsoft.config;

import br.com.maxxsoft.repository.impl.PrototypeRepositoryImpl;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 17/02/17.
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.maxxsoft.repository", repositoryBaseClass = PrototypeRepositoryImpl.class)
public class MongoConfig extends AbstractMongoConfiguration {
    private final List<Converter<?, ?>> converters = new ArrayList();
    @Value("${spring.data.mongodb.database}")
    private String dataBaseName;
    @Value("${spring.data.mongodb.host}")
    private String hostDataBase;
    @Value("${spring.data.mongodb.port}")
    private String portDataBase;


    @Override
    protected String getDatabaseName() {
        return dataBaseName;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(hostDataBase, Integer.valueOf(portDataBase));
    }

    @Override
    public CustomConversions customConversions() {
        return super.customConversions();
    }

    @Bean
    public MongoRepositoryFactory getMongoRepositoryFactory() {
        try {
            return new MongoRepositoryFactory(this.mongoTemplate());
        } catch (Exception e) {
            throw new RuntimeException("error creating mongo repository factory", e);
        }
    }

}
