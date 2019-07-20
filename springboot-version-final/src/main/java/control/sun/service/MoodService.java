package control.sun.service;


import control.sun.domain.Mood;

public interface MoodService {

    public Mood save(Mood mood);

    public String asynSave(Mood mood);

}
