package net.b0at.preventxyzconnection;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordin on 8/5/2017.
 * Jordin is still best hacker.
 */
public class ConnectionConfig {
    @SerializedName("kick-message")
    public String kickMessage = "-f Please use &9B0AT.NET&f to connect to this server!";

    @SerializedName("blacklist")
    public List<String> blacklistedHostnames = Arrays.asList("b0at.xyz", "ncp.b0at.xyz");

    public ConnectionConfig() {

    }
}
