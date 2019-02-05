import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Application  implements IQuery{
    private List<Record> records = new ArrayList<>();

    public List<Record> loadRecords() {
        try {
            return  records;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // count
    public void executeSQL01() {
        long count = records.stream().count();
        System.out.println("Count :" +count);
    }

    // count, where
    public void executeSQL02() {
        long count =  records.stream().filter(x-> x.getSource().equals("a") && x.getDestination().equals("g")
                && x.getType().equals("n") && x.getWeight() >= 20 && x.getSorter() <= 5).count();
        System.out.println(count);
    }

    // count, where, in
    public void executeSQL03() {
        long count = records.stream().filter(x -> x.getSource().equals("a") || x.getSource().equals("c")
                && x.getDestination().equals("g") && x.getType().equals("e") && x.getCustoms().equals("y")).count();
        System.out.println(count);
    }

    // count, where, not in
    public void executeSQL04() {
        long count = records.stream().filter(x -> x.getSource().equals("b") && !x.getDestination().equals("f")
                || !x.getDestination().equals("h") && x.getType().equals("n")
                && x.getCustoms().equals("n")).count();
        System.out.println(count);
    }


    // id, where, in, order by desc limit
    public void executeSQL05() {
        List<Integer> results = records.stream().filter(x-> x.getSource().equals("b") || x.getSource().equals("c") &&
                x.getDestination().equals("g") && x.getType().equals("n") && x.getSorter() <= 5 && x.getCustoms().equals("y")
                && x.getExtendedSecurityCheck().equals("y")).sorted(Comparator.comparing(Record::getWeight)
                .reversed()).limit(3).map(Record::getId).collect(Collectors.toList());
        System.out.println(results.toString());


    }

    // id, where, in, order by desc, order by asc
    public void executeSQL06() {
        List<Integer> results = records.stream().filter(x->x.getSource().equals("a") || x.getSource().equals("d")
        && x.getDestination().equals("f") || x.getDestination().equals("e") && x.getType().equals("b")
        && x.getWeight() >=29 && x.getCustoms().equals("y") && x.getDestination().equals("y"))
                .sorted(Comparator.comparing(Record::getWeight).reversed().thenComparing(Record::getDestination))
                .map(Record::getId).collect(Collectors.toList());
        System.out.println(results.toString());
    }

    // count, group by
    public void executeSQL07() {
        Map<String, Long> result = records.stream().collect(Collectors.groupingBy(Record::getCustoms, Collectors.counting()));
        System.out.println(result.toString());
    }

    // count, where, group by
    public void executeSQL08() {
        Map<Integer, Long> result =  records.stream().filter(x->x.getCustoms().equals("y") && x.getExtendedSecurityCheck().equals("y"))
        .collect(Collectors.groupingBy(Record::getSorter, Collectors.counting()));
        System.out.println(result.toString());

    }

    // count, where, in, group by
    public void executeSQL09() {

    }

    // count, where, not in, group by
    public void executeSQL10() {
    }

    // sum, where, not in, in, group by
    public void executeSQL11() {
    }

    // avg, where, in, in, group by
    public void executeSQL12() {
    }

    // sum, where, in
    public void executeSQL13() {
        long sum = records.stream().filter(x->x.getSource().equals("a") || x.getSource().equals("c") && x.getDestination().equals("f")
        || x.getDestination().equals("h") && x.getType().equals("e") && x.getCustoms().equals("n")).mapToInt(Record::getWeight).sum();
        System.out.println(sum);
    }

    // avg, where, in, in, group by
    public void executeSQL14() {
    }

    public void execute() {
        loadRecords();
        executeSQL01();
        executeSQL02();
        executeSQL03();
        executeSQL04();
        executeSQL05();
        executeSQL06();
        executeSQL07();
        executeSQL08();
        executeSQL09();
        executeSQL10();
        executeSQL11();
        executeSQL12();
        executeSQL13();
        executeSQL14();
    }

    public static void main(String... args) {

    }
}