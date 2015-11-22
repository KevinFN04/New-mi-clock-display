//Importa la clase estatica Calendar para obtener la fecha actual.
import java.util.Calendar;

/**
 * Write a description of class ClockDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClockDisplay
{
    // Guarda la hora
    private NumberDisplay horas;
    // Guarda los minutos
    private NumberDisplay minutos;
    // Guarda el año
    private NumberDisplay anyo;
    // Guarda el dia
    private NumberDisplay dia;
    // Guarda el mes
    private NumberDisplay mes;
    // Almacena la hora actual con 5 caracteres
    private String horaActual;
    // Permite elegir si estamos en formato 12 horas(true) o 24 horas(false)
    private boolean relojDe12Horas;

    /**
     * Crea un objeto ClockDisplay con hora por defecto 00:00 y la fecha Actual.
     */
    public ClockDisplay()
    {
        Calendar fecha = Calendar.getInstance();
        int diaAc = fecha.get(Calendar.DAY_OF_MONTH);
        int mesAc = fecha.get(Calendar.MONTH); 
        int anyoAc = fecha.get(Calendar.YEAR)%100;
        
        horas = new NumberDisplay(24);
        minutos = new NumberDisplay(60);
        dia = new NumberDisplay(31);
        mes = new NumberDisplay(13);
        anyo = new NumberDisplay(99);
        
        dia.setValue(diaAc);
        mes.setValue(mesAc + 1);
        anyo.setValue(anyoAc);
        
        horaActual = horas.getDisplayValue() + ":" + minutos.getDisplayValue() + "," + dia.getDisplayValue() 
        + "/" + mes.getDisplayValue() + "/" + anyo.getDisplayValue();
    }

    /** 
     * Crea un objeto ClockDisplay con la hora, minutos y fecha dados
     */
    public ClockDisplay (int horasX, int minutosX, int diaX, int mesX, int anyoX, boolean formato)
    {
        horas =   new NumberDisplay(24);
        minutos = new NumberDisplay(60);
        dia = new NumberDisplay(31);
        mes = new NumberDisplay(13);
        anyo = new NumberDisplay(99);
        dia.setValue(diaX);
        mes.setValue(mesX);
        anyo.setValue(anyoX);
        horas.setValue(horasX); 
        minutos.setValue(minutosX);
        relojDe12Horas = formato;
        updateTime();
    }

    /**
     * Fija la hora del reloj a la hora, minutos y fecha dados
     */
    public void setTime(int horaY, int minutoY, int diaY, int mesY, int anyoY)
    {
        horas.setValue(horaY);  
        minutos.setValue(minutoY);
        dia.setValue(diaY);
        mes.setValue(mesY);
        anyo.setValue(anyoY);
        updateTime();
    }

    /**
     * Devuelve la hora y fecha del reloj como una cadena de caracteres
     */
    public String getTime()
    {
        return horaActual;
    }

    /**
     * Hace avanzar la hora 1 minuto
     */
    public void timeTick()
    {
        minutos.increment();
        if ( minutos.getValue() == 0){
            horas.increment();
        }
        if (horas.getValue() == 0 && minutos.getValue() == 0){
            dia.increment();
        }
        if (dia.getValue() == 0){
            dia.increment();
            mes.increment();
        }
        if (mes.getValue() == 0){
            mes.increment();
            anyo.increment();
        }
        
        updateTime();
    }

    /**
     * Actualiza el atributo horaActual siguiendo las normas de un
     * reloj de 12 horas.
     */
    public void updateTime()
    {
        if (relojDe12Horas) {
            String formato = "a.m";
            int horaAhora = horas.getValue(); 
            if (horaAhora >= 12){
                formato = "p.m.";
            }

            if (horaAhora > 12) {
                horaAhora = horaAhora - 12;
            }
            else if (horaAhora == 0) {
                horaAhora = 12;
            }
            horaActual = horaAhora + ":" + minutos.getDisplayValue() + " " + formato + ", " + dia.getDisplayValue() 
            + "/" + mes.getDisplayValue() + "/" + anyo.getDisplayValue();
        }
        else {
            horaActual = horas.getDisplayValue() + ":" + minutos.getDisplayValue() + ", " + dia.getDisplayValue() 
            + "/" + mes.getDisplayValue() + "/" + anyo.getDisplayValue();    
        }
    }
    
    /**
     * Método que permite alternar entre los modos del reloj
     */
    public void changeFormat()
    {
        relojDe12Horas = !relojDe12Horas;
        updateTime();   
    }
}








