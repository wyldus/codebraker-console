package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Game;
import java.io.IOException;
import java.lang.reflect.Proxy;
import retrofit2.Response;

public class GameRepository {

  private final CodebreakerServiceProxy proxy;

  public GameRepository() {
    proxy = CodebreakerServiceProxy.getInstance();
  }

  public Game newGame(String pool, int length) throws IOException {
    Game game = new Game();
    game.setPool(pool);
    game.setLength(length);
    Response<Game> response = proxy.startGame(game).execute();
    if (response.isSuccessful()){
     return response.body();
    } else {
      throw new IllegalArgumentException();
    }

  }

}
