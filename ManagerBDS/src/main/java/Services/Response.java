package Services;

public class Response {

    public Response() {
    }

    public void onSuccessAddRent() {
        System.out.println("Add Success");
    }

    public void onFailAddRent(String message) {
        System.out.println(" Add Fail , because : " + message);
    }

}
