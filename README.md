# Bootcamp AEM + Adobe Commerce: Desafio de Integração

Este repositório contém os módulos e componentes desenvolvidos para a integração entre o **Adobe Experience Manager (AEM)** e o **Adobe Commerce (Magento)**.

---

## O que foi desenvolvido

### 1. Adobe Commerce (Magento)
Localizado na pasta `/MAGENTO`, contém os seguintes módulos:
* **CatalogApi**: Implementação de uma API REST customizada (`GET /V1/bootcamp/products`) para expor produtos com atributos específicos (`tech_stack` e `bootcamp_highlight`).
* **AemContent**: Módulo responsável por consumir Experience Fragments do AEM e renderizá-los no frontend do Magento.

### 2. AEM (Adobe Experience Manager)
Localizado na pasta `/AEM`:
* **Sling Models**: Lógica em Java para consumo e parsing da API REST do Magento.
* **Componente Showcase**: Componente HTL criado para listar os produtos dinamicamente na página.
* **Experience Fragments**: Configuração de exportação de conteúdo via JSON para o Magento.

---

## ⚠️ Observações Técnicas (Integração de Banner)
Atualmente, o módulo `AemContent` no Magento consegue realizar a chamada ao AEM e receber o JSON correspondente ao **Experience Fragment**. 

Entretanto, a integração do banner está em estágio parcial:
* **Status:** O JSON é recebido com sucesso, mas a renderização da imagem no frontend do Magento apresenta limitações (a imagem não é exibida corretamente devido ao mapeamento de caminhos binários do AEM).
* **Próximo Passo:** Ajustar o parser do componente para tratar URLs absolutas de assets vindas do AEM Service.

---

### Visualização no AEM (Modo Cliente)
Para visualizar o componente de vitrine consumindo os dados do Magento:
* **URL:** `http://localhost:4502/content/bootcamp-ErikPinheiro/us/en.html?wcmmode=disabled`

### Endpoint Magento
Para verificar os dados brutos enviados ao AEM:
* **URL:** `http://localhost/rest/V1/bootcamp/products`

---
