package com.example.superherosightings.dao;

import com.example.superherosightings.models.Hero;
import com.example.superherosightings.models.Location;
import com.example.superherosightings.models.Organization;
import com.example.superherosightings.models.Sighting;
import com.example.superherosightings.models.Superpower;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SightingDaoImplTest {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    public SightingDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Hero> heros = heroDao.getAllHeros();
        for(Hero hero : heros){
            heroDao.deleteHeroById(hero.getId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations){
            locationDao.deleteLocationById(location.getId());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings){
            sightingDao.deleteSightingById(sighting.getId());
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers){
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testGetSightingById() {
    }
    
    @Test
    public void testGetAndAddSighting(){
 
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower.setDescription("Flies at a very high speed.");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setAttrHero(true);
        hero.setName("Superman");
        hero.setDescription("Man the super man");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Kensington");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("This is in Central London");
        location.setAddressInformation("8 High Street");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        
        assertEquals(sighting,fromDao);

    }

   
    @Test
    public void testGetAllSightings() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower.setDescription("Flies at a very high speed.");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setAttrHero(true);
        hero.setName("Superman");
        hero.setDescription("Man the super man");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Kensington");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("This is in Central London");
        location.setAddressInformation("8 High Street");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        // Sighting 2
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Sings");
        superpower2.setDescription("Sings very high notes.");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        
        List<Sighting> sightings2 = new ArrayList<>();        
        
        Hero hero2 = new Hero();
        hero2.setAttrHero(false);
        hero2.setName("Batman");
        hero2.setDescription("This is the Bat Man.");
        hero2.setSuperpowers(superpowers2);
        hero2.setSightings(sightings2);
        hero2 = heroDao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("Milano");
        location2.setLatitude(1.3);
        location2.setLongitude(5.36);
        location2.setDescription("This is in Italy");
        location2.setAddressInformation("10 Via Milano");
        location2 = locationDao.addLocation(location2);
        
        Date date2 = Date.valueOf("2017-03-31");  
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroId(hero2.getId());
        sighting2.setLocation(location2);
        sighting2.setDate(date2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sighting> sightingsDao = sightingDao.getAllSightings();
        assertEquals(2, sightingsDao.size());
        assertTrue(sightingsDao.contains(sighting));
        assertTrue(sightingsDao.contains(sighting2));
    }

    
    @Test
    public void testAssociateLocationsForSightings() {
    }

   
    @Test
    public void testAddSighting() {
    }

   
    @Test
    public void testUpdateSighting() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower.setDescription("Flies at a very high speed.");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setAttrHero(true);
        hero.setName("Superman");
        hero.setDescription("Man the super man");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Kensington");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("This is in Central London");
        location.setAddressInformation("8 High Street");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting,fromDao);
        
        Date date2 = Date.valueOf("1990-01-30"); 
        sighting.setDate(date2);
        
        sightingDao.updateSighting(sighting);
        assertNotEquals(sighting,fromDao);
        
        fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting,fromDao);
    }

    
    @Test
    public void testDeleteSightingById() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower.setDescription("Flies at a very high speed.");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setAttrHero(true);
        hero.setName("Superman");
        hero.setDescription("Man the super man");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Kensington");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("This is in Central London");
        location.setAddressInformation("8 High Street");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting,fromDao);
        
        sightingDao.deleteSightingById(sighting.getId());
        
        fromDao = sightingDao.getSightingById(sighting.getId());
        assertNull(fromDao);
    }

    
    @Test
    public void testGetSightingsForLocation() {
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower.setDescription("Flies at a very high speed.");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setAttrHero(true);
        hero.setName("Superman");
        hero.setDescription("Man the super man");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Kensington");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("This is in Central London");
        location.setAddressInformation("8 High Street");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        // Sighting 2
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Sings");
        superpower2.setDescription("Sings very high notes.");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        
        List<Sighting> sightings2 = new ArrayList<>();        
        
        Hero hero2 = new Hero();
        hero2.setAttrHero(false);
        hero2.setName("Batman");
        hero2.setDescription("This is the Bat Man.");
        hero2.setSuperpowers(superpowers2);
        hero2.setSightings(sightings2);
        hero2 = heroDao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("Milano");
        location2.setLatitude(1.3);
        location2.setLongitude(5.36);
        location2.setDescription("This is in Italy");
        location2.setAddressInformation("10 Via Milano");
        location2 = locationDao.addLocation(location2);
        
        Date date2 = Date.valueOf("2017-03-31");  
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroId(hero2.getId());
        sighting2.setLocation(location2);
        sighting2.setDate(date2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sighting> sightingsDao = sightingDao.getSightingsForLocation(location);
        assertTrue(sightingsDao.contains(sighting));
        assertFalse(sightingsDao.contains(sighting2));
    }
    
}
