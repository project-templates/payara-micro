package sample.web;

import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.domain.Sample;

@Stateless
@Path("hello")
public class HelloResource {
    private static final Logger logger = LoggerFactory.getLogger(HelloResource.class);
    
    @PersistenceContext(unitName="SampleUnit")
    private EntityManager em;
    
    @GET
    public String hello() {
        logger.info("Hello Payara Micro!!");
        
        TypedQuery<Sample> query = this.em.createNamedQuery(Sample.FIND_ALL, Sample.class);
        
        return query.getResultList().stream().map(Sample::toString).collect(Collectors.joining("\n"));
    }
}
