<?php
namespace Bootcamp\AemContent\Block;

use Magento\Framework\View\Element\Template;
use Magento\Framework\HTTP\Client\Curl;

class AemBanner extends Template {
    protected $curl;

    public function __construct(Template\Context $context, Curl $curl, array $data = []) {
        $this->curl = $curl;
        parent::__construct($context, $data);
    }

    public function getAemBannerData()
{
    // 1. A URL com o host especial do Docker
    $url = 'http://host.docker.internal:4502/content/experience-fragments/bootcamp-ErikPinheiro/banner-promo-bootcamp/master.model.json';
    
    // 2. Criar o "ticket de entrada" (Credenciais admin:admin)
    $login = 'admin';
    $password = 'admin';
    $base64Auth = base64_encode("$login:$password");

    // 3. Configurar a requisição para enviar a autorização
    $opts = [
        "http" => [
            "method" => "GET",
            "header" => "Authorization: Basic $base64Auth\r\n" .
                        "Content-Type: application/json\r\n"
        ]
    ];

    $context = stream_context_create($opts);

    try {
        // 4. Tentar buscar os dados
        $json = file_get_contents($url, false, $context);
        return json_decode($json, true);
    } catch (\Exception $e) {
        return [];
    }
}
}