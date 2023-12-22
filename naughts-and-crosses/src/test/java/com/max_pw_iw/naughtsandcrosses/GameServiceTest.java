package com.max_pw_iw.naughtsandcrosses;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.max_pw_iw.naughtsandcrosses.dto.GameRequest;
import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.repository.GameRepository;
import com.max_pw_iw.naughtsandcrosses.repository.UserRepository;
import com.max_pw_iw.naughtsandcrosses.service.GameServiceImpl;
import com.max_pw_iw.naughtsandcrosses.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void CreateGameTest(){
        User user = new User("bingus", "bingus_password");
        Game game = new Game(true, true);
        when(userService.getUser("bingus")).thenReturn(user);
        when(gameRepository.save(game)).thenReturn(game);
        when(userRepository.save(user)).thenReturn(user);

        GameRequest gameRequest = new GameRequest( true, true);



        game.setPrimaryUser(user);

        Game result = gameService.createGame(gameRequest, "bingus");

        verify(gameRepository, times(1)).save(game);
    }
}
