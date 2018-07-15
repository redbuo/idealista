package com.idealista.drone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DriverTest {

    private static Mocks mocks;
    @Mock
    private DriverImpl drone;
    private List<String> expectedRange1;
    private List<String> expectedRange2;
    private List<String> expectedRangeN;

    @BeforeClass
    public static void initTest() throws IOException {
        mocks = new Mocks();
        mocks.initTestUrbanizacionesMap("urbanMap.json");
    }

    @Before
    public void initMockito() {
        Mockito.when(drone.obtenerAdyacente(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenAnswer(invocation -> mocks.obtenerAdyacente((String) invocation.getArguments()[0], (Direccion) invocation.getArguments()[1]));
        Mockito.when(drone.obtenerIdentificador(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenAnswer(invocation -> mocks.obtenerIdentificador((Double) invocation.getArguments()[0], (Double) invocation.getArguments()[1]));
        Mockito.when(drone.obtenerUrbanizaciones(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenCallRealMethod();
        expectedRange1 = Arrays.asList("id_Urb_13", "id_Urb_8", "id_Urb_9", "id_Urb_14", "id_Urb_19", "id_Urb_18", "id_Urb_17", "id_Urb_12", "id_Urb_7");
        List<String> expectedRange2Extra = Arrays.asList("id_Urb_2", "id_Urb_3", "id_Urb_4", "id_Urb_5", "id_Urb_10", "id_Urb_15", "id_Urb_20", "id_Urb_25", "id_Urb_24", "id_Urb_23", "id_Urb_22", "id_Urb_21", "id_Urb_16", "id_Urb_11", "id_Urb_6", "id_Urb_1");
        expectedRange2 = new ArrayList<>();
        expectedRange2.addAll(expectedRange1);
        expectedRange2.addAll(expectedRange2Extra);
        expectedRangeN = new ArrayList<>();
        expectedRangeN.addAll(expectedRange2);
        expectedRangeN.add(Mocks.NO_URB_AVAILABLE);
    }

    @Test
    public void testRango1() {
        List<String> urbanizaciones = drone.obtenerUrbanizaciones(40.41604373378076, -3.696166640364254, 1);
        Assert.assertEquals(expectedRange1.size(), urbanizaciones.size());
        Assert.assertTrue(urbanizaciones.containsAll(expectedRange1));
    }

    @Test
    public void testRango2() {
        List<String> urbanizaciones = drone.obtenerUrbanizaciones(40.41604373378076, -3.696166640364254, 2);
        Assert.assertEquals(expectedRange2.size(), urbanizaciones.size());
        Assert.assertTrue(urbanizaciones.containsAll(expectedRange2));
    }

    @Test
    public void testRangoN() {
        List<String> urbanizaciones = drone.obtenerUrbanizaciones(40.41604373378076, -3.696166640364254, 3);
        Assert.assertEquals(expectedRangeN.size(), urbanizaciones.size());
        Assert.assertTrue(urbanizaciones.containsAll(expectedRangeN));
    }
}
