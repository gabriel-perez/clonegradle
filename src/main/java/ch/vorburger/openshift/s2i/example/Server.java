/*
 * #%L
 * ch.vorburger.openshift
 * %%
 * Copyright (C) 2018 - 2018 Michael Vorburger
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package ch.vorburger.openshift.s2i.example;

import java.util.Enumeration;
import java.net.*;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.*;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.auth.*;
import io.kubernetes.client.apis.CoreApi;


/**
 * Simplest possible HTTP Server in Java, without dependencies to any external
 * framework.
 *
 * This is example "how-to" show case server; do NOT use this for real!
 *
 * @author Michael Vorburger.ch
 */
@SuppressWarnings("restriction")
public class Server implements AutoCloseable {

    private static final int HTTP_OK_STATUS = 200;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        new Server();
    }

    private final HttpServer httpServer;

    public Server() throws IOException {

        int port = 8080;
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", exchange -> {
            String messageKube = "";

            //try {

                /*ApiClient client = Config.defaultClient();
                Configuration.setDefaultApiClient(client);

                ApiKeyAuth BearerToken = (ApiKeyAuth) client.getAuthentication("BearerToken");
                BearerToken.setApiKey("yDv4dQiKUWjiVfqOCTcGIzfUFyz_893hSoyHRxIWyyU");
                //BearerToken.setApiKeyPrefix("Token");

                CoreApi apiInstance = new CoreApi();*/
                //V1APIVersions result = apiInstance.getAPIVersions();
                //System.out.println(result);

                //messageKube = result.toString();

                /*V1PodList list;
                CoreV1Api api = new CoreV1Api();

                list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

                for (V1Pod item : list.getItems()) {
                    messageKube += item.getMetadata().getName() + "\r\n";
                }*/
            /*}
            catch (ApiException e) {
                messageKube = "Error trying to list pods: \r\n" + e.getMessage();
            }*/

            String response = "Kubernetes test " + messageKube;
            exchange.sendResponseHeaders(HTTP_OK_STATUS, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });
        httpServer.start();

        System.out.println("started clonegradle v12 web server on http://localhost:" + port);
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        try {

            System.out.println("Preparing Kubernetes client");

            //ApiClient client = Config.defaultClient();
            //ApiClient client = Config.fromConfig("resources/kubeconfig.yaml");
            ApiClient client = Config.fromCluster();

            //ApiKeyAuth BearerToken = (ApiKeyAuth) client.getAuthentication("BearerToken");
            //BearerToken.setApiKey("oNRbGCqK_rPKWK097baPK9mF5fA4sVA8cnISMQguNcg");
            //BearerToken.setApiKeyPrefix("Bearer");

            Configuration.setDefaultApiClient(client);

            CoreV1Api apiInstance = new CoreV1Api();

            /*System.out.println("Listing configs");
            //api.listConfigMapForAllNamespaces()
            String _continue = null; // String | The continue option should be set when retrieving more results from the server. Since this value is server defined, clients may only use the continue value from a previous query result with identical query parameters (except for the value of continue) and the server may reject a continue value it does not recognize. If the specified continue value is no longer valid whether due to expiration (generally five to fifteen minutes) or a configuration change on the server, the server will respond with a 410 ResourceExpired error together with a continue token. If the client needs a consistent list, it must restart their list without the continue field. Otherwise, the client may send another list request with the token received with the 410 error, the server will respond with a list starting from the next key, but from the latest snapshot, which is inconsistent from the previous list results - objects that are created, modified, or deleted after the first list request will be included in the response, as long as their keys are after the \"next key\".  This field is not supported when watch is true. Clients may start a watch from the last resourceVersion value returned by the server and not miss any modifications.
            String fieldSelector = null; // String | A selector to restrict the list of returned objects by their fields. Defaults to everything.
            String labelSelector = null; // String | A selector to restrict the list of returned objects by their labels. Defaults to everything.
            Integer limit = 56; // Integer | limit is a maximum number of responses to return for a list call. If more items exist, the server will set the `continue` field on the list metadata to a value that can be used with the same initial query to retrieve the next set of results. Setting a limit may return fewer than the requested amount of items (up to zero items) in the event all requested objects are filtered out and clients should only use the presence of the continue field to determine whether more results are available. Servers may choose not to support the limit argument and will return all of the available results. If limit is specified and the continue field is empty, clients may assume that no more results are available. This field is not supported if watch is true.  The server guarantees that the objects returned when using continue will be identical to issuing a single list call without a limit - that is, no objects created, modified, or deleted after the first request is issued will be included in any subsequent continued requests. This is sometimes referred to as a consistent snapshot, and ensures that a client that is using limit to receive smaller chunks of a very large result can ensure they see all possible objects. If objects are updated during a chunked list the version of the object that was present at the time the first list result was calculated is returned.
            String pretty = null; // String | If 'true', then the output is pretty printed.
            String resourceVersion = null; // String | When specified with a watch call, shows changes that occur after that particular version of a resource. Defaults to changes from the beginning of history. When specified for list: - if unset, then the result is returned from remote storage based on quorum-read flag; - if it's 0, then we simply return what we currently have in cache, no guarantee; - if set to non zero, then the result is at least as fresh as given rv.
            Integer timeoutSeconds = 56; // Integer | Timeout for the list/watch call. This limits the duration of the call, regardless of any activity or inactivity.
            Boolean watch = true; // Boolean | Watch for changes to the described resources and return them as a stream of add, update, and remove notifications. Specify resourceVersion.
            try {
                V1ConfigMapList result = apiInstance.listConfigMapForAllNamespaces(_continue, fieldSelector, true, labelSelector, limit, pretty, resourceVersion, timeoutSeconds, watch);
                System.out.println(result);
                for (V1ConfigMap item : result.getItems()) {
                    System.out.println(item.getMetadata().getName());
                }
            } catch (ApiException e) {
                System.err.println("Exception when calling CoreV1Api#listConfigMapForAllNamespaces");
                e.printStackTrace();
            }*/

            System.out.println("Listing pods");
            V1PodList list = apiInstance.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

            for (V1Pod item : list.getItems()) {
                System.out.println(item.getMetadata().getName());
            }
        }
        catch (ApiException e) {
            System.out.println("Error trying to list pods: \r\n" + e.getMessage());
        }


    }

    @Override
    public void close() {
        httpServer.stop(0);
    }
}
