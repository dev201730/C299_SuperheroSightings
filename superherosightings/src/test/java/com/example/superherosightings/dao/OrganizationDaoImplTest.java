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
public class OrganizationDaoImplTest {
    
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
    
    public OrganizationDaoImplTest() {
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
    public void testGetOrganizationById() {
    }
    
    @Test
    public void testGetAndAddOrganization(){

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
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Organization ABC");
        organization.setAttrHero(true);
        organization.setDescription("This is ABC.org");
        organization.setAddress("London,UK");
        organization.setContact("444387297208");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
    }

    
    @Test
    public void testGetAllOrganizations() {
        
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
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Organization ABC");
        organization.setAttrHero(true);
        organization.setDescription("This is ABC.org");
        organization.setAddress("London,UK");
        organization.setContact("444387297208");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        // Organization 2
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test name2");
        superpower2.setDescription("Test description2");
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
        
        sightings2.add(sighting2);
        
        hero2.setSightings(sightings2);
        
        heroDao.updateHero(hero2);
        
        List<Hero> heros2 = new ArrayList<>();        
        heros2.add(hero2);
        
        Organization organization2 = new Organization();
        organization2.setName("Test name2");
        organization2.setAttrHero(false);
        organization2.setDescription("Test description2");
        organization2.setAddress("Test address2");
        organization2.setContact("Test contact2");
        organization2.setMembers(heros2);
        organization2 = organizationDao.addOrganization(organization2);
        
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }

  
    @Test
    public void testAddOrganization() {
    }

  
    @Test
    public void testUpdateOrganization() {
        
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
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Organization ABC");
        organization.setAttrHero(true);
        organization.setDescription("This is ABC.org");
        organization.setAddress("London,UK");
        organization.setContact("444387297208");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);

        organization.setName("Organization XYZ");
        organization.setAttrHero(true);
        organization.setDescription("This is XYZ.org");
        organization.setAddress("Birmingham,UK");
        organization.setContact("4438731798909");
        
        organizationDao.updateOrganization(organization);
        assertNotEquals(organization,fromDao);
        
        fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
    }

   
    @Test
    public void testDeleteOrganizationById() {
        
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
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Organization ABC");
        organization.setAttrHero(true);
        organization.setDescription("This is ABC.org");
        organization.setAddress("London,UK");
        organization.setContact("444387297208");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
        
        organizationDao.deleteOrganizationById(organization.getId());
        
        fromDao = organizationDao.getOrganizationById(organization.getId());
        assertNull(fromDao);
    }

  
    @Test
    public void testGetOrganizationsForHero() {
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
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Organization ABC");
        organization.setAttrHero(true);
        organization.setDescription("This is ABC.org");
        organization.setAddress("London,UK");
        organization.setContact("444387297208");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        // Organization 2
        
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
        
        sightings2.add(sighting2);
        
        hero2.setSightings(sightings2);
        
        heroDao.updateHero(hero2);
        
        List<Hero> heros2 = new ArrayList<>();        
        heros2.add(hero2);
        
        Organization organization2 = new Organization();
        organization2.setName("Test name2");
        organization2.setAttrHero(false);
        organization2.setDescription("Test description2");
        organization2.setAddress("Test address2");
        organization2.setContact("Test contact2");
        organization2.setMembers(heros2);
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> fromDao = organizationDao.getOrganizationsForHero(hero);
        assertTrue(fromDao.contains(organization));
        assertFalse(fromDao.contains(organization2));
    }
    
}
