package com.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    // product Id from https://manager.licensedns.net
    private static final String PRODUCT_ID = "ADA14AE9-08A8-4AE2-B69E-AAE277B8346F";

    // license key example, get the license key from user in production
    private static final String LICENSE_KEY = "5F32A-UN7KF-UE9V8-AW3NS";

    public static void main(String[] args) throws Exception {

        DNSResponse dnsResponse = httpequest();

        // check if dnssec valid
        System.out.println("DNSSEC valid: "+dnsResponse.isDNSSECValidated());

        // print all txt strings defined in the license
       dnsResponse.getTXTStrings().forEach(System.out::println);
    }

    private static DNSResponse httpequest() throws Exception {
        String action = "a"; // activate
        String domain = DigestUtils.sha256Hex(LICENSE_KEY + PRODUCT_ID).substring(0, 32); // key and product hash
        String fingerprint = "my-fingerprint"; // fingerprint
        String domainQuery = action + "." + domain + "." + fingerprint + ".q.licensedns.net";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dns.google/resolve?name=" + domainQuery + "&type=16&do=1"))
                // uncomment to use 1.1.1.1
                //.uri(URI.create("https://1.1.1.1/dns-query?name=q.licensedns.net&type=16&do=1"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        DNSResponse dnsResponse = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response.body(), DNSResponse.class);

        return dnsResponse;
    }
}
