package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class BlocServiceImplMockTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    Bloc bloc = new Bloc("bloc1", 5L);
    List<Bloc> listBlocs = new ArrayList<>() {
        {
            add(new Bloc("bloc2", 5L));
            add(new Bloc("bloc3", 5L));

        }
    };

    @Test
    @Order(1)
    void testRetrieveBloc() {
        Mockito.when(blocRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bloc));
        Bloc bloc1 = blocService.retrieveBloc(1L);
        Assertions.assertNotNull(bloc1);
    }

    @Test
    @Order(2)
    void testRetrieveAllBlocs() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);
        List<Bloc> listB = blocService.retrieveAllBlocs();
        Assertions.assertEquals(2, listB.size());
    }

    @Test
    @Order(3)
    void testAddBloc() {
        Bloc b = new Bloc();
        b.setNomBloc("blocTest");

        Mockito.when(blocRepository.save(Mockito.any(Bloc.class)))
                .thenAnswer(invocation -> {
                    Bloc savedBloc = invocation.getArgument(0);
                    listBlocs.add(savedBloc);
                    return savedBloc;
                });

        blocService.addBloc(b);

        Assertions.assertEquals(3, listBlocs.size());
    }

}
