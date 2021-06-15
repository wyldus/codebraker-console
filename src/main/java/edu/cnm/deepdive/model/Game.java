package edu.cnm.deepdive.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Game {

  @Expose
  private String id;

  @Expose
  private Date created;

  @Expose
  private String pool;

  @Expose
  private int length;

  @Expose
  private int guessCount;

  @Expose
  private boolean solved;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getPool() {
    return pool;
  }

  public void setPool(String pool) {
    this.pool = pool;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getGuessCount() {
    return guessCount;
  }

  public void setGuessCount(int guessCount) {
    this.guessCount = guessCount;
  }

  public boolean isSolved() {
    return solved;
  }

  public void setSolved(boolean solved) {
    this.solved = solved;
  }
}
