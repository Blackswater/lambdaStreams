public class Record {
    private int id;
    private String source;
    private String destination;
    private String type;
    private int weight;
    private int sorter;
    private String customs;
    private String extendedSecurityCheck;

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getSorter() {
        return sorter;
    }

    public String getCustoms() {
        return customs;
    }

    public String getExtendedSecurityCheck() {
        return extendedSecurityCheck;
    }

    public Record(int id, String source, String destination,
                  String type, int weight, int sorter, String customs,
                  String extendedSecurityCheck) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.weight = weight;
        this.sorter = sorter;
        this.customs = customs;
        this.extendedSecurityCheck = extendedSecurityCheck;


    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(source).append(";").append(destination).append(";");
        stringBuilder.append(type).append(";").append(weight).append(";").append(sorter).append(";");
        stringBuilder.append(customs).append(";").append(extendedSecurityCheck);
        return stringBuilder.toString();


    }
}