package com.max_pw_iw.naughtsandcrosses.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private long id;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "date_started", nullable = true)
    private LocalDate dateStarted;

    @Column(name = "date_ended", nullable = true)
    private LocalDate dateEnded;

    @Column(name = "last_action_date", nullable = true)
    private LocalDate lastActionDate;

    @NotNull(message = "opponent humanity must be decided")
    @Column(name = "is_opponent_human", nullable = false)
    private boolean isOpponentHuman;
      
    
    @NotNull(message = "player order must be defined")
    @Column(name = "does_primary_user_start", nullable = false)
    private boolean doesPrimaryUserStart;

    @NotNull(message = "player identity must be defined")
    @Column(name = "is_primary_user_Os", nullable = false)
    private boolean isPrimaryUserOs;

    @Column(name = "game_state", nullable = false)
    private GameState gameState = GameState.INITIALIZED;

    @Column(name = "moves", nullable = false)
    private String[] moves = new String[9];

    @ManyToOne(optional = false)
    @JoinColumn(name = "primary_user_id", referencedColumnName = "id")
	private User primaryUser;

    @ManyToOne(optional = true)
    @JoinColumn(name = "secondary_user_id", referencedColumnName = "id")
	private User secondaryUser;


    public Game(boolean isOpponentHuman, boolean doesPrimaryUserStart, boolean isPrimaryUserOs) {
        this.isOpponentHuman = isOpponentHuman;
        this.doesPrimaryUserStart = doesPrimaryUserStart;
        this.isPrimaryUserOs = isPrimaryUserOs;
        if(!isOpponentHuman){
            this.gameState = GameState.ACTIVE;
        }
    }

}
