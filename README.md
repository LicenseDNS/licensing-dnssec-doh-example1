# License Validation, Activation and Deactivation using DOH

Public DNS servers offer support for DNS over HTTPS (DoH), which enhances security and privacy by encrypting DNS queries. This feature enables users to activate and deactivate licenses by sending DNS queries to well-known public DNS servers, such as Google (8.8.8.8) and CloudFlare (1.1.1.1). 

To perform these actions, the process involves crafting simple REST requests that communicate with the DNS servers. This method allows for reliable and efficient license management without compromising user data or system integrity. By leveraging the capabilities of DoH, businesses and individuals can ensure a more secure infrastructure when managing license activation and deactivation.

In this example, we will send a request to either Google's or CloudFlare's DNS servers to obtain license data. This process involves making a DNS query to retrieve information associated with a specific domain name. By leveraging the reliable services of these well-known DNS providers, we can efficiently gather the necessary license information for our application or system. This approach ensures quick response times and high availability, making it an effective method for handling license data retrieval.

[LicenseDNS.net](https://www.LicenseDNS.net/)
