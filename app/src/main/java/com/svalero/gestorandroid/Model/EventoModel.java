package com.svalero.gestorandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class EventoModel implements Parcelable {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private double latitud;
    private double longitud;
    private List<String> imagenes;
    private boolean disponible;

    // Constructor vacío necesario para Parcelable
    public EventoModel() {
    }

    // Constructor para crear una instancia de la clase
    public EventoModel(int id, String titulo, String descripcion, String fecha,
                       double latitud, double longitud, List<String> imagenes,
                       boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagenes = imagenes;
        this.disponible = disponible;
    }

    // Métodos para Parcelable
    protected EventoModel(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        fecha = in.readString();
        latitud = in.readDouble();
        longitud = in.readDouble();
        imagenes = in.createStringArrayList();
        disponible = in.readByte() != 0;
    }

    public static final Parcelable.Creator<EventoModel> CREATOR = new Parcelable.Creator<EventoModel>() {
        @Override
        public EventoModel createFromParcel(Parcel in) {
            return new EventoModel(in);
        }

        @Override
        public EventoModel[] newArray(int size) {
            return new EventoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeString(fecha);
        dest.writeDouble(latitud);
        dest.writeDouble(longitud);
        dest.writeStringList(imagenes);
        dest.writeByte((byte) (disponible ? 1 : 0));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
