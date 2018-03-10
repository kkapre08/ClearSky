package com.egen.clearSky.entity;

import org.springframework.stereotype.Component;

@Component
public class Wind {
    private float speed;
    private float degree;


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
        return "Wind{" +
                "speed=" + speed +
                ", degree=" + degree +
                '}';
    }
}
