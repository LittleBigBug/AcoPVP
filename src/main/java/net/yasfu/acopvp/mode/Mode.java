package net.yasfu.acopvp.mode;

import net.yasfu.acopvp.entities.Arena;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class Mode {

    private ArrayList<Player> players;

    private Arena currentArena;

    private GameState state = GameState.STATE_WAITING;

    private GameState nextState;

    private long roundTimer = 0l;

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void setCurrentArena(Arena arena) {
        this.currentArena = arena;
    }

    public void setGameState(GameState gameState) {
        this.state = gameState;
    }

    public GameState getGameState() {
        return this.state;
    }

    public Arena getCurrentArena() {
        return this.currentArena;
    }

    public boolean isGameActive() {
        return this.state == GameState.STATE_GAME;
    }

    public boolean isFreezeState() {
        switch (this.state) {
            case STATE_PREGAME:
            case STATE_INTERMISSION:
                return true;
        }

        return false;
    }

    /**
     * @param time Epoch time (milliseconds)
     */
    public void setNextStateTime(long time) {
        this.roundTimer = time;
    }

    public float getNextStateTime() {
        return this.roundTimer;
    }

    /**
     * @return Total time limit of the mode, in seconds (Default 3600, 5min)
     */
    public int getTimeLimit() {
        return 3600;
    }

    /**
     * @return How many seconds to make players wait in the lobby
     */
    public int getWaitTime() {
        return 30;
    }

    public int minPlayers = 2;

    /**
     * Called when the player needs their kit
     * @param ply
     */
    public void playerLoadOut(Player ply) {

    }

    /**
     * Called on game start
     */
    public void onGameStart() {
        for (Player ply : this.players) {
            PlayerFreeze.unFreezePlayer(ply);
        }
    }

    /**
     * Called when players are teleported to the arena
     */
    public void onGamePreStart() {
        this.nextState = GameState.STATE_GAME;
        this.onGameIntermission(5);
    }

    public void onGameEnd() {
        this.nextState = GameState.STATE_GAME_POST;
        this.roundTimer = System.currentTimeMillis() + (3 * 1000L);

        for (Player ply : this.players) {
            PlayerFreeze.freezePlayer(ply);
        }
    }

    /**
     * Called when an intermission starts (pre-game or other)
     * @param length Length of the intermission in seconds
     */
    public void onGameIntermission(float length) {
        this.state = GameState.STATE_INTERMISSION;
        this.roundTimer = System.currentTimeMillis() + (long) (length * 1000);
    }

    /**
     * Called when the player requests a respawn
     * @param ply Player wanting to respawn
     * @return The location the player should spawn at
     */
    public abstract Location choosePlayerSpawn(Player ply);

    public void playerStart(Player ply) {
        this.playerStart(ply, true);
    }

    public void playerStart(Player ply, boolean spawnLocation) {
        if (spawnLocation) {
            Location loc = this.choosePlayerSpawn(ply);
            ply.teleport(loc);
        }

        this.playerLoadOut(ply);
    }

    /**
     * @param ply Player who wants to join the match
     * @return If they can join
     */
    public boolean canPlayerJoin(Player ply) {
        return true;
    }

    /**
     * Called on player join
     * @param ply player joining
     */
    public void onPlayerJoin(Player ply) {
        int plyCount = this.players.size();
        this.players.add(ply);

        if (this.isFreezeState()) {
            PlayerFreeze.freezePlayer(ply);
        }

        if (plyCount < minPlayers) {
            this.state = GameState.STATE_WAITING;
            this.nextState = GameState.STATE_WAITING;

            if ((plyCount + 1) >= minPlayers) {
                this.roundTimer = System.currentTimeMillis() + (this.getWaitTime() * 1000L);
                this.nextState = GameState.STATE_GAME;
            }
        } else if (this.isGameActive()) {
            this.playerStart(ply);
        }
    }

    /**
     * Called when a player dies
     * @param ply Dead player
     * @param killer Killer of player (null if none, or disconnect)
     * @return Returns if the player was removed from the match
     */
    public boolean onPlayerDeath(Player ply, Player killer) {
        return false;
    }

    /**
     * Called when the player is removed from the game
     * (Disconnect, Left Game, Eliminated)
     * @param ply
     */
    public void onPlayerRemove(Player ply) {
        this.players.remove(ply);

        PlayerFreeze.unFreezePlayer(ply);

        int plyCount = this.players.size();

        if (plyCount < minPlayers) {
            if (this.isGameActive()) {
                this.onGameEnd();
                return;
            }

            this.state = GameState.STATE_WAITING;
            this.nextState = GameState.STATE_WAITING;
        }
    }

}
