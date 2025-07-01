package com.Classy.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Component
public class ArquivoProcessador {

    private static final  Set<String> TIPOS_PERMITIDOS = Set.of(
            "application/pdf",
            "image/png",
            "image/jpeg"
    );

    private static final long TAMANHO_MAXIMO = 5 * 1024 * 1024; // 5MB

    public byte[] converterMultipartParaBytes (MultipartFile arquivoMultipart) throws IOException {
        if(arquivoMultipart == null || arquivoMultipart.isEmpty()){
            return null;
        }
            validarTamanho(arquivoMultipart);
            validar(arquivoMultipart);
            return arquivoMultipart.getBytes();
    }

    public void validar(MultipartFile arquivo){
        String contentType = arquivo.getContentType();
        if (contentType == null || !TIPOS_PERMITIDOS.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado: " + contentType);
        }
    }


    public void validarTamanho (MultipartFile arquivoMultipart){
        if(arquivoMultipart.getSize() > TAMANHO_MAXIMO){
            throw new IllegalArgumentException("Arquivo muito grande, máximo permitido é 5 MB.");
        }
    }

    public String extrairExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains("/")) {
            return null;
        }
        return "." + nomeArquivo.substring(nomeArquivo.lastIndexOf("/" ) + 1);
    }

    public String gerarContentType(String extensao) {

        if(extensao != null){
            extensao = extensao.toLowerCase().replaceFirst("^\\.", "");

            switch (extensao) {
                case "png":
                case "jpg":
                case "jpeg":
                case "gif":
                    return "image/" + extensao;
                case "pdf":
                    return "application/pdf";
                default:
                    throw new IllegalArgumentException("Extensão não suportada: " + extensao);
            }
        }

        return null;

    }

}
