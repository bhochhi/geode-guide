import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.query.*;
import org.apache.geode.cache.query.QueryService;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException {


        //Add a locator, given its host and port, to this factory. Note that if the host is
        // unknown at the time of this call the locator will still be added.
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("localhost", 10334)
                .create();

        //Create and return a client region factory that is initialized to create a region using the given predefined region attributes.
        // ClientRegionShortcut.PROXY:A PROXY region has no local state and forwards all operations to a server. Read all other options.
        Region<String, String> region = cache.<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("hello");

        //TASK 1:   create region and put some values to server
        System.out.println("TASK 1:   create region and put some values to server");
        //Places a new value into an entry in this region with the specified key.
//        region.put("11", "Hello from second app");
//        region.put("22", "World from second app");

        System.out.format("region size %s\n",region.size());
        //Returns the <code>Set</code> of <code>Region.Entry</code> objects in this region.
        for (Map.Entry<String, String> entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }


        //Task 2: Query the region in server and print the values
        System.out.println("Task 2: Query the region in server and print the values");

        // Identify your query string.
        String queryString = "SELECT * FROM /hello";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults results = (SelectResults) query.execute();

        // Find the Size of the ResultSet.
        System.out.format("result size %s \n",results.size());
        Iterator<String> iterable = results.iterator();
        while (iterable.hasNext()) {
            System.out.println(iterable.next());
        }

        //Task 3: Query the region in the server by key. e.g. get cache by key = 22.
        System.out.println("Task 3: Query the region in the server by key. e.g. get cache by key = 22.");

        //option 2
        Set<String> stringSet = region.keySetOnServer();

        for (String key : stringSet) {
            System.out.format("key =>> %s, value =>> %s\n", key, region.get(key));
        }

        //option 1
        System.out.format("Key>>: %s,  value>>: %s \n","11",region.get("11"));

        //Terminates this region service and releases all its resources.
        cache.close();

    }
}
