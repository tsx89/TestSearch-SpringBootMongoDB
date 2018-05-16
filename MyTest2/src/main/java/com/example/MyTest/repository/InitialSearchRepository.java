package com.example.MyTest.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.regex.Pattern;
import org.springframework.stereotype.Repository;

import com.example.MyTest.model.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class InitialSearchRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection<Entity> searchEntity(String streetNo,  String streetName, String city, String postCode) {

        log.info("streetNo: " +  streetNo);
        log.info("streetName: " +  streetName);
        log.info("city: " +  city);
        log.info("postCode: " +  postCode);


        String pattern = "(\\d+)(.*)";
//
//        // Create a Pattern object
//        Pattern pattern_StreetNo = Pattern.compile(pattern);
//        Matcher matcher = pattern.matcher(input)

        return mongoTemplate.find(Query.query(new Criteria()
                                .andOperator(
                                        Criteria.where("STREET_NO").is(streetNo),
                                        Criteria.where("STREET_NAME").regex(streetName, "i"))
                         ), Entity.class);


//        if ( streetNo != null ){
//                if ( city != null ){
//                    if ( postCode!= null ){
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NO").regex(streetNo, "i"),
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POSTAL_MUNICIPALITY_NAME").regex(city, "i"),
//                                        Criteria.where("POST_CODE").regex(postCode, "i"))
//                         ), Entity.class);
//                    }else{
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NO").regex(streetNo, "i"),
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POSTAL_MUNICIPALITY_NAME").regex(city, "i"))
//                        ), Entity.class);
//                    }
//                }else{
//                    if (postCode != null) {
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NO").regex(streetNo, "i"),
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POST_CODE").regex(postCode, "i"))
//                        ), Entity.class);
//
//                    }else{
//                        log.info(" 11111 ");
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NO").regex(streetNo, "i"),
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"))
//                        ), Entity.class);
//                    }
//
//                }
//
//            }else{
//
//                if ( city != null ){
//                    if ( postCode!= null ){
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POSTAL_MUNICIPALITY_NAME").regex(city, "i"),
//                                        Criteria.where("POST_CODE").regex(postCode, "i"))
//                        ), Entity.class);
//                    }else{
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POSTAL_MUNICIPALITY_NAME").regex(city, "i"))
//                        ), Entity.class);
//                    }
//                }else{
//                    if (postCode != null) {
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"),
//                                        Criteria.where("POST_CODE").regex(postCode, "i"))
//                        ), Entity.class);
//
//                    }else{
//                        return mongoTemplate.find(Query.query(new Criteria()
//                                .andOperator(
//                                        Criteria.where("STREET_NAME").regex(streetName, "i"))
//                        ), Entity.class);
//                    }
//
//                }
//            }


    }

}
