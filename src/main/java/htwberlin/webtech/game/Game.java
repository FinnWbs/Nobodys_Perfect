package htwberlin.webtech.game;

import htwberlin.webtech.spieler.Spieler;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    @OneToMany(fetch = FetchType.EAGER)
    public List<Spieler> spieler;

    @Column(length = 1000)
    public String currentQuestion;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> possibleAnswers;

    public String givenSongName;

    public Game() {}

    public Game(String gameName, List<Spieler>players) {
        this.name = gameName;
        this.spieler = players;
    }


    public String getCurrentQuestion() {
        return this.currentQuestion;
    }
    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getGivenSongName() {
        return givenSongName;
    }

    public void setGivenSongName(String givenSongName) {
        this.givenSongName = givenSongName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Spieler> getPlayers() {
//        return this.players;
//    }
//
//    public void setPlayers(List<Spieler> players) {
//        this.players = players;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Game)) return false;
//
//        Game game = (Game) o;
//
//        if (getId() != null ? !getId().equals(game.getId()) : game.getId() != null) return false;
//        return getName() != null ? getName().equals(game.getName()) : game.getName() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return getId() != null ? getId().hashCode() : 0;
//    }
//
//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}