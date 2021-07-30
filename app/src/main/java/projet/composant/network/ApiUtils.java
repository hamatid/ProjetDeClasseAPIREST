package projet.composant.network;

import projet.composant.service.ProgrammerService;

public class ApiUtils {
    public ApiUtils() {
    }

    public static final String API_URL = "http://api.relwendez.site/agribio/public/";

    public static ProgrammerService getProgrammerService(){
        return RetrofitClient.getClient(API_URL).create(ProgrammerService.class);

    }


}
