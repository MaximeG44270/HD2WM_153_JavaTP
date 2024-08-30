package eni.tp.app.eni_app.bll;

public class ManagerResponse<T> {

    public String code;
    public String message;
    public T data;

    public static <T> ManagerResponse performResponse(String code, String message, T data) {
        ManagerResponse<T> response = new ManagerResponse<>();
        response.code = code;
        response.message = message;
        response.data = data;

        //Afficher la réponse métier dans la console/log (avant de return réponse)
        System.out.printf("Reponse metier - code : %s - Message: %s%n", response.code, response.message);
        return response;
    }
}