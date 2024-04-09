public class AllEvents implements EVENTHANDLER{

    @Override
    public void submitIn(String id) {

    }

    @Override
    public void submitOut(String id) {

    }

    @Override
    public void showEmployees() {

    }

    //constructor
    public AllEvents(){
        GUI gui = new GUI();
        gui.setSubmitIn(this);
        gui.setSubmitOut(this);
        gui.setShowEmployees(this);
    }
}
