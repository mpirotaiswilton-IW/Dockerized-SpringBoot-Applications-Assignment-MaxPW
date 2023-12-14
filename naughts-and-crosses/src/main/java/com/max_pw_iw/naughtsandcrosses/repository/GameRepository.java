package com.max_pw_iw.naughtsandcrosses.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.max_pw_iw.naughtsandcrosses.entity.Game;


public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findById(long id);
}
