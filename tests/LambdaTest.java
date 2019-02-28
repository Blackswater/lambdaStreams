import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Inet4Address;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LambdaTest {
    Application app;
    @BeforeEach
    void setup() {
        app = new Application();
        app.loadRecords();
    }

    @Test
    void executeSQL01() {
        assertEquals(1000000, app.executeSQL01());
    }

    @Test
    void executeSQL02() {
        assertEquals(3123, app.executeSQL02());
    }

    @Test
    void executeSQL03() {
        assertEquals(3136, app.executeSQL03());
    }

    @Test
    void executeSQL04() {
        assertEquals(28246, app.executeSQL04());
    }

    @Test
    void executeSQL07() {
        List<Integer> list = new ArrayList<>();
        list.add(357530);
        list.add(59471);
        list.add(136168);
        assertEquals(list, app.executeSQL07());
    }

    @Test
    void executeSQL08() {
        List<Integer> list = new ArrayList<>();
        list.add(158036);
        list.add(188829);
        list.add(196332);
        list.add(289290);
        list.add(937204);
        list.add(491565);
        list.add(500654);
        list.add(108316);
        list.add(282370);
        list.add(422002);
        list.add(540879);
        list.add(563094);
        list.add(625456);
        list.add(685382);
        list.add(252566);
        list.add(495325);
        assertEquals(list, app.executeSQL08());
    }

    @Test
    void executeSQL09() {
        Map<String, Long> map = new HashMap<>();
        map.put("n", 899935l);
        map.put("y", 100065l);
        assertTrue(map.containsKey("n") == app.executeSQL09().containsKey("n"));
        assertTrue(map.containsKey("y") == app.executeSQL09().containsKey("y"));
        assertTrue(map.containsValue(899935) == app.executeSQL09().containsValue(899935));
        assertEquals(map.get("n"), app.executeSQL09().get("n"));
        assertEquals(map.get("y"), app.executeSQL09().get("y"));
        assertTrue(map.containsValue(100065) == app.executeSQL09().containsValue(100065));
        assertEquals(map, app.executeSQL09());
    }

    @Test
    void executeSQL10() {
        Map<Integer, Long> map = new HashMap<>();
        map.put(1,257l);
        map.put(11,252l);
        map.put(6,255l);
        map.put(5,263l);
        map.put(12,231l);
        map.put(9,255l);
        map.put(2,270l);
        map.put(4,254l);
        map.put(7,248l);
        map.put(8,257l);
        map.put(3,230l);
        map.put(10,244l);
        assertEquals(map, app.executeSQL10());
    }

    @Test
    void executeSQL11() {
        Map<String, Long> map = new HashMap<>();
        map.put("f", 1513l);
        map.put("h", 1511l);
        map.put("g", 1498l);
        assertTrue(map.containsKey("f") == app.executeSQL11().containsKey("f"));
        assertTrue(map.containsKey("h") == app.executeSQL11().containsKey("h"));
        assertTrue(map.containsKey("g") == app.executeSQL11().containsKey("g"));
        assertTrue(map.containsValue(1513) == app.executeSQL11().containsValue(1513));
        assertTrue(map.containsValue(1511) == app.executeSQL11().containsValue(1513));
        assertTrue(map.containsValue(1498) == app.executeSQL11().containsValue(1498));
        assertEquals(map, app.executeSQL11());
    }

    @Test
    void executeSQL12() {
        Map<String, Long> map = new HashMap<>();
        map.put("n", 2239l);
        map.put("y", 64l);
        assertTrue(map.containsKey("n") == app.executeSQL12().containsKey("n"));
        assertTrue(map.containsKey("y") == app.executeSQL12().containsKey("y"));
        assertTrue(map.containsValue(2239) == app.executeSQL12().containsValue(2239));
        assertTrue(map.containsValue(64) == app.executeSQL12().containsValue(64));
        assertEquals(map, app.executeSQL12());

    }

    @Test
    void executeSQL13() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,18755);
        map.put(3,18922);
        map.put(6,18739);
        map.put(5,19273);
        assertTrue(map.containsKey(1) == app.executeSQL13().containsKey(1));
        assertTrue(map.containsKey(3) == app.executeSQL13().containsKey(3));
        assertTrue(map.containsKey(6) == app.executeSQL13().containsKey(6));
        assertTrue(map.containsKey(5) == app.executeSQL13().containsKey(5));
        assertTrue(map.containsValue(18755) == app.executeSQL13().containsValue(18755));
        assertTrue(map.containsValue(18922) == app.executeSQL13().containsValue(18922));
        assertTrue(map.containsValue(18739) == app.executeSQL13().containsValue(18739));
        assertTrue(map.containsValue(19273) == app.executeSQL13().containsValue(19273));
        assertEquals(map, app.executeSQL13());
    }

    @Test
    void executeSQL14() {
        Map<String, Double> map = new HashMap<>();
        map.put("f",19.00019860644478 );
        map.put("h",18.989878224974202 );
        assertTrue(map.containsKey("f") == app.executeSQL14().containsKey("f"));
        assertTrue(map.containsKey("h") == app.executeSQL14().containsKey("h"));
        assertTrue(map.containsValue(19.00019860644478) == app.executeSQL14().containsValue(19.00019860644478));
        assertTrue(map.containsValue(18.989878224974202) == app.executeSQL14().containsValue(18.989878224974202));
        assertEquals(map, app.executeSQL14());

    }

    @Test
    void executeSQL05() {
        assertEquals(1072413l, app.executeSQL05());
    }

    @Test
    void executeSQL06() {
        OptionalDouble test = OptionalDouble.of(18.98754523640941);
        assertEquals(test, app.executeSQL06());
    }
}