public class Protocol {
    protected String employeeName;
    protected String hoursOfWorkForThisClient;
    protected String date;
    protected String weekOfYear;
    protected String clientsFromList;


    public Protocol(String weekOfYear, String date, String clientsFromList, String hoursOfWorkForThisClient) {
        //this.employeeName = employeeName;
        this.weekOfYear = weekOfYear;
        this.date = date;
        this.clientsFromList = clientsFromList;
        this.hoursOfWorkForThisClient = hoursOfWorkForThisClient;


    }

    @Override
    public String toString() {
        return "Protocol{" +
                "name='" + employeeName + '\'' +
                ", hoursOfWorkForThisClient='" + hoursOfWorkForThisClient + '\'' +
                ", date='" + date + '\'' +
                ", weekOfYear='" + weekOfYear + '\'' +
                ", clientsFromList='" + clientsFromList + '\'' +
                '}';
    }
}
