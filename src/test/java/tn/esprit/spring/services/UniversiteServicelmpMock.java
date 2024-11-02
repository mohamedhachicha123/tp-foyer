package tn.esprit.spring.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class UniversiteServicelmpMock {
    @Mock
    UniversiteRepository universiteRepository;

    @InjectMocks
    UniversiteServiceImpl universityService;
    Universite universite=new Universite("u1","1");
    List<Universite> listuniversites = new ArrayList<>() {
        {
            add(new Universite("U2", "1"));
            add(new Universite("U3", "2"));

        }
    };
    @Test
    @Order(1)
    void testRetrieveu() {
        Mockito.when(universiteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(universite));
        Universite universite1 = universityService.retrieveUniversite(1L);
        Assertions.assertNotNull(universite1);
    }
    @Test
    @Order(2)
    void testRetrieveAllU() {
        Mockito.when(universiteRepository.findAll()).thenReturn(listuniversites);
        List<Universite> listU = universityService.retrieveAllUniversites();
        Assertions.assertEquals(2, listU.size());
    }

    @Test
    @Order(3)
    void testAddu() {
        Universite b = new Universite();
        b.setNomUniversite("UTest");

        Mockito.when(universiteRepository.save(Mockito.any(Universite.class)))
                .thenAnswer(invocation -> {
                    Universite savedU = invocation.getArgument(0);
                    listuniversites.add(savedU);
                    return savedU;
                });

        universityService.addUniversite(b);

        Assertions.assertEquals(3, listuniversites.size());
    }


}
