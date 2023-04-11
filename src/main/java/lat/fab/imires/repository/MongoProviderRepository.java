package lat.fab.imires.repository;

import lat.fab.imires.model.Client;
import lat.fab.imires.model.Imi;
import lat.fab.imires.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.ArrayElemAt.arrayOf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MongoProviderRepository implements ProviderRepository {

    @Autowired
    private DataProviderRepository providerRepository;
    private final ReactiveMongoTemplate rxMongoTemplate;

    public MongoProviderRepository(ReactiveMongoTemplate rxMongoTemplate) {
        this.rxMongoTemplate = rxMongoTemplate;
    }

    @Override
    public Mono<Provider> save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Mono<Void> remove(String id) {
        return providerRepository.deleteById(id);
    }

    @Override
    public Flux<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Mono<Provider> findById(String id) {
        return providerRepository.findById(id);
    }

    @Override
    public Mono<Provider> findByEmail(String email) {
        return providerRepository.findByEmail(email).next();
    }

    // threshold: minimum value to consider a client to need a provider
    @Override
    public Flux<Provider> findSuggestedProviders(Client c, int threshold) {
        // get low-score vars for client c
        if (c.getImis().isEmpty()) { return Flux.empty(); }

        Imi lastImi = c.getImis().get(c.getImis().size() - 1);
        List<Integer> weakVars = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : lastImi.getVars().entrySet()) {
            if (entry.getValue() <= threshold) {
                weakVars.add(entry.getKey());
            }
        }

        // find providers that give services on those low-score vars (imi var >= 4)
        // select providers that offer services in those low-score areas
        List<Criteria> criterias = new ArrayList<>();
        Criteria criteria = new Criteria();
        for (Integer index : weakVars) {
            criterias.add(
                    new Criteria(String.format("services.%d", index)).not().size(0)
                            .and("imis").not().size(0)
                            .and(String.format("lastImi.vars.%d", index)).gte(4)
            );
        }

        AddFieldsOperation addStage = Aggregation.addFields().addField("lastImi")
                                        .withValue(arrayOf("imis").elementAt(-1)).build();

        MatchOperation matchStage = Aggregation.match(new Criteria().orOperator(
                                                                criterias
                                                        ));

        /*ProjectionOperation projectStage = Aggregation.project("name", "participants", "lastModified", "enabled", "visibility",
                "place", "dateTime", "timeZone", "hasStartTime", "description",
                "infoUrl", "tags", "extraUrls", "sdgs")
                //.and(filter("participants")
                //    .as("participant")
                //    .by(valueOf("participant.u_id").equalToValue(userId))
                //    ).as("participants")
                .and("participants")
                .size()
                .as("nParticipants"); */
        //SortOperation sortStage = Aggregation.sort(Sort.Direction.DESC, "lastModified");

        TypedAggregation<Provider> aggregation = Aggregation.newAggregation(Provider.class, addStage, matchStage);
        return this.rxMongoTemplate.aggregate(aggregation, Provider.class);
    }

    @Override
    public Flux<Provider> findByCountriesContaining(String country) {
        return this.providerRepository.findByCountriesContaining(country);
    }

    @Override
    public Flux<Provider> findByIndustriesContaining(String industry) {
        return this.providerRepository.findByIndustriesContaining(industry);
    }

}
