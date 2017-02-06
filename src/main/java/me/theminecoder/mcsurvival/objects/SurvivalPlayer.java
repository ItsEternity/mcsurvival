package me.theminecoder.mcsurvival.objects;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.UUID;

/**
 * @author theminecoder
 * @version 1.0
 */
public class SurvivalPlayer {

    private UUID uuid;
    private String name;

    private Date firstJoin;
    private Date lastJoin;

    private boolean receivedStarterKit = false;

    SurvivalPlayer(){  //GSON
    }

    public SurvivalPlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.firstJoin = new Date();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstJoin() {
        return firstJoin;
    }

    public Date getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(Date lastJoin) {
        this.lastJoin = lastJoin;
    }

    public boolean hasReceivedStarterKit() {
        return receivedStarterKit;
    }

    public void setReceivedStarterKit(boolean receivedStarterKit) {
        this.receivedStarterKit = receivedStarterKit;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("uuid", uuid)
                .add("name", name)
                .toString();
    }
}
