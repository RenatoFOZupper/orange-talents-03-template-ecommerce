package br.com.zup.mercadolivre.produtos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {
	/*
	 * 
	 *  Converte uma lista MultipartFile para um lista de string simulando os links do storage
	 */
	public Set<String> send(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename()).collect(Collectors.toSet());
	}

}
