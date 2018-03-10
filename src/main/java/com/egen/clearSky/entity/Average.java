package com.egen.clearSky.entity;

public class Average {
    private float humidity;
    private float pressure;
    private float temperature;
    private float timestamp;
    private float speed;
    private float degree;

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Average{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                ", speed=" + speed +
                ", degree=" + degree +
                '}';
    }
}
