package com.bootcamp.core.models;

import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, 
       adapters = ProductShowcase.class, 
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductShowcaseImpl implements ProductShowcase {

    @ValueMapValue
    private String sectionTitle;

    @ValueMapValue
    private String apiUrL; // URL vinda do diálogo do AEM

    private List<ProductData> products = new ArrayList<>();

    @PostConstruct
    protected void init() {
        if (apiUrL != null && !apiUrL.isEmpty()) {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrL))
                        .GET()
                        .build();

                // Faz a chamada para o Magento
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    // Transforma o texto do Magento em produtos reais
                    parseJson(response.body());
                }
            } catch (Exception e) {
                // Em caso de erro, adicionamos um aviso visual
                products.add(new ProductData("Erro ao conectar com Magento", "ERROR", "0.00"));
            }
        }
    }

    private void parseJson(String json) {
        Gson gson = new Gson(); 
        ProductData[] loadedProducts = gson.fromJson(json, ProductData[].class);
        
        if (loadedProducts != null) {
            for (ProductData p : loadedProducts) {
                products.add(p);
            }
        }
    }

    @Override
    public String getSectionTitle() {
        return sectionTitle != null ? sectionTitle : "Vitrine de Produtos";
    }

    @Override
    public List<ProductData> getProducts() {
        return products;
    }

    @Override
    public boolean isEmpty() {
        return products == null || products.isEmpty();
    }
}