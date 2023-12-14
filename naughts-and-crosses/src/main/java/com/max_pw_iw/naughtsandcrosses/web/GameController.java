package com.max_pw_iw.naughtsandcrosses.web;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.service.GameService;
import com.max_pw_iw.naughtsandcrosses.validation.Move;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    GameService gameService;

	@GetMapping("/{id}")
	public ResponseEntity<Game> GetGameById(@PathVariable Long id) {
		return new ResponseEntity<>(gameService.getGame(id), HttpStatus.OK);
	}

    @PostMapping("/")
	public ResponseEntity<Long> createGame(@RequestBody Game game, Authentication authentication) {
		return new ResponseEntity<>(gameService.createGame(game, authentication.getName()).getId(), HttpStatus.CREATED);
	}

	@PutMapping("join/{id}")
	public ResponseEntity<Long> joinGame(@PathVariable Long id, Authentication authentication) {
		return new ResponseEntity<>(gameService.joinGame(id, authentication.getName()).getId(), HttpStatus.OK);
	}

	// move format: (squareNum)_(primaryUsersTurn==true ? 1 : 2)_(isOsTurn == true ? "O" : "X")

	@PutMapping("/{id}/play/{move}")
	public ResponseEntity<String[]> playMove(@PathVariable Long id, @PathVariable @Move Integer move, Authentication authentication) {
		return new ResponseEntity<>(gameService.addMove(id, move, authentication.getName()).getMoves(),HttpStatus.OK);
	}

}
