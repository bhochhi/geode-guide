import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;

import java.util.Map;

public class App {

    public static void main(String[] args) {

        //Add a locator, given its host and port, to this factory. Note that if the host is
        // unknown at the time of this call the locator will still be added.
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("localhost", 10334)
                .create();

        //Create and return a client region factory that is initialized to create a region using the given predefined region attributes.
        // ClientRegionShortcut.LOCAL: A LOCAL region only has local state and never sends operations to a server.
        Region<String, String> region = cache.<String, String>createClientRegionFactory(ClientRegionShortcut.LOCAL).create("hello");

        //Places a new value into an entry in this region with the specified key.
        region.put("1", "Hello");
        region.put("2", "World");

        //Returns the <code>Set</code> of <code>Region.Entry</code> objects in this region.
        for (Map.Entry<String, String> entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }
        //Terminates this region service and releases all its resources.
        cache.close();

    }
}
