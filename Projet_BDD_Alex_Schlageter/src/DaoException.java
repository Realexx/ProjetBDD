import java.sql.SQLException;

public class DaoException extends Exception {
    public DaoException(Throwable cause){
        super(cause);
    }
    public DaoException(String message){
        super(message);
    }
}
