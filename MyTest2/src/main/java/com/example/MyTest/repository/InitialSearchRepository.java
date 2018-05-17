package com.example.MyTest.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.example.MyTest.model.Entity;


@Repository
public class InitialSearchRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection<Entity> searchEntity(String streetNo,  String streetName, String city, String postCode) {

    	List<Criteria> criteriaList = new ArrayList<Criteria>();
    	    	 
    	 
    	if ( postCode != null && !postCode.equals("")) {
	        String postcode1 = postCode.substring(0, 3);
	        String postcode2 = postCode.substring(3);
	        log.info("postCode 1: " +  postcode1);
	        log.info("postCode 2 : " +  postcode2); 

	        Criteria criteria = new Criteria()
        			.andOperator(Criteria.where("POSTAL_CODE").regex("^"+postcode1, "i"),
   				         Criteria.where("POSTAL_CODE").regex(postcode2+"$", "i") ); 
	        	criteriaList.add(criteria);
    	}
    	if ( streetNo != null && !streetNo.equals("")) {
    		 log.info("street no: : " +  streetNo);
    		 Criteria criteria = new Criteria()
         			.orOperator( Criteria.where("STREET_NO").is(streetNo), 
         					   Criteria.where("STREET_NO").regex("^"+streetNo+"[a-zA-Z]+"));
         					
 	        criteriaList.add(criteria);
    		 
    	}
    	if ( streetName != null && !streetName.equals("")) {
    		
    		Criteria criteria = new Criteria()
         			.andOperator(Criteria.where("STREET_NAME").regex(streetName.replaceAll("\\*", ".*"), "i"));
         					
 	       criteriaList.add(criteria);
 
    	}
    	if ( city != null && !city.equals("")) {
    		Criteria criteria = new Criteria().
    				andOperator( Criteria.where("UPPER_TIER_MUNICIPALITY").regex(city.replaceAll("\\*", ".*"), "i") ); 
    		criteriaList.add(criteria);	
    	}
    
    	Aggregation agg = null;
    	int numberOfCriteria = criteriaList.size();
    	switch( numberOfCriteria) {
    		case 1: 
    			agg = newAggregation(match(criteriaList.get(0)));
    			break;
    		case 2: 
    			agg = newAggregation( match(criteriaList.get(0)), match(criteriaList.get(1)));
    			break;
    		case 3: 
    			agg = newAggregation( match(criteriaList.get(0)),   
    								 match(criteriaList.get(1)), 
    								 match(criteriaList.get(2)) );
    			break;
    		case 4: 
    			agg = newAggregation( match(criteriaList.get(0)), 
    								match(criteriaList.get(1)), 
    								match(criteriaList.get(2)), 
    								match(criteriaList.get(3)) );
    			break;
    	}
        

		//Convert the aggregation result into a List
		AggregationResults<Entity> groupResults 
			= mongoTemplate.aggregate(agg, Entity.class, Entity.class );
		
		List<Entity> result = groupResults.getMappedResults();
		
		return result;


    	
    	
//        // Create a Pattern object
//        Pattern pattern_StreetNo = Pattern.compile(pattern);
//        Matcher matcher = pattern.matcher(input)

//        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NO").is(streetNo),
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"))
//                         ), Entity.class);




    }

}
