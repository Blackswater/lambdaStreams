import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Application {
    private List<Record> records = new ArrayList<>();

    public static void main(String... args) {
        Application app = new Application();
        app.execute();
    }

    public void loadRecords() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data/records.csv"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                records.add(new Record(Integer.parseInt(strings[0]), strings[1], strings[2], strings[3], Integer.parseInt(strings[4]),
                        Integer.parseInt(strings[5]), strings[6], strings[7]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // count
    public long executeSQL01() {
        long count = records.stream().count();
        System.out.println("Count :" + count);
        return count;
    }

    // count, where
    public long executeSQL02() {
        long count = records.stream().filter(x -> x.getSource().equals("a") && x.getDestination().equals("g")
                && x.getType().equals("n") && x.getWeight() >= 20 && x.getSorter() <= 5).count();
        System.out.println(count);
        return count;
    }

    // count, where, in
    public long executeSQL03() {
        long count = records.stream().filter(x -> x.getSource().equals("a") || x.getSource().equals("c")).filter(x -> x.getDestination().equals("g")).filter(x -> x.getType().equals("e")).filter(x -> x.getCustoms().equals("y")).count();
        System.out.println(count);
        return count;
    }

    // count, where, not in
    public long executeSQL04() {
        long count = records.stream().filter(x -> x.getSource().equals("b")).filter(x -> !x.getDestination().equals("f")
                && !x.getDestination().equals("h")).filter(x -> x.getType().equals("n")).filter(x -> x.getCustoms().equals("n")).count();
        System.out.println(count);
        return count;
    }

    // id, where, in, order by desc limit
    public List<Integer> executeSQL07() {
        List<Integer> results = records.stream().filter(x -> x.getSource().equals("b") || x.getSource().equals("c")).filter(x -> x.getDestination().equals("g")).filter(x -> x.getType().equals("n")).filter(x -> x.getSorter() <= 5)
                .filter(x -> x.getCustoms().equals("y")).filter(x -> x.getExtendedSecurityCheck().equals("y")).sorted(Comparator.comparing(Record::getWeight)
                        .reversed()).limit(3).map(Record::getId).collect(Collectors.toList());
        System.out.println("SQL 07: " + results.toString());
        return results;

    }

    // id, where, in, order by desc, order by asc
    public List<Integer> executeSQL08() {
        List<Integer> results = records.stream().filter(x -> x.getSource().equals("a") || x.getSource().equals("d"))
                .filter(x -> x.getDestination().equals("f") || x.getDestination().equals("e")).filter(x -> x.getType().equals("b"))
                .filter(x -> x.getWeight() >= 29).filter(x -> x.getCustoms().equals("y")).filter(x -> x.getExtendedSecurityCheck().equals("y"))
                .sorted(Comparator.comparing(Record::getWeight).reversed().thenComparing(Record::getDestination))
                .map(Record::getId).collect(Collectors.toList());
        System.out.println("SQL 08: " + results.toString());
        return results;
    }

    // count, group by
    public Map<String, Long> executeSQL09() {
        Map<String, Long> result = records.stream().collect(Collectors.groupingBy(Record::getCustoms, Collectors.counting()));
        System.out.println("SQL 09: " + result.toString());
        return result;
    }

    // count, where, group by
    public Map<Integer, Long> executeSQL10() {
        Map<Integer, Long> result = records.stream().filter(x -> x.getCustoms().equals("y") && x.getExtendedSecurityCheck().equals("y"))
                .collect(Collectors.groupingBy(Record::getSorter, Collectors.counting()));
        System.out.println("SQL 10: " + result.toString());
        return result;

    }

    // count, where, in, group by
    public Map<String, Long> executeSQL11() {
        Map<String, Long> result = records.stream().filter(x -> x.getSource().equals("c")).filter(x -> x.getDestination().equals("f") || x.getDestination().equals("g")
                || x.getDestination().equals("h")).filter(x -> x.getType().equals("n")).filter(x -> x.getCustoms().equals("y")).filter(x -> x.getExtendedSecurityCheck().equals("n"))
                .collect(Collectors.groupingBy(Record::getDestination, Collectors.counting()));
        System.out.println("SQL 1: " + result);
        return result;

    }

    // count, where, not in, group by
    public Map<String, Long> executeSQL12() {
        Map<String, Long> result = records.stream().filter(x -> x.getSource().equals("a")).filter(x -> x.getDestination().equals("f")).filter(x -> !x.getType().equals("b") && !x.getType().equals("e"))
                .filter(x -> x.getCustoms().equals("n")).filter(x -> x.getSorter() == 8).collect(Collectors.groupingBy(Record::getExtendedSecurityCheck, Collectors.counting()
                ));
        System.out.println("SQL 12: " + result);
        return result;
    }

    // sum, where, not in, in, group by
    public Map<Integer, Integer> executeSQL13() {
        Map<Integer, Integer> result = records.stream().filter(x -> !x.getSource().equals("a") && !x.getSource().equals("c")).filter(x -> x.getDestination().equals("h")).filter(x -> x.getSorter() == 1 ||
                x.getSorter() == 3 || x.getSorter() == 5 || x.getSorter() == 6).filter(x -> x.getCustoms().equals("y")).filter(x -> x.getExtendedSecurityCheck().equals("n"))
                .collect(Collectors.groupingBy(Record::getSorter, Collectors.summingInt(Record::getWeight)));
        System.out.println("SQL 13: " + result);
        return result;
    }

    // avg, where, in, in, group by
    public Map<String, Double> executeSQL14() {
        Map<String, Double> result = records.stream().filter(x -> x.getSource().equals("a") || x.getSource().equals("b")).filter(x -> x.getDestination().equals("f")
                || x.getDestination().equals("h")).filter(x -> x.getExtendedSecurityCheck().equals("n")).collect(Collectors.groupingBy(Record::getDestination, Collectors.averagingInt(Record::getWeight)));
        System.out.println("SQL 14: " + result);
        return result;
    }

    // sum, where, in
    public long executeSQL05() {
        long sum = records.stream().filter(x -> x.getSource().equals("a") || x.getSource().equals("c")).filter(x -> x.getDestination().equals("f")
                || x.getDestination().equals("h")).filter(x -> x.getType().equals("e")).filter(x -> x.getCustoms().equals("n")).mapToInt(Record::getWeight).sum();
        System.out.println("SQL 05: " + sum);
        return sum;
    }

    // avg, where, not in
    public OptionalDouble executeSQL06() {
        OptionalDouble avg = records.stream().filter(x -> x.getSource().equals("a") && !x.getDestination().equals("f") || !x.getDestination().equals("g")
                && x.getType().equals("n") && x.getCustoms().equals("y")).mapToDouble(Record::getWeight).average();
        System.out.println("SQL 06 : " + avg);
        return avg;
    }

    private void execute() {
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


}