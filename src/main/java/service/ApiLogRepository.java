package service;

import domain.ApiLogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by etheodor on 26/05/2016.
 */

public interface ApiLogRepository extends MongoRepository<ApiLogEntry, String> {



}


