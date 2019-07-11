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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.JSON;
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

        System.out.println("started clonegradle v23 web server on http://localhost:" + port);
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        try {

            System.out.println("Preparing Kubernetes client");

            //ApiClient client = Config.defaultClient();
            //ApiClient client = Config.fromConfig("resources/kubeconfig.yaml");
            ApiClient client = Config.fromCluster();
            //ApiClient client = Config.fromToken("https://api.us-west-2.online-starter.openshift.com:6443/apis/user.openshift.io/v1/users/~","Authorization: Bearer fTRF8_hYSN07rsx3M5Kh0x3VXK2u7__zZT961HcqUvE");

            //ApiKeyAuth BearerToken = (ApiKeyAuth) client.getAuthentication("BearerToken");
            //BearerToken.setApiKey("oNRbGCqK_rPKWK097baPK9mF5fA4sVA8cnISMQguNcg");
            //BearerToken.setApiKeyPrefix("Bearer");

            Configuration.setDefaultApiClient(client);
            CoreV1Api apiInstance = new CoreV1Api();

            /*System.out.println("Listing authentications");
            Iterator var2 = client.getAuthentications().values().iterator();

            Authentication auth;
            while (var2.hasNext()) {
                 auth = (Authentication)var2.next();
                System.out.println(auth.toString());


                if (auth instanceof ApiKeyAuth)
                {
                    ApiKeyAuth apiAuth = (ApiKeyAuth)auth;
                    System.out.println(apiAuth.getLocation());
                }
            }*/

            //System.out.println("Listing configs");
            //apiInstance.listNamespacedConfigMap("krakenprj", false, "true", null, null, null, 60, null, 60, false);


            /*System.out.println("Listing namespaces");
            //V1PodList list = apiInstance.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            V1NamespaceList nslist = apiInstance.listNamespace(null, null, null, null, null, null, null, null, null);

            for (V1Namespace item : nslist.getItems()) {
                System.out.println(item.getMetadata().getName());
            }*/




            System.out.println("Listing pods");
            //V1PodList list = apiInstance.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            V1PodList list = apiInstance.listNamespacedPod("krakenprj", null, null, null, null, null, null, null, null, null);

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
