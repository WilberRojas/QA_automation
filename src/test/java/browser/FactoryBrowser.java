package browser;

public class FactoryBrowser {
    /**
     *
     * @param browserType
     * @return
     */
    public static IBrowser make(String browserType){
        IBrowser browser;
        switch (browserType.toLowerCase()){
            case "chrome":
                browser= new Chrome();
                break;
            case "headless":
                browser=new Headless();
                break;
            default:
                browser= new Firefox();
                break;
        }
        return browser;
    }

}