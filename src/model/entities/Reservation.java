package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //Static para não ser instanciado um novo sdf para cada objeto reservation que o app tiver

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();//Diferença das duas datas em mS
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);//Converte de mS para dias
    }
    public String updateDates(Date checkIn, Date checkOut){
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)){//Testar se checkin e checkout são antes de agora
           return "Reservation dates for update must be future dates";
        }
        if (!checkOut.after(checkIn)){//Testar se chekout não é antes do checkin
           return "Check-out date must be after check-in date";
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
        return null; //Se retornar nulo não deu nenhum erro / Se retornar alguma String deu erro
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}
